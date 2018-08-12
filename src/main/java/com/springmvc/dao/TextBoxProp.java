package com.springmvc.dao;

public class TextBoxProp extends Property{

	private int length;
	String value;

	public TextBoxProp(int id, String name, short stage, int sequence, boolean saveRequired, int length, String value) {
		super(id, name, stage, sequence, saveRequired);
		this.type = PropertyType.TEXT;
		this.length = length;
		this.value = value;
	}
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
