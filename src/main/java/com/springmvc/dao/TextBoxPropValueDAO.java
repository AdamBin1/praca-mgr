package com.springmvc.dao;

import org.springframework.data.repository.Repository;

import com.springmvc.data.model.PropValue;
import com.springmvc.data.model.TextBoxPropValue;

public interface TextBoxPropValueDAO extends Repository<TextBoxPropValue, Integer> {
	public TextBoxPropValue save(TextBoxPropValue tbpv);

	public Iterable<TextBoxPropValue> saveAll(Iterable<TextBoxPropValue> textBoxPropValues);

	public PropValue findByObjectIdAndPropId(int objectId, Integer propId);
}
