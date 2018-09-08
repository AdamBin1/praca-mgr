package com.springmvc.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COMBO_OPTIONS")
public class ComboOption {

	@Id
	@Column(name = "ID", nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "VALUE")
	private String value;
	
	@Column(name = "SEC")
	private int sec;
	
	@ManyToOne
	private ComboBoxField comboBoxField;
	
	public ComboOption() {
	}

	public ComboOption(Integer id, String value, int sec, ComboBoxField comboBoxField) {
		this.id = id;
		this.value = value;
		this.sec = sec;
		this.comboBoxField = comboBoxField;
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

	public ComboBoxField getComboBoxField() {
		return comboBoxField;
	}

	public void setComboBoxField(ComboBoxField comboBoxField) {
		this.comboBoxField = comboBoxField;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comboBoxField == null) ? 0 : comboBoxField.hashCode());
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
		if (comboBoxField == null) {
			if (other.comboBoxField != null)
				return false;
		} else if (!comboBoxField.equals(other.comboBoxField))
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

}
