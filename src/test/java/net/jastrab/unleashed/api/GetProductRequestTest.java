package net.jastrab.unleashed.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GetProductRequestTest {

    @Test
    @DisplayName("it tests that the product request query string is built properly")
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

    @Test
    @DisplayName("it tests that the product request builds properly for different pages")
    void testGetProductPageRequest() {
        GetProductRequest request = GetProductRequest.builder()
                .productGroup("AT1M")
                .build();

        assertEquals("/Products/", request.getPath());
        assertEquals("productGroup=AT1M", request.getQuery());

        GetProductRequest reqPage2 = request.forPage(2);
        assertEquals("/Products/Page/2", reqPage2.getPath());
        assertEquals("productGroup=AT1M", reqPage2.getQuery());

        GetProductRequest reqPage3 = reqPage2.forPage(3);
        assertEquals("/Products/Page/3", reqPage3.getPath());

        assertThrows(IllegalArgumentException.class, () -> request.forPage(0));
    }

    @Test
    @DisplayName("it generates a request for all products given no builder parameters")
    void testGetProductEmptyRequest() {
        final GetProductRequest request = GetProductRequest.builder().build();

        assertEquals("/Products/", request.getPath());
        assertEquals("", request.getQuery());
    }

    @Test
    @DisplayName("it generates a product request for a specific product by its GUID")
    void testGetProductByGuidRequest() {
        UUID guid = UUID.randomUUID();
        final GetProductRequest request = new GetProductRequest(guid);

        assertEquals("/Products/" + guid.toString(), request.getPath());
    }

}
