package com.springmvc.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springmvc.dao.TextBoxPropDAO;
import com.springmvc.data.model.ObjectModel;
import com.springmvc.data.model.PropValue;
import com.springmvc.data.model.TextBoxProp;
import com.springmvc.data.model.TextBoxPropValue;

@Component
public class ObjectValidator {
	
	@Autowired
	TextBoxPropDAO textBoxPropDAO;
	
	public ObjectValidator() {
	}
	
	public List<String> validateObject(ObjectModel object){

	List<String> errors = new ArrayList<>();
	
	if(!isAtLeastOneValue(object)) {
		errors.add("Przynajmniej jedna wartość musi być ustawiona!");
	}

		object.getTextBoxPropValues().forEach(textBoxPropValue -> {
			String error = validateTextBoxPropValue(textBoxPropValue);
			if(error!=null) {
				errors.add(error);
			}			
		});
		
		if(errors.isEmpty()) {
			return null;
		}
		return errors;
	}

	private boolean isAtLeastOneValue(ObjectModel object) {
		for(PropValue pv:object.getTextBoxPropValues()) {
			if(pv.isSet()) {
				return true;
			}
		}
		
		for(PropValue pv:object.getComboBoxPropValues()) {
			if(pv.isSet()) {
				return true;
			}
		}
		
		for(PropValue pv:object.getDateTextBoxPropValues()) {
			if(pv.isSet()) {
				return true;
			}
		}
			
		return false;
	}

	private String validateTextBoxPropValue(TextBoxPropValue tbpv) {
		TextBoxProp tbp = textBoxPropDAO.findById(tbpv.getPropId());
			if(tbpv.getValue().length()>tbp.getLength()) {
				return "Wartość jest zbyt długa dla pola: " + tbp.getName();
			}
		return null;
	}
}
