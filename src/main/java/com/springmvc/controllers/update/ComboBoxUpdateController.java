package com.springmvc.controllers.update;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
		
		return new ModelAndView("addcombo");
	}
	
	@RequestMapping("/save")
	public ModelAndView createCombo(@RequestBody String s) {
		System.out.println(s);
		
		
		return null;
	}
}
