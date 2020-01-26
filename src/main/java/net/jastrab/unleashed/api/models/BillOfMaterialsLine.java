package net.jastrab.unleashed.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class BillOfMaterialsLine {
    private final UUID guid;

    private int lineNumber;
    private Product product;
    private BigDecimal quantity;
    private BigDecimal wastageQuantity;

    private BigDecimal totalLineCost;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String createdBy;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String lastModifiedBy;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime createdOn;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime lastModifiedOn;

    public BillOfMaterialsLine(Product product, BigDecimal quantity) {
        this.guid = UUID.randomUUID();
        this.product = product;
        this.quantity = quantity;
        this.wastageQuantity = BigDecimal.ZERO;
    }


    @JsonCreator
    private BillOfMaterialsLine(@JsonProperty("Guid") UUID guid,
                                @JsonProperty("CreatedBy") String createdBy,
                                @JsonProperty("lastModifiedBy") String lastModifiedBy,
                                @JsonProperty("CreatedOn") LocalDateTime createdOn,
                                @JsonProperty("LastModifiedOn") LocalDateTime lastModifiedOn) {
        this.guid = guid;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
        this.createdOn = createdOn;
        this.lastModifiedOn = lastModifiedOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public LocalDateTime getLastModifiedOn() {
        return lastModifiedOn;
    }
}
