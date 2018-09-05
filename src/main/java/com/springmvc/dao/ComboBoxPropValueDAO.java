package com.springmvc.dao;

import java.util.List;

import com.springmvc.data.model.ComboBoxPropValue;

public interface ComboBoxPropValueDAO {

	public List<ComboBoxPropValue> getComboBoxPropValuesForStageId(short stageId);

	public void insertOrUpdateComboBoxPropValue(ComboBoxPropValue cbpv);
}
