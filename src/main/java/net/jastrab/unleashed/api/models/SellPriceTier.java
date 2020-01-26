package net.jastrab.unleashed.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.jastrab.unleashed.api.http.SimpleResource;

import java.util.Objects;
import java.util.UUID;

public final class SellPriceTier implements SimpleResource {
    private final int tier;
    private final String name;

    @JsonCreator
    private SellPriceTier(@JsonProperty("Tier") int tier,
                          @JsonProperty("Name") String name) {
        this.tier = tier;
        this.name = name;
    }

    public int getTier() {
        return tier;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getBasePath() {
        return "/SellPriceTier";
    }

    @Override
    public UUID getGuid() {
        throw new RuntimeException("Guid is not supported on SellPriceTier");
    }

    @Override
    public String toString() {
        return "SellPriceTier{" +
                "tier=" + tier +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SellPriceTier that = (SellPriceTier) o;
        return tier == that.tier &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tier, name);
    }
}
