package com.springmvc.dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.dao.impl.StageDaoImpl;
import com.springmvc.data.model.Stage;

@Service
@Transactional
public class StageConfigurationService {
	
	@Autowired
	private StageDaoImpl stageDAO; 
	
	public StageConfigurationService() {
	}
	
	public Stage getStageForId(int id) {
		Stage stage = stageDAO.findById(id);
		
		stage.updateProperties();
		
		return stage;
	}
	
	// wszystkie oprócz tej bez nazwy, bo ona jest main
	public List<Stage> getAllStages(){
		List<Stage> stages = stageDAO.findAll();
		
		//usuniecie main stage
		for(int i=0;i<stages.size();i++) {
			if(stages.get(i).getName()==null) {
				stages.remove(i);
				break;
			}
		}
		
		stages.sort((s1, s2) -> s1.getSec()-s2.getSec());
		stages.parallelStream().forEach(s -> s.updateProperties());
		return stages;
	}
	
	public void saveStage(Stage stage) {
		stageDAO.save(stage);
	}

	public Object getMainStage() {
		Stage stage = stageDAO.findByName(null);
		if(stage == null) {
			Stage s = new Stage(null, null, null, null, null, null);
			stageDAO.save(s);
			stage = stageDAO.findByName(null);
		}
		stage.updateProperties();
		return stage;
	}

	public boolean isNameInDatabase(Integer id, String name) {
		if(id == null) {
			return stageDAO.countByName(name) < 1;
		} else {
			return stageDAO.countByIdNotAndName(id, name) < 1;
		}
	}

	public boolean isSecInDatabase(Integer id, int sec) {
		if(id == null) {
			return stageDAO.countBySec(sec) < 1;
		} else {
			return stageDAO.countByIdNotAndSec(id, sec) < 1;
		}
	}
	
}
