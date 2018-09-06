package com.springmvc.validation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.springmvc.data.model.FieldType;
import com.springmvc.data.model.Stage;
import com.springmvc.data.model.TextBoxProp;

@Component
public class StageValidator {
	
	boolean wrongNames;
	boolean wrongSec;
	
	public StageValidator() {
	}
	
	public List<String> validateStage(Stage stage){
		return validateStage(stage, false);
	}
	
	public List<String> validateMainStage(Stage stage){
		return validateStage(stage, true);
	}
	
	private List<String> validateStage(Stage stage, boolean mainStage){
		wrongNames = false;
		wrongSec = false;
		List<String> errors = new ArrayList<>();
		
		if(!mainStage) {
			if(stage.getName() == null || stage.getName().isEmpty()) {
				errors.add("Nazwa nie może być pusta");
			} else if (stage.getName().length()>50) {
				errors.add("Nazwa etapu zbyt długa");
			}
		}
		
		if(!mainStage && (stage.getSec() == null || stage.getName().isEmpty())) {
			errors.add("Nieprawidłowy numer w sekwencji dla etapu");
		}
		
		stage.getProperties().stream().forEach(property -> {
			if(property.getName() != null && property.getName().length() > 50) {
				errors.add("Nazwa właściwości jest zbyt długa: " + property.getName());
			}
		});
		
		stage.getProperties().stream().forEach(property -> {
			if(!wrongNames && (property.getName() == null || property.getName().isEmpty())) {
				wrongNames = true;
				errors.add("Nazwy właściwości nie mogą być puste");
			}
		});
		if(wrongNames) {
			return errors;
		}
		Set<String> values = new HashSet<>();
		stage.getProperties().stream().forEach(property -> {
			values.add(property.getName());
		});
		
		if(values.size() != stage.getProperties().size()) {
			errors.add("Nazwy właściwości muszą być unikalne");
			return errors;
		}
		
		stage.getProperties().stream().forEach(property -> {
			if(property.getType() == FieldType.TEXT) {
				TextBoxProp tbp = (TextBoxProp) property;
				if(tbp.getLength() < 1 || tbp.getLength() > 999) {
					errors.add("Nieprawidłowa długość dla właściwości o nazwie " + property.getName() + "");
				}			
			}
		});		
		
		Set<Integer> sequences = new HashSet<>();
		stage.getProperties().stream().forEach(property -> {
			if(property.getSec() < 1 ||property.getSec() > 999) {
				wrongSec = true;
				errors.add("Nieprawidłowy numer w sekwencji dla właściwości o nazwie " + property.getName() + "");
			}
			sequences.add(property.getSec());
		});
		
		if(!wrongSec && (sequences.size() != stage.getProperties().size())) {
			errors.add("Wartości numerów w sekwencji muszą być unikalne");
		}
		
		if(errors.isEmpty()) {
			return null;
		}
		return errors;
	}
}
