package com.springmvc.dao;

public class ComboOption {

	int id;
	String value;
	int sec;
	
	public ComboOption(int id, String value, int sec) {
		this.id = id;
		this.value = value;
		this.sec = sec;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getSec() {
		return sec;
	}
	public void setSec(int sec) {
		this.sec = sec;
	}
	
}
