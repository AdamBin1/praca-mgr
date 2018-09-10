package com.springmvc.dao;

import org.springframework.data.repository.Repository;

import com.springmvc.data.model.ComboBoxPropValue;
import com.springmvc.data.model.PropValue;

public interface ComboBoxPropValueDAO extends Repository<ComboBoxPropValue, Integer>{

	public ComboBoxPropValue save(ComboBoxPropValue cbp);

	public Iterable<ComboBoxPropValue> saveAll(Iterable<ComboBoxPropValue> comboBoxPropValues);

	public PropValue findByObjectIdAndPropId(int objectId, Integer propId);

}
