package net.jastrab.unleashed.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UnleashedResponse<T> {
    private int statusCode;
    private final Pagination pagination;
    private final List<T> items = new ArrayList<>();

    @JsonCreator
    public UnleashedResponse(@JsonProperty("Pagination") Pagination pagination,
                             @JsonProperty("Items") List<T> items) {
        this.pagination = pagination;
        this.items.addAll(items);
    }

    public Optional<Pagination> getPagination() {
        return Optional.ofNullable(pagination);
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
