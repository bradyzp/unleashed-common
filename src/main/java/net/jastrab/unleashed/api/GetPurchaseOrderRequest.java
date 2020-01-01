package net.jastrab.unleashed.api;

import net.jastrab.unleashed.api.http.GetRequest;
import net.jastrab.unleashed.api.http.QueryStringBuilder;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class GetPurchaseOrderRequest implements GetRequest {

    private final String query;
    private final String path;


    private GetPurchaseOrderRequest(String query, String path) {
        this.query = query;
        this.path = path;
    }

    public static GetPurchaseOrderRequestBuilder builder() {
        return new GetPurchaseOrderRequestBuilder();
    }

    public static class GetPurchaseOrderRequestBuilder {
        private static final String BASE_PATH = "/PurchaseOrders/";
        private static final String SUPPLIER_CODE_KEY = "supplierCode";
        private static final String START_DATE_KEY = "startDate";
        private static final String END_DATE_KEY = "endDate";
        private static final String BRIEF_KEY = "brief";

        private final Map<String, Object> parametersMap = new HashMap<>();

        public GetPurchaseOrderRequestBuilder supplierCode(String supplierCode) {
            parametersMap.put(SUPPLIER_CODE_KEY, supplierCode);
            return this;
        }

        public GetPurchaseOrderRequestBuilder startDate(LocalDateTime startDate) {
            parametersMap.put(START_DATE_KEY, startDate);
            return this;
        }

        public GetPurchaseOrderRequestBuilder endDate(LocalDateTime endDate) {
            parametersMap.put(END_DATE_KEY, endDate);
            return this;
        }

        public GetPurchaseOrderRequestBuilder brief() {
            parametersMap.put(BRIEF_KEY, true);
            return this;
        }

        public GetPurchaseOrderRequestBuilder detailed() {
            parametersMap.put(BRIEF_KEY, false);
            return this;
        }

        public GetPurchaseOrderRequest guid(String guid) {
            final QueryStringBuilder qsb = QueryStringBuilder.builder(BRIEF_KEY, parametersMap.getOrDefault(BRIEF_KEY, true));

            return new GetPurchaseOrderRequest(qsb.build(), BASE_PATH + guid);
        }

        public GetPurchaseOrderRequest build() {
            final QueryStringBuilder qsb = QueryStringBuilder.builder();
            qsb.putAll(parametersMap);

            return new GetPurchaseOrderRequest(qsb.build(), BASE_PATH);
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
