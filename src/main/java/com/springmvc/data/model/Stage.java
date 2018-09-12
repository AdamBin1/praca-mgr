package com.springmvc.data.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "STAGES")
public class Stage{
	
	@Id
	@Column(name = "ID", nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	
	@Column(name = "NAME")
	private String name;	//null for main stage
	
	@Column(name = "SEC")
	private Integer sec;
	
	@Column(name = "TEXT_PROPERTIES")
	@OneToMany(orphanRemoval=true, fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "stage")
	private Set<TextBoxProp> textBoxProperties;
	
	@Column(name = "COMBO_PROPERTIES")
	@OneToMany(orphanRemoval=true, fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "stage")
	private Set<ComboBoxProp> comboBoxProperties;
	
	@Column(name = "DATE_PROPERTIES")
	@OneToMany(orphanRemoval=true, fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "stage")
	private Set<DateTextBoxProp> dateTextBoxProperties;
	
	@Transient
	private List<Property> properties;
	
	public Stage() {
	}
	
	public Stage(Integer id) {
		this.id = id;
	}
	
	public Stage(Integer id, String name, Integer sec, List<Property> properties) {
		this.id = id;
		this.name = name;
		this.sec = sec;
		
		this.properties = properties;
	}

	public Stage(Integer id, String name, Integer sec, 
			Set<TextBoxProp> textBoxProperties,
			Set<ComboBoxProp> comboBoxProperties,
			Set<DateTextBoxProp> dateTextBoxProperties) {
		this.id = id;
		this.name = name;
		this.sec = sec;
		this.textBoxProperties = textBoxProperties;
		this.comboBoxProperties = comboBoxProperties;
		this.dateTextBoxProperties = dateTextBoxProperties;
		
		updateProperties();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSec() {
		return sec;
	}

	public void setSec(Integer sec) {
		this.sec = sec;
	}

	public Set<TextBoxProp> getTextBoxProperties() {
		return textBoxProperties;
	}

	public void setTextBoxProperties(Set<TextBoxProp> textBoxProperties) {
		this.textBoxProperties = textBoxProperties;
	}

	public Set<ComboBoxProp> getComboBoxProperties() {
		return comboBoxProperties;
	}

	public void setComboBoxProperties(Set<ComboBoxProp> comboBoxProperties) {
		this.comboBoxProperties = comboBoxProperties;
	}

	public Set<DateTextBoxProp> getDateTextBoxProperties() {
		return dateTextBoxProperties;
	}

	public void setDateTextBoxProperties(Set<DateTextBoxProp> dateTextBoxProperties) {
		this.dateTextBoxProperties = dateTextBoxProperties;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	public void updateProperties() {
		if(textBoxProperties != null && comboBoxProperties != null && dateTextBoxProperties != null) {
			this.properties = new ArrayList<>();
			this.properties.addAll(textBoxProperties);
			this.properties.addAll(comboBoxProperties);
			this.properties.addAll(dateTextBoxProperties);
			
			this.properties.sort((p1,p2) -> p1.getSec() - p2.getSec());
		}
	}
	
	public boolean isMainStage() {
		return this.getName() == null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((properties == null) ? 0 : properties.hashCode());
		result = prime * result + ((sec == null) ? 0 : sec.hashCode());
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
		Stage other = (Stage) obj;
		if (comboBoxProperties == null) {
			if (other.comboBoxProperties != null)
				return false;
		} else if (!comboBoxProperties.equals(other.comboBoxProperties))
			return false;
		if (dateTextBoxProperties == null) {
			if (other.dateTextBoxProperties != null)
				return false;
		} else if (!dateTextBoxProperties.equals(other.dateTextBoxProperties))
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
		if (properties == null) {
			if (other.properties != null)
				return false;
		} else if (!properties.equals(other.properties))
			return false;
		if (sec == null) {
			if (other.sec != null)
				return false;
		} else if (!sec.equals(other.sec))
			return false;
		if (textBoxProperties == null) {
			if (other.textBoxProperties != null)
				return false;
		} else if (!textBoxProperties.equals(other.textBoxProperties))
			return false;
		return true;
	}

	public Stage cloneStageProperties() {
		Stage clonedStage = new Stage();
		
		Set<TextBoxProp> textBoxProps = new HashSet<>();
		this.getTextBoxProperties().forEach( prop -> {
			textBoxProps.add(new TextBoxProp(prop));
		});
		
		clonedStage.setTextBoxProperties(textBoxProps);
		
		Set<ComboBoxProp> comboBoxProps = new HashSet<>();
		this.getComboBoxProperties().forEach( prop -> {
			comboBoxProps.add(new ComboBoxProp(prop));
		});
		
		clonedStage.setComboBoxProperties(comboBoxProps);
		
		Set<DateTextBoxProp> dateTextBoxProps = new HashSet<>();
		this.getDateTextBoxProperties().forEach( prop -> {
			dateTextBoxProps.add(new DateTextBoxProp(prop));
		});
		
		clonedStage.setDateTextBoxProperties(dateTextBoxProps);
		
		clonedStage.updateProperties();
		
		return clonedStage;
	}

}
