package net.jastrab.unleashed.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UnitOfMeasure {
    private final String guid;
    private final String name;

    public UnitOfMeasure(@JsonProperty("Guid") String guid,
                         @JsonProperty("Name") String name) {
        this.guid = guid;
        this.name = name;
    }

    public String getGuid() {
        return guid;
    }

    public String getName() {
        return name;
    }
}
