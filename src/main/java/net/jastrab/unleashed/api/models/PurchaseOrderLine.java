package net.jastrab.unleashed.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class PurchaseOrderLine {
    private final String guid;

    private Double BCSubTotal;
    private Double BCUnitPrice;
    private String comments;
    private double discountedUnitPrice;
    private double discountRate;
    private LocalDateTime dueDate;
    private LocalDateTime lastModifiedOn;
    private int lineNumber;
    private double lineTax;
    private double orderQuantity;
    private Product product;
    private Double receiptQuantity;
    // TAX goes here
    private double unitPrice;
    private Double volume;
    private Double weight;

    public PurchaseOrderLine(@JsonProperty("Guid") String guid) {
        this.guid = guid;
    }

    public String getGuid() {
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

    public double getLineTax() {
        return lineTax;
    }

    public void setLineTax(double lineTax) {
        this.lineTax = lineTax;
    }

    public double getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(double orderQuantity) {
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
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
}
