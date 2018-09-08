package com.springmvc.data.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "OBJECTS")
public class ObjectModel {

	@Id
	@Column(name = "ID", nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "ACTIVE_STAGE_ID")
	private Integer activeStageId;
	
	@Transient
	private List<PropValue> values;
	
	public ObjectModel() {
		super();
	}
	
	public ObjectModel(Integer id, Integer activeStageId, List<PropValue> values) {
		super();
		this.id = id;
		this.activeStageId = activeStageId;
		this.values = values;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getActiveStageId() {
		return activeStageId;
	}

	public void setActiveStageId(Integer activeStageId) {
		this.activeStageId = activeStageId;
	}

	public List<PropValue> getValues() {
		return values;
	}

	public void setValues(List<PropValue> values) {
		this.values = values;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activeStageId == null) ? 0 : activeStageId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((values == null) ? 0 : values.hashCode());
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
		ObjectModel other = (ObjectModel) obj;
		if (activeStageId == null) {
			if (other.activeStageId != null)
				return false;
		} else if (!activeStageId.equals(other.activeStageId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}
	
}
