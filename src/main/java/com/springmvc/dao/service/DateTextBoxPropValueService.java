package com.springmvc.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.dao.DateTextBoxPropValueDAO;
import com.springmvc.data.model.DateTextBoxPropValue;
import com.springmvc.data.model.PropValue;

@Service
public class DateTextBoxPropValueService {

	@Autowired
	DateTextBoxPropValueDAO dateTextBoxPropValueDAO;
	
	public DateTextBoxPropValue save(DateTextBoxPropValue dtbpv) {
		return dateTextBoxPropValueDAO.save(dtbpv);
	}

	public Iterable<DateTextBoxPropValue> saveAll(Iterable<DateTextBoxPropValue> dateTextBoxPropValues) {
		return dateTextBoxPropValueDAO.saveAll(dateTextBoxPropValues);
	}

	public PropValue findByObjectIdAndPropId(int objectId, Integer propId) {
		return dateTextBoxPropValueDAO.findByObjectIdAndPropId(objectId, propId);
	}

	public void deleteByObjectId(Integer objectId) {
		dateTextBoxPropValueDAO.deleteByObjectId(objectId);
	}
	
}