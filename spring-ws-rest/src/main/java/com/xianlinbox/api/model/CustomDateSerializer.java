package com.xianlinbox.api.model;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.joda.time.DateTime;

import java.io.IOException;

public class CustomDateSerializer extends JsonSerializer<DateTime> {
    @Override
    public void serialize(DateTime value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
       jgen.writeString(value.toString("yyyy-MM-dd HH:mm:ss"));
    }
}
