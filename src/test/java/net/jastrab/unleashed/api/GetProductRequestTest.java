package net.jastrab.unleashed.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Tag("unit")
class GetProductRequestTest {

    @Test
    @DisplayName("Test get product request builds expected query")
    void testGetProductRequestFilterQuery() {
        GetProductRequest request = GetProductRequest.builder()
                .productCode("AS-123-ND")
                .orderBy(GetProductRequest.OrderBy.CREATED)
                .productBarCode("AS-123-ND")
                .build();
        final String query = request.getQuery();

        assertTrue(query.contains("productCode=AS-123-ND"));
        assertTrue(query.contains("productBarCode=AS-123-ND"));
        assertTrue(query.contains("orderBy=CreatedOn"));

    }

    @Nested
    @DisplayName("Test functionality of product pagination requests")
    public class ProductRequestPages {

        @ParameterizedTest
        @ValueSource(ints = {1, 2, 5, 9, 33})
        @DisplayName("Test that the product request builds properly for different valid page numbers")
        void testGetProductPageRequest(int pageNumber) {
            GetProductRequest request = GetProductRequest.builder()
                    .productGroup("AT1M")
                    .build().forPage(pageNumber);

            assertEquals("/Products/Page/" + pageNumber, request.getPath());
        }

        @Test
        @DisplayName("Test that query parameters are retained for different page requests")
        void testGetProductPageRequestWithParams() {
            GetProductRequest baseRequest = GetProductRequest.builder()
                    .productGroup("AT1M")
                    .build();

            assertEquals("productGroup=AT1M", baseRequest.getQuery());

            GetProductRequest pageRequest = baseRequest.forPage(2);

            assertEquals("productGroup=AT1M", pageRequest.getQuery());
        }

        @Test
        @DisplayName("Test that illegal argument exception is thrown given invalid page number")
        void testGetProductPageIllegalPageNumber() {
            GetProductRequest request = GetProductRequest.builder()
                    .productGroup("AT1M")
                    .build();

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                request.forPage(0);
            });

            assertEquals("Page number cannot be less than 1", exception.getMessage());
        }
    }

    @Test
    @DisplayName("it generates a request for all products given no builder parameters")
    void testGetProductEmptyRequest() {
        final GetProductRequest request = GetProductRequest.builder().build();

        assertEquals("/Products/", request.getPath());
        assertEquals("", request.getQuery());
    }

    @Test
    @DisplayName("Test equals comparison between two identical GetProductRequest")
    void testCompareRequests() {
        var requestA = requestForProduct("WM-171918-ND");
        var requestB = requestForProduct("WM-171918-ND");

        assertEquals(requestA, requestA);
        assertEquals(requestA, requestB);
    }

    @Test
    @DisplayName("Test comparison of non-identical GetProductRequests")
    void testCompareRequestsNonEqual() {
        var requestA = requestForProduct("WM-12345-ND");
        var requestB = requestForProduct("MC-AB123");

        assertNotEquals(requestA, requestB);
        assertNotEquals(requestA, requestA.forPage(3));
    }

    @Test
    @DisplayName("it generates a product request for a specific product by its GUID")
    void testGetProductByGuidRequest() {
        UUID guid = UUID.randomUUID();
        final GetProductRequest request = new GetProductRequest(guid);

        assertEquals("/Products/" + guid.toString(), request.getPath());
    }

    private static GetProductRequest requestForProduct(String productCode) {
        return GetProductRequest.builder()
                .productCode(productCode)
                .build();
    }

}
