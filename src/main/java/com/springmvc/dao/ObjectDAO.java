package com.springmvc.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.Repository;

import com.springmvc.model.ObjectModel;

public interface ObjectDAO extends Repository<ObjectModel, Integer> {

	public List<ObjectModel> findAll();

	public ObjectModel findById(Integer id);

	public ObjectModel save(ObjectModel object);

	@Transactional
	public void delete(ObjectModel object);

}
