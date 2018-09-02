package com.springmvc.dao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springmvc.dao.impl.ComboOptionDaoImpl;
import com.springmvc.data.model.ComboOption;

@Service
public class ComboOptionService {
	
	public ComboOptionService() {
		
	}
		
	public List<ComboOption>getComboOptionsForComboId(int id) {
		ComboOptionDaoImpl comboOptionDaoImpl = new ComboOptionDaoImpl();
		return comboOptionDaoImpl.getComboOptionsForComboId(id);
	}
}
