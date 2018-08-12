package com.springmvc.dao.service;

import java.util.List;
import java.util.Map;

import com.springmvc.dao.ComboBoxProp;
import com.springmvc.dao.DateTextBoxProp;
import com.springmvc.dao.Property;
import com.springmvc.dao.TextBoxProp;

public class PropertyService {
	
	/**
	 * 	Zmienia dane obiektom w kolekcji według wartości z
	 * @param properties
	 * @param jsonString ze zbiorem par w postaci {id: X, value: Y}
	 * @return
	 */
	public List<Property> updateFromJson(List<Property> properties, String jsonString) {

		Map<Integer, String> inputMap = JsonService.convertJsonToMap(jsonString);

		for (Property p : properties) {
			if (inputMap.containsKey(p.getId())) {
				String newValue = inputMap.get(p.getId());
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

}
