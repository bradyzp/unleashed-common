package net.jastrab.unleashed.api.converters;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.time.LocalDateTime;

public class UnleashedObjectMapper extends ObjectMapper {

    public UnleashedObjectMapper() {
        super();
        setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SimpleModule module = new SimpleModule("Unleashed Serialization Module");

        module.addSerializer(new UnleashedLocalDateTimeSerializer());
        module.addDeserializer(LocalDateTime.class, new UnleashedLocalDateTimeDeserializer());

        registerModule(module);
    }

}
