package org.amura.interview.test.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestHelperUtils {
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static String getJsonString(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		}catch(JsonProcessingException ex) {
			return "";
		}
	}
	
	public static <T> T getObjectFromJson(String json, Class<T> className) {
		try {
			return mapper.readValue(json, className);
		} catch (IOException e) {
			return null;
		}
	}
}
