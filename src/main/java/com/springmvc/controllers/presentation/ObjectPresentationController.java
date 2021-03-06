package com.springmvc.controllers.presentation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.model.IdValuePair;
import com.springmvc.model.ObjectModel;
import com.springmvc.model.Stage;
import com.springmvc.service.ObjectService;
import com.springmvc.service.StageService;

@Controller
@RequestMapping("modelowanie")
public class ObjectPresentationController {

	@Autowired
	private ObjectService objectService;

	@Autowired
	private StageService stageService;

	@RequestMapping("/produkty")
	public ModelAndView showAllObjects() {

		Map<String, Object> model = new HashMap<>();

		Stage stage = stageService.getMainStage();

		List<Stage> allMainStages = stageService.getMainStageForAllObjects();

		model.put("stage", stage);
		model.put("allMainStages", allMainStages);

		return new ModelAndView("showallobjects", model);

	}

	@RequestMapping("/dodaj")
	public ModelAndView addMainObject() {

		Map<String, Object> model = new HashMap<>();

		model.put("stage", stageService.getMainStage());

		return new ModelAndView("addobject", model);

	}

	@RequestMapping("/produkt/{id_obiektu}")
	public ModelAndView editMainObject(@PathVariable("id_obiektu") int objectId) {

		Map<String, Object> model = new HashMap<>();

		ObjectModel object = objectService.getObjectById(objectId);
		if (object == null) {
			return new ModelAndView("resource-not-found", model);
		}

		Stage stage = stageService.getMainStage();
		stageService.updateValues(stage, objectId);
		model.put("stage", stage);
		model.put("object", object);

		return new ModelAndView("addobject", model);

	}

	@RequestMapping("/produkt/{id_obiektu}/etap/{id_etapu}")
	public ModelAndView showObjectStage(@PathVariable("id_obiektu") int objectId,
			@PathVariable("id_etapu") int stageId) {

		Map<String, Object> model = new HashMap<>();

		Stage stage = stageService.getStageForId(stageId);

		// żeby nie pozwolić na otwieranie main stage do edycji jako etap
		// if(stage.isMainStage()) {
		// return new ModelAndView("resource-not-found", model);
		// }
		stageService.updateValues(stage, objectId);

		ObjectModel object = objectService.getObjectById(objectId);
		if (object == null) {
			return new ModelAndView("resource-not-found", model);
		}

		Stage mainStage = stageService.getMainStage();
		stageService.updateValues(mainStage, objectId);
		model.put("mainStage", mainStage);

		model.put("stage", stage);
		model.put("object", object);

		List<IdValuePair> idValuePairs = stageService.getIdAndNamesForAllStages();
		model.put("stagenames", idValuePairs);

		return new ModelAndView("showobjectstage", model);

	}

}
