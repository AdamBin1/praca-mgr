package com.springmvc.controllers.update;

import java.util.HashMap;
import java.util.List;
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
import com.springmvc.dao.service.PropertyService;
import com.springmvc.data.model.Property;

@Controller
public class ObjectUpdateController {
	
	@Autowired
	JsonService jsonService;
	
	@Autowired
	PropertyService propertyService;

	@RequestMapping(value = "/konfiguracja/update", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> save(@RequestBody String jsonString) {

		System.out.println(jsonString);
		List<Property> propList = jsonService.convertJsonToProperties(jsonString);
		//TODO:  walidacja
		
		propertyService.saveProperties(propList);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);

		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("jakis", "ten");
		errorMap.put("jakis2", "tamten");
		JsonService.createJsonMessage(errorMap);
		
		

		return new ResponseEntity<String>(JsonService.createJsonMessage(errorMap), responseHeaders, HttpStatus.OK);
	}

}
