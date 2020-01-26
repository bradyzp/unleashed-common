package net.jastrab.unleashed.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.jastrab.unleashed.api.http.HttpMethod;
import net.jastrab.unleashed.api.http.MutableResource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Product implements MutableResource {
    private static final String RESOURCE_PATH = "/Products/";

    private final UUID guid;
    private final ResourceOrigin origin;
    private final String productCode;
    private final String productDescription;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private final BigDecimal averageLandPrice;

    private String barcode;
    private String binLocation;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime createdOn;
    private boolean isComponent;
    private boolean isSellable = true;
    private BigDecimal lastCost;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String lastModifiedBy;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime lastModifiedOn;
    private Double minimumOrderQuantity;
    private Double minimumSaleQuantity;
    private BigDecimal minimumSellPrice;
    private boolean neverDiminishing = false;
    private String notes;
    private boolean obsolete;
    private Double packSize;
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

    /*Xero Accounting Fields*/
    private String xeroCostOfGoodsAccount;
    private String xeroSalesAccount;
    private String xeroSalesTaxCode;
    private BigDecimal xeroSalesTaxRate;
    private String xeroTaxCode;
    private BigDecimal xeroTaxRate;

    public Product(String productCode, String productDescription) {
        this.origin = ResourceOrigin.LOCAL;
        this.guid = UUID.randomUUID();
        this.productCode = productCode;
        this.productDescription = productDescription;
        this.averageLandPrice = null;
    }

    @JsonCreator
    private Product(
            @JsonProperty("Guid") UUID guid,
            @JsonProperty("ProductCode") String productCode,
            @JsonProperty("ProductDescription") String productDescription,
            @JsonProperty("AverageLandPrice") BigDecimal averageLandPrice) {
        this.origin = ResourceOrigin.REMOTE;
        this.guid = guid;
        this.productCode = productCode;
        this.productDescription = productDescription;
        this.averageLandPrice = averageLandPrice;
    }

    @Override
    public UUID getGuid() {
        return guid;
    }

    @Override
    public String getBasePath() {
        return RESOURCE_PATH;
    }

    @Override
    public ResourceOrigin getOrigin() {
        return origin;
    }

    @Override
    public HttpMethod getUpdateMethod() {
        return HttpMethod.POST;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public BigDecimal getAverageLandPrice() {
        return averageLandPrice;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * @return the product's bin location
     * @apiNote This does not appear to work currently, the API always returns NULL
     */
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

    public BigDecimal getLastCost() {
        return lastCost;
    }

    public void setLastCost(BigDecimal lastCost) {
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

    public BigDecimal getMinimumSellPrice() {
        return minimumSellPrice;
    }

    public void setMinimumSellPrice(BigDecimal minimumSellPrice) {
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

    @Override
    public String toString() {
        return "Product{" +
                "guid='" + guid + '\'' +
                ", productCode='" + productCode + '\'' +
                ", averageLandPrice=" + averageLandPrice +
                ", barcode='" + barcode + '\'' +
                '}';
    }

}
