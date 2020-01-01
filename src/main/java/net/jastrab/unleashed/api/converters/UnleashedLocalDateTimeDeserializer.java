package net.jastrab.unleashed.api.converters;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnleashedLocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {
    private static final Pattern DATE_PATTERN = Pattern.compile("/Date\\((\\d*)\\)/");

    protected UnleashedLocalDateTimeDeserializer() {
        super(LocalDateTime.class);
    }

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.readValueAs(String.class);

        Matcher matcher = DATE_PATTERN.matcher(value);
        if (matcher.matches()) {
            long epochMillis = Long.parseLong(matcher.group(1));
            return LocalDateTime.ofEpochSecond(epochMillis / 1000, (int) (epochMillis % 1000) * 1000000, ZoneOffset.UTC);
        }

        return LocalDateTime.MIN;
    }
}
