package net.jastrab.unleashed.api;

import net.jastrab.unleashed.api.http.PaginatedUnleashedRequest;
import net.jastrab.unleashed.api.http.QueryStringBuilder;
import net.jastrab.unleashed.api.models.Supplier;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GetSupplierRequest extends PaginatedUnleashedRequest<Supplier> {
    private static final String BASE_PATH = "/Suppliers/";
    private final String query;
    private final String path;

    protected GetSupplierRequest(String path, String query) {
        super(Supplier.class);
        this.query = query;
        this.path = path;
    }

    public static GetSupplierRequest forGuid(UUID guid) {
        return new GetSupplierRequest(BASE_PATH + guid.toString(), "");
    }

    public static GetSupplierRequestBuilder builder() {
        return new GetSupplierRequestBuilder();
    }

    public static class GetSupplierRequestBuilder {
        private static final String SUPPLIER_CODE_KEY = "supplierCode";
        private static final String CONTACT_EMAIL_KEY = "contactEmail";
        private static final String PAGE_SIZE_KEY = "pageSize";
        private final Map<String, Object> parametersMap = new HashMap<>();

        public GetSupplierRequestBuilder supplierCode(String supplierCode) {
            parametersMap.put(SUPPLIER_CODE_KEY, supplierCode);
            return this;
        }

        public GetSupplierRequestBuilder contactEmail(String contactEmail) {
            parametersMap.put(CONTACT_EMAIL_KEY, contactEmail);
            return this;
        }

        public GetSupplierRequestBuilder pageSize(int pageSize) {
            parametersMap.put(PAGE_SIZE_KEY, pageSize);
            return this;
        }

        public GetSupplierRequest build() {
            QueryStringBuilder queryStringBuilder = QueryStringBuilder.builder();
            queryStringBuilder.putAll(parametersMap);

            return new GetSupplierRequest(BASE_PATH, queryStringBuilder.build());
        }
    }

    @Override
    public String getQuery() {
        return query;
    }

    @Override
    public String getPath() {
        return path;
    }
}
