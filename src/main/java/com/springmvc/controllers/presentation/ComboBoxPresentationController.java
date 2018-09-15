package com.springmvc.controllers.presentation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.dao.service.ComboBoxConfigurationService;

@Controller
@RequestMapping("konfiguracja")
public class ComboBoxPresentationController {

	@Autowired
	private ComboBoxConfigurationService comboBoxConfigurationService;

	@RequestMapping("/pola_wyboru")
	public ModelAndView showComboboxes() {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("comboboxes", comboBoxConfigurationService.getAllComboBoxFields());

		return new ModelAndView("showcomboboxes", model);

	}
}
