package net.jastrab.unleashed.api;

import net.jastrab.unleashed.api.http.PostRequest;
import net.jastrab.unleashed.api.models.Product;


public class CreateProductRequest implements PostRequest {
    private final Product product;
    private final String guid;

    private CreateProductRequest(Product product, String guid) {
        this.product = product;
        this.guid = guid;
    }

    public static CreateProductRequestBuilder builder() {
        return new CreateProductRequestBuilder();
    }

    @Override
    public Object getBody() {
        return product;
    }

    @Override
    public String getQuery() {
        return "";
    }

    @Override
    public String getPath() {
        return "/Products/" + guid;
    }

    public static class CreateProductRequestBuilder {
        private Product product;
        private String guid;

        private CreateProductRequestBuilder() {

        }

        public CreateProductRequestBuilder product(Product product) {
            this.product = product;
            this.guid = product.getGuid();

            return this;
        }

        public CreateProductRequest build() {

            if (this.product == null) {
                throw new IllegalStateException("Product must be specified");
            }

            return new CreateProductRequest(this.product, this.guid);
        }

    }

}
