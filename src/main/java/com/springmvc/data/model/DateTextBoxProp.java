package com.springmvc.data.model;

public class DateTextBoxProp extends Property{
		
	public DateTextBoxProp() {
		super();
	}

	public DateTextBoxProp(int id, String name, short stage, int sequence, boolean saveRequired) {
		super(id, name, stage, sequence, saveRequired);
		this.type = PropertyType.DATE;
	}
}
