package net.jastrab.unleashed.api;

import net.jastrab.unleashed.api.http.HttpMethod;
import net.jastrab.unleashed.api.http.PaginatedUnleashedRequest;
import net.jastrab.unleashed.api.http.QueryStringBuilder;
import net.jastrab.unleashed.api.models.Product;
import net.jastrab.unleashed.api.models.ProductGroup;
import net.jastrab.unleashed.api.models.SortOrder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class GetProductRequest extends PaginatedUnleashedRequest<Product> {
    private static final String BASE_PATH = "/Products/";

    public enum OrderBy {
        LAST_MODIFIED("LastModifiedOn"),
        CREATED("CreatedOn"),
        PRODUCT_CODE("ProductCode");

        private final String value;

        OrderBy(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    private final String query;
    private final String path;

    protected GetProductRequest(String query) {
        super(Product.class);
        Objects.requireNonNull(query, "Query string cannot be null");
        this.query = query;
        this.path = BASE_PATH;
    }

    private GetProductRequest(String query, int page) {
        super(Product.class);
        this.query = query;
        this.path = BASE_PATH + "Page/" + page;
    }

    public GetProductRequest(UUID guid) {
        super(Product.class);
        this.query = "";
        this.path = BASE_PATH + guid.toString();
    }

    @Override
    public String getQuery() {
        return query;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public GetProductRequest forPage(int page) {
        if(page < 1)
            throw new IllegalArgumentException("Page number cannot be less than 1");
        return new GetProductRequest(query, page);
    }

    public static class GetProductRequestBuilder {
        private static final String PRODUCT_ID_KEY = "productId";
        private static final String PRODUCT_BARCODE_KEY = "productBarCode";
        private static final String PRODUCT_GROUP_KEY = "productGroup";
        private static final String PRODUCT_CODE_KEY = "productCode";
        private static final String PRODUCT_DESCRIPTION_KEY = "productDescription";
        private static final String BRIEF_KEY = "brief";
        private static final String SORT_KEY = "sort";
        private static final String ORDER_BY_KEY = "orderBy";
        private static final String INCLUDE_OBSOLETE_KEY = "includeObsolete";
        private static final String EXCLUDE_ASSEMBLED_KEY = "excludeAssembled";
        private static final String EXCLUDE_COMPONENTS_KEY = "excludeComponents";

//        private static final Set<String> PAGE_VALID_KEYS = new HashSet<>();
//
//        // TODO This is wrong (conceptually)
//        static {
//            PAGE_VALID_KEYS.add(PRODUCT_GROUP_KEY);
//            PAGE_VALID_KEYS.add(SORT_KEY);
//            PAGE_VALID_KEYS.add(ORDER_BY_KEY);
//            PAGE_VALID_KEYS.add(INCLUDE_OBSOLETE_KEY);
//            PAGE_VALID_KEYS.add(EXCLUDE_ASSEMBLED_KEY);
//            PAGE_VALID_KEYS.add(EXCLUDE_COMPONENTS_KEY);
//        }

        private final Map<String, Object> parametersMap = new HashMap<>();

        /* Product Request Filters */

        public GetProductRequestBuilder brief(boolean brief) {
            parametersMap.put(BRIEF_KEY, brief ? "true" : "false");
            return this;
        }

        public GetProductRequestBuilder productId(String... productIds) {
            String value = String.join(",", productIds);
            parametersMap.put(PRODUCT_ID_KEY, value);

            return this;
        }

        public GetProductRequestBuilder productGroup(String productGroup) {
            parametersMap.put(PRODUCT_GROUP_KEY, productGroup);
            return this;
        }

        public GetProductRequestBuilder productGroup(ProductGroup productGroup) {
            parametersMap.put(PRODUCT_GROUP_KEY, productGroup.getGroupName());
            return this;
        }

        public GetProductRequestBuilder productCode(String productCode) {
            parametersMap.put(PRODUCT_CODE_KEY, productCode);
            return this;
        }

        public GetProductRequestBuilder productDescription(String productDescription) {
            parametersMap.put(PRODUCT_DESCRIPTION_KEY, productDescription);
            return this;
        }

        public GetProductRequestBuilder includeObsolete(boolean include) {
            parametersMap.put(INCLUDE_OBSOLETE_KEY, include ? "true" : "false");
            return this;
        }

        public GetProductRequestBuilder excludeAssembled(boolean exclude) {
            parametersMap.put(EXCLUDE_ASSEMBLED_KEY, exclude ? "true" : "false");
            return this;
        }

        public GetProductRequestBuilder excludeComponents(boolean exclude) {
            parametersMap.put(EXCLUDE_COMPONENTS_KEY, exclude ? "true" : "false");
            return this;
        }

        public GetProductRequestBuilder productBarCode(String barcode) {
            parametersMap.put(PRODUCT_BARCODE_KEY, barcode);
            return this;
        }

        /* Request Meta-Options (Sort/Order/Page) */

        public GetProductRequestBuilder sortOrder(SortOrder order) {
            parametersMap.put(SORT_KEY, order.getValue());
            return this;
        }

        public GetProductRequestBuilder orderBy(OrderBy field) {
            parametersMap.put(ORDER_BY_KEY, field.getValue());
            return this;
        }

        public GetProductRequest build() {
            QueryStringBuilder queryStringBuilder = QueryStringBuilder.builder();
            queryStringBuilder.putAll(parametersMap);

            return new GetProductRequest(queryStringBuilder.build());
        }
    }

    public static GetProductRequestBuilder builder() {
        return new GetProductRequestBuilder();
    }

    @Override
    public String toString() {
        return "GetProductRequest{" +
                "query='" + query + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetProductRequest request = (GetProductRequest) o;
        return Objects.equals(query, request.query) &&
                path.equals(request.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(query, path);
    }
}
