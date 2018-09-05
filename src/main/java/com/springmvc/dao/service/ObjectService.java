package com.springmvc.dao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.data.model.ComboBoxPropValue;
import com.springmvc.data.model.PropValue;
import com.springmvc.data.model.TextBoxPropValue;

@Service
public class ObjectService {
	
	@Autowired
	private ComboBoxPropValueService comboBoxPropValueService; 
	
	@Autowired
	private TextBoxPropValueService textBoxPropValueService; 
	
//	@Autowired
//	private DateTextBoxPropValuePropService dateTextValuePropService; 
	
	public List<PropValue> getObjectValuesForStageId(short stageId){
		List<PropValue> propValuesList = new ArrayList<>();
		propValuesList.addAll(comboBoxPropValueService.getComboBoxPropValuesForStageId(stageId));
		propValuesList.addAll(textBoxPropValueService.getComboBoxPropValuesForStageId(stageId));
//		propValuesList.addAll(dateTextBoxPropValueService.getAllDateTextBoxProperties());
		
		propValuesList.sort((p1,p2) -> p1.getProperty().getSec() - p2.getProperty().getSec());
		return propValuesList;
	}
	
	public void saveValues(List<PropValue> values) {
		for(PropValue value: values) {
			switch (value.getType()) {
			case TEXT:
				textBoxPropValueService.saveTextBoxPropValue((TextBoxPropValue)value);
				break;
			case COMBO:
				comboBoxPropValueService.saveComboBoxPropValue((ComboBoxPropValue)value);
				break;
			case DATE:
		//		dateTextBoxPropValueService.saveDateTextBoxValue((DateTextBoxPropValue)value);
			}
		}
	}
}
