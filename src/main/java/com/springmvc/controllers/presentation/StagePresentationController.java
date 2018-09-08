package com.springmvc.controllers.presentation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.dao.service.StageConfigurationService;

@Controller
public class StagePresentationController {
	
	@Autowired
	StageConfigurationService stagesConfigurationService;
	
	@RequestMapping("/konfiguracja/etapy")
	public ModelAndView showStages() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("stages", stagesConfigurationService.getAllStages());
		
		return new ModelAndView("showstages", model);

	}
}
