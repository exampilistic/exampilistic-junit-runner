package org.exampilistic.engines.junit.extinterfaces;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Map;

public class JsonParser {
	private Gson gson = new Gson();
	private Type hashMapType = new TypeToken<Map<String, Object>>(){}.getType();

	public Map<String, Object> fromJson(Reader reader) {
		return gson.fromJson(reader, hashMapType);
	}
}
