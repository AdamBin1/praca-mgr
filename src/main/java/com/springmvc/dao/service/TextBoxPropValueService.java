package com.springmvc.dao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springmvc.dao.TextBoxPropValueDAO;
import com.springmvc.dao.impl.TextBoxPropValueDaoImpl;
import com.springmvc.data.model.TextBoxPropValue;

@Service
public class TextBoxPropValueService {
	
	public TextBoxPropValueService() {
		
	}
	
	public List<TextBoxPropValue> getComboBoxPropValuesForStageId(short stageId) {
		TextBoxPropValueDAO textBoxPropValueDAO = new TextBoxPropValueDaoImpl();
		
		return textBoxPropValueDAO.getTextBoxPropValuesForStageId(stageId);
	}

	public void saveTextBoxPropValue(TextBoxPropValue tbpv) {
		TextBoxPropValueDAO textBoxPropValueDAO = new TextBoxPropValueDaoImpl();
		
		textBoxPropValueDAO.insertOrUpdateTextBoxPropValue(tbpv);
	}

	public void saveComboBoxPropValue(TextBoxPropValue tbpv) {
		TextBoxPropValueDAO textBoxPropValueDAO = new TextBoxPropValueDaoImpl();
		
		textBoxPropValueDAO.insertOrUpdateTextBoxPropValue(tbpv);
	}
}
