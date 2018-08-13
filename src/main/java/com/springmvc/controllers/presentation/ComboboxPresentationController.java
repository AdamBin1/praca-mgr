package com.springmvc.controllers.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.model.AllComboBoxesModel;

@Controller
public class ComboboxPresentationController {

	@RequestMapping("konfiguracja/pola_wyboru")
	public ModelAndView showComboboxes() {
		
	//	try
		
		AllComboBoxesModel cm = new AllComboBoxesModel();
		return new ModelAndView("showcomboboxes", cm.getModel());

	}
}
