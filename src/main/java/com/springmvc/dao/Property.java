package com.springmvc.dao;

public abstract class Property {

	short stage;
	int sequence;
	String value;

	public short getStage() {
		return stage;
	}

	public void setStage(short stage) {
		this.stage = stage;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
