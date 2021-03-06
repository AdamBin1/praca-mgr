package com.springmvc.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.service.ComboBoxConfigurationService;
import com.springmvc.service.StageService;

@Controller
public class ObjectConfigurationController {

	@Autowired
	private StageService stageConfigurationService;

	@Autowired
	private ComboBoxConfigurationService comboBoxConfigurationService;

	@RequestMapping("/konfiguracja/produkt")
	public ModelAndView showMainConfiguration() {

		Map<String, Object> model = new HashMap<>();

		model.put("mainstage", stageConfigurationService.getMainStage());
		model.put("comboboxes", comboBoxConfigurationService.getAllComboBoxFields());

		return new ModelAndView("configureobjectmain", model);
	}
}
