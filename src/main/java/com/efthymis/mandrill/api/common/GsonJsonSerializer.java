package com.efthymis.mandrill.api.common;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;

import com.efthymis.mandrill.api.messages.MandrillMessage;

public class GsonJsonSerializer {

	private static final String dateFormatStr = "yyyy-MM-dd HH:mm:ss";

	private final Gson gson;

	public GsonJsonSerializer() {
		this(createGsonBuilder().create());
	}

	public GsonJsonSerializer(Gson gson) {
		this.gson = gson;
	}

	public <T> T fromJsonString(String json, Class<T> classOfT) {
		return gson.fromJson(json, classOfT);
	}

	public String toJsonString(Object src, Type typeOfSrc) {
		return gson.toJson(src, typeOfSrc);
	}

	private static GsonBuilder createGsonBuilder() {
		return new GsonBuilder()
				.setDateFormat(dateFormatStr)
				.registerTypeAdapter(Date.class, new GsonJsonSerializer.DateDeserializer())
				.registerTypeAdapter(Map.class, new GsonJsonSerializer.MapSerializer())
				.registerTypeAdapter(MandrillMessage.Recipient.Type.class,
						new GsonJsonSerializer.RecipientTypeSerializer());
	}

	private static final class DateDeserializer
			implements JsonDeserializer<Date>, com.google.gson.JsonSerializer<Date> {

		private SimpleDateFormat getNewDefaultDateTimeFormat() {
			final SimpleDateFormat formatter = new SimpleDateFormat(dateFormatStr);
			formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
			return formatter;
		}

		public final Date deserialize(final JsonElement json,
				final Type typeOfT,
				final JsonDeserializationContext context)
				throws JsonParseException {

			if(!json.isJsonPrimitive()) {
				throw new JsonParseException(
						"Unexpected type for date: " +json.toString());

			}
			try {
				return getNewDefaultDateTimeFormat().parse(json.getAsString());

			} catch(final ParseException e) {
				throw new JsonParseException("Failed to parse date '"
						+json.getAsString()+ "'", e);

			}
		}

		public JsonElement serialize(
				final Date src,
				final Type typeOfSrc,
				final JsonSerializationContext context) {

			return new JsonPrimitive(getNewDefaultDateTimeFormat().format(src));
		}
	}

	private static class MapSerializer implements com.google.gson.JsonSerializer<Map<? extends Object,? extends Object>> {

		public final JsonElement serialize(
				final Map<?, ?> src,
				final Type typeOfSrc,
				final JsonSerializationContext context) {

			Object value;
			final JsonObject json = new JsonObject();
			for(Object key : src.keySet()) {
				value = src.get(key);
				json.add( key.toString(), context.serialize(
						value, value.getClass()) );
			}
			return json;

		}

	}

	private static final class RecipientTypeSerializer
			implements JsonDeserializer<MandrillMessage.Recipient.Type>, com.google.gson.JsonSerializer<MandrillMessage.Recipient.Type> {

		public final MandrillMessage.Recipient.Type deserialize(
				final JsonElement json,
				final Type typeOfT,
				final JsonDeserializationContext context)
				throws JsonParseException {

			if(!json.isJsonPrimitive()) {
				throw new JsonParseException(
						"Unexpected type for recipient type: " +json.toString());
			}

			return MandrillMessage.Recipient.Type.valueOf(
					json.getAsString().toUpperCase());

		}

		public JsonPrimitive serialize(
				final MandrillMessage.Recipient.Type src,
				final Type typeOfSrc,
				final JsonSerializationContext context) {

			return new JsonPrimitive(src.name().toLowerCase());
		}
	}
}
