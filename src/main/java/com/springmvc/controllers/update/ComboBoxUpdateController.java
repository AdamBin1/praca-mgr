package com.springmvc.controllers.update;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.dao.service.ComboBoxConfigurationService;
import com.springmvc.dao.service.JsonService;
import com.springmvc.dao.service.ResponseService;
import com.springmvc.data.model.ComboBoxField;
import com.springmvc.validation.ComboBoxValidator;

@Controller
@RequestMapping("/konfiguracja/pola_wyboru")
public class ComboBoxUpdateController {

	@Autowired
	private ComboBoxConfigurationService comboBoxConfigurationService;
	
	@Autowired
	private ComboBoxValidator comboBoxValidator;
	
	@Autowired
	private JsonService jsonService;
	
	@Autowired
	private ResponseService responseService;
	
	
	// DOKOŃCZYĆ/ZMIENIĆ
	
	@RequestMapping("/dodaj")
	public ModelAndView addCombo() {
		
		return new ModelAndView("addcombo");
	}
	
	@RequestMapping("/edytuj/{id}")
	public ModelAndView editCombo(@PathVariable("id") int id) {
		
		HashMap<String, Object> model = new HashMap<String, Object>();
		ComboBoxField cbf = comboBoxConfigurationService.getComboBoxFieldForId(id);
		if(cbf == null) {
			return new ModelAndView("resource-not-found");
		} else {
			model.put("combobox", cbf);
		}

		return new ModelAndView("addcombo", model);
	}
	
	@RequestMapping(value = {"edytuj/zatwierdz", "/zatwierdz"}, method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> save(@RequestBody String jsonString) {
		System.out.println(jsonString);
		ComboBoxField cbf = jsonService.convertJsonToComboBoxField(jsonString);
		
		List<String> errors = comboBoxValidator.validateComboBoxField(cbf);
		if(errors == null) {
			comboBoxConfigurationService.saveComboBoxField(cbf);
			//TODO walidacja zapisu i podmiana errors
		}
		return responseService.createResponseEntity(errors);
	}
}