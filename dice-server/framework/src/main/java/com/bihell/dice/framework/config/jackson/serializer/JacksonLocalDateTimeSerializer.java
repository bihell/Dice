package com.bihell.dice.framework.config.jackson.serializer;

import com.bihell.dice.config.constant.DatePattern;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * Jackson LocalDateTime 自定义序列化器 todo
 * </p>
 */
public class JacksonLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        String string = null;
        if (localDateTime != null) {
            string = localDateTime.format(DateTimeFormatter.ofPattern(DatePattern.YYYY_MM_DD_HH_MM_SS));
        }
        jsonGenerator.writeString(string);
    }

}
