package com.springmvc.dao;

import java.util.List;

import com.springmvc.data.model.DateTextBoxProp;

public interface DateTextBoxPropDAO {
	public List<DateTextBoxProp> getAllDateTextBoxes();

	public void insertOrUpdateDateTextBoxProp(DateTextBoxProp dtbp);

	DateTextBoxProp getDateTextBoxPropertyForId(int id);
}
