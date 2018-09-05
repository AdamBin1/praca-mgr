package com.springmvc.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.springmvc.dao.ComboBoxPropValueDAO;
import com.springmvc.dao.service.ComboBoxPropService;
import com.springmvc.data.model.ComboBoxPropValue;

@Component
public class ComboBoxPropValueDaoImpl implements ComboBoxPropValueDAO{

	private static List<ComboBoxPropValue> list;
	
	@Override
	public List<ComboBoxPropValue> getComboBoxPropValuesForStageId(int objectId, short stageId) {
		return list.parallelStream().filter(cbpv -> cbpv.getObjectId() == objectId && cbpv.getProperty().getStage() == stageId).collect(Collectors.toList());
	}
	
	@Override
	public List<ComboBoxPropValue> getNewComboBoxPropValuesForStageId(short stageId) {
		return list.parallelStream().filter(cbpv -> cbpv.getProperty().getStage() == stageId).collect(Collectors.toList());
	}

	@Override
	public void insertOrUpdateComboBoxPropValue(ComboBoxPropValue cbpv) {
		if(cbpv.getId() == -1) {
			list.add(cbpv);
		} else {
			list.removeIf(o->cbpv.getId() == o.getId());
			list.add(cbpv);
		}
		
	}

	@PostConstruct
	public void init() {
			list = new ArrayList<ComboBoxPropValue>();
			ComboBoxPropService cbps = new ComboBoxPropService();
			
			list.add(new ComboBoxPropValue(1, null, 0, cbps.getComboBoxPropertyForId(1)));
//			list.add(new ComboBoxProp(2, "Combo prop 2", (short)0, 25, false, cbcs.getComboBoxFieldForId(2)));

	}

}
