package com.springmvc.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.Repository;

import com.springmvc.data.model.ComboBoxPropValue;
import com.springmvc.data.model.PropValue;

@Transactional
public interface ComboBoxPropValueDAO extends Repository<ComboBoxPropValue, Integer>{

	public ComboBoxPropValue save(ComboBoxPropValue cbp);

	public Iterable<ComboBoxPropValue> saveAll(Iterable<ComboBoxPropValue> comboBoxPropValues);

	public PropValue findByObjectIdAndPropId(int objectId, Integer propId);

	public void deleteByObjectId(Integer objectId);

}
