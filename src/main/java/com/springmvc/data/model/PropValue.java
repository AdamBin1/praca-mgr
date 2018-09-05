package com.springmvc.data.model;

public class PropValue {
	int id;
	FieldType type;
	Property property;
	
	public PropValue() {
		
	}
	
	public PropValue(int id) {
		this.id = id;
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

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}
	
}
