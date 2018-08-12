package com.springmvc.dao;

import java.util.ArrayList;
import java.util.List;

public class ComboBoxField{

	
	int id;
	String name;
	boolean saveRequired;
	List<ComboOption> options = new ArrayList<>();
	
	public ComboBoxField(int id, String name, boolean saveRequired, List<ComboOption> options) {
		this.id = id;
		this.name = name;
		this.saveRequired = saveRequired;
		this.options = options;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSaveRequired() {
		return saveRequired;
	}

	public void setSaveRequired(boolean saveRequired) {
		this.saveRequired = saveRequired;
	}

	public List<ComboOption> getOptions() {
		return options;
	}

	public void setOptions(List<ComboOption> options) {
		this.options = options;
	}
	
}
