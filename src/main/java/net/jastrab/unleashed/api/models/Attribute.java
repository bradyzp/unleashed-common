package net.jastrab.unleashed.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.UUID;

public final class Attribute {
    private final UUID guid;
    private final String name;
    private final String value;
    private final boolean isRequired;

    public Attribute(String name, String value, boolean isRequired) {
        this(UUID.randomUUID(), name, value, isRequired);
    }

    @JsonCreator
    private Attribute(@JsonProperty("Guid") UUID guid,
                      @JsonProperty("Name") String name,
                      @JsonProperty("Value") String value,
                      @JsonProperty("IsRequired") boolean isRequired) {
        this.guid = guid;
        this.name = name;
        this.value = value;
        this.isRequired = isRequired;
    }

    public UUID getGuid() {
        return guid;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public boolean isRequired() {
        return isRequired;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "guid=" + guid +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", isRequired=" + isRequired +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attribute attribute = (Attribute) o;
        return isRequired == attribute.isRequired &&
                guid.equals(attribute.guid) &&
                name.equals(attribute.name) &&
                value.equals(attribute.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid, name, value, isRequired);
    }
}
