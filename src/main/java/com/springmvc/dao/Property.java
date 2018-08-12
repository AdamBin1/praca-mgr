package com.springmvc.dao;

public class Property {

	int id;
	PropertyType type;
	String name;
	short stage;
	int sequence;
	boolean saveRequired;
	
	public Property(int id, String name, short stage, int sequence, boolean saveRequired) {
		this.id = id;
		this.name = name;
		this.stage = stage;
		this.sequence = sequence;
		this.saveRequired = saveRequired;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public boolean isSaveRequired() {
		return saveRequired;
	}

	public void setSaveRequired(boolean saveRequired) {
		this.saveRequired = saveRequired;
	}

}
