package com.springmvc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.springmvc.dao.DateTextBoxPropDAO;
import com.springmvc.data.model.DateTextBoxProp;

@Component
public class DateTextBoxPropDaoImpl implements DateTextBoxPropDAO{

	private static List<DateTextBoxProp> list;

	
	@Override
	public List<DateTextBoxProp> getAllDateTextBoxes() {
		return list;
	}
	
	@Override
	public DateTextBoxProp getDateTextBoxPropertyForId(int id) {
		for(DateTextBoxProp dtbp : list) {
			if(dtbp.getId() == id) {
				return dtbp;
			}
		}
		return null;
	}
	
	@Override
	public void insertOrUpdateDateTextBoxProp(DateTextBoxProp dtbp) {
		if(dtbp.getId() == -1) {
			list.add(dtbp);
		} else {
			list.removeIf(o->dtbp.getId() == o.getId());
			list.add(dtbp);
		}
	}
	
	@PostConstruct
	public void init() {
			list = new ArrayList<DateTextBoxProp>();
			
			list.add(new DateTextBoxProp(7, "Date Text prop 7", (short)0, 15, false));
			list.add(new DateTextBoxProp(8, "Date Text prop 8", (short)0, 5, false));
			list.add(new DateTextBoxProp(9, "Date Text prop 9", (short)0, 70, false));

	}





}
