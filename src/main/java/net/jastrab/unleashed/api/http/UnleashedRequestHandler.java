package net.jastrab.unleashed.api.http;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.jastrab.unleashed.api.converters.UnleashedObjectMapper;
import net.jastrab.unleashed.api.exceptions.BadRequestException;
import net.jastrab.unleashed.api.exceptions.HttpClientException;
import net.jastrab.unleashed.api.models.CreatableResource;
import net.jastrab.unleashed.api.models.UnleashedError;
import net.jastrab.unleashed.api.models.UnleashedResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.function.Supplier;

public class UnleashedRequestHandler {
    private static final Marker RESPONSE_MARKER = MarkerFactory.getMarker("RESPONSE");
    private static final Marker REQUEST_MARKER = MarkerFactory.getMarker("REQUEST");
    private static final Logger LOGGER = LoggerFactory.getLogger(UnleashedRequestHandler.class);
    private static final int OK_200 = 200;
    private static final int CREATED_201 = 201;
    private static final int BAD_400 = 400;


    private final HttpClient client;
    private final ObjectMapper mapper = UnleashedObjectMapper.getInstance().getMapper();

    public UnleashedRequestHandler(HttpClient httpClient) {
        this.client = httpClient;
    }

    public <T> Optional<UnleashedResponse<T>> getResponse(HttpRequest request, Class<T> target) {
        try {
            HttpResponse<Supplier<UnleashedResponse<T>>> response = client.send(request, new JsonBodyHandler<>(target));
            LOGGER.trace(RESPONSE_MARKER, "Status Code: <{}>", response.statusCode());
            return Optional.of(response.body().get());
        } catch (IOException | InterruptedException e) {
            return Optional.empty();
        }
    }

    /**
     * Used to send HttpRequests which are expected to return a single Object, i.e. in the case
     * of a resource creation or update action.
     * <p>
     * TODO: Don't mix unchecked exceptions and Optional return type? - maybe it's ok
     * @return Optional containing the created/updated entity, or an Optional.empty() if the request failed
     *         due to a local client error (Thread interruption or IOException)
     * @throws BadRequestException if the endpoint returns an HTTP 400 error due to a bad request
     * @throws HttpClientException if then endpoint returns a status code of anything other than 200 OK,
     *                             201 Created, or 400 Bad Request
     */
    public <T extends CreatableResource> Optional<T> sendForEntity(HttpRequest request, Class<T> target) {
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            final int statusCode = response.statusCode();
            LOGGER.trace(RESPONSE_MARKER, "Status Code: <{}> Response: {}", statusCode, response.body());

            if (statusCode == CREATED_201 | statusCode == OK_200) {
                T responseObject = mapper.readValue(response.body(), target);
                LOGGER.debug("Item of type {} was created with guid {}", target.getName(), responseObject.getGuid());
                return Optional.of(responseObject);
            }

            LOGGER.error("postRequest failed with error code: <{}> response body: {}", response.statusCode(), response.body());

            if (response.statusCode() == BAD_400) {
                UnleashedError error = mapper.readValue(response.body(), UnleashedError.class);
                throw new BadRequestException(error);
            } else {
                throw new HttpClientException(response, response.statusCode());
            }
        } catch (InterruptedException e) {
            LOGGER.error("Thread interrupted while executing POST request", e);
            Thread.currentThread().interrupt();
            return Optional.empty();
        } catch (IOException e) {
            LOGGER.error("IOException occurred while executing POST request", e);
            return Optional.empty();
        }
    }

    public void delete(HttpRequest request) {
        if (!request.method().equals("DELETE"))
            throw new IllegalArgumentException("Http Request must use DELETE method");

        try {
            client.send(request, HttpResponse.BodyHandlers.discarding());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public class JsonBodyHandler<W> implements HttpResponse.BodyHandler<Supplier<UnleashedResponse<W>>> {
        private final Class<W> target;

        public JsonBodyHandler(Class<W> target) {
            this.target = target;
        }

        private HttpResponse.BodySubscriber<Supplier<UnleashedResponse<W>>> jsonHandler(Class<W> type, int statusCode) {
            HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();
            JavaType parType = mapper.getTypeFactory().constructParametricType(UnleashedResponse.class, type);

            return HttpResponse.BodySubscribers.mapping(
                    upstream,
                    inputStream -> () -> {
                        try (InputStream stream = inputStream) {
                            UnleashedResponse<W> response = mapper.readValue(stream, parType);
                            response.setStatusCode(statusCode);
                            return response;
                        } catch (IOException e) {
                            LOGGER.error("Error parsing response body", e);
                            throw new UncheckedIOException(e);
                        }
                    });
        }

        @Override
        public HttpResponse.BodySubscriber<Supplier<UnleashedResponse<W>>> apply(HttpResponse.ResponseInfo responseInfo) {
            return jsonHandler(target, responseInfo.statusCode());
        }
    }

}
