package com.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.dao.TextBoxPropValueDAO;
import com.springmvc.model.PropValue;
import com.springmvc.model.TextBoxPropValue;

@Service
public class TextBoxPropValueService {

	@Autowired
	private TextBoxPropValueDAO textBoxPropValueDAO;

	public TextBoxPropValueService() {
	}

	public TextBoxPropValue save(TextBoxPropValue tbpv) {
		return textBoxPropValueDAO.save(tbpv);
	}

	public void deleteByObjectId(Integer objectId) {
		textBoxPropValueDAO.deleteByObjectId(objectId);
	}

	public Iterable<TextBoxPropValue> saveAll(Iterable<TextBoxPropValue> textBoxPropValues) {
		return textBoxPropValueDAO.saveAll(textBoxPropValues);
	}

	public PropValue findByObjectIdAndPropId(int objectId, Integer propId) {
		return textBoxPropValueDAO.findByObjectIdAndPropId(objectId, propId);
	}

}
