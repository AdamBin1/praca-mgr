package com.springmvc.dao;

public class Property {

	PropertyType type;
	String name;
	short stage;
	int sequence;
	
	public PropertyType getType() {
		return type;
	}

	public void setType(PropertyType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

}
