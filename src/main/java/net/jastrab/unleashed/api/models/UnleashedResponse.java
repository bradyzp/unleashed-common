package net.jastrab.unleashed.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UnleashedResponse<T> {
    private int statusCode;
    private final Pagination pagination;
    private final List<T> items;

    public UnleashedResponse(@JsonProperty("Pagination") Pagination pagination,
                             @JsonProperty("Items") List<T> items) {
        this.pagination = pagination;
        this.items = items;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public List<T> getItems() {
        return items;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
