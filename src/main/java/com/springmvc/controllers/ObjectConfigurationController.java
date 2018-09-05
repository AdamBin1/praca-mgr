package com.springmvc.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.dao.service.ComboBoxConfigurationService;
import com.springmvc.dao.service.StageConfigurationService;

@Controller
public class ObjectConfigurationController {

	@Autowired
	StageConfigurationService stageConfigurationService;
	
	@Autowired
	ComboBoxConfigurationService comboBoxConfigurationService;
	
	@RequestMapping("/konfiguracja/obiekt")
	public ModelAndView showMainConfiguration() {
		
		Map<String, Object> model = new HashMap<>();
		
		model.put("mainstage", stageConfigurationService.getStageForId(0));
		model.put("comboboxes", comboBoxConfigurationService.getAllComboBoxFields());
		
		return new ModelAndView("configureobjectmain", model);
	}
}
