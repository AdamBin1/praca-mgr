package com.springmvc.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.springmvc.data.model.ObjectModel;

public interface ObjectDAO extends Repository<ObjectModel, Integer> {

	public List<ObjectModel> findAll();
	
	public ObjectModel findById(Integer id);

	public ObjectModel save(ObjectModel object);
	
}
