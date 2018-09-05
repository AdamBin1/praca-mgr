package com.springmvc.data.model;

public enum FieldType {
	TEXT(1), DATE(2), COMBO(3);

	private final int id;

	FieldType(int id) {
		this.id = id;
	}

	public int getValue() {
		return id;
	}
}
