package com.springmvc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.springmvc.dao.StageDAO;
import com.springmvc.dao.service.ComboBoxPropService;
import com.springmvc.dao.service.DateTextBoxPropService;
import com.springmvc.dao.service.TextBoxPropService;
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
	public Stage getStageForId(int id) {
		for(Stage s:list) {
			if(s.getId() == id) {
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
		TextBoxPropService tbps = new TextBoxPropService();
		DateTextBoxPropService dtbps = new DateTextBoxPropService();
		
		list2.addAll(cbps.getAllComboBoxProperties());
		list2.addAll(tbps.getAllTextBoxProperties());
		list2.addAll(dtbps.getAllDateTextBoxProperties());
				
		list.add(new Stage(0, null, null, list2));
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
