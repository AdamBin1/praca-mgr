package com.springmvc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.springmvc.dao.TextBoxPropDAO;
import com.springmvc.data.model.TextBoxProp;

@Component
public class TextBoxPropDaoImpl implements TextBoxPropDAO{

	private static List<TextBoxProp> list;

	
	@Override
	public List<TextBoxProp> getAllTextBoxes() {
		return list;
	}
	
	@Override
	public void insertOrUpdateTextBoxProp(TextBoxProp tbp) {
		if(tbp.getId() == -1) {
			list.add(tbp);
		} else {
			list.removeIf(o->tbp.getId() == o.getId());
			list.add(tbp);
		}
		
	}
	
	@PostConstruct
	public void init() {
			list = new ArrayList<TextBoxProp>();
			
			list.add(new TextBoxProp(3, "Text prop 3", (short)0, 10, false, 3));
	//		list.add(new TextBoxProp(4, "Text prop 4", (short)0, 50, false, 4));
	//		list.add(new TextBoxProp(5, "Text prop 5", (short)0, 100, false, 5));

	}





}
