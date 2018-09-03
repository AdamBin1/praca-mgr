package com.springmvc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.springmvc.dao.ComboBoxPropDAO;
import com.springmvc.dao.service.ComboBoxConfigurationService;
import com.springmvc.data.model.ComboBoxProp;

@Component
public class ComboBoxPropDaoImpl implements ComboBoxPropDAO{

	private static List<ComboBoxProp> list;

	
	@Override
	public List<ComboBoxProp> getAllComboBoxes() {
		return list;
	}
	
	@Override
	public void insertOrUpdateComboBoxProp(ComboBoxProp cbp) {
		if(cbp.getId() == -1) {
			list.add(cbp);
		} else {
			list.removeIf(o->cbp.getId() == o.getId());
			list.add(cbp);
		}
		
	}
	
	@PostConstruct
	public void init() {
			list = new ArrayList<ComboBoxProp>();
			ComboBoxConfigurationService cbcs = new ComboBoxConfigurationService();
			
			list.add(new ComboBoxProp(1, "Combo prop 1", (short)0, 1, false, cbcs.getComboBoxFieldForId(1), 0));
//			list.add(new ComboBoxProp(2, "Combo prop 2", (short)0, 25, false, cbcs.getComboBoxFieldForId(2), 0));

	}



}
