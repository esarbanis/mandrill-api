package org.esarbanis.mandrill.api.common;

import java.lang.reflect.Type;

public interface JsonSerializer {

	<T> T fromJsonString(String json, Class<T> classOfT);

	String toJsonString(Object src, Type typeOfSrc);
}
