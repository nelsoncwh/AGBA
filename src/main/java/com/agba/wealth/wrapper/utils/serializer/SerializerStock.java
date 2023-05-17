package com.agba.wealth.wrapper.utils.serializer;

import java.io.IOException;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

public class SerializerStock extends JsonSerializer<String> implements ContextualSerializer{

	public SerializerStock() {
		
	}
	
	@Override
	public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property)
			throws JsonMappingException {
		return this;
	}

	@Override
	public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		if(Objects.isNull(value)) {
			gen.writeNull();
		} else {
			String stockCodeOnly = value.toLowerCase().replace(".us", "").replace(".hk", "");
			gen.writeString(stockCodeOnly);
		}
	}

	protected JsonFormat.Value findFormatOverrides(SerializerProvider provider,
			BeanProperty prop, Class<?> typeForDefaults)
	{
		if (prop != null) {
			return prop.findPropertyFormat(provider.getConfig(), typeForDefaults);
		}
		return provider.getDefaultPropertyFormat(typeForDefaults);
	}

}
