package com.springmvc.dao;

public enum PropertyType {
	TEXT(1), DATE(2), COMBO(3);

	private final int id;

	PropertyType(int id) {
		this.id = id;
	}

	public int getValue() {
		return id;
	}
}
