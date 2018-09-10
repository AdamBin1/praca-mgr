package com.springmvc.dao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.dao.ComboBoxFieldDAO;
import com.springmvc.data.model.ComboBoxField;
import com.springmvc.data.model.IdSecPair;

@Service
@Transactional
public class ComboBoxConfigurationService {
	
	@Autowired
	private ComboBoxFieldDAO comboBoxFieldDAO; 
	
	public ComboBoxConfigurationService() {
	}
	
	public ComboBoxField getComboBoxFieldForId(int id) {
		ComboBoxField cbf = comboBoxFieldDAO.findById(id);
		return cbf;
	}
	
	public List<ComboBoxField> getAllComboBoxFields(){
		List<ComboBoxField> list = comboBoxFieldDAO.findAll();
		// sortowanie po nazwie
		list.sort((cbf1, cbf2) -> cbf1.getName().compareTo(cbf2.getName()));
		return list;
	}
	
	public ComboBoxField saveComboBoxField(ComboBoxField cbf) {
		return comboBoxFieldDAO.save(cbf);
	}

	public boolean isNameInDatabase(Integer id, String name) {
		if(id == null) {
			return comboBoxFieldDAO.countByName(name) > 0;
		} else {
			return comboBoxFieldDAO.countByIdNotAndName(id, name) > 0;
		}
	}

	public List<IdSecPair> getIdSecPairList(ComboBoxField cbf) {
		List<IdSecPair> idSecPairs = new ArrayList<>();
		cbf.getOptions().parallelStream().forEach(option -> idSecPairs.add(new IdSecPair(option.getId(), option.getSec())));
		return idSecPairs;
	}
}
