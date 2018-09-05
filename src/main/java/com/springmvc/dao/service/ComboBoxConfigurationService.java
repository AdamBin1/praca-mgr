package com.springmvc.dao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springmvc.dao.ComboBoxFieldDAO;
import com.springmvc.dao.impl.ComboBoxFieldDaoImpl;
import com.springmvc.data.model.ComboBoxField;

@Service
public class ComboBoxConfigurationService {
	

	private ComboBoxFieldDAO comboBoxFieldDAO; 
	
	public ComboBoxConfigurationService() {
		comboBoxFieldDAO = new ComboBoxFieldDaoImpl();
	}
	
	public ComboBoxField getComboBoxFieldForId(int id) {
		ComboBoxField cbf = comboBoxFieldDAO.getComboBoxFieldForId(id);
		cbf.getOptions().sort((op1, op2) -> op1.getSec() - op2.getSec());
		return cbf;
	}
	
	public List<ComboBoxField> getAllComboBoxFields(){
		List<ComboBoxField> list = comboBoxFieldDAO.getAllComboBoxes();
		// sortowanie po nazwie
		list.sort((cbf1, cbf2) -> cbf1.getName().compareTo(cbf2.getName()));
		return list;
	}
	
	public void saveComboBoxField(ComboBoxField cbf) {
		comboBoxFieldDAO.insertOrUpdateComboBoxField(cbf);
	}
}
