package com.agba.wealth.wrapper.utils.serializer;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

public class SerializerFloat extends JsonSerializer<Float> implements ContextualSerializer{

	protected DecimalFormat decimalFormat;

	protected DecimalFormat defaultDecimalFormat = new DecimalFormat("0.####");

	public SerializerFloat() {
	}

	public SerializerFloat(DecimalFormat decimalFormat) {
		this.decimalFormat = decimalFormat;
	}

	@Override
	public void serialize(Float value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

		if(Objects.isNull(value)) {
			gen.writeNull();
		} else {
			if(null != decimalFormat) {
				gen.writeNumber(decimalFormat.format(value));
			}else {
				gen.writeNumber(defaultDecimalFormat.format(value));
			}
		}
	}

	@Override
	public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property)
			throws JsonMappingException {

		JsonFormat.Value format = findFormatOverrides(prov, property, handledType());
		if (format == null) {
			return this;
		}

		if (format.hasPattern()) {
			DecimalFormat decimalFormat = new DecimalFormat(format.getPattern());
			return new SerializerFloat(decimalFormat);
		}

		return this;
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
