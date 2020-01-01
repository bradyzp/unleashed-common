package net.jastrab.unleashed.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductGroup {
    private final String guid;
    private final String groupName;
    private String lastModifiedOn;

    public ProductGroup(@JsonProperty("Guid") String guid,
                        @JsonProperty("GroupName") String groupName) {
        this.guid = guid;
        this.groupName = groupName;
    }

    @JsonProperty("LastModifiedOn")
    public String getLastModifiedOn() {
        return lastModifiedOn;
    }

    public void setLastModifiedOn(String lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }
}
