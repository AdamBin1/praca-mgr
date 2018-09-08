package com.springmvc.dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.data.model.ObjectModel;
import com.springmvc.data.model.PropValue;

@Service
public class ObjectService {
	
	@Autowired
	private ComboBoxPropValueService comboBoxPropValueService; 
	
	@Autowired
	private TextBoxPropValueService textBoxPropValueService; 
	
	@Autowired
	private DateTextBoxPropValueService dateTextBoxPropValueService; 
	
	public List<PropValue> getObjectValuesForStageId(int objectId, short stageId){
//		List<PropValue> propValuesList = new ArrayList<>();
//		propValuesList.addAll(comboBoxPropValueService.getComboBoxPropValuesForStageId(objectId, stageId));
//		propValuesList.addAll(textBoxPropValueService.getComboBoxPropValuesForStageId(objectId, stageId));
//		propValuesList.addAll(dateTextBoxPropValueService.getDateTextBoxPropValuesForStageId(objectId, stageId));
//		
//		propValuesList.sort((p1,p2) -> p1.getProperty().getSec() - p2.getProperty().getSec());
//		return propValuesList;
		return null;
	}
	
	public ObjectModel getObjectForStageId(int objectId, short stageId) {
		ObjectModel model = new ObjectModel();
		model.setId(objectId);
		model.setValues(getObjectValuesForStageId(objectId, stageId));
		return model;
	}
	
	public List<PropValue> getNewObjectValuesForStageId(short stageId) {
//		List<PropValue> propValuesList = new ArrayList<>();
//		propValuesList.addAll(comboBoxPropValueService.getNewComboBoxPropValuesForStageId(stageId));
//		propValuesList.addAll(textBoxPropValueService.getNewComboBoxPropValuesForStageId(stageId));
//		propValuesList.addAll(dateTextBoxPropValueService.getNewDateTextBoxPropValuesForStageId(stageId));
//		
//		propValuesList.sort((p1,p2) -> p1.getProperty().getSec() - p2.getProperty().getSec());
//		return propValuesList;
		return null;
	}
	
	public void saveValues(List<PropValue> values) {
//		for(PropValue value: values) {
//			switch (value.getType()) {
//			case TEXT:
//				textBoxPropValueService.saveTextBoxPropValue((TextBoxPropValue)value);
//				break;
//			case COMBO:
//				comboBoxPropValueService.saveComboBoxPropValue((ComboBoxPropValue)value);
//				break;
//			case DATE:
//				dateTextBoxPropValueService.saveDateTextBoxPropValue((DateTextBoxPropValue)value);
//			}
//		}
	}

}
