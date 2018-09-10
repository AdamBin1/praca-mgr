package com.springmvc.data.model;

public interface Property {

	public Integer getId();

	public void setId(Integer id);
	
	public FieldType getType();

	public void setType(FieldType type);

	public String getName();
	
	public void setName(String name);

	public Stage getStage();

	public void setStage(Stage stage);

	public int getSec();

	public void setSec(int sec);
	
	public PropValue getPropValue();
	
	public void setPropValue(PropValue propValue);

}
