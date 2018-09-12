package com.springmvc.dao.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springmvc.data.model.ComboBoxField;
import com.springmvc.data.model.ComboBoxProp;
import com.springmvc.data.model.ComboBoxPropValue;
import com.springmvc.data.model.ComboOption;
import com.springmvc.data.model.DateTextBoxProp;
import com.springmvc.data.model.DateTextBoxPropValue;
import com.springmvc.data.model.IdPropIdType;
import com.springmvc.data.model.IdSecPair;
import com.springmvc.data.model.ObjectModel;
import com.springmvc.data.model.PropValue;
import com.springmvc.data.model.Property;
import com.springmvc.data.model.Stage;
import com.springmvc.data.model.TextBoxProp;
import com.springmvc.data.model.TextBoxPropValue;

@Component
public class JsonService {
	
	@Autowired
	ComboBoxConfigurationService comboBoxConfigurationService;
	
	@Autowired
	ObjectService objectService;

	private static final ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
	}

	/**
	 *  Mapuje wejściowy JSON na główny etap gotowy do zapisu.
	 *  
	 *  Struktura JSONa:
	 *  	 obiekty {"id" - #id_prop (pusta dla nowych), name - #nazwa_prop, sec - #numer_w_sekwencji,
	 *  		type - #typ_prop, val1 - (#dlugosc dla pol tekstowych, #id_combo_field dla combo)}
	 * @param inputJson
	 * @return
	 */
	public Stage convertJsonToMainStage(String inputJson) {

		List<HashMap<String, String>> dataAsMap = createDataMap(inputJson);
		
		Stage stage = new Stage(1, null, null, null);
		setPropertiesForStage(stage, dataAsMap);

		return stage;
	}

	/**
	 *  Mapuje wejściowy JSON na obiekt Stage gotowy do zapisu.
	 *  
	 *  Struktura JSONa:
	 *  	opcjonalna para {stage_id - #id} - występuje tylko dla edycji
	 *  	para			{name - #nazwa}
	 *  	para			{sec - #sec}
	 *  	następnie obiekty {"id" - #id_prop (pusta dla nowych), name - #nazwa_prop, sec - #numer_w_sekwencji,
	 *  		type - #typ_prop, val1 - (#dlugosc dla pol tekstowych, #id_combo_field dla combo)}
	 * @param inputJson
	 * @return
	 */
	public Stage convertJsonToStage(String inputJson) {
		List<HashMap<String, String>> dataAsMap = createDataMap(inputJson);

		Stage stage = new Stage();
		if(dataAsMap.get(0).containsKey("stage_id")) {
			stage.setId(Integer.parseInt(dataAsMap.get(0).get("stage_id")));
			dataAsMap.remove(0);
		}
		stage.setName(dataAsMap.get(0).get("name"));
		dataAsMap.remove(0);
		
		if(!dataAsMap.get(0).get("sec").isEmpty()) {
			stage.setSec(Integer.parseInt(dataAsMap.get(0).get("sec")));
		}
		dataAsMap.remove(0);
		
		setPropertiesForStage(stage, dataAsMap);
		return stage;
	}
	
	/**
	 *  Mapuje wejściowy JSON na element ComboBoxField gotowy do zapisu.
	 *  
	 *  Struktura JSONa:
	 *  	opcjonalna para {combo_id - #id} - występuje tylko dla edycji
	 *  	para			{name - #nazwa}
	 *  	następnie trójki {"id" - #id_opcji (pusta dla nowych), val - #tresc_opcji, sec - #numer_w_sekwencji}
	 * @param inputJson
	 * @return
	 */
	public ComboBoxField convertJsonToComboBoxField(String inputJson) {
		List<HashMap<String, String>> dataAsMap = createDataMap(inputJson);
		
		ComboBoxField cbf = new ComboBoxField();
		if(dataAsMap.get(0).containsKey("combo_id")) {
			cbf.setId(Integer.parseInt(dataAsMap.get(0).get("combo_id")));
			dataAsMap.remove(0);
		}
		cbf.setName(dataAsMap.get(0).get("name"));
		dataAsMap.remove(0);
		
		SortedSet<ComboOption> options = new TreeSet<ComboOption>();
		HashMap<String, String> map;
		for(int i=0 ; i < dataAsMap.size() ; i++) {
			map = dataAsMap.get(i);
			ComboOption co = new ComboOption();
			if(!map.get("id").isEmpty()){
				co.setId(Integer.parseInt(map.get("id")));
			} else {
				// minusowe oznaczają, że są to nowe elementy
				co.setId(-1-i);
			}
			co.setValue(map.get("val"));
			if(!map.get("sec").equals("")) {
				co.setSec(Integer.parseInt(map.get("sec")));
			}
			
			co.setComboBoxField(cbf);
			options.add(co);
		}

		cbf.setOptions(options);
		return cbf;
	}
	
	private List<HashMap<String, String>> createDataMap(String inputJson) {
		List<HashMap<String, String>> dataAsMap = null;
		try {
			dataAsMap = objectMapper.readValue(inputJson, List.class);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return dataAsMap;
	}
	
	private void setPropertiesForStage(Stage stage, List<HashMap<String, String>> dataAsMap) {
		Set<TextBoxProp> textBoxProps = new HashSet();
		Set<ComboBoxProp> comboBoxProps = new HashSet();
		Set<DateTextBoxProp> dateTextBoxProps = new HashSet();
		
		stage.setTextBoxProperties(textBoxProps);
		stage.setComboBoxProperties(comboBoxProps);
		stage.setDateTextBoxProperties(dateTextBoxProps);
		
		for(HashMap<String, String> map:dataAsMap) {
			Property prop = null;
			switch (map.get("type")) {
			case "TEXT":
				prop = new TextBoxProp();
				textBoxProps.add((TextBoxProp) prop);
				if(!map.get("val1").isEmpty()){
					((TextBoxProp)prop).setLength((Integer.parseInt(map.get("val1"))));
				}
				break;
			case "COMBO":
				prop = new ComboBoxProp();
				comboBoxProps.add((ComboBoxProp) prop);
				if(!map.get("val1").isEmpty()){
					((ComboBoxProp)prop).setComboBoxField(comboBoxConfigurationService.getComboBoxFieldForId(Integer.parseInt(map.get("val1"))));
				}
				break;
			case "DATE":
				prop = new DateTextBoxProp();
				dateTextBoxProps.add((DateTextBoxProp) prop);
				//TODO: mapowanie wartości
				break;
			}
			
			if(!map.get("id").isEmpty()){
				prop.setId(Integer.parseInt(map.get("id")));
			}
			if(!map.get("name").isEmpty()) {
				prop.setName(map.get("name"));
			}
			if(!map.get("sec").isEmpty() && !map.get("sec").equals("0")) {
				prop.setSec(Integer.parseInt(map.get("sec")));
			}
			prop.setStage(stage);
			
		}
		stage.updateProperties();
	}
	
	/**
	 *  Mapuje wejściowy JSON na obiekt gotowy do zapisu.
	 *  
	 *  Struktura JSONa:
	 *  	opcjonalna para {object_id - #id} - występuje tylko dla edycji
	 *  	następnie czwórki {"id" - #id_wartości (pusta dla nowych), "prop_id" - id property, type - #typ, val - #wartosc}
	 * @param inputJson
	 * @return
	 */
	public ObjectModel convertJsonToObject(String inputJson) {
		List<HashMap<String, String>> dataAsMap = createDataMap(inputJson);
		Integer objectId = null;
		if(dataAsMap.isEmpty()) {
			return null;
		}
		if(dataAsMap.get(0).containsKey("object_id")) {
			objectId = Integer.parseInt(dataAsMap.get(0).get("object_id"));
			dataAsMap.remove(0);
		}
		
		ObjectModel object;
		if(objectId == null) {
			object = new ObjectModel();
		} else {
			object = objectService.getObjectById(objectId);
		}
		Collection<TextBoxPropValue> textBoxPropValues = new ArrayList<>();
		Collection<ComboBoxPropValue> comboBoxPropValues = new ArrayList<>();
		Collection<DateTextBoxPropValue> dateTextBoxPropValues = new ArrayList<>();

		object.setTextBoxPropValues(textBoxPropValues);
		object.setComboBoxPropValues(comboBoxPropValues);
		object.setDateTextBoxPropValues(dateTextBoxPropValues);
		
		
		for(HashMap<String, String> map : dataAsMap) {
			Integer propValId = map.get("id").isEmpty() ? null : Integer.parseInt(map.get("id"));
			if(propValId == null && map.get("val").isEmpty()){
				continue;
			}
			PropValue propVal = null;
			switch (map.get("type")) {
			case "TEXT":
				propVal = new TextBoxPropValue();
				((TextBoxPropValue)propVal).setValue(map.get("val"));
				textBoxPropValues.add((TextBoxPropValue) propVal);
				break;
			case "COMBO":
				propVal = new ComboBoxPropValue();
				if(!map.get("val").isEmpty()) {
					((ComboBoxPropValue)propVal).setValue(Integer.parseInt(map.get("val")));
				}
				comboBoxPropValues.add((ComboBoxPropValue) propVal);
				break;
			case "DATE":
				propVal = new DateTextBoxPropValue();
				if(!map.get("val").isEmpty()) {
					((DateTextBoxPropValue)propVal).setValue(LocalDate.parse(map.get("val")));
				}
				dateTextBoxPropValues.add((DateTextBoxPropValue) propVal);
				break;
			}
			propVal.setPropId(Integer.parseInt(map.get("prop_id")));
			propVal.setId(propValId);
		}
		return object;
	}
	
	public String createJsonMessage(Map<? extends Object, ? extends Object> data) {
		StringBuilder sb = new StringBuilder();
		sb.append('{');
		
		data.forEach((Object ob1, Object ob2) -> sb.append('\"').append(ob1).append("\": \"").append(ob2).append("\","));
		
		sb.setCharAt(sb.length()-1, '}');
		
		return sb.toString();
	}
	
	public String createJsonSuccessMessage() {
	    String json = "{\"message\": \"" + "success" + "\"}";
		return json;
	}
	
	public String createJsonErrorsMessage(List<String> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"").append("errors").append("\":[");
		
		list.forEach(messageString -> sb.append("{\"").append("error").append("\": \"").append(convertToASCII(messageString)).append("\"},"));
		
		sb.setCharAt(sb.length()-1, ']');
		sb.append('}');
		
		return sb.toString();
	}
	
	public String createJsonSuccessMessageForIdSecPairs(List<IdSecPair> idSecPairs) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"").append("idSecPairs").append("\":[");
		
		idSecPairs.forEach(idSec -> sb.append("{\"").append("id").append("\":\"").append(idSec.getId())
				.append("\",\"sec").append("\":\"").append(idSec.getSec()).append("\"},"));
		
		sb.setCharAt(sb.length()-1, ']');
		sb.append('}');
		
		return sb.toString();
	}
	
	public String createJsonSuccessMessageForIdPropIdType(List<IdPropIdType> idPropIdType) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"").append("idPropIdPairs").append("\":[");
		
		idPropIdType.forEach(data -> sb.append("{\"").append("id").append("\":\"").append(data.getId())
				.append("\",\"propId").append("\":\"").append(data.getPropId())
				.append("\",\"type").append("\":\"").append(data.getFieldType()).append("\"},"));
		
		sb.setCharAt(sb.length()-1, ']');
		sb.append('}');
		
		return sb.toString();
	}
	
	private String convertToASCII(String string) {
		string = string.replace("ą", "&#261;");
		string = string.replace("ć", "&#263;");
		string = string.replace("ę", "&#281;");
		string = string.replace("ł", "&#322;");
		string = string.replace("ń", "&#324;");
		string = string.replace("ó", "&#243;");
		string = string.replace("ś", "&#347;");
		string = string.replace("ź", "&#378;");
		string = string.replace("ż", "&#380;");
		
		return string;
	}

}
