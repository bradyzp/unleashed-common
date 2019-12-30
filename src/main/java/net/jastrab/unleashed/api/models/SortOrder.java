package net.jastrab.unleashed.api.models;

public enum SortOrder {
    ASCENDING("asc"),
    DESCENDING("desc");

    private final String value;

    SortOrder(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
