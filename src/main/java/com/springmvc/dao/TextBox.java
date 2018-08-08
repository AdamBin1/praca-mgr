package com.springmvc.dao;

public class TextBox extends Property{

	private int length;
	String value;

	public TextBox(String name, short stage, int sequence, int length, String value) {
		this.type = PropertyType.TEXT;
		this.name = name;
		this.stage = stage;
		this.sequence = sequence;
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
