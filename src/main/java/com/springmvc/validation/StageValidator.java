package com.springmvc.validation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springmvc.dao.service.StageService;
import com.springmvc.model.ComboBoxProp;
import com.springmvc.model.FieldType;
import com.springmvc.model.Stage;
import com.springmvc.model.TextBoxProp;

@Component
public class StageValidator {

	@Autowired
	StageService stageConfigurationService;

	boolean wrongNames;
	boolean wrongSec;

	public StageValidator() {
	}

	public List<String> validateStage(Stage stage) {
		return validateStage(stage, false);
	}

	public List<String> validateMainStage(Stage stage) {
		return validateStage(stage, true);
	}

	private List<String> validateStage(Stage stage, boolean mainStage) {
		wrongNames = false;
		wrongSec = false;
		List<String> errors = new ArrayList<>();

		if (!mainStage) {
			if (stage.getName() == null || stage.getName().isEmpty()) {
				errors.add("Nazwa nie może być pusta");
			} else if (stage.getName().length() > 50) {
				errors.add("Nazwa etapu zbyt długa");
			}

			if (stageConfigurationService.isNameInDatabase(stage.getId(), stage.getName())) {
				errors.add("Nazwa etapu jest już użyta");
			}

		}

		if (!mainStage && (stage.getSec() == null || stage.getName().isEmpty())) {
			errors.add("Nieprawidłowy numer w sekwencji dla etapu");
		} else if (!mainStage && stageConfigurationService.isSecInDatabase(stage.getId(), stage.getSec())) {
			errors.add("Istnieje etap z takim numerem w sekwencji");
		}

		stage.getProperties().stream().forEach(property -> {
			if (property.getName() != null && property.getName().length() > 50) {
				errors.add("Nazwa właściwości jest zbyt długa: " + property.getName());
			}
		});

		stage.getProperties().stream().forEach(property -> {
			if (!wrongNames && (property.getName() == null || property.getName().isEmpty())) {
				wrongNames = true;
				errors.add("Nazwy właściwości nie mogą być puste");
			}
		});
		if (wrongNames) {
			return errors;
		}
		Set<String> values = new HashSet<>();
		stage.getProperties().stream().forEach(property -> {
			values.add(property.getName());
		});

		if (values.size() != stage.getProperties().size()) {
			errors.add("Nazwy właściwości muszą być unikalne");
			return errors;
		}

		stage.getProperties().stream().forEach(property -> {
			if (property.getType() == FieldType.TEXT) {
				TextBoxProp tbp = (TextBoxProp) property;
				if (tbp.getLength() < 1 || tbp.getLength() > 999) {
					errors.add("Nieprawidłowa długość dla właściwości o nazwie " + property.getName() + "");
				}
			}
		});

		stage.getProperties().stream().forEach(property -> {
			if (property.getType() == FieldType.COMBO) {
				ComboBoxProp cbp = (ComboBoxProp) property;
				if (cbp.getComboBoxField() == null) {
					errors.add("Wartości nie mogą być puste dla właściwości o nazwie " + property.getName() + "");
				}
			}
		});

		Set<Integer> sequences = new HashSet<>();
		stage.getProperties().stream().forEach(property -> {
			if (property.getSec() < 1 || property.getSec() > 999) {
				wrongSec = true;
				errors.add("Nieprawidłowy numer w sekwencji dla właściwości o nazwie " + property.getName() + "");
			}
			sequences.add(property.getSec());
		});

		if (!wrongSec && (sequences.size() != stage.getProperties().size())) {
			errors.add("Wartości numerów w sekwencji muszą być unikalne");
		}

		if (stage.getProperties().isEmpty()) {
			if (mainStage) {
				errors.add("Obiekt musi mieć przynajmniej jedną właściwość");
			} else {
				errors.add("Etap nie może być pusty");
			}
		}

		if (errors.isEmpty()) {
			return null;
		}
		return errors;
	}
}
