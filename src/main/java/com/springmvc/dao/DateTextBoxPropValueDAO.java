package com.springmvc.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.Repository;

import com.springmvc.model.DateTextBoxPropValue;
import com.springmvc.model.PropValue;

@Transactional
public interface DateTextBoxPropValueDAO extends Repository<DateTextBoxPropValue, Integer> {

	public DateTextBoxPropValue save(DateTextBoxPropValue dtbpv);

	public Iterable<DateTextBoxPropValue> saveAll(Iterable<DateTextBoxPropValue> dateTextBoxPropValues);

	public PropValue findByObjectIdAndPropId(int objectId, Integer propId);

	public void deleteByObjectId(Integer objectId);

}
