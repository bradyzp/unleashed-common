package net.jastrab.unleashed.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Immutable Pagination entity describing pagination details for an endpoint response.
 *
 * Pagination information is returned on the following entities:
 * - AttributeSets
 * - Customers
 * - DeliveryMethods
 * - Products
 * - PurchaseOrders
 * - SalesOrders
 * - Salespersons
 * - StockAdjustments
 * - StockOnHand
 * - SupplierReturnReasons
 * - Warehouses
 *
 * @see <a href="https://apidocs.unleashedsoftware.com/Pagination">Unleashed API Doc - Pagination</a>
 *
 * */
public class Pagination {
    private final int numberOfItems;
    private final int pageSize;
    private final int pageNumber;
    private final int numberOfPages;

    public Pagination(@JsonProperty("NumberOfItems") int numberOfItems,
                      @JsonProperty("PageSize") int pageSize,
                      @JsonProperty("PageNumber") int pageNumber,
                      @JsonProperty("NumberOfPages") int numberOfPages) {
        this.numberOfItems = numberOfItems;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.numberOfPages = numberOfPages;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    @Override
    public String toString() {
        return "Pagination{" +
                "numberOfItems=" + numberOfItems +
                ", pageSize=" + pageSize +
                ", pageNumber=" + pageNumber +
                ", numberOfPages=" + numberOfPages +
                '}';
    }
}
