package com.springmvc.controllers.update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springmvc.dao.Property;
import com.springmvc.dao.service.JsonService;
import com.springmvc.dao.service.PropertyService;
import com.springmvc.model.PageModel;

@Controller
public class ObjectUpdateController {

	@RequestMapping(value = "/konfiguracja/update", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> save(@RequestBody String jsonString) {

		PageModel pm = new PageModel();
		PropertyService ps = new PropertyService();
		ps.updateFromJson((List<Property>) pm.getModel().get("properties"), jsonString);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);

		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("jakis", "ten");
		errorMap.put("jakis2", "tamten");
		JsonService.createJsonMessage(errorMap);

		return new ResponseEntity<String>(JsonService.createJsonMessage(errorMap), responseHeaders, HttpStatus.OK);
	}

}
