package net.jastrab.unleashed.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

public class PurchaseOrderLine {
    private final UUID guid;

    private Double BCSubTotal;
    private Double BCUnitPrice;
    private String comments;
    private double discountedUnitPrice;
    private double discountRate;
    private LocalDateTime dueDate;
    private LocalDateTime lastModifiedOn;
    private int lineNumber;
    private BigDecimal lineTotal = BigDecimal.ZERO;
    private BigDecimal lineTax = BigDecimal.ZERO;
    private BigDecimal orderQuantity;
    private Product product;
    private Double receiptQuantity;
    // TAX goes here
    private BigDecimal unitPrice;
    private Double volume;
    private Double weight;

    public PurchaseOrderLine(Product product, BigDecimal unitPrice, int orderQuantity) {
        this.guid = UUID.randomUUID();
        this.product = product;

        this.orderQuantity = BigDecimal.valueOf(orderQuantity);
        this.unitPrice = unitPrice;
        this.lineTotal = this.unitPrice.multiply(this.orderQuantity);
        this.dueDate = LocalDateTime.now();

    }

    @JsonCreator
    private PurchaseOrderLine(@JsonProperty("Guid") UUID guid) {
        this.guid = guid;
    }

    public UUID getGuid() {
        return guid;
    }

    public Double getBCSubTotal() {
        return BCSubTotal;
    }

    public void setBCSubTotal(Double BCSubTotal) {
        this.BCSubTotal = BCSubTotal;
    }

    public Double getBCUnitPrice() {
        return BCUnitPrice;
    }

    public void setBCUnitPrice(Double BCUnitPrice) {
        this.BCUnitPrice = BCUnitPrice;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public double getDiscountedUnitPrice() {
        return discountedUnitPrice;
    }

    public void setDiscountedUnitPrice(double discountedUnitPrice) {
        this.discountedUnitPrice = discountedUnitPrice;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getLastModifiedOn() {
        return lastModifiedOn;
    }

    public void setLastModifiedOn(LocalDateTime lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public BigDecimal getLineTax() {
        return lineTax;
    }

    public void setLineTax(BigDecimal lineTax) {
        this.lineTax = lineTax;
    }

    public BigDecimal getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(BigDecimal orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getReceiptQuantity() {
        return receiptQuantity;
    }

    public void setReceiptQuantity(Double receiptQuantity) {
        this.receiptQuantity = receiptQuantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public BigDecimal getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }

    @Override
    public String toString() {
        return "PurchaseOrderLine{" +
                "guid=" + guid +
                ", productCode=" + product.getProductCode() +
                ", lineTotal=" + lineTotal +
                ", orderQuantity=" + orderQuantity +
                '}';
    }
}
