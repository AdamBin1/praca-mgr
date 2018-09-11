package com.springmvc.controllers.presentation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.dao.service.ObjectService;
import com.springmvc.dao.service.StageService;
import com.springmvc.data.model.IdValuePair;
import com.springmvc.data.model.ObjectModel;
import com.springmvc.data.model.Stage;

@Controller
@RequestMapping("modelowanie")
public class ObjectPresentationController {

	@Autowired
	ObjectService objectService;
	
	@Autowired
	StageService stageService;
	
	
	@RequestMapping("/dodaj")
	public ModelAndView addMainObject() {
		
		Map<String, Object> model = new HashMap<>();
		
		model.put("stage", stageService.getMainStage());
		
		return new ModelAndView("addobject", model);

	}
	
	@RequestMapping("/obiekt/{id_obiektu}")
	public ModelAndView editMainObject(@PathVariable("id_obiektu") int objectId) {
		
		Map<String, Object> model = new HashMap<>();
		
		ObjectModel object = objectService.getObjectById(objectId);
		if(object == null) {
			return new ModelAndView("resource-not-found", model);
		}
		
		Stage stage = stageService.getMainStage();
		stageService.updateValues(stage, objectId);
		model.put("stage", stage);
		model.put("object", object);
		
		return new ModelAndView("addobject", model);
		
	}
	
	@RequestMapping("/obiekt/{id_obiektu}/etap/{id_etapu}")
	public ModelAndView showObjectStage(@PathVariable("id_obiektu") int objectId, @PathVariable("id_etapu") int stageId) {
		
		Map<String, Object> model = new HashMap<>();
		
		// żeby nie pozwolić na otwieranie main stage do edycji jako etap
//		if(stageId == 1) {
//			return new ModelAndView("resource-not-found", model);
//		}
		
		ObjectModel object = objectService.getObjectById(objectId);
		if(object == null) {
			return new ModelAndView("resource-not-found", model);
		}
		
		Stage mainStage = stageService.getMainStage();
		stageService.updateValues(mainStage, objectId);
		model.put("mainStage", mainStage);
		
		Stage stage = stageService.getStageForId(stageId);
		stageService.updateValues(stage, objectId);
		
		model.put("stage", stage);
		model.put("object", object);
		
		List<IdValuePair> idValuePairs = stageService.getIdAndNamesForAllStages();
		model.put("stagenames", idValuePairs);
		
		return new ModelAndView("showobjectstage", model);

	}

}
