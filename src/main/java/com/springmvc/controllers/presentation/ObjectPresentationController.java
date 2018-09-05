package com.springmvc.controllers.presentation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.dao.service.ObjectService;
import com.springmvc.data.model.ObjectModel;
import com.springmvc.model.PageModel;

@Controller
@RequestMapping("modelowanie")
public class ObjectPresentationController {

	@Autowired
	ObjectService objectService;
	
	
	@RequestMapping("/dodaj")
	public ModelAndView addMainObject() {
		
		Map<String, Object> model = new HashMap<>();
		ObjectModel object = new ObjectModel(null, objectService.getObjectValuesForStageId((short)0));
		model.put("object", object);
		
		return new ModelAndView("addobject", model);

	}
	
	@RequestMapping("/obiekt/{nazwa_obiektu}")
	public ModelAndView editMainObject(@PathVariable("nazwa_obiektu") String objectName) {
		
	//	try
		
		PageModel pm = new PageModel();
		return new ModelAndView("addobject", pm.getModel());

	}
	
	@RequestMapping("/obiekt/{nazwa_obiektu}/etap/{nazwa_etapu}")
	public ModelAndView showObjectStage(@PathVariable("nazwa_obiektu") String objectName, @PathVariable("nazwa_etapu") String stageName) {
		
	//	try
		
		PageModel pm = new PageModel();
		return new ModelAndView("showobjectstage", pm.getModel());

	}
	
	@RequestMapping("/obiekt/{nazwa_obiektu}/etap/{nazwa_etapu}/edytuj")
	public ModelAndView editObjectStage(@PathVariable("nazwa_obiektu") String objectName, @PathVariable("nazwa_etapu") String stageName) {
		
	//	try
		
		PageModel pm = new PageModel();
		return new ModelAndView("showobjectstage", pm.getModel());

	}
}
