package net.jastrab.unleashed.api;

import net.jastrab.unleashed.api.http.UnleashedPath;
import net.jastrab.unleashed.api.http.UnleashedRequestBuilder;
import net.jastrab.unleashed.api.http.UnleashedRequestHandler;
import net.jastrab.unleashed.api.models.AttributeSet;
import net.jastrab.unleashed.api.models.Product;
import net.jastrab.unleashed.api.security.ApiCredential;
import net.jastrab.unleashed.api.security.ApiSignatureGenerator;
import net.jastrab.unleashed.api.security.UnleashedCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpRequest;
import java.security.InvalidKeyException;
import java.util.List;

public class UnleashedClient {
    private final Logger logger = LoggerFactory.getLogger(UnleashedClient.class);
    private final UnleashedRequestHandler handler = new UnleashedRequestHandler();

    private final ApiSignatureGenerator signatureGenerator;
    private final String apiId;

    private UnleashedClient(ApiCredential credential) throws InvalidKeyException {
        this.signatureGenerator = new ApiSignatureGenerator(credential);
        this.apiId = credential.getId();
    }

    public List<AttributeSet> getAttributeSets() {

        HttpRequest request = new UnleashedRequestBuilder(apiId)
                .path(UnleashedPath.ATTRIBUTE_SETS)
                .signWith(signatureGenerator)
                .get()
                .build();

        return handler.getResponse(request, AttributeSet.class)
                .orElseThrow(() -> new RuntimeException("Failed to retrieve attribute sets"))
                .getItems();
    }

    public List<Product> getProduct(GetProductRequest getProductRequest) {
        logger.trace("Performing getProduct request, path: {}, query: {}", getProductRequest.getPath(), getProductRequest.getQuery());
        HttpRequest request = new UnleashedRequestBuilder(apiId)
                .signWith(signatureGenerator)
                .getRequest(getProductRequest);

        return handler.getResponse(request, Product.class)
                .orElseThrow(() -> new RuntimeException("Failed to retrieve product"))
                .getItems();
    }

    public static UnleashedClientBuilder builder() {
        return new UnleashedClientBuilder();
    }

    public static class UnleashedClientBuilder {
        private UnleashedCredentialsProvider provider;

        public UnleashedClientBuilder credentialsProvider(UnleashedCredentialsProvider provider) {
            this.provider = provider;
            return this;
        }

        public UnleashedClient build() throws InvalidKeyException {
            if(provider == null) {
                throw new IllegalStateException("No credentials provider was supplied, unable to instantiate UnleashedClient");
            }

            return new UnleashedClient(provider.get());
        }
    }
}
