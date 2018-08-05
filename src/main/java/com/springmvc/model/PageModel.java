package com.springmvc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.springmvc.dao.Property;
import com.springmvc.dao.TextBox;

public class PageModel {
	private Map<String, Object> model;

	public PageModel() {
		this.model = new HashMap<String, Object>();
		init(model);
	}

	private void init(Map<String, Object> model2) {
		List<Property> listaProperty = new ArrayList<Property>();
		listaProperty.add(new TextBox((short) 1, 1, 10, "test"));
		model.put("properties", listaProperty);

	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

}
