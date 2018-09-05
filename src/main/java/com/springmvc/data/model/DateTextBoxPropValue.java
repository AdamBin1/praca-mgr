package com.springmvc.data.model;

import java.time.LocalDate;

public class DateTextBoxPropValue extends PropValue{
	LocalDate value;	
	
	public DateTextBoxPropValue() {
		super();
		this.type = FieldType.DATE;
	}

	public DateTextBoxPropValue(int id, LocalDate value, Integer stageId, DateTextBoxProp property) {
		super(id);
		this.type = FieldType.DATE;
		this.value = value;
		this.objectId = stageId;
		this.property = property;
	}

	public LocalDate getValue() {
		return value;
	}

	public void setValue(LocalDate value) {
		this.value = value;
	}

}
