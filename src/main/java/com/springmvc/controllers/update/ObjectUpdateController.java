package com.springmvc.controllers.update;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springmvc.dao.service.JsonService;
import com.springmvc.dao.service.StageConfigurationService;
import com.springmvc.data.model.ObjectModel;
import com.springmvc.data.model.Stage;

@Controller
public class ObjectUpdateController {
	
	@Autowired
	JsonService jsonService;
	
	@Autowired
	StageConfigurationService stageConfigurationService;
	
	@RequestMapping(value = "/modelowanie/zatwierdz", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> saveObject(@RequestBody String jsonString) {

		System.out.println(jsonString);
		ObjectModel object = jsonService.convertJsonToObject(jsonString);
		//TODO:  walidacja
		
	//	stageConfigurationService.saveStage(mainStage);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);

		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("jakis", "ten");
		JsonService.createJsonMessage(errorMap);
		
		

		return new ResponseEntity<String>(JsonService.createJsonMessage(errorMap), responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/konfiguracja/zatwierdz", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> saveConfiguration(@RequestBody String jsonString) {

		System.out.println(jsonString);
		Stage mainStage = jsonService.convertJsonToMainStage(jsonString);
		//TODO:  walidacja
		
		stageConfigurationService.saveStage(mainStage);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);

		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("jakis", "ten");
		JsonService.createJsonMessage(errorMap);
		
		

		return new ResponseEntity<String>(JsonService.createJsonMessage(errorMap), responseHeaders, HttpStatus.OK);
	}

}
