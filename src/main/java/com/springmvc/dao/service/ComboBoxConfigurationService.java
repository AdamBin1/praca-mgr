package com.springmvc.dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.dao.impl.ComboBoxFieldDaoImpl;
import com.springmvc.data.model.ComboBoxField;

@Service
@Transactional
public class ComboBoxConfigurationService {
	
	@Autowired
	private ComboBoxFieldDaoImpl comboBoxFieldDAO; 
	
	public ComboBoxConfigurationService() {
	}
	
	public ComboBoxField getComboBoxFieldForId(int id) {
		ComboBoxField cbf = comboBoxFieldDAO.findById(id);
		cbf.getOptions().sort((o1, o2)->o1.getSec()-o2.getSec());
		return cbf;
	}
	
	public List<ComboBoxField> getAllComboBoxFields(){
		List<ComboBoxField> list = comboBoxFieldDAO.findAll();
		// sortowanie po nazwie
		list.sort((cbf1, cbf2) -> cbf1.getName().compareTo(cbf2.getName()));
		return list;
	}
	
	public void saveComboBoxField(ComboBoxField cbf) {
		comboBoxFieldDAO.save(cbf);
	}

	public boolean isNameInDatabase(Integer id, String name) {
		if(id == null) {
			return comboBoxFieldDAO.countByName(name) < 1;
		} else {
			return comboBoxFieldDAO.countByIdNotAndName(id, name) < 1;
		}
	}
}
