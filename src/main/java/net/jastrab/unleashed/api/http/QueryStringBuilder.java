package net.jastrab.unleashed.api.http;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class QueryStringBuilder {
    private final Map<String, String> parameters = new HashMap<>();

    private QueryStringBuilder() {
    }

    public QueryStringBuilder put(String key, Object value) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);

        parameters.put(key, encode(value));
        return this;
    }

    public QueryStringBuilder putAll(Map<String, Object> values) {
        values.forEach(this::put);
        return this;
    }

    public String build() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            if (builder.length() > 0)
                builder.append("&");
            builder.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return builder.toString();
    }

    public static QueryStringBuilder builder() {
        return new QueryStringBuilder();
    }

    public static QueryStringBuilder builder(String key, Object value) {
        QueryStringBuilder builder = new QueryStringBuilder();
        builder.put(key, value);
        return builder;
    }

    private static String encode(Object value) {
        return URLEncoder.encode(value.toString().trim(), StandardCharsets.UTF_8);
    }
}
