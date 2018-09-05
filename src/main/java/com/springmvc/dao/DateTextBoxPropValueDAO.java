package com.springmvc.dao;

import java.util.List;

import com.springmvc.data.model.DateTextBoxPropValue;

public interface DateTextBoxPropValueDAO {

	public List<DateTextBoxPropValue> getDateTextBoxPropValuesForStageId(int objectId, short stageId);

	public void insertOrUpdateDateTextBoxPropValue(DateTextBoxPropValue dtbpv);

	public List<DateTextBoxPropValue> getNewDateTextBoxPropValuesForStageId(short stageId);
}
