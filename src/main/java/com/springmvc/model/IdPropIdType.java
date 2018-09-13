package com.springmvc.model;

public class IdPropIdType {
	int id;
	int propId;
	FieldType fieldType;

	public IdPropIdType(int id, int propId, FieldType fieldType) {
		super();
		this.id = id;
		this.propId = propId;
		this.fieldType = fieldType;
	}

	public int getId() {
		return id;
	}

	public int getPropId() {
		return propId;
	}

	public FieldType getFieldType() {
		return fieldType;
	}

}
