package com.springmvc.dao.impl;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.springmvc.data.model.Stage;

public interface StageDaoImpl extends Repository<Stage, Integer> {

	public List<Stage> findAll();
	
	public Stage findById(Integer id);

	public Stage findByName(String name);
	
	public Stage save(Stage stage);
	
	public int countByName(String name);
	
	public int countByIdNotAndName(Integer id, String name);
	
	public int countBySec(int sec);
	
	public int countByIdNotAndSec(Integer id, int sec);
	
//	@Override
//	public Stage getMainStage() {
//		Query q = entityManager.createQuery("SELECT s FROM Stage s WHERE s.name = null");
//		
//		//dla pustej bazy inicjalizacja main stage
//		if(q.getResultList().isEmpty()) {
//			Stage s = new Stage(null, null, null, null, null, null);
//			insertOrUpdateStage(s);
//			q = entityManager.createQuery("SELECT s FROM Stage s WHERE s.name = null");
//		}
//		
//		Stage s = (Stage) q.getSingleResult();
//		return s;
//	}

}
