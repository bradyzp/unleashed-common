package net.jastrab.unleashed.api.http;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Tag("unit")
class QueryStringBuilderTest {

    @Test
    @DisplayName("it tests that a query string with a single parameter is created properly")
    void buildSingle() {
        QueryStringBuilder builder = QueryStringBuilder.builder("productCode", 100045);
        String queryString = builder.build();

        assertEquals("productCode=100045", queryString);
    }

    @Test
    @DisplayName("it tests that a query string with multiple parameters is created properly")
    void buildMultiQuery() {
        String queryString = QueryStringBuilder.builder()
                .put("productCode", 10564)
                .put("category", "Services").build();
        assertTrue(queryString.contains("category=Services"));
        assertTrue(queryString.contains("productCode=10564"));
    }

    @Test
    @DisplayName("it tests that the query string builder returns an empty string (not-null) with no parameters")
    void buildEmptyQuery() {
        String queryString = QueryStringBuilder.builder().build();
        assertEquals("", queryString);

    }

    @Test
    @DisplayName("Test usage of QueryStringBuilder map constructor")
    void buildMultiParamMap() {
        Map<String, Object> parameters = Map.of(
                "param1", 1,
                "param2", 2,
                "param3", "value3");

        String queryString = QueryStringBuilder.builder().putAll(parameters).build();

        assertAll("Parameters are all present", () -> {
            assertTrue(queryString.contains("param1=1"), "param1 not present");
            assertTrue(queryString.contains("param2=2"), "param2 not present");
            assertTrue(queryString.contains("param3=value3"), "param3 not present");
        });

    }
}
