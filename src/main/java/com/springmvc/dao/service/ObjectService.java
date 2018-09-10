package com.springmvc.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.dao.ObjectDAO;
import com.springmvc.data.model.ObjectModel;

@Service
public class ObjectService {
	
	@Autowired
	private ComboBoxPropValueService comboBoxPropValueService; 
	
	@Autowired
	private TextBoxPropValueService textBoxPropValueService; 
	
	@Autowired
	private DateTextBoxPropValueService dateTextBoxPropValueService;
	
	@Autowired
	ObjectDAO objectDao;
	
	public ObjectModel saveObjectAndPropValues(ObjectModel object) {
		ObjectModel savedObject = objectDao.save(object);
		
		object.getTextBoxPropValues().forEach(tbpv -> {
			tbpv.setObjectId(savedObject.getId());
		});
		
		savedObject.setTextBoxPropValues(textBoxPropValueService.saveAll(object.getTextBoxPropValues()));
		
		object.getComboBoxPropValues().forEach(cbp -> {
			cbp.setObjectId(savedObject.getId());
		});
		
		savedObject.setComboBoxPropValues(comboBoxPropValueService.saveAll(object.getComboBoxPropValues()));
		
		object.getDateTextBoxPropValues().forEach(dtbpv -> {
			dtbpv.setObjectId(savedObject.getId());
		});
		
		savedObject.setDateTextBoxPropValues(dateTextBoxPropValueService.saveAll(object.getDateTextBoxPropValues()));
		
		return savedObject;
	}

	public ObjectModel getObjectById(Integer objectId) {
		return objectDao.findById(objectId);
	}

}
