package com.springmvc.dao.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.springmvc.data.model.ComboBoxField;
import com.springmvc.data.model.ComboBoxProp;
import com.springmvc.data.model.ComboOption;
import com.springmvc.data.model.DateTextBoxProp;
import com.springmvc.data.model.Property;
import com.springmvc.data.model.Stage;
import com.springmvc.data.model.TextBoxProp;

@Component
public class JsonService {

	private static final ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
	}

	/**
	 *  Mapuje wejściowy JSON na listę Properties gotową do zapisu.
	 *  
	 *  Struktura JSONa:
	 *  	 obiekty {"id" - #id_prop (-1 dla nowych), name - #nazwa_prop, sec - #numer_w_sekwencji,
	 *  		type - #typ_prop, val1 - (#dlugosc dla pol tekstowych, #id_combo_field dla combo)}
	 * @param inputJson
	 * @return
	 */
	public static List<Property> convertJsonToProperties(String inputJson) {

		List<HashMap<String, String>> dataAsMap = createDataMap(inputJson);

		return getPropertyListFromDataAsMap(dataAsMap);
	}

	/**
	 *  Mapuje wejściowy JSON na obiekt Stage gotowy do zapisu.
	 *  
	 *  Struktura JSONa:
	 *  	opcjonalna para {stage_id - #id} - występuje tylko dla edycji
	 *  	para			{name - #nazwa}
	 *  	para			{sec - #sec}
	 *  	następnie obiekty {"id" - #id_prop (-1 dla nowych), name - #nazwa_prop, sec - #numer_w_sekwencji,
	 *  		type - #typ_prop, val1 - (#dlugosc dla pol tekstowych, #id_combo_field dla combo)}
	 * @param inputJson
	 * @return
	 */
	public static Stage convertJsonToStage(String inputJson) {
		List<HashMap<String, String>> dataAsMap = createDataMap(inputJson);

		Stage stage = new Stage();
		if(dataAsMap.get(0).containsKey("stage_id")) {
			stage.setId(Integer.parseInt(dataAsMap.get(0).get("stage_id")));
			dataAsMap.remove(0);
		}
		stage.setName(dataAsMap.get(0).get("name"));
		dataAsMap.remove(0);
		
		stage.setSec(Integer.parseInt(dataAsMap.get(0).get("sec")));
		dataAsMap.remove(0);
		
		stage.setProperties(getPropertyListFromDataAsMap(dataAsMap));
		return stage;
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
			co.setComboBoxFieldId(cbf.getId());
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
	
	private static List<Property> getPropertyListFromDataAsMap(List<HashMap<String, String>> dataAsMap) {
		List<Property> propList = new ArrayList<>();
		
		for(HashMap<String, String> map:dataAsMap) {
			Property prop = null;
			switch (map.get("type")) {
			case "TEXT":
				prop = new TextBoxProp();
				if(!map.get("val1").isEmpty()){
					((TextBoxProp)prop).setLength((Integer.parseInt(map.get("val1"))));
				}
				break;
			case "COMBO":
				prop = new ComboBoxProp();
				if(!map.get("val1").isEmpty()){
					((ComboBoxProp)prop).setComboBoxField(new ComboBoxField(Integer.parseInt(map.get("val1"))));
				}
				break;
			case "DATE":
				prop = new DateTextBoxProp();
				//TODO: mapowanie wartości
				break;
			}
			
			if(!map.get("id").equals("-1")){
				prop.setId(Integer.parseInt(map.get("id")));
			}
			if(!map.get("name").isEmpty()) {
				prop.setName(map.get("name"));
			}
			if(!map.get("sec").equals("0")) {
				prop.setSec(Integer.parseInt(map.get("sec")));
			}
			
			propList.add(prop);
			
		}
		
		return propList;
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
