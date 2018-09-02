package com.springmvc.controllers.update;

import java.util.HashMap;

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
import com.springmvc.data.model.ComboBoxField;

@Controller
@RequestMapping("/konfiguracja/pola_wyboru")
public class ComboBoxUpdateController {

	@Autowired
	private ComboBoxConfigurationService comboBoxConfigurationService;
	
	// DOKOŃCZYĆ/ZMIENIĆ
	
	@RequestMapping("/dodaj")
	public ModelAndView addCombo() {
		
		return new ModelAndView("addcombo");
	}
	
	@RequestMapping("/edytuj/{nazwa}")
	public ModelAndView editCombo(@PathVariable("nazwa") String name) {
		
		HashMap<String, Object> model = new HashMap<String, Object>();
		ComboBoxField cbf = comboBoxConfigurationService.getComboBoxFieldForName(name);
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
		ComboBoxField cbf = JsonService.convertJsonToComboBoxField(jsonString);
		
		//TODO: walidacja
		comboBoxConfigurationService.saveComboBoxField(cbf);
		
		return null;
	}
}