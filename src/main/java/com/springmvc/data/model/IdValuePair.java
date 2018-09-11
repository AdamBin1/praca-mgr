package com.springmvc.data.model;

public class IdValuePair {
	int id;
	String value;
	
	public IdValuePair(int id, String value) {
		super();
		this.id = id;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public String getValue() {
		return value;
	}
	
}
