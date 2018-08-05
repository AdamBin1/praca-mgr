package com.springmvc.error;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.springmvc.dao.Property;
import com.springmvc.dao.TextBox;

public class BadRequestModelAndView {

	public BadRequestModelAndView() {
	}

	public static ModelAndView getBadRequestModelAndView() {
		
		ModelAndView mav = new ModelAndView("badrequest", "msg", "Błędny adres");
		
		return mav;
	}
}
