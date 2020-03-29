package net.jastrab.unleashed.api.http;

import net.jastrab.unleashed.api.security.ApiCredential;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public interface UnleashedRequest<T> {
    String AUTHORITY = "api.unleashedsoftware.com";
    String CONTENT_TYPE = "application/json";

    /**
     * Get the complete map of headers for the request, including signature
     *
     * @return a map of HTTP headers for the request
     */
    default Map<String, List<String>> getHeaders() {
        if(!isSigned())
            throw new IllegalStateException("Request is not signed");
        return Map.of(
                "Content-Type", List.of(CONTENT_TYPE),
                "Accept", List.of(CONTENT_TYPE),
                "api-auth-id", List.of(getApiId()),
                "api-auth-signature", List.of(getAuthSignature())
        );
    }

    HttpMethod getHttpMethod();

    String getPath();

    default String getQuery() {
        return "";
    }

    String getApiId();

    String getAuthSignature();

    /**
     * Get the request body for the API request. Default is null
     * @return
     */
    default T getRequestBody() {
        return null;
    }

    Type getResponseType();

    void sign(ApiCredential credential);

    default boolean isSigned() {
        return getAuthSignature() != null && getApiId() != null;
    }

}
