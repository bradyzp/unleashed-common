package net.jastrab.unleashed.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.jastrab.unleashed.api.http.SimpleResource;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public final class CustomerType implements SimpleResource {

    private final UUID guid;
    private final LocalDateTime lastModifiedOn;
    private final String typeName;

    @JsonCreator
    private CustomerType(@JsonProperty("Guid") UUID guid,
                         @JsonProperty("LastModifiedOn") LocalDateTime lastModifiedOn,
                         @JsonProperty("TypeName") String typeName) {
        this.guid = guid;
        this.lastModifiedOn = lastModifiedOn;
        this.typeName = typeName;
    }

    public UUID getGuid() {
        return guid;
    }

    public LocalDateTime getLastModifiedOn() {
        return lastModifiedOn;
    }

    public String getTypeName() {
        return typeName;
    }

    @Override
    public String getBasePath() {
        return "/CustomerTypes";
    }

    @Override
    public String toString() {
        return "CustomerType{" +
                "guid=" + guid +
                ", lastModifiedOn=" + lastModifiedOn +
                ", typeName='" + typeName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerType that = (CustomerType) o;
        return guid.equals(that.guid) &&
                lastModifiedOn.equals(that.lastModifiedOn) &&
                typeName.equals(that.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid, lastModifiedOn, typeName);
    }
}
