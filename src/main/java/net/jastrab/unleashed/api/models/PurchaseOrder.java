package net.jastrab.unleashed.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.jastrab.unleashed.api.http.CreatableResource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;


/**
 * Model class for an Unleashed PurchaseOrder.
 *
 * Note that the Unleashed API does not allow for the modification of a PurchaseOrder after it is
 * created. However, individual purchase order lines can be deleted (but not added).
 */
public class PurchaseOrder implements CreatableResource {
    private final UUID guid;
    private final ResourceOrigin origin;
    private String orderNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String createdBy;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String lastModifiedBy;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime lastModifiedOn;
    private LocalDateTime orderDate;

    private PurchaseOrderStatus orderStatus;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean printed;
    private Map<String, PurchaseOrderLine> purchaseOrderLines = new HashMap<>();
    private LocalDateTime receivedDate;
    private LocalDateTime requiredDate;
    private BigDecimal subTotal = BigDecimal.ZERO;
    private Supplier supplier;
    private LocalDateTime supplierInvoiceDate;
    private String supplierRef;

    // TODO add Tax

    private BigDecimal taxRate;
    private BigDecimal taxTotal = BigDecimal.ZERO;
    private BigDecimal total = BigDecimal.ZERO;
    private Double totalVolume;
    private Double totalWeight;

    // TODO add warehouse

    private String xeroTaxCode;


    /**
     * Create a new PurchaseOrder with the minimum required fields/values
     * <p>
     * This constructor will create a PurchaseOrder with the specified supplier,
     * and will set the order status to 'Parked'. The required date will be set to
     * the current Local Date Time
     *
     * @param supplier Specify the supplier for this purchase order
     */
    public PurchaseOrder(Supplier supplier) {
        this(supplier, LocalDateTime.now());
    }

    public PurchaseOrder(Supplier supplier, LocalDateTime requiredDate) {
        this.origin = ResourceOrigin.LOCAL;
        this.guid = UUID.randomUUID();
        this.supplier = supplier;
        this.requiredDate = requiredDate;
        this.orderStatus = PurchaseOrderStatus.Parked;
    }

    @JsonCreator
    private PurchaseOrder(@JsonProperty("Guid") UUID guid,
                          @JsonProperty("OrderNumber") String orderNumber) {
        this.origin = ResourceOrigin.REMOTE;
        this.guid = guid;
        this.orderNumber = orderNumber;
    }

    /**
     * Method to add an Order Line to this purchase order.
     * This method will automatically re-calculate the required
     * PurchaseOrder subTotal, taxTotal, and total fields based on
     * the added {@link PurchaseOrderLine}
     *
     * @param line A valid PurchaseOrderLine
     */
    public void addOrderLine(PurchaseOrderLine line) {
        line.setLineNumber(purchaseOrderLines.size() + 1);
        purchaseOrderLines.put(line.getProduct().getProductCode(), line);
        // TODO: move the calculation into the getters, possibly remove the fields
        //       or use utility method to recalculate after addition/deletion
        // TODO Consider using a HashMap internally to store PO Lines, keyed by the product code?
        //      then flatten into list in the getter & for serialization
        subTotal = subTotal.add(line.getLineTotal());
        taxTotal = taxTotal.add(line.getLineTax());
        total = total.add(subTotal.add(taxTotal));
    }

    // TODO implement
    public void removeOrderLine(String productCode) {
        PurchaseOrderLine line = purchaseOrderLines.remove(productCode);
        if(line == null) {
            return;
        }

        subTotal = subTotal.subtract(line.getLineTotal());
        taxTotal = taxTotal.subtract(line.getLineTax());

        total = total.subtract(subTotal.add(taxTotal));
        // todo: recalculate PO line numbers after a removal
    }

    public void removeOrderLine(Product product) {
        removeOrderLine(product.getProductCode());
    }


    @Override
    public UUID getGuid() {
        return guid;
    }

    @Override
    public String getBasePath() {
        return "/PurchaseOrders/" + guid;
    }

    @Override
    public ResourceOrigin getOrigin() {
        return origin;
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

    /**
     * Return an Unmodifiable List containing all of the {@link PurchaseOrderLine}'s
     * in this PurchaseOrder
     *
     * PurchaseOrderLines may not be directly added to the internal list, as the Unleashed
     * API requires the manual calculation of subTotal, taxTotal, and total fields when
     * creating a new PurchaseOrder.
     *
     * Use the {@link #addOrderLine(PurchaseOrderLine)} method to add a new PurchaseOrderLine
     */
    public Collection<PurchaseOrderLine> getPurchaseOrderLines() {
        return Collections.unmodifiableCollection(purchaseOrderLines.values());
    }

    public void setPurchaseOrderLines(List<PurchaseOrderLine> purchaseOrderLines) {
        purchaseOrderLines.forEach(line -> this.purchaseOrderLines.put(line.getProduct().getProductCode(), line));
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

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
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

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(BigDecimal taxTotal) {
        this.taxTotal = taxTotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
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

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "guid=" + guid +
                ", orderNumber='" + orderNumber + '\'' +
                ", origin='" + origin + '\'' +
                '}';
    }
}
