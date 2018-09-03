package com.springmvc.dao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springmvc.dao.DateTextBoxPropDAO;
import com.springmvc.dao.impl.DateTextBoxPropDaoImpl;
import com.springmvc.data.model.DateTextBoxProp;

@Service
public class DateTextBoxPropService {
	
	public DateTextBoxPropService() {
		
	}
		
	public List<DateTextBoxProp> getAllDateTextBoxProperties() {
		DateTextBoxPropDAO dateTextBoxPropDAO = new DateTextBoxPropDaoImpl();
		
		return dateTextBoxPropDAO.getAllDateTextBoxes();
	}

	public void saveDateTextBoxProperty(DateTextBoxProp dtbp) {
		DateTextBoxPropDAO dateTextBoxPropDAO = new DateTextBoxPropDaoImpl();
		
		dateTextBoxPropDAO.insertOrUpdateDateTextBoxProp(dtbp);
	}
}
