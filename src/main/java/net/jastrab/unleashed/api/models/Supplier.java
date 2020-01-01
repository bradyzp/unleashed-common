package net.jastrab.unleashed.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Supplier {
    private final String guid;
    private final String supplierCode;
    private String supplierName;

    public Supplier(@JsonProperty("Guid") String guid,
                    @JsonProperty("SupplierCode") String supplierCode) {
        this.guid = guid;
        this.supplierCode = supplierCode;
    }

    public String getGuid() {
        return guid;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}
