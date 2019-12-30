package net.jastrab.unleashed.api;

import net.jastrab.unleashed.api.http.GetRequest;
import net.jastrab.unleashed.api.http.QueryStringBuilder;
import net.jastrab.unleashed.api.models.SortOrder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GetProductRequest implements GetRequest {
    private static final String BASE_PATH = "/Products/";
    private static final String PRODUCT_ID_KEY = "productId";
    private static final String PRODUCT_BARCODE_KEY = "productBarCode";
    private static final String PRODUCT_GROUP_KEY = "productGroup";
    private static final String PRODUCT_CODE_KEY = "productCode";
    private static final String PRODUCT_DESCRIPTION_KEY = "productDescription";
    private static final String SORT_KEY = "sort";
    private static final String ORDER_BY_KEY = "orderBy";
    private static final String INCLUDE_OBSOLETE_KEY = "includeObsolete";
    private static final String EXCLUDE_ASSEMBLED_KEY = "excludeAssembled";
    private static final String EXCLUDE_COMPONENTS_KEY = "excludeComponents";

    private static final Set<String> PAGE_VALID_KEYS = new HashSet<>();

    static {
        PAGE_VALID_KEYS.add(SORT_KEY);
        PAGE_VALID_KEYS.add(ORDER_BY_KEY);
        PAGE_VALID_KEYS.add(INCLUDE_OBSOLETE_KEY);
        PAGE_VALID_KEYS.add(EXCLUDE_ASSEMBLED_KEY);
        PAGE_VALID_KEYS.add(EXCLUDE_COMPONENTS_KEY);
    }

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

    private String query;
    private String path;

    private GetProductRequest(String query, String path) {
        this.query = query;
        this.path = path;
    }

    public static class GetProductRequestBuilder {
        private final Map<String, Object> parametersMap = new HashMap<>();

        /**
         * Static builder method to create a request for a single specific product specified by its GUID
         *
         * @param guid The unique ID String for a specific existing product, in the format of a uuid4 type GUID
         *
         * @apiNote All product filters/meta-options are ignored by this builder function
         */
        public static GetProductRequest guid(String guid) {
            return new GetProductRequest("", BASE_PATH + guid);
        }

        /* Product Request Filters */

        public GetProductRequestBuilder productId(String... productIds) {
            String value = String.join(",", productIds);
            parametersMap.put(PRODUCT_ID_KEY, value);

            return this;
        }

        public GetProductRequestBuilder productGroup(String productGroup) {
            parametersMap.put(PRODUCT_GROUP_KEY, productGroup);
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

        /**
         * Return a request for a specific page of products
         */
        public GetProductRequest page(int pageNumber) {
            QueryStringBuilder qsb = QueryStringBuilder.builder();
            parametersMap.forEach((key, value) -> {
                if (PAGE_VALID_KEYS.contains(key)) {
                    qsb.put(key, value);
                }
            });
            return new GetProductRequest(qsb.build(), BASE_PATH + "Page/" + pageNumber);
        }

        public GetProductRequest build() {
            QueryStringBuilder queryStringBuilder = QueryStringBuilder.builder();
            queryStringBuilder.putAll(parametersMap);

            return new GetProductRequest(queryStringBuilder.build(), BASE_PATH);
        }
    }

    public static GetProductRequestBuilder builder() {
        return new GetProductRequestBuilder();
    }

    public String getQuery() {
        return query;
    }

    public String getPath() {
        return path;
    }
}
