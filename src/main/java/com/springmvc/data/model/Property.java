package com.springmvc.data.model;

public class Property {

	int id;
	FieldType type;
	String name;
	short stage;
	int sec;
	boolean saveRequired;
	
	public Property() {
		
	}
	
	public Property(int id, String name, short stage, int sec, boolean saveRequired) {
		this.id = id;
		this.name = name;
		this.stage = stage;
		this.sec = sec;
		this.saveRequired = saveRequired;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FieldType getType() {
		return type;
	}

	public void setType(FieldType type) {
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

	public int getSec() {
		return sec;
	}

	public void setSec(int sec) {
		this.sec = sec;
	}

	public boolean isSaveRequired() {
		return saveRequired;
	}

	public void setSaveRequired(boolean saveRequired) {
		this.saveRequired = saveRequired;
	}

}
