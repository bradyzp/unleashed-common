package net.jastrab.unleashed.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Currency {
    private final UUID guid;
    private final String currencyCode;
    private final String description;
    private final LocalDateTime lastModifiedOn;

    @JsonCreator
    public Currency(@JsonProperty("Guid") UUID guid,
                    @JsonProperty("CurrencyCode") String currencyCode,
                    @JsonProperty("Description") String description,
                    @JsonProperty("LastModifiedOn") LocalDateTime lastModifiedOn) {
        this.guid = guid;
        this.currencyCode = currencyCode;
        this.description = description;
        this.lastModifiedOn = lastModifiedOn;
    }

    public UUID getGuid() {
        return guid;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getLastModifiedOn() {
        return lastModifiedOn;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "guid=" + guid +
                ", currencyCode='" + currencyCode + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return guid.equals(currency.guid) &&
                currencyCode.equals(currency.currencyCode) &&
                description.equals(currency.description) &&
                Objects.equals(lastModifiedOn, currency.lastModifiedOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid, currencyCode, description, lastModifiedOn);
    }
}
