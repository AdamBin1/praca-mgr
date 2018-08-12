package com.springmvc.error;

import org.springframework.web.servlet.ModelAndView;

public class BadRequestModelAndView {

	public BadRequestModelAndView() {
	}

	public static ModelAndView getBadRequestModelAndView() {
		
		ModelAndView mav = new ModelAndView("badrequest", "msg", "Błędny adres");
		
		return mav;
	}
}
