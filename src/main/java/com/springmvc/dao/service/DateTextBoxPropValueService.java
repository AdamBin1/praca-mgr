package com.springmvc.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.dao.DateTextBoxPropValueDAO;
import com.springmvc.model.DateTextBoxPropValue;
import com.springmvc.model.PropValue;

@Service
public class DateTextBoxPropValueService {

	@Autowired
	DateTextBoxPropValueDAO dateTextBoxPropValueDAO;

	public DateTextBoxPropValue save(DateTextBoxPropValue dtbpv) {
		return dateTextBoxPropValueDAO.save(dtbpv);
	}

	public Iterable<DateTextBoxPropValue> saveAll(Iterable<DateTextBoxPropValue> dateTextBoxPropValues) {
		dateTextBoxPropValues.forEach(a -> {
			System.out.println(a.getValue().toString());
		});
		Iterable<DateTextBoxPropValue> daty =  dateTextBoxPropValueDAO.saveAll(dateTextBoxPropValues);
		daty.forEach(a -> {
			System.out.println(a.getValue().toString());
		});
		return daty;
	}

	public PropValue findByObjectIdAndPropId(int objectId, Integer propId) {
		return dateTextBoxPropValueDAO.findByObjectIdAndPropId(objectId, propId);
	}

	public void deleteByObjectId(Integer objectId) {
		dateTextBoxPropValueDAO.deleteByObjectId(objectId);
	}

}