package com.springmvc.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "COMBO_PROPS")
public class ComboBoxProp implements Property{

	@Id
	@Column(name = "ID", nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Transient
	private FieldType type;
	
	@Column(name = "NAME")
	private String name;
	
	@ManyToOne
	private Stage stage;
	
	@Column(name = "SEC")
	private int sec;
	
	@ManyToOne
	private ComboBoxField comboBoxField;
	
	@Transient
	private ComboBoxPropValue comboBoxPropValue;
	
	public ComboBoxProp() {
		super();
		this.type = FieldType.COMBO;
	}
	
	public ComboBoxProp(Integer id, String name, Stage stage, int sequence, ComboBoxField comboBoxField) {
		super();
		this.id = id;
		this.name = name;
		this.stage = stage;
		this.sec = sequence;
		this.type = FieldType.COMBO;
		this.comboBoxField = comboBoxField;
	}
	
	public ComboBoxProp(ComboBoxProp prop) {
		this.id = prop.id;
		this.name = prop.name;
		this.stage = prop.stage;
		this.sec = prop.sec;
		this.type = FieldType.COMBO;
		this.comboBoxField = prop.comboBoxField;
	}

	/**
	 *  Zwraca pierwsze wystąpienie wartości na liście opcji (i jedyne, bo lista opcji jest unikalna)
	 *  
	 * @param value
	 * @return
	 */
	public int getComboOptionIdByValue(String value) {
		return comboBoxField.getOptions().stream().filter(co -> co.getValue().equals(value)).findFirst().get().getId();
	}

	public ComboBoxField getComboBoxField() {
		return comboBoxField;
	}

	public void setComboBoxField(ComboBoxField comboBoxField) {
		this.comboBoxField = comboBoxField;
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
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Stage getStage() {
		return stage;
	}

	@Override
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@Override
	public int getSec() {
		return sec;
	}

	@Override
	public void setSec(int sec) {
		this.sec = sec;
	}

	@Override
	public ComboBoxPropValue getPropValue() {
		return comboBoxPropValue;
	}
	
	@Override
	public void setPropValue(PropValue propValue) {
		this.comboBoxPropValue = (ComboBoxPropValue) propValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + sec;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		ComboBoxProp other = (ComboBoxProp) obj;
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sec != other.sec)
			return false;
		if (stage == null) {
			if (other.stage != null)
				return false;
		} else if (!stage.equals(other.stage))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
}
