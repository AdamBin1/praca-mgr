package com.springmvc.dao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springmvc.dao.ComboBoxPropValueDAO;
import com.springmvc.dao.impl.ComboBoxPropValueDaoImpl;
import com.springmvc.data.model.ComboBoxPropValue;

@Service
public class ComboBoxPropValueService {
	
	public ComboBoxPropValueService() {
		
	}
	
	public List<ComboBoxPropValue> getComboBoxPropValuesForStageId(int objectId, short stageId) {
		ComboBoxPropValueDAO comboBoxPropValueDAO = new ComboBoxPropValueDaoImpl();
		
		return comboBoxPropValueDAO.getComboBoxPropValuesForStageId(objectId, stageId);
	}

	public void saveComboBoxPropValue(ComboBoxPropValue cbpv) {
		ComboBoxPropValueDAO comboBoxPropValueDAO = new ComboBoxPropValueDaoImpl();
		
		comboBoxPropValueDAO.insertOrUpdateComboBoxPropValue(cbpv);
	}

	public List<ComboBoxPropValue> getNewComboBoxPropValuesForStageId(short stageId) {
		ComboBoxPropValueDAO comboBoxPropValueDAO = new ComboBoxPropValueDaoImpl();
		
		return null;//comboBoxPropValueDAO.getNewComboBoxPropValuesForStageId(stageId);
	}
}
