package net.jastrab.unleashed.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.UUID;

public class SupplierWithProductCode {
    private final UUID guid;
    private final String supplierCode;
    private final String supplierName;
    private String supplierProductCode;
    private String supplierProductDescription;
    private BigDecimal supplierProductPrice;

    @JsonCreator
    public SupplierWithProductCode(@JsonProperty("Guid") UUID guid,
                                    @JsonProperty("SupplierCode") String supplierCode,
                                    @JsonProperty("SupplierName") String supplierName) {
        this.guid = guid;
        this.supplierCode = supplierCode;
        this.supplierName = supplierName;
    }

    public UUID getGuid() {
        return guid;
    }

    public String getSupplierProductCode() {
        return supplierProductCode;
    }

    public void setSupplierProductCode(String supplierProductCode) {
        this.supplierProductCode = supplierProductCode;
    }

    public String getSupplierProductDescription() {
        return supplierProductDescription;
    }

    public void setSupplierProductDescription(String supplierProductDescription) {
        this.supplierProductDescription = supplierProductDescription;
    }

    public BigDecimal getSupplierProductPrice() {
        return supplierProductPrice;
    }

    public void setSupplierProductPrice(BigDecimal supplierProductPrice) {
        this.supplierProductPrice = supplierProductPrice;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    @Override
    public String toString() {
        return "SupplierWithProductCode{" +
                "guid=" + guid +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", supplierProductCode='" + supplierProductCode + '\'' +
                ", supplierProductDescription='" + supplierProductDescription + '\'' +
                ", supplierProductPrice=" + supplierProductPrice +
                '}';
    }
}
