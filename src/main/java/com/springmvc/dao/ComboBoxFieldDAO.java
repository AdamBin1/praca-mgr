package com.springmvc.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.springmvc.data.model.ComboBoxField;

@Component
public interface ComboBoxFieldDAO {
	
	public List<ComboBoxField> getAllComboBoxes();
	
	public void insertOrUpdateComboBoxField(ComboBoxField cbf);

	public ComboBoxField getComboBoxFieldForId(int id);

	public boolean isNameInDatabase(Integer id, String name);

}
