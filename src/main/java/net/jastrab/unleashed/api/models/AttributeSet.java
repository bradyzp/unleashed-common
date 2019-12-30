package net.jastrab.unleashed.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AttributeSet extends CreatorMetadata {
    private String guid;
    private String setName;
    private String type;
    private List<Attribute> attributes;

    public AttributeSet(@JsonProperty("Guid") String guid,
                        @JsonProperty("SetName") String setName,
                        @JsonProperty("Type") String type,
                        @JsonProperty("Attributes") List<Attribute> attributes) {
        this.guid = guid;
        this.setName = setName;
        this.type = type;
        this.attributes = attributes;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

}
