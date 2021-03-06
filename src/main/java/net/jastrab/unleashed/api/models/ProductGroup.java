package net.jastrab.unleashed.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.jastrab.unleashed.api.http.SimpleResource;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public final class ProductGroup implements SimpleResource {
    private final UUID guid;
    private final String groupName;
    private final LocalDateTime lastModifiedOn;

    @JsonCreator
    private ProductGroup(@JsonProperty("Guid") UUID guid,
                         @JsonProperty("GroupName") String groupName,
                         @JsonProperty("LastModifiedOn") LocalDateTime lastModifiedOn) {
        this.guid = guid;
        this.groupName = groupName;
        this.lastModifiedOn = lastModifiedOn;
    }

    public UUID getGuid() {
        return guid;
    }

    public String getGroupName() {
        return groupName;
    }

    public LocalDateTime getLastModifiedOn() {
        return lastModifiedOn;
    }

    @Override
    public String getBasePath() {
        return "/ProductGroups";
    }

    public String toString() {
        return "ProductGroup{" +
                "guid=" + guid +
                ", groupName='" + groupName + '\'' +
                ", lastModifiedOn='" + lastModifiedOn + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductGroup that = (ProductGroup) o;
        return guid.equals(that.guid) &&
                groupName.equals(that.groupName) &&
                lastModifiedOn.equals(that.lastModifiedOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid, groupName, lastModifiedOn);
    }
}
