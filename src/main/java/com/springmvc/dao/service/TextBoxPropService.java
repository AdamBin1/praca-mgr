package com.springmvc.dao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springmvc.dao.TextBoxPropDAO;
import com.springmvc.dao.impl.TextBoxPropDaoImpl;
import com.springmvc.data.model.TextBoxProp;

@Service
public class TextBoxPropService {
	
	public TextBoxPropService() {
		
	}
		
	public List<TextBoxProp> getAllTextBoxProperties() {
		TextBoxPropDAO textBoxPropDAO = new TextBoxPropDaoImpl();
		
		return textBoxPropDAO.getAllTextBoxes();
	}
	
	public TextBoxProp getTextBoxPropertyForId(int id) {
		TextBoxPropDAO textBoxPropDAO = new TextBoxPropDaoImpl();
		
		return textBoxPropDAO.getTextBoxPropertyForId(id);
	}

	
	public void saveTextBoxPropery(TextBoxProp tbp) {
		TextBoxPropDAO textBoxPropDAO = new TextBoxPropDaoImpl();
		
		textBoxPropDAO.insertOrUpdateTextBoxProp(tbp);
	}

}
