package com.springmvc.model;

import java.util.HashMap;
import java.util.Map;

public class PageModel {
		
	private Map<String, Object> model;

	public PageModel() {
		this.model = new HashMap<String, Object>();
		init(model);
	}

	private void init(Map<String, Object> model2) {
//		List<Property> listaProperty = new ArrayList<Property>();
//		listaProperty.add(new TextBoxProp(1, "Text 1", (short) 1, 1, false, 10, "test1"));
//		listaProperty.add(new TextBoxProp(2, "Text 2", (short) 1, 2, false, 10, "test2"));
//		List<ComboOption> listaOpcji = new ArrayList<ComboOption>();
//		listaOpcji.add(new ComboOption(1, "Opcja 1", 1, 3));
//		listaOpcji.add(new ComboOption(2, "Opcja 2123", 3, 3));
//		listaProperty.add(new ComboBoxProp(3, "Combo 1", (short) 1, 3, false, listaOpcji, 1));
//		listaProperty.add(new ComboBoxProp(4, "Combo 2", (short) 1, 4, false, listaOpcji, 2));
//		listaProperty.add(new DateTextBoxProp(5, "Data 1", (short)1, 5, false, "2005-12-30"));
//		listaProperty.add(new DateTextBoxProp(6, "Data 2", (short)1, 6, false, "2010-05-05"));
//		


	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

}
