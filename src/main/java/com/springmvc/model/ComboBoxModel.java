package com.springmvc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.springmvc.dao.ComboBoxField;
import com.springmvc.dao.ComboOption;

public class ComboBoxModel {
	private Map<String, Object> model;

	public ComboBoxModel() {
		this.model = new HashMap<String, Object>();
		init(model);
	}
	
	public ComboBoxModel(String name) {
		this();
		
		// TODO: hibernate get for name
		Object o = model.get("combobox");
		((ComboBoxField)o).setName(name);
		
	}

	private void init(Map<String, Object> model2) {
		List<ComboOption> listaOpcji = new ArrayList<ComboOption>();
		listaOpcji.add(new ComboOption(1, "Opcja 1", 1));
		listaOpcji.add(new ComboOption(2, "Opcja 2123", 2));
		
		//Sortowanie listy Opcji zawsze po sec;
		listaOpcji.sort((co1, co2) -> co1.getSec() - co2.getSec());
		
		ComboBoxField comboBoxField = new ComboBoxField(1, "nazwa", listaOpcji);
		model.put("combobox", comboBoxField);
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

}
