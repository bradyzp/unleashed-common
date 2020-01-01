package net.jastrab.unleashed.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public class Product {
    private final String guid;
    private final String productCode;

    private Double averageLandPrice;
    private String barcode;
    private String binLocation;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime createdOn;
    private boolean isComponent;
    private boolean isSellable;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Double lastCost;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String lastModifiedBy;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime lastModifiedOn;
    private Double minimumOrderQuantity;
    private Double minimumSaleQuantity;
    private Double minimumSellPrice;
    private boolean neverDiminishing;
    private String notes;
    private boolean obsolete;
    private Double packSize;
    private String productDescription;
    private ProductGroup productGroup;
    private Double reOrderPoint;
    private Supplier supplier;
    private boolean taxablePurchase;
    private boolean taxableSales;
    private UnitOfMeasure unitOfMeasure;
    private Double height;
    private Double depth;
    private Double width;
    private Double weight;
    // TODO: Add Xero Fields?


    public Product(String productCode) {
        this.guid = UUID.randomUUID().toString();
        this.productCode = productCode;
    }

    public Product(
            @JsonProperty("Guid") String guid,
            @JsonProperty("ProductCode") String productCode) {
        this.guid = guid;
        this.productCode = productCode;
    }

    public String getGuid() {
        return guid;
    }

    public String getProductCode() {
        return productCode;
    }

    public Double getAverageLandPrice() {
        return averageLandPrice;
    }

    public void setAverageLandPrice(Double averageLandPrice) {
        this.averageLandPrice = averageLandPrice;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBinLocation() {
        return binLocation;
    }

    public void setBinLocation(String binLocation) {
        this.binLocation = binLocation;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public boolean isComponent() {
        return isComponent;
    }

    public void setComponent(boolean component) {
        isComponent = component;
    }

    public boolean isSellable() {
        return isSellable;
    }

    public void setSellable(boolean sellable) {
        isSellable = sellable;
    }

    public Double getLastCost() {
        return lastCost;
    }

    public void setLastCost(Double lastCost) {
        this.lastCost = lastCost;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public LocalDateTime getLastModifiedOn() {
        return lastModifiedOn;
    }

    public void setLastModifiedOn(LocalDateTime lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    public Double getMinimumOrderQuantity() {
        return minimumOrderQuantity;
    }

    public void setMinimumOrderQuantity(Double minimumOrderQuantity) {
        this.minimumOrderQuantity = minimumOrderQuantity;
    }

    public Double getMinimumSaleQuantity() {
        return minimumSaleQuantity;
    }

    public void setMinimumSaleQuantity(Double minimumSaleQuantity) {
        this.minimumSaleQuantity = minimumSaleQuantity;
    }

    public Double getMinimumSellPrice() {
        return minimumSellPrice;
    }

    public void setMinimumSellPrice(Double minimumSellPrice) {
        this.minimumSellPrice = minimumSellPrice;
    }

    public boolean isNeverDiminishing() {
        return neverDiminishing;
    }

    public void setNeverDiminishing(boolean neverDiminishing) {
        this.neverDiminishing = neverDiminishing;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isObsolete() {
        return obsolete;
    }

    public void setObsolete(boolean obsolete) {
        this.obsolete = obsolete;
    }

    public Double getPackSize() {
        return packSize;
    }

    public void setPackSize(Double packSize) {
        this.packSize = packSize;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    public Double getReOrderPoint() {
        return reOrderPoint;
    }

    public void setReOrderPoint(Double reOrderPoint) {
        this.reOrderPoint = reOrderPoint;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public boolean isTaxablePurchase() {
        return taxablePurchase;
    }

    public void setTaxablePurchase(boolean taxablePurchase) {
        this.taxablePurchase = taxablePurchase;
    }

    public boolean isTaxableSales() {
        return taxableSales;
    }

    public void setTaxableSales(boolean taxableSales) {
        this.taxableSales = taxableSales;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }
}
