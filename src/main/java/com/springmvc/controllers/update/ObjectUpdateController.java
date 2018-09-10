package com.springmvc.controllers.update;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springmvc.dao.service.JsonService;
import com.springmvc.dao.service.ObjectService;
import com.springmvc.dao.service.ResponseService;
import com.springmvc.dao.service.StageService;
import com.springmvc.data.model.IdPropIdType;
import com.springmvc.data.model.IdSecPair;
import com.springmvc.data.model.ObjectModel;
import com.springmvc.data.model.Stage;
import com.springmvc.validation.ObjectValidator;
import com.springmvc.validation.StageValidator;

@Controller
public class ObjectUpdateController {
	
	@Autowired
	StageService stageService;

	@Autowired
	StageValidator stageValidator;
	
	@Autowired
	ObjectValidator objectValidator;
	
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
		
		if(errors == null) {
			object = objectService.saveObjectAndPropValues(object);
			return responseService.createSuccessResponseEntity();
		}

		return responseService.createErrorResponseEntity(errors);
	}
	
	@RequestMapping(value = "/modelowanie/obiekt/zatwierdz", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> saveObject(@RequestBody String jsonString) {

		System.out.println(jsonString);
		ObjectModel object = jsonService.convertJsonToObject(jsonString);
		List<String> errors = objectValidator.validateObject(object);
		
		if(errors == null) {
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

		if(errors == null) {
			Stage savedStage = stageService.saveStage(mainStage);
			//TODO walidacja zapisu i podmiana errors
			List<IdSecPair> idSecPairs = stageService.getIdSecPairList(savedStage);
			return responseService.createSuccessResponseEntityForIdSecPairs(idSecPairs);
		}
		
		return responseService.createErrorResponseEntity(errors);
	}

}
