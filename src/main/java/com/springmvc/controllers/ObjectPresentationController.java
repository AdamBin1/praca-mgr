package com.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.model.PageModel;

@Controller
public class ObjectPresentationController {

	@RequestMapping("/obiekt/{id}")
	public ModelAndView showObject(@PathVariable("id") Integer id) {
		
	//	try
		
		PageModel pm = new PageModel();
		return new ModelAndView("showobject", pm.getModel());

	}
}
