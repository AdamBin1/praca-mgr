package com.springmvc.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.springmvc.data.model.Stage;

@Component
public interface StageDAO {
	
	public List<Stage> getAllStages();
	
	public void init();

	public Stage getStageForName(String name);

	public void insertOrUpdateStage(Stage stage);

}
