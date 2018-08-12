package com.springmvc.dao.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

public class JsonService {

	private static final ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
	}

	public static Map<Integer, String> convertJsonToMap(String inputJson) {

		List<HashMap<String, String>> dataAsMap = null;
		try {
			dataAsMap = objectMapper.readValue(inputJson, List.class);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return dataAsMap.stream().collect(Collectors.toMap(mapKey, mapValue));
	}

	static Function<HashMap<String, String>, Integer> mapKey = new Function<HashMap<String, String>, Integer>() {
		@Override
		public Integer apply(HashMap<String, String> t) {
			return Integer.parseInt(t.get("id"));
		}
	};

	static Function<HashMap<String, String>, String> mapValue = new Function<HashMap<String, String>, String>() {
		@Override
		public String apply(HashMap<String, String> t) {
			return t.get("val");
		}
	};

	public static String createSuccessMessage() {
	    String json = "{\"message\": \"" + "success" + "\"}"; 
		return json;
	}
	
	public static String createJsonMessage(Map<? extends Object, ? extends Object> data) {
		StringBuilder sb = new StringBuilder();
		sb.append('{');
		
		data.forEach((Object ob1, Object ob2) -> sb.append('\"').append(ob1).append("\": \"").append(ob2).append("\","));
		
		sb.setCharAt(sb.length()-1, '}');
		
		return sb.toString();
	}
}
