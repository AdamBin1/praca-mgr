package com.springmvc.data.model;

public class TextBoxPropValue  extends PropValue{
	String value;	
	
	public TextBoxPropValue() {
		super();
		this.type = FieldType.TEXT;
	}

	public TextBoxPropValue(Integer id, String value, TextBoxProp property) {
		super(id);
		this.type = FieldType.TEXT;
		this.value = value;
		this.property = property;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
