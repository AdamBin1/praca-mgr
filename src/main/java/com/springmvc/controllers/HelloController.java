package com.springmvc.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@RequestMapping("/hello")
	public ModelAndView mymethod() {
		Map<String, String> model = new HashMap<String, String>();
		model.put("msg", "Hello z mapy ąćś");
		return new ModelAndView("hellopage", model);

	}
}
