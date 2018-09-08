package com.springmvc.validation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springmvc.dao.service.ComboBoxConfigurationService;
import com.springmvc.data.model.ComboBoxField;

@Component
public class ComboBoxValidator {
	
	@Autowired
	ComboBoxConfigurationService comboBoxConfigurationService;
	
	boolean wrongNames;
	boolean wrongSec;
	
	public ComboBoxValidator() {
	}
	
	public List<String> validateComboBoxField(ComboBoxField cbf){
		wrongNames = false;
		wrongSec = false;
		List<String> errors = new ArrayList<>();
		
		if(cbf.getName() == null || cbf.getName().isEmpty()) {
			errors.add("Nazwa nie może być pusta");
		}
		
		if(comboBoxConfigurationService.isNameInDatabase(cbf.getId(), cbf.getName())) {
			errors.add("Nazwa jest użyta w innym polu wyboru");
		}
		
		cbf.getOptions().stream().forEach(option -> {
			if(!wrongNames && (option.getValue() == null || option.getValue().isEmpty())) {
				wrongNames = true;
				errors.add("Wartości opcji nie mogą być puste");
			}
		});
		if(wrongNames) {
			return errors;
		}
		Set<String> values = new HashSet<>();
		cbf.getOptions().stream().forEach(option -> {
			values.add(option.getValue());
		});
		
		if(values.size() != cbf.getOptions().size()) {
			errors.add("Wartości opcji muszą być unikalne");
			return errors;
		}
		
		Set<Integer> sequences = new HashSet<>();
		cbf.getOptions().stream().forEach(option -> {
			if(option.getSec() < 1 ||option.getSec() > 999) {
				wrongSec = true;
				errors.add("Nieprawidłowy numer w sekwencji dla opcji o nazwie " + option.getValue() + "");
			}
			sequences.add(option.getSec());
		});
		
		if(!wrongSec && (sequences.size() != cbf.getOptions().size())) {
			errors.add("Wartości numerów w sekwencji muszą być unikalne");
		}
		
		if(errors.isEmpty()) {
			return null;
		}
		return errors;
	}
}
