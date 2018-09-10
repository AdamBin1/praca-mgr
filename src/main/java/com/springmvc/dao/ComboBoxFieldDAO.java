package com.springmvc.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.springmvc.data.model.ComboBoxField;

public interface ComboBoxFieldDAO extends Repository<ComboBoxField, Integer>{

	public List<ComboBoxField> findAll();
	
	public ComboBoxField findById(Integer id);

	public ComboBoxField save(ComboBoxField cbf);
	
	public int countByName(String name);
	
	public int countByIdNotAndName(Integer id, String name);

}