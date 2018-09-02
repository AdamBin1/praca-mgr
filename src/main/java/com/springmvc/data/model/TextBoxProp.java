package com.springmvc.data.model;

public class TextBoxProp extends Property{

	private int length;
	
	public TextBoxProp() {
		super();
	}

	public TextBoxProp(int id, String name, short stage, int sequence, boolean saveRequired, int length) {
		super(id, name, stage, sequence, saveRequired);
		this.type = PropertyType.TEXT;
		this.length = length;
	}
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
}
