package com.springmvc.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.springmvc.data.model.Stage;

public interface StageDAO extends Repository<Stage, Integer> {

	public List<Stage> findAll();
	
	public Stage findById(Integer id);

	public Stage findByName(String name);
	
	public Stage save(Stage stage);
	
	public int countByName(String name);
	
	public int countByIdNotAndName(Integer id, String name);
	
	public int countBySec(int sec);
	
	public int countByIdNotAndSec(Integer id, int sec);

	public Stage findFirstByNameNotNullOrderBySec();
	
}
