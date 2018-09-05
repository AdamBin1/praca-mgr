package com.springmvc.dao;

import java.util.List;

import com.springmvc.data.model.TextBoxProp;

public interface TextBoxPropDAO {
	public List<TextBoxProp> getAllTextBoxes();
	
	public void insertOrUpdateTextBoxProp(TextBoxProp tbp);

	public TextBoxProp getTextBoxPropertyForId(int id);
}
