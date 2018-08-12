package com.springmvc.dao;

public class DateTextBoxProp extends Property{
	
	private String value;

	public DateTextBoxProp(int id, String name, short stage, int sequence, boolean saveRequired, String value) {
		super(id, name, stage, sequence, saveRequired);
		this.type = PropertyType.DATE;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
