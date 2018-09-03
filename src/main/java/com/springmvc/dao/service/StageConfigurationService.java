package com.springmvc.dao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springmvc.dao.StageDAO;
import com.springmvc.dao.impl.StageDaoImpl;
import com.springmvc.data.model.Stage;

@Service
public class StageConfigurationService {
	

	private StageDAO stageDAO; 
	
	public StageConfigurationService() {
		stageDAO = new StageDaoImpl();
	}
	
	public Stage getStageForName(String name) {
		stageDAO.init();
		Stage stage = stageDAO.getStageForName(name);
		stage.getProperties().sort((op1, op2) -> op1.getSec() - op2.getSec());
		return stage;
	}
/*	
	public ComboBoxField getComboBoxFieldForId(int id) {
		ComboBoxField cbf = comboBoxFieldDAO.getComboBoxFieldForId(id);
		cbf.getOptions().sort((op1, op2) -> op1.getSec() - op2.getSec());
		return cbf;
	}
*/	
	public List<Stage> getAllStages(){
		stageDAO.init();
		List<Stage> list = stageDAO.getAllStages();
		// sortowanie po sec
		list.sort((s1, s2) -> s1.getSec().compareTo(s2.getSec()));
		return list;
	}
	
	public void saveStage(Stage stage) {
		stageDAO.insertOrUpdateStage(stage);
	}
	
}
