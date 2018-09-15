package com.springmvc.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.dao.ComboBoxPropValueDAO;
import com.springmvc.model.ComboBoxPropValue;
import com.springmvc.model.PropValue;

@Service
public class ComboBoxPropValueService {

	@Autowired
	private ComboBoxPropValueDAO comboBoxPropValueDAO;

	public ComboBoxPropValueService() {
	}

	public ComboBoxPropValue save(ComboBoxPropValue cbp) {
		return comboBoxPropValueDAO.save(cbp);
	}

	public Iterable<ComboBoxPropValue> saveAll(Iterable<ComboBoxPropValue> comboBoxPropValues) {
		return comboBoxPropValueDAO.saveAll(comboBoxPropValues);
	}

	public PropValue findByObjectIdAndPropId(int objectId, Integer propId) {
		return comboBoxPropValueDAO.findByObjectIdAndPropId(objectId, propId);
	}

	public void deleteByObjectId(Integer objectId) {
		comboBoxPropValueDAO.deleteByObjectId(objectId);
	}
}
