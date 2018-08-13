package com.springmvc.dao.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.codehaus.jackson.map.ObjectMapper;

import com.springmvc.dao.ComboBoxField;
import com.springmvc.dao.ComboOption;

public class JsonService {

	private static final ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
	}

	public static Map<String, String> convertJsonToMap(String inputJson) {

		List<HashMap<String, String>> dataAsMap = createDataMap(inputJson);

		return dataAsMap.stream().collect(Collectors.toMap(t1 -> t1.get("id"), t2->t2.get("val")));
	}
	
	/**
	 *  Mapuje wejściowy JSON na element ComboBoxField gotowy do zapisu.
	 *  
	 *  Struktura JSONa:
	 *  	opcjonalna para {combo_id - #id} - występuje tylko dla edycji
	 *  	para			{name - #nazwa}
	 *  	następnie trójki {"id" - #id_opcji (-1 dla nowych), val - #tresc_opcji, sec - #numer_w_sekwencji}
	 * @param inputJson
	 * @return
	 */
	public static ComboBoxField convertJsonToComboBoxField(String inputJson) {
		List<HashMap<String, String>> dataAsMap = createDataMap(inputJson);
		
		ComboBoxField cbf = new ComboBoxField();
		if(dataAsMap.get(0).containsKey("combo_id")) {
			cbf.setId(Integer.parseInt(dataAsMap.get(0).get("combo_id")));
			dataAsMap.remove(0);
		}
		cbf.setName(dataAsMap.get(0).get("name"));
		dataAsMap.remove(0);
		
		List<ComboOption> options = new ArrayList<ComboOption>();
		for(HashMap<String, String> map:dataAsMap) {
			ComboOption co = new ComboOption();
			if(!map.get("id").equals("-1")){
				co.setId(Integer.parseInt(map.get("id")));
			}
			co.setValue(map.get("val"));
			if(!map.get("sec").equals("")) {
				co.setSec(Integer.parseInt(map.get("sec")));
			}
			options.add(co);
		}

		cbf.setOptions(options);
		return cbf;
	}
	
	private static List<HashMap<String, String>> createDataMap(String inputJson) {
		List<HashMap<String, String>> dataAsMap = null;
		try {
			dataAsMap = objectMapper.readValue(inputJson, List.class);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return dataAsMap;
	}

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
