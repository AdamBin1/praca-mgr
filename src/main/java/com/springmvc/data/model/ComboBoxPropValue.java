package com.springmvc.data.model;

public class ComboBoxPropValue extends PropValue{
	Integer value;
	
	public ComboBoxPropValue() {
		super();
		this.type = FieldType.COMBO;
	}

	public ComboBoxPropValue(int id, Integer value, Integer objectId, ComboBoxProp property) {
		super(id);
		this.type = FieldType.COMBO;
		this.value = value;
		this.objectId = objectId;
		this.property = property;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
