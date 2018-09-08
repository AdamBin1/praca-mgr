package com.springmvc.data.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TEXT_PROP_VALUES")
public class TextBoxPropValue implements PropValue{
	
	
	@Id
	@Column(name = "ID", nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Transient
	private FieldType type;
	
	//TODO: co tutaj?
	private Integer objectId;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL, optional=false)
	private TextBoxProp property;
	
	@Column(name = "VALUE")
	private String value;	
	
	public TextBoxPropValue() {
		super();
		this.type = FieldType.TEXT;
	}

	public TextBoxPropValue(Integer id, String value, Integer stageId, TextBoxProp property) {
		this.id = id;
		this.type = FieldType.TEXT;
		this.value = value;
		this.objectId = stageId;
		this.property = property;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public FieldType getType() {
		return type;
	}

	@Override
	public void setType(FieldType type) {
		this.type = type;
	}

	@Override
	public Integer getObjectId() {
		return objectId;
	}

	@Override
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	@Override
	public Property getProperty() {
		return property;
	}

	@Override
	public void setProperty(Property property) {
		this.property = (TextBoxProp) property;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((objectId == null) ? 0 : objectId.hashCode());
		result = prime * result + ((property == null) ? 0 : property.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TextBoxPropValue other = (TextBoxPropValue) obj;
		if (id != other.id)
			return false;
		if (objectId == null) {
			if (other.objectId != null)
				return false;
		} else if (!objectId.equals(other.objectId))
			return false;
		if (property == null) {
			if (other.property != null)
				return false;
		} else if (!property.equals(other.property))
			return false;
		if (type != other.type)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
