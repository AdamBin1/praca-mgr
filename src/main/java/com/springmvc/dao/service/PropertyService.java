package com.springmvc.dao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.data.model.ComboBoxProp;
import com.springmvc.data.model.DateTextBoxProp;
import com.springmvc.data.model.Property;
import com.springmvc.data.model.TextBoxProp;

@Service
public class PropertyService {
	
	@Autowired
	private ComboBoxPropService comboBoxPropService; 
	
	@Autowired
	private TextBoxPropService textBoxPropService; 
	
	@Autowired
	private DateTextBoxPropService dateTextBoxPropService; 
	
	public List<Property> getAllProperties(){
		List<Property> propertyList = new ArrayList<>();
		propertyList.addAll(comboBoxPropService.getAllComboBoxProperties());
		propertyList.addAll(textBoxPropService.getAllTextBoxProperties());
		propertyList.addAll(dateTextBoxPropService.getAllDateTextBoxProperties());
		
		propertyList.sort((p1,p2) -> p1.getSec() - p2.getSec());
		return propertyList;
	}
	
	public void saveProperties(List<Property> properties) {
		for(Property prop : properties) {
			switch (prop.getType()) {
			case TEXT:
				textBoxPropService.saveTextBoxPropery((TextBoxProp)prop);
				break;
			case COMBO:
				comboBoxPropService.saveComboBoxProperty((ComboBoxProp)prop);
				break;
			case DATE:
				dateTextBoxPropService.saveDateTextBoxProperty((DateTextBoxProp)prop);
			}
		}
	}
}
