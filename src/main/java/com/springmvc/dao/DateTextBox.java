package com.springmvc.dao;

public class DateTextBox extends Property{
	
	private String value;

	public DateTextBox(String name, short stage, int sequence, String value) {
		this.type = PropertyType.DATE;
		this.name = name;
		this.stage = stage;
		this.sequence = sequence;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
