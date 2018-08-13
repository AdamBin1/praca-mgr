package com.springmvc.controllers.update;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.dao.ComboBoxField;
import com.springmvc.dao.service.JsonService;
import com.springmvc.model.ComboBoxModel;

@Controller
@RequestMapping("/konfiguracja/pola_wyboru")
public class ComboBoxUpdateController {

	
	// DOKOŃCZYĆ/ZMIENIĆ
	
	@RequestMapping("/dodaj")
	public ModelAndView addCombo() {
		
		return new ModelAndView("addcombo");
	}
	
	@RequestMapping("/edytuj/{nazwa}")
	public ModelAndView editCombo(@PathVariable("nazwa") String name) {
		ComboBoxModel cbm = new ComboBoxModel(name);
		return new ModelAndView("addcombo", cbm.getModel());
	}
	
	@RequestMapping(value = {"edytuj/zatwierdz", "/zatwierdz"}, method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> save(@RequestBody String jsonString) {
		System.out.println(jsonString);
		ComboBoxField cbf = JsonService.convertJsonToComboBoxField(jsonString);
		
		return null;
	}
}