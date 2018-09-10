package com.springmvc.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TEXT_PROP_VALUES")
public class TextBoxPropValue implements PropValue{
	
	
	@Id
	@Column(name = "ID", nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Transient
	private FieldType type;
	
	@Column(name = "OBJECT_ID")
	private Integer objectId;
	
	@Column(name = "PROP_ID")
	private Integer propId;
	
	@Column(name = "VALUE")
	private String value;	
	
	public TextBoxPropValue() {
		super();
		this.type = FieldType.TEXT;
	}

	public TextBoxPropValue(Integer id, String value, Integer objectId, Integer propId) {
		this.id = id;
		this.type = FieldType.TEXT;
		this.value = value;
		this.objectId = objectId;
		this.propId = propId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
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
	public Integer getPropId() {
		return propId;
	}

	@Override
	public void setPropId(Integer propId) {
		this.propId = propId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((objectId == null) ? 0 : objectId.hashCode());
		result = prime * result + ((propId == null) ? 0 : propId.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (objectId == null) {
			if (other.objectId != null)
				return false;
		} else if (!objectId.equals(other.objectId))
			return false;
		if (propId == null) {
			if (other.propId != null)
				return false;
		} else if (!propId.equals(other.propId))
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

	@Override
	public boolean isSet() {
		return (this.value != null && !this.value.isEmpty());
	}
}
