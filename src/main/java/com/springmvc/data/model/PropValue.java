package com.springmvc.data.model;

public interface PropValue {

	public Integer getId();

	public void setId(Integer id);

	public FieldType getType();

	public void setType(FieldType type);

	public Integer getObjectId();

	public void setObjectId(Integer objectId);

	public Integer getPropId();

	public void setPropId(Integer propId);

	public boolean isSet();

}
