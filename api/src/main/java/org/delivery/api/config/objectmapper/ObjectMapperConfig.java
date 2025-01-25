package org.delivery.api.config.objectmapper;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        var objectMapper = new ObjectMapper();

        objectMapper.registerModule(new Jdk8Module()); // JDK Version 8+
        objectMapper.registerModule(new JavaTimeModule()); // Local Date

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // Ignore Unknown Properties
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false); // Ignore Empty Objects

        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Write Dates Following ISO 8601

        objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy()); // SnakeCase Property Name

        return objectMapper;
    }
}
