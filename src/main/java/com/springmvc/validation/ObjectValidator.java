package com.springmvc.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.springmvc.data.model.ObjectModel;
import com.springmvc.data.model.PropValue;
import com.springmvc.data.model.TextBoxProp;
import com.springmvc.data.model.TextBoxPropValue;

@Component
public class ObjectValidator {
	
	public ObjectValidator() {
	}
	
	public List<String> validateObject(ObjectModel object){

		List<String> errors = new ArrayList<>();

//		object.getValues().stream().forEach(propValue -> {
//			String error = validateValue(propValue);
//			if(error!=null) {
//				errors.add(error);
//			}			
//		});
//		
//		if(errors.isEmpty()) {
//			return null;
//		}
		return errors;
	}

	private String validateValue(PropValue propValue) {
		switch (propValue.getType()) {
		case TEXT:
			TextBoxPropValue tbpv = (TextBoxPropValue) propValue;
			TextBoxProp tbp = (TextBoxProp) tbpv.getProperty();
			if(tbpv.getValue().length()>tbp.getLength()) {
				return "Wartość jest zbyt długa dla pola: " + tbp.getName();
			}
			break;
		case COMBO:
		case DATE:
		default:
			break;
		}
		return null;
	}
}
