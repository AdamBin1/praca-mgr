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
	
	@PostConstruct
	public void init() {
			list = new ArrayList<DateTextBoxProp>();
			
			list.add(new DateTextBoxProp(7, "Date Text prop 7", (short)0, 15, false));
	//		list.add(new DateTextBoxProp(8, "Date Text prop 8", (short)0, 5, false));
	//		list.add(new DateTextBoxProp(9, "Date Text prop 9", (short)0, 70, false));

	}



}
