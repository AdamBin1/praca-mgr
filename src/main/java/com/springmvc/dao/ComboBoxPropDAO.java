package com.springmvc.dao;

import java.util.List;

import com.springmvc.data.model.ComboBoxProp;

public interface ComboBoxPropDAO {
	public List<ComboBoxProp> getAllComboBoxes();

	public void insertOrUpdateComboBoxProp(ComboBoxProp cbp);

	public ComboBoxProp getComboBoxPropertyForId(int id);
}
