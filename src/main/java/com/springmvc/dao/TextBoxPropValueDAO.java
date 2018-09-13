package com.springmvc.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.Repository;

import com.springmvc.model.PropValue;
import com.springmvc.model.TextBoxPropValue;

@Transactional
public interface TextBoxPropValueDAO extends Repository<TextBoxPropValue, Integer> {
	public TextBoxPropValue save(TextBoxPropValue tbpv);

	public Iterable<TextBoxPropValue> saveAll(Iterable<TextBoxPropValue> textBoxPropValues);

	public PropValue findByObjectIdAndPropId(int objectId, Integer propId);

	public void deleteByObjectId(Integer objectId);
}
