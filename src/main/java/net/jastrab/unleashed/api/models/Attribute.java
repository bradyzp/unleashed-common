package net.jastrab.unleashed.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Attribute {
    private String guid;
    private String name;
    private String value;
    private boolean isRequired;

    public Attribute(@JsonProperty("Guid") String guid,
                     @JsonProperty("Name") String name,
                     @JsonProperty("Value") String value,
                     @JsonProperty("IsRequired") boolean isRequired) {
        this.guid = guid;
        this.name = name;
        this.value = value;
        this.isRequired = isRequired;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }
}
