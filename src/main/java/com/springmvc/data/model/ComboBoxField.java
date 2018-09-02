package com.springmvc.data.model;

import java.util.ArrayList;
import java.util.List;

public class ComboBoxField{

	
	Integer id;
	String name;
	List<ComboOption> options = new ArrayList<>();
	
	
	public ComboBoxField() {
	}
	
	public ComboBoxField(Integer id) {
		this.id = id;
	}

	public ComboBoxField(Integer id, String name, List<ComboOption> options) {
		this.id = id;
		this.name = name;
		this.options = options;
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

	public List<ComboOption> getOptions() {
		return options;
	}

	public void setOptions(List<ComboOption> options) {
		this.options = options;
	}
	
}
