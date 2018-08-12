package com.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.model.PageModel;

@Controller
public class ObjectConfigurationController {

	@RequestMapping("/konfiguracja")
	public ModelAndView showMainConfiguration() {
		
	//	try
		
		PageModel pm = new PageModel();
		return new ModelAndView("configureobjectmain", pm.getModel());

	}
}
