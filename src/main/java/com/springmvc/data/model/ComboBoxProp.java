package com.springmvc.data.model;

public class ComboBoxProp extends Property{

	ComboBoxField comboBoxField;
	int choosenOption;
	
	public ComboBoxProp() {
		super();
		this.type = PropertyType.COMBO;
	}
	
	public ComboBoxProp(int id, String name, short stage, int sequence, boolean saveRequired, ComboBoxField comboBoxField, int choosenOption) {
		super(id, name, stage, sequence, saveRequired);
		this.type = PropertyType.COMBO;
		this.comboBoxField = comboBoxField;
		this.choosenOption = choosenOption;
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

	public int getChoosenOption() {
		return choosenOption;
	}

	public void setChoosenOption(int choosenOption) {
		this.choosenOption = choosenOption;
	}

	public String getValue() {
		return comboBoxField.getOptions().get(choosenOption).getValue();
	}
	
}
