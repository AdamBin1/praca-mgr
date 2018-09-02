package com.springmvc.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COMBO_OPTIONS")
public class ComboOption {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	@Column(name = "VALUE")
	protected String value;
	
	@Column(name = "SEC")
	protected int sec;
	
	@Column(name = "COMBO_BOX_FIELD_ID")
	protected Integer comboBoxFieldId;
	
	public ComboOption() {
	}

	public ComboOption(Integer id, String value, int sec, Integer comboBoxFieldId) {
		this.id = id;
		this.value = value;
		this.sec = sec;
		this.comboBoxFieldId = comboBoxFieldId;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getSec() {
		return sec;
	}
	public void setSec(int sec) {
		this.sec = sec;
	}
	public Integer getComboBoxFieldId() {
		return comboBoxFieldId;
	}

	public void setComboBoxFieldId(Integer comboBoxFieldId) {
		this.comboBoxFieldId = comboBoxFieldId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comboBoxFieldId == null) ? 0 : comboBoxFieldId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + sec;
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
		ComboOption other = (ComboOption) obj;
		if (comboBoxFieldId == null) {
			if (other.comboBoxFieldId != null)
				return false;
		} else if (!comboBoxFieldId.equals(other.comboBoxFieldId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (sec != other.sec)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ComboOption [id=" + id + ", value=" + value + ", sec=" + sec + ", comboBoxFieldId=" + comboBoxFieldId
				+ "]";
	}

}
