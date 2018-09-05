package com.springmvc.dao;

import java.util.List;

import com.springmvc.data.model.TextBoxPropValue;

public interface TextBoxPropValueDAO {

	public List<TextBoxPropValue> getTextBoxPropValuesForStageId(short stageId);

	public void insertOrUpdateTextBoxPropValue(TextBoxPropValue tbpv);
}
