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
		ObjectModel object = new ObjectModel();
		object.setValues(objectService.getNewObjectValuesForStageId((short)0));
		model.put("object", object);
		
		return new ModelAndView("addobject", model);

	}
	
	@RequestMapping("/obiekt/{id_obiektu}")
	public ModelAndView editMainObject(@PathVariable("id_obiektu") int id) {
		
		Map<String, Object> model = new HashMap<>();
		ObjectModel object = objectService.getObjectForStageId(id, (short)0);
		object.setValues(objectService.getObjectValuesForStageId(id, (short)0));
		model.put("object", object);
		
		return new ModelAndView("addobject", model);

	}
	
	@RequestMapping("/obiekt/{id_obiektu}/etap/{id_etapu}")
	public ModelAndView showObjectStage(@PathVariable("id_obiektu") int objectId, @PathVariable("id_etapu") int stageId) {
		
	//	try
		
		PageModel pm = new PageModel();
		return new ModelAndView("showobjectstage", pm.getModel());

	}

}
