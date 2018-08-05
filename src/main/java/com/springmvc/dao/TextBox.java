package com.springmvc.dao;

public class TextBox extends Property{

	private int length;

	public TextBox(short stage, int sequence, int length, String value) {
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
	
}
