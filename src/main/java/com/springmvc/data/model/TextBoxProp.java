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
@Table(name = "TEXT_PROPS")
public class TextBoxProp implements Property{

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
	
	@Column(name = "LENGTH")	
	private int length;
	
	@Transient
	TextBoxPropValue textBoxPropValue;
	
	public TextBoxProp() {
		super();
		this.type = FieldType.TEXT;
	}

	public TextBoxProp(Integer id, String name, Stage stage, int sequence, boolean saveRequired, int length) {
		super();
		this.id = id;
		this.name = name;
		this.stage = stage;
		this.sec = sequence;
		this.type = FieldType.TEXT;
		this.length = length;
	}
	
	public TextBoxProp(TextBoxProp prop) {
		this.id = prop.id;
		this.name = prop.name;
		this.stage = prop.stage;
		this.sec = prop.sec;
		this.type = FieldType.TEXT;
		this.length = prop.length;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
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
	public TextBoxPropValue getPropValue() {
		return textBoxPropValue;
	}
	
	@Override
	public void setPropValue(PropValue propValue) {
		this.textBoxPropValue = (TextBoxPropValue) propValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + length;
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
		TextBoxProp other = (TextBoxProp) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (length != other.length)
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
