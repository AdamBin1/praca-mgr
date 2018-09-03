package com.springmvc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.springmvc.dao.StageDAO;
import com.springmvc.dao.service.ComboBoxPropService;
import com.springmvc.data.model.Property;
import com.springmvc.data.model.Stage;

@Component
public class StageDaoImpl implements StageDAO{

	private static List<Stage> list = null;

	@Override
	public List<Stage> getAllStages() {
		return list;
	}
	
	@Override
	public Stage getStageForName(String name) {
		for(Stage s:list) {
			if(s.getName().equals(name)) {
				return s;
			}
		}
		return null;
	}

	public void init() {
		if(list != null) {
			return;
		}
		list = new ArrayList<Stage>();
		ArrayList<Property> list2 = new ArrayList<>();
		ComboBoxPropService cbps = new ComboBoxPropService();
		list2.addAll(cbps.getAllComboBoxProperties());
		
		list.add(new Stage(1, "Etap 1", 1, list2));
		list.add(new Stage(2, "Etap 2", 20, list2));
		list.add(new Stage(3, "Etap 3", 30, list2));
	}

	@Override
	public void insertOrUpdateStage(Stage stage) {
		// TODO: podczas zamieniania na hibernate trzeba zadbać o zapis elementów z listy opcji
		init();
		if(stage.getId() == null) {
			list.add(stage);
		} else {
			list.removeIf(o->stage.getId() == o.getId());
			list.add(stage);
		}		
	}



}
