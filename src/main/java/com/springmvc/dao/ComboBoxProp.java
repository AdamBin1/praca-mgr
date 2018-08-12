package com.springmvc.dao;

import java.util.ArrayList;
import java.util.List;

public class ComboBoxProp extends Property{

	List<ComboOption> options = new ArrayList<>();
	int choosenOption;
	
	public ComboBoxProp(int id, String name, short stage, int sequence, boolean saveRequired, List<ComboOption> options, int choosenOption) {
		super(id, name, stage, sequence, saveRequired);
		this.type = PropertyType.COMBO;
		this.options = options;
		this.choosenOption = choosenOption;
	}
	
	/**
	 *  Zwraca pierwsze wystąpienie wartości na liście opcji (i jedyne, bo lista opcji jest unikalna)
	 *  
	 * @param value
	 * @return
	 */
	public int getComboOptionIdByValue(String value) {
		return getOptions().stream().filter(co -> co.getValue().equals(value)).findFirst().get().getId();
	}
	
	public List<ComboOption> getOptions() {
		return options;
	}


	public void setOptions(List<ComboOption> options) {
		this.options = options;
	}


	public int getChoosenOption() {
		return choosenOption;
	}


	public void setChoosenOption(int choosenOption) {
		this.choosenOption = choosenOption;
	}

	public String getValue() {
		return options.get(choosenOption).getValue();
	}
	
}
