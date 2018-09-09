package com.springmvc.data.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

//@Entity
//@Table(name = "OBJECTS")
public class ObjectModel {

	@Id
	@Column(name = "ID", nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "ACTIVE_STAGE_ID")
	private Integer activeStageId;
	
	@Column(name = "STAGE_VALUES")
	@OneToMany(orphanRemoval=true, fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "objectModel")
	private Set<StageValues> stageValues;
	
	public ObjectModel() {
		super();
	}
	
	public ObjectModel(Integer id, Integer activeStageId, Set<StageValues> stageValues) {
		super();
		this.id = id;
		this.activeStageId = activeStageId;
		this.stageValues = stageValues;
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

	public Set<StageValues> getStageValues() {
		return stageValues;
	}

	public void setStageValues(Set<StageValues> stageValues) {
		this.stageValues = stageValues;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activeStageId == null) ? 0 : activeStageId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (stageValues == null) {
			if (other.stageValues != null)
				return false;
		} else if (!stageValues.equals(other.stageValues))
			return false;
		return true;
	}

	
	
}
