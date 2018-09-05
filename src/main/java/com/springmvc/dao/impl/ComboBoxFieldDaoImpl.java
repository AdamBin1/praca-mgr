package com.springmvc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.springmvc.dao.ComboBoxFieldDAO;
import com.springmvc.dao.service.ComboOptionService;
import com.springmvc.data.model.ComboBoxField;

@Component
public class ComboBoxFieldDaoImpl implements ComboBoxFieldDAO{

	private static List<ComboBoxField> list;

	
	@Override
	public List<ComboBoxField> getAllComboBoxes() {
		return list;
	}

	@Override
	public void insertOrUpdateComboBoxField(ComboBoxField cbf) {
		// TODO: podczas zamieniania na hibernate trzeba zadbać o zapis elementów z listy opcji
		if(cbf.getId() == null) {
			list.add(cbf);
		} else {
			list.removeIf(o->cbf.getId() == o.getId());
			list.add(cbf);
		}
	}
	
	@Override
	public ComboBoxField getComboBoxFieldForId(int id) {
		for(ComboBoxField cbf:list) {
			if(cbf.getId() == id) {
				return cbf;
			}
		}
		return null;
	}
	
	@PostConstruct
	public void init() {
			list = new ArrayList<ComboBoxField>();
			ComboOptionService cos = new ComboOptionService();
			list.add(new ComboBoxField(1, "Combo 1dao", cos.getComboOptionsForComboId(1)));
			list.add(new ComboBoxField(2, "Combo 2dao", cos.getComboOptionsForComboId(2)));
	}


}
