package com.springmvc.dao;

import java.util.ArrayList;
import java.util.List;

public class ComboBox extends Property{

	List<ComboOption> options = new ArrayList<>();
	int choosenOption;
	
	public ComboBox(String name, short stage, int sequence, List<ComboOption> options, int choosenOption) {
		this.type = PropertyType.COMBO;
		this.name = name;
		this.stage = stage;
		this.sequence = sequence;
		this.options = options;
		this.choosenOption = choosenOption;
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
