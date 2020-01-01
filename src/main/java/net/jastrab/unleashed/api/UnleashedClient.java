package net.jastrab.unleashed.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.jastrab.unleashed.api.http.GetRequest;
import net.jastrab.unleashed.api.http.PostRequest;
import net.jastrab.unleashed.api.http.UnleashedRequestBuilder;
import net.jastrab.unleashed.api.http.UnleashedRequestHandler;
import net.jastrab.unleashed.api.models.AttributeSet;
import net.jastrab.unleashed.api.models.Product;
import net.jastrab.unleashed.api.models.PurchaseOrder;
import net.jastrab.unleashed.api.security.ApiCredential;
import net.jastrab.unleashed.api.security.ApiSignatureGenerator;
import net.jastrab.unleashed.api.security.UnleashedCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpRequest;
import java.security.InvalidKeyException;
import java.util.List;
import java.util.Optional;

public class UnleashedClient {
    private final Logger logger = LoggerFactory.getLogger(UnleashedClient.class);
    private final UnleashedRequestHandler handler = new UnleashedRequestHandler();

    private final ApiSignatureGenerator signatureGenerator;
    private final String apiId;

    private UnleashedClient(ApiCredential credential) throws InvalidKeyException {
        this.signatureGenerator = new ApiSignatureGenerator(credential);
        this.apiId = credential.getId();
    }

    public static UnleashedClientBuilder builder() {
        return new UnleashedClientBuilder();
    }

    public List<AttributeSet> getAttributeSets() {

        HttpRequest request = new UnleashedRequestBuilder(apiId)
                .path("/AttributeSets")
                .signWith(signatureGenerator)
                .get()
                .build();

        return handler.getResponse(request, AttributeSet.class)
                .orElseThrow(() -> new RuntimeException("Failed to retrieve attribute sets"))
                .getItems();
    }

    public List<Product> getProduct(GetProductRequest getProductRequest) {
        logger.trace("Performing getProduct request, path: {}, query: {}", getProductRequest.getPath(), getProductRequest.getQuery());

        return handler.getResponse(prepareGetRequest(getProductRequest), Product.class)
                .orElseThrow(() -> new RuntimeException("Failed to retrieve product"))
                .getItems();
    }

    public Optional<Product> createProduct(CreateProductRequest createProductRequest) throws JsonProcessingException {
        logger.trace("Creating product from CreateProductRequest");

        return handler.postRequest(preparePostRequest(createProductRequest), Product.class);
    }

    public List<PurchaseOrder> getPurchaseOrder(GetPurchaseOrderRequest getPurchaseOrderRequest) {

        logger.trace("Performing getPurchaseOrder request, query: {}", getPurchaseOrderRequest.getQuery());

        return handler.getResponse(prepareGetRequest(getPurchaseOrderRequest), PurchaseOrder.class)
                .orElseThrow(() -> new RuntimeException("Failed to retrieve purchase order(s)"))
                .getItems();
    }

    /**
     * Helper utility to retrieve a single product, returning only the first item if many are returned from the query
     */
    public Optional<Product> getFirstProduct(GetProductRequest getProductRequest) {
        return getProduct(getProductRequest).stream().findFirst();
    }

    private HttpRequest prepareGetRequest(GetRequest getRequest) {
        return new UnleashedRequestBuilder(apiId)
                .signWith(signatureGenerator)
                .getRequest(getRequest);
    }

    private HttpRequest preparePostRequest(PostRequest postRequest) throws JsonProcessingException {
        return new UnleashedRequestBuilder(apiId)
                .signWith(signatureGenerator)
                .postRequest(postRequest);
    }

    public static class UnleashedClientBuilder {
        private UnleashedCredentialsProvider provider;

        public UnleashedClientBuilder credentialsProvider(UnleashedCredentialsProvider provider) {
            this.provider = provider;
            return this;
        }

        public UnleashedClient build() throws InvalidKeyException {
            if (provider == null) {
                throw new IllegalStateException("No credentials provider was supplied, unable to instantiate UnleashedClient");
            }

            return new UnleashedClient(provider.get());
        }
    }
}
