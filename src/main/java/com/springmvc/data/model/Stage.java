package com.springmvc.data.model;

import java.util.ArrayList;
import java.util.List;

public class Stage{
	
	
	Integer id;		//0 for main stage
	String name;	//null for main stage
	Integer sec;
	List<Property> properties = new ArrayList<>();
	
	
	public Stage() {
	}
	
	public Stage(Integer id) {
		this.id = id;
	}

	public Stage(Integer id, String name, Integer sec, List<Property> properties) {
		this.id = id;
		this.name = name;
		this.sec = sec;
		this.properties = properties;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSec() {
		return sec;
	}

	public void setSec(Integer sec) {
		this.sec = sec;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

}
