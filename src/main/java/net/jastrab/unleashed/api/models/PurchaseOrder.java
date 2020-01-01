package net.jastrab.unleashed.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class PurchaseOrder {
    private final String guid;
    private final String orderNumber;

    private String createdBy;
    private LocalDateTime createdOn;
    private Double BCSubTotal;
    private Double BCTaxTotal;
    private Double BCTotal;
    private String comments;
    private LocalDateTime completedDate;
    // TODO add currency
    private String deliveryCity;
    private String deliveryCountry;
    private String deliveryName;
    private String deliveryPostCode;
    private String deliveryRegion;
    private String deliveryStreetAddress;
    private String deliveryStreetAddress2;
    private String deliverySuburb;
    private double discountRate;
    private Double exchangeRate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedOn;
    private LocalDateTime orderDate;

    private PurchaseOrderStatus orderStatus;

    private boolean printed;
    private List<PurchaseOrderLine> purchaseOrderLines;
    private LocalDateTime receivedDate;
    private LocalDateTime requiredDate;
    private Double subTotal;
    private Supplier supplier;
    private LocalDateTime supplierInvoiceDate;
    private String supplierRef;

    // TODO add Tax

    private Double taxRate;
    private Double taxTotal;
    private Double total;
    private Double totalVolume;
    private Double totalWeight;

    // TODO add warehouse

    private String xeroTaxCode;


    public PurchaseOrder(@JsonProperty("Guid") String guid,
                         @JsonProperty("OrderNumber") String orderNumber) {
        this.guid = guid;
        this.orderNumber = orderNumber;
    }

    public String getGuid() {
        return guid;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Double getBCSubTotal() {
        return BCSubTotal;
    }

    public void setBCSubTotal(Double BCSubTotal) {
        this.BCSubTotal = BCSubTotal;
    }

    public Double getBCTaxTotal() {
        return BCTaxTotal;
    }

    public void setBCTaxTotal(Double BCTaxTotal) {
        this.BCTaxTotal = BCTaxTotal;
    }

    public Double getBCTotal() {
        return BCTotal;
    }

    public void setBCTotal(Double BCTotal) {
        this.BCTotal = BCTotal;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDateTime getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(LocalDateTime completedDate) {
        this.completedDate = completedDate;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryCountry() {
        return deliveryCountry;
    }

    public void setDeliveryCountry(String deliveryCountry) {
        this.deliveryCountry = deliveryCountry;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryPostCode() {
        return deliveryPostCode;
    }

    public void setDeliveryPostCode(String deliveryPostCode) {
        this.deliveryPostCode = deliveryPostCode;
    }

    public String getDeliveryRegion() {
        return deliveryRegion;
    }

    public void setDeliveryRegion(String deliveryRegion) {
        this.deliveryRegion = deliveryRegion;
    }

    public String getDeliveryStreetAddress() {
        return deliveryStreetAddress;
    }

    public void setDeliveryStreetAddress(String deliveryStreetAddress) {
        this.deliveryStreetAddress = deliveryStreetAddress;
    }

    public String getDeliveryStreetAddress2() {
        return deliveryStreetAddress2;
    }

    public void setDeliveryStreetAddress2(String deliveryStreetAddress2) {
        this.deliveryStreetAddress2 = deliveryStreetAddress2;
    }

    public String getDeliverySuburb() {
        return deliverySuburb;
    }

    public void setDeliverySuburb(String deliverySuburb) {
        this.deliverySuburb = deliverySuburb;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
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

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public PurchaseOrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(PurchaseOrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public boolean isPrinted() {
        return printed;
    }

    public void setPrinted(boolean printed) {
        this.printed = printed;
    }

    public List<PurchaseOrderLine> getPurchaseOrderLines() {
        return purchaseOrderLines;
    }

    public void setPurchaseOrderLines(List<PurchaseOrderLine> purchaseOrderLines) {
        this.purchaseOrderLines = purchaseOrderLines;
    }

    public LocalDateTime getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDateTime receivedDate) {
        this.receivedDate = receivedDate;
    }

    public LocalDateTime getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(LocalDateTime requiredDate) {
        this.requiredDate = requiredDate;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public LocalDateTime getSupplierInvoiceDate() {
        return supplierInvoiceDate;
    }

    public void setSupplierInvoiceDate(LocalDateTime supplierInvoiceDate) {
        this.supplierInvoiceDate = supplierInvoiceDate;
    }

    public String getSupplierRef() {
        return supplierRef;
    }

    public void setSupplierRef(String supplierRef) {
        this.supplierRef = supplierRef;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Double getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(Double taxTotal) {
        this.taxTotal = taxTotal;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(Double totalVolume) {
        this.totalVolume = totalVolume;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getXeroTaxCode() {
        return xeroTaxCode;
    }

    public void setXeroTaxCode(String xeroTaxCode) {
        this.xeroTaxCode = xeroTaxCode;
    }
}
