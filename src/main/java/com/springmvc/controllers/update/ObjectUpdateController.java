package com.springmvc.controllers.update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.model.IdPropIdType;
import com.springmvc.model.IdSecPair;
import com.springmvc.model.ObjectModel;
import com.springmvc.model.Stage;
import com.springmvc.service.JsonService;
import com.springmvc.service.ObjectService;
import com.springmvc.service.ResponseService;
import com.springmvc.service.StageService;
import com.springmvc.validation.ObjectValidator;
import com.springmvc.validation.StageValidator;

@Controller
public class ObjectUpdateController {

	@Autowired
	private StageService stageService;

	@Autowired
	private StageValidator stageValidator;

	@Autowired
	private ObjectValidator objectValidator;

	@Autowired
	private JsonService jsonService;

	@Autowired
	private ObjectService objectService;

	@Autowired
	private ResponseService responseService;

	@RequestMapping(value = "/modelowanie/zatwierdz", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> addObject(@RequestBody String jsonString) {

		System.out.println(jsonString);
		ObjectModel object = jsonService.convertJsonToObject(jsonString);
		List<String> errors = objectValidator.validateObject(object);

		if (errors == null) {
			objectService.setFirstStageAsActive(object);
			object = objectService.saveObjectAndPropValues(object);
			return responseService.createSuccessResponseEntity();
		}

		return responseService.createErrorResponseEntity(errors);
	}

	@RequestMapping(value = "/modelowanie/produkt/zatwierdz", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> saveObject(@RequestBody String jsonString) {

		System.out.println(jsonString);
		ObjectModel object = jsonService.convertJsonToObject(jsonString);
		List<String> errors = objectValidator.validateObject(object);

		if (errors == null) {
			object = objectService.saveObjectAndPropValues(object);
			List<IdPropIdType> idPropIdPairs = stageService.getIdPropIdPairList(object);
			return responseService.createSuccessResponseEntityForIdPropIdPairs(idPropIdPairs);
		}

		return responseService.createErrorResponseEntity(errors);
	}

	@RequestMapping(value = "/konfiguracja/zatwierdz", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> saveConfiguration(@RequestBody String jsonString) {

		System.out.println(jsonString);
		Stage mainStage = jsonService.convertJsonToMainStage(jsonString);
		List<String> errors = stageValidator.validateMainStage(mainStage);

		if (errors == null) {
			Stage savedStage = stageService.saveStage(mainStage);
			List<IdSecPair> idSecPairs = stageService.getIdSecPairList(savedStage);
			return responseService.createSuccessResponseEntityForIdSecPairs(idSecPairs);
		}

		return responseService.createErrorResponseEntity(errors);
	}

	@RequestMapping("/modelowanie/produkt/{id_obiektu}/przenies")
	public ModelAndView moveObjectToNextStage(@PathVariable("id_obiektu") int objectId) {

		Map<String, Object> model = new HashMap<>();

		ObjectModel object = objectService.moveToNextStage(objectId);
		if (object == null) {
			return new ModelAndView("resource-not-found", model);
		}

		String redirection = "redirect:/modelowanie/produkt/" + objectId + "/etap/" + object.getActiveStageId();

		return new ModelAndView(redirection, model);

	}

	@RequestMapping("/modelowanie/produkt/{id_obiektu}/otworz_aktywny")
	public ModelAndView showActiveStage(@PathVariable("id_obiektu") int objectId) {

		Map<String, Object> model = new HashMap<>();

		ObjectModel object = objectService.getObjectById(objectId);
		if (object == null) {
			return new ModelAndView("resource-not-found", model);
		}

		String redirection = "redirect:/modelowanie/produkt/" + objectId + "/etap/" + object.getActiveStageId();

		return new ModelAndView(redirection, model);

	}

	@RequestMapping(value = "/modelowanie/usun/{id_obiektu}", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> removeObject(@PathVariable("id_obiektu") int objectId) {

		objectService.remove(objectId);

		return responseService.createSuccessResponseEntity();

	}

}
