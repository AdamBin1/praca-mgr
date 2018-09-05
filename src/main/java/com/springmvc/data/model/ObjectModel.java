package com.springmvc.data.model;

import java.util.List;

public class ObjectModel {

	private Integer id;
	private Integer activeStage;
	private List<PropValue> values;
	
	public ObjectModel() {
		super();
	}
	
	public ObjectModel(Integer id, Integer activeStage, List<PropValue> values) {
		super();
		this.id = id;
		this.activeStage = activeStage;
		this.values = values;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getActiveStage() {
		return activeStage;
	}

	public void setActiveStage(Integer activeStage) {
		this.activeStage = activeStage;
	}

	public List<PropValue> getValues() {
		return values;
	}

	public void setValues(List<PropValue> values) {
		this.values = values;
	}
	
}
