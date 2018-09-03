package com.springmvc.dao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springmvc.dao.ComboBoxPropDAO;
import com.springmvc.dao.impl.ComboBoxPropDaoImpl;
import com.springmvc.data.model.ComboBoxProp;

@Service
public class ComboBoxPropService {
	
	public ComboBoxPropService() {
		
	}
		
	public List<ComboBoxProp> getAllComboBoxProperties() {
		ComboBoxPropDAO comboBoxPropDAO = new ComboBoxPropDaoImpl();
		
		return comboBoxPropDAO.getAllComboBoxes();
	}

	public void saveComboBoxProperty(ComboBoxProp cbp) {
		ComboBoxPropDAO comboBoxPropDAO = new ComboBoxPropDaoImpl();
		
		comboBoxPropDAO.insertOrUpdateComboBoxProp(cbp);
	}
}
