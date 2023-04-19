package com.agba.wealth.wrapper.utils.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Objects;

public class YN2BoolDeserializer extends JsonDeserializer<Boolean> {
    private Boolean mapYNToBoolean(String value) {
        Boolean b = null;
        if (!Objects.isNull(value)) {
            switch (value) {
                case "Yes" -> b = true;
                case "No" -> b = false;
            }
        }
        return b;
    }

    @Override
    public Boolean deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        String value = parser.getValueAsString();
        return mapYNToBoolean(value);
    }
}
