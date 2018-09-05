package com.springmvc.data.model;

import java.time.LocalDate;

public class DateTextBoxPropValue extends PropValue{
	int id;
	LocalDate value;	
	
	public DateTextBoxPropValue() {
		super();
		this.type = FieldType.DATE;
	}

	public DateTextBoxPropValue(int id, LocalDate value, DateTextBoxProp property) {
		super(id);
		this.type = FieldType.DATE;
		this.value = value;
		this.property = property;
	}

	public LocalDate getValue() {
		return value;
	}

	public void setValue(LocalDate value) {
		this.value = value;
	}

}
