package com.springmvc.dao.impl;

import java.util.List;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.springmvc.data.model.ComboBoxField;

@EnableTransactionManagement
public interface ComboBoxFieldDaoImpl extends Repository<ComboBoxField, Integer>{

	public List<ComboBoxField> findAll();
	
	public ComboBoxField findById(Integer id);

	public ComboBoxField save(ComboBoxField cbf);
	
	public int countByName(String name);
	
	public int countByIdNotAndName(Integer id, String name);

}
