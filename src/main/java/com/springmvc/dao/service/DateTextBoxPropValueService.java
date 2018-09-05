package com.springmvc.dao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springmvc.dao.DateTextBoxPropDAO;
import com.springmvc.dao.DateTextBoxPropValueDAO;
import com.springmvc.dao.impl.DateTextBoxPropDaoImpl;
import com.springmvc.dao.impl.DateTextBoxPropValueDaoImpl;
import com.springmvc.data.model.DateTextBoxProp;
import com.springmvc.data.model.DateTextBoxPropValue;

@Service
public class DateTextBoxPropValueService {
	
	public DateTextBoxPropValueService() {
		
	}
	
	public List<DateTextBoxPropValue> getDateTextBoxPropValuesForStageId(int objectId, short stageId) {
		DateTextBoxPropValueDAO dateTextBoxPropValueDAO = new DateTextBoxPropValueDaoImpl();
		
		return dateTextBoxPropValueDAO.getDateTextBoxPropValuesForStageId(objectId, stageId);
	}
	
	public DateTextBoxProp getDateTextBoxPropertyForId(int id) {
		DateTextBoxPropDAO dateTextBoxPropDAO = new DateTextBoxPropDaoImpl();
		
		return dateTextBoxPropDAO.getDateTextBoxPropertyForId(id);
	}

	public void saveDateTextBoxPropValue(DateTextBoxPropValue dtbpv) {
		DateTextBoxPropValueDAO dateTextBoxPropValueDAO = new DateTextBoxPropValueDaoImpl();
		
		dateTextBoxPropValueDAO.insertOrUpdateDateTextBoxPropValue(dtbpv);
	}

	public List<DateTextBoxPropValue> getNewDateTextBoxPropValuesForStageId(short stageId) {
		DateTextBoxPropValueDAO dateTextBoxPropValueDAO = new DateTextBoxPropValueDaoImpl();
		
		return dateTextBoxPropValueDAO.getNewDateTextBoxPropValuesForStageId(stageId);
	}
}
