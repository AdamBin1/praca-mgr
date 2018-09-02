package com.springmvc.dao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.data.model.Property;
import com.springmvc.data.model.TextBoxProp;

@Service
public class PropertyService {
	
	@Autowired
	private ComboBoxPropService comboBoxPropService; 
	
	@Autowired
	private TextBoxPropService textBoxPropService; 
	
	@Autowired
	private DateTextBoxPropService dateTextBoxPropService; 
	
	/**
	 * 	Zmienia dane obiektom w kolekcji według wartości z
	 * @param properties
	 * @param jsonString ze zbiorem par w postaci {id: X, value: Y}
	 * @return
	 */
/*	public List<Property> updateFromJson(List<Property> properties, String jsonString) {

		Map<String, String> inputMap = JsonService.convertJsonToProperties(jsonString);

		for (Property p : properties) {
			String key = Integer.toString(p.getId());
			if (inputMap.containsKey(key)) {
				String newValue = inputMap.get(key);
				switch (p.getType()) {
				case TEXT:
					TextBoxProp tb = (TextBoxProp) p;
					if (!tb.getValue().equals(newValue)) {
						tb.setValue(newValue);
						tb.setSaveRequired(true);
					}
					break;
				case DATE:
					DateTextBoxProp dtb = (DateTextBoxProp) p;
					if (!dtb.getValue().equals(newValue)) {
						dtb.setValue(newValue);
						dtb.setSaveRequired(true);
					}
					break;
				case COMBO:
					ComboBoxProp cb = (ComboBoxProp) p;
					int newChoosenOption = cb.getComboOptionIdByValue(newValue);
					if (cb.getChoosenOption() != newChoosenOption) {
						cb.setChoosenOption(newChoosenOption);
						cb.setSaveRequired(true);
					}
					break;
				default:
					break;
				}
			}
		}

		return properties;
	}
*/
	public List<Property> getAllProperties(){
		List<Property> propertyList = new ArrayList<>();
		propertyList.addAll(comboBoxPropService.getAllComboBoxProperties());
		propertyList.addAll(textBoxPropService.getAllTextBoxProperties());
		propertyList.addAll(dateTextBoxPropService.getAllDateTextBoxProperties());
		//TODO: nastepne property
		
		propertyList.sort((p1,p2) -> p1.getSec() - p2.getSec());
		return propertyList;
	}
	
	public void saveProperties(List<Property> properties) {
		for(Property prop : properties) {
			switch (prop.getType()) {
			case TEXT:
				textBoxPropService.saveTextBoxPropery((TextBoxProp)prop);
				break;

			default:
				break;
			}
		}
	}
}
