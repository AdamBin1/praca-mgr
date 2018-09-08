package com.springmvc.dao;

import java.util.List;

import com.springmvc.data.model.ComboBoxPropValue;

public interface ComboBoxPropValueDAO {

	public List<ComboBoxPropValue> getComboBoxPropValuesForStageId(int objectId, short stageId);

	public void insertOrUpdateComboBoxPropValue(ComboBoxPropValue cbpv);

//	public List<ComboBoxPropValue> getNewComboBoxPropValuesForStageId(short stageId);
}
