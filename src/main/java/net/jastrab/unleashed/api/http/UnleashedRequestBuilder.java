package net.jastrab.unleashed.api.http;

import net.jastrab.unleashed.api.security.ApiSignatureGenerator;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;

public class UnleashedRequestBuilder {
    private static final String SCHEME = "https";
    private static final String AUTHORITY = "api.unleashedsoftware.com";

    private ApiSignatureGenerator signatureGenerator;
    private String path = "";
    private String query = "";

    private final HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();

    public UnleashedRequestBuilder(String apiId) {
        requestBuilder.headers(
                UnleashedConstants.ContentType, UnleashedConstants.ApplicationJson,
                UnleashedConstants.Accept, UnleashedConstants.ApplicationJson);
        requestBuilder.header(UnleashedConstants.ApiAuthId, apiId);

    }

    public UnleashedRequestBuilder path(String path) {
        this.path = path;
        return this;
    }

    public UnleashedRequestBuilder query(String query) {
        this.query = query;
        return this;
    }

    public UnleashedRequestBuilder query(QueryStringBuilder builder) {
        this.query = builder.build();
        return this;
    }

    public UnleashedRequestBuilder get() {
        requestBuilder.GET();
        return this;
    }

    public HttpRequest getRequest(GetRequest request) {
        query(request.getQuery());
        path(request.getPath());
        return build();
    }

    public UnleashedRequestBuilder signWith(ApiSignatureGenerator generator) {
        this.signatureGenerator = generator;
        return this;
    }

    public HttpRequest build() {
        try {
            requestBuilder.uri(new URI(SCHEME, AUTHORITY, path, query, null));
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid argument supplied to URI", e);
        }

        if (this.signatureGenerator != null) {
            String signature = signatureGenerator.getSignature(query);
            requestBuilder.header(UnleashedConstants.ApiAuthSignature, signature);
        }

        return requestBuilder.build();
    }

}