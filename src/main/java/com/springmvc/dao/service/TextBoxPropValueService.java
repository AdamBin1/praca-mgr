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
	
	public List<TextBoxPropValue> getComboBoxPropValuesForStageId(int objectId, short stageId) {
		TextBoxPropValueDAO textBoxPropValueDAO = new TextBoxPropValueDaoImpl();
		
		return textBoxPropValueDAO.getTextBoxPropValuesForStageId(objectId, stageId);
	}

	public void saveTextBoxPropValue(TextBoxPropValue tbpv) {
		TextBoxPropValueDAO textBoxPropValueDAO = new TextBoxPropValueDaoImpl();
		
		textBoxPropValueDAO.insertOrUpdateTextBoxPropValue(tbpv);
	}

	public List<TextBoxPropValue> getNewComboBoxPropValuesForStageId(short stageId) {
		TextBoxPropValueDAO textBoxPropValueDAO = new TextBoxPropValueDaoImpl();
		
		return textBoxPropValueDAO.getNewTextBoxPropValuesForStageId(stageId);
	}
}
