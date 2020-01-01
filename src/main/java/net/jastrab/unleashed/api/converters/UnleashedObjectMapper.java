package net.jastrab.unleashed.api.converters;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.time.LocalDateTime;

public enum UnleashedObjectMapper {
    INSTANCE;

    private final ObjectMapper mapper;


    UnleashedObjectMapper() {
        mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SimpleModule module = new SimpleModule("Unleashed Serialization Module");

        module.addSerializer(new UnleashedLocalDateTimeSerializer());
        module.addDeserializer(LocalDateTime.class, new UnleashedLocalDateTimeDeserializer());

        mapper.registerModule(module);

    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public static UnleashedObjectMapper getInstance() {
        return INSTANCE;
    }

}
