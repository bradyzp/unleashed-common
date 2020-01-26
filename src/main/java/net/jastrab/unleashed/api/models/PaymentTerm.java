package net.jastrab.unleashed.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.jastrab.unleashed.api.http.SimpleResource;

import java.util.Objects;
import java.util.UUID;

/**
 * Entity representing a Payment Terms Unleashed object
 */
public final class PaymentTerm implements SimpleResource {
    private final UUID guid;
    private final int days;
    private final boolean obsolete;
    private final String name;
    private final PaymentTermType type;

    public enum PaymentTermType {
        @JsonProperty("DaysAfter")
        DAYS_AFTER("DaysAfter"),  // simple # of days after
        @JsonProperty("DaysOfTheMonthFollowing")
        DAY_OF_MONTH_FOLLOWING("DaysOfTheMonthFollowing"),  // e.g. specific day of the next month (e.g. on 20th)
        @JsonProperty("DaysFollowingTheEndOfTheMonth")
        DAYS_FOLLOWING_END_OF_MONTH("DaysFollowingTheEndOfTheMonth"),  // e.g. 15 days after the end of the current month
        @JsonProperty("EndOfTheMonthFollowing")
        END_OF_MONTH_FOLLOWING("EndOfTheMonthFollowing");  // end of the next month

        private final String jsonValue;

        PaymentTermType(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJsonValue() {
            return jsonValue;
        }
    }

    @JsonCreator
    private PaymentTerm(@JsonProperty("Guid") UUID guid,
                        @JsonProperty("Days") int days,
                        @JsonProperty("Obsolete") boolean obsolete,
                        @JsonProperty("PaymentTermDescription") String name,
                        @JsonProperty("Type") PaymentTermType type) {
        this.guid = guid;
        this.days = days;
        this.obsolete = obsolete;
        this.name = name;
        this.type = type;
    }

    public UUID getGuid() {
        return guid;
    }

    public int getDays() {
        return days;
    }

    public boolean isObsolete() {
        return obsolete;
    }

    public String getName() {
        return name;
    }

    public PaymentTermType getType() {
        return type;
    }

    @Override
    public String getBasePath() {
        return "/PaymentTerms";
    }

    @Override
    public String toString() {
        return "PaymentTerms{" +
                "guid=" + guid +
                ", days=" + days +
                ", obsolete=" + obsolete +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentTerm that = (PaymentTerm) o;
        return days == that.days &&
                obsolete == that.obsolete &&
                guid.equals(that.guid) &&
                name.equals(that.name) &&
                type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid, days, obsolete, name, type);
    }
}
