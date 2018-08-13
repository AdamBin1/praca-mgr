package com.springmvc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.springmvc.dao.ComboBoxField;
import com.springmvc.dao.ComboOption;

public class AllComboBoxesModel {
	private Map<String, Object> model;

	public AllComboBoxesModel() {
		this.model = new HashMap<String, Object>();
		init(model);
	}

	private void init(Map<String, Object> model2) {
		List<ComboBoxField> listaCombobox= new ArrayList<ComboBoxField>();
		List<ComboOption> listaOpcji = new ArrayList<ComboOption>();
		listaOpcji.add(new ComboOption(1, "Opcja 1", 1));
		listaOpcji.add(new ComboOption(2, "Opcja 2123", 2));
		listaCombobox.add(new ComboBoxField(2, "BBB", listaOpcji));
		listaCombobox.add(new ComboBoxField(1, "AAA", listaOpcji));
		
		//sortowanie listyCombobox zawsze po nazwie
		listaCombobox.sort((cbf1, cbf2) -> cbf1.getName().compareTo(cbf2.getName()));
		
		model.put("comboboxes", listaCombobox);
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

}
