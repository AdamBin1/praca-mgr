package com.springmvc.dao;

import java.util.List;

public class PropertiesListWrapper {
	private List<Property> propertiesList;
	
	public PropertiesListWrapper(List<Property> list) {
		this.propertiesList = list;
	}

	public List<Property> getPropertiesList() {
		return propertiesList;
	}

	public void setPropertiesList(List<Property> propertiesList) {
		this.propertiesList = propertiesList;
	}

}
