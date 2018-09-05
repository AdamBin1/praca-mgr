package com.springmvc.data.model;

public class ComboBoxProp extends Property{

	ComboBoxField comboBoxField;
	
	public ComboBoxProp() {
		super();
		this.type = FieldType.COMBO;
	}
	
	public ComboBoxProp(int id, String name, short stage, int sequence, boolean saveRequired, ComboBoxField comboBoxField) {
		super(id, name, stage, sequence, saveRequired);
		this.type = FieldType.COMBO;
		this.comboBoxField = comboBoxField;
	}
	
	/**
	 *  Zwraca pierwsze wystąpienie wartości na liście opcji (i jedyne, bo lista opcji jest unikalna)
	 *  
	 * @param value
	 * @return
	 */
	public int getComboOptionIdByValue(String value) {
		return comboBoxField.getOptions().stream().filter(co -> co.getValue().equals(value)).findFirst().get().getId();
	}

	public ComboBoxField getComboBoxField() {
		return comboBoxField;
	}

	public void setComboBoxField(ComboBoxField comboBoxField) {
		this.comboBoxField = comboBoxField;
	}
	
}
