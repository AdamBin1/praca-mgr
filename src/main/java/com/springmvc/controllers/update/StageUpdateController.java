package com.springmvc.controllers.update;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.dao.service.ComboBoxConfigurationService;
import com.springmvc.dao.service.JsonService;
import com.springmvc.dao.service.StageConfigurationService;
import com.springmvc.data.model.Stage;

@Controller
@RequestMapping("/konfiguracja/etapy")
public class StageUpdateController {

	@Autowired
	private StageConfigurationService stageConfigurationService;
	
	@Autowired
	ComboBoxConfigurationService comboBoxConfigurationService;
	
	// DOKOŃCZYĆ/ZMIENIĆ
	
	@RequestMapping("/dodaj")
	public ModelAndView add() {
		
		HashMap<String, Object> model = new HashMap<String, Object>();
		model.put("comboboxes", comboBoxConfigurationService.getAllComboBoxFields());
		return new ModelAndView("addstage", model);
	}
	
	@RequestMapping("/edytuj/{id}")
	public ModelAndView edit(@PathVariable("id") int id) {
		
		HashMap<String, Object> model = new HashMap<String, Object>();
		Stage stage = stageConfigurationService.getStageForId(id);
		if(stage == null) {
			return new ModelAndView("resource-not-found");
		} else {
			model.put("stage", stage);
		}
		model.put("comboboxes", comboBoxConfigurationService.getAllComboBoxFields());
		
		return new ModelAndView("addstage", model);
	}
	
	@RequestMapping(value = {"edytuj/zatwierdz", "/zatwierdz"}, method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> save(@RequestBody String jsonString) {
		System.out.println(jsonString);
		Stage stage = JsonService.convertJsonToStage(jsonString);
		
		//TODO: walidacja
		stageConfigurationService.saveStage(stage);
		
		return null;
		
	}
}