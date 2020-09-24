package com.bihell.dice.framework.config.jackson.deserializer;

import com.bihell.dice.framework.config.converter.StringToDoubleUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * todo
 */
public class JacksonDoubleDeserializer extends JsonDeserializer<Double> {

    @Override
    public Double deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String string = jsonParser.getText();
        return StringToDoubleUtil.convert(string);
    }
}
