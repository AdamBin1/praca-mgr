package com.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springmvc.dao.Person;

@Controller
public class ObjectUpdateController {

	  
	  @RequestMapping(value = "/obiekt/update/{id}" , method = RequestMethod.POST)
	  public @ResponseBody Person save(@RequestBody String jsonString) {
		  
		  System.out.println(jsonString);
	//     Person person=personService.savedata(jsonString);
	//     return person;
			return null;
	  }
	
}
