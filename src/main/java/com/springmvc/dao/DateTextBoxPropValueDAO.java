package com.springmvc.dao;

import org.springframework.data.repository.Repository;

import com.springmvc.data.model.DateTextBoxPropValue;
import com.springmvc.data.model.PropValue;

public interface DateTextBoxPropValueDAO extends Repository<DateTextBoxPropValue, Integer>{

	public DateTextBoxPropValue save(DateTextBoxPropValue dtbpv);

	public Iterable<DateTextBoxPropValue> saveAll(Iterable<DateTextBoxPropValue> dateTextBoxPropValues);

	public PropValue findByObjectIdAndPropId(int objectId, Integer propId);

}
