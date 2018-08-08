package com.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/combobox")
public class ComboBoxController {

	@RequestMapping("/add")
	public ModelAndView addCombo() {
		return new ModelAndView("addcombo");
	}
	
	@RequestMapping("/create")
	public ModelAndView createCombo(@RequestBody String s) {
		System.out.println(s);
		
		
		return null;
	}
}
