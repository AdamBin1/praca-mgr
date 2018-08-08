package com.springmvc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.springmvc.dao.ComboBox;
import com.springmvc.dao.ComboOption;
import com.springmvc.dao.DateTextBox;
import com.springmvc.dao.PropertiesListWrapper;
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
		listaProperty.add(new TextBox("Text 1", (short) 1, 1, 10, "test1"));
		listaProperty.add(new TextBox("Text 2", (short) 1, 2, 10, "test2"));
		List<ComboOption> listaOpcji = new ArrayList<ComboOption>();
		listaOpcji.add(new ComboOption(1, "Opcja 1", 1));
		listaOpcji.add(new ComboOption(2, "Opcja 2123", 2));
		listaProperty.add(new ComboBox("Combo 1", (short) 1, 3, listaOpcji, 1));
		listaProperty.add(new ComboBox("Combo 2", (short) 1, 4, listaOpcji, 2));
		listaProperty.add(new DateTextBox("Data 1", (short)1, 5, "2005-12-30"));
		listaProperty.add(new DateTextBox("Data 2", (short)1, 6, "2010-05-05"));
		PropertiesListWrapper propertiesListWrapper = new PropertiesListWrapper(listaProperty);
	//	model.put("propertiesListWrapper", propertiesListWrapper);
		model.put("properties", listaProperty);

	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

}
