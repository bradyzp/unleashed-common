package net.jastrab.unleashed.api.http;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.jastrab.unleashed.api.converters.UnleashedObjectMapper;
import net.jastrab.unleashed.api.models.UnleashedResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.function.Supplier;

public class UnleashedRequestHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(UnleashedRequestHandler.class);
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = UnleashedObjectMapper.getInstance().getMapper();

    public <T> Optional<UnleashedResponse<T>> getResponse(HttpRequest request, Class<T> target) {
        try {
            HttpResponse<Supplier<UnleashedResponse<T>>> response = client.send(request, new JsonBodyHandler<>(target));
            return Optional.of(response.body().get());
        } catch (IOException | InterruptedException e) {
            return Optional.empty();
        }
    }

    public <T> Optional<T> postRequest(HttpRequest request, Class<T> target) {
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            T responseObject = mapper.readValue(response.body(), target);
            return Optional.of(responseObject);
        } catch (IOException | InterruptedException e) {
            LOGGER.error("Exception occurred while executing POST request", e);
            return Optional.empty();
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
