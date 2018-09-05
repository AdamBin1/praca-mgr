package com.springmvc.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.springmvc.dao.TextBoxPropValueDAO;
import com.springmvc.dao.service.TextBoxPropService;
import com.springmvc.data.model.TextBoxPropValue;

@Component
public class TextBoxPropValueDaoImpl implements TextBoxPropValueDAO{

	private static List<TextBoxPropValue> list;
	
	@Override
	public List<TextBoxPropValue> getTextBoxPropValuesForStageId(int objectId, short stageId) {
		return list.parallelStream().filter(tbpv -> tbpv.getObjectId() == objectId && tbpv.getProperty().getStage() == stageId).collect(Collectors.toList());
	}
	
	@Override
	public List<TextBoxPropValue> getNewTextBoxPropValuesForStageId(short stageId) {
		return list.parallelStream().filter(tbpv -> tbpv.getProperty().getStage() == stageId).collect(Collectors.toList());
	}

	@Override
	public void insertOrUpdateTextBoxPropValue(TextBoxPropValue tbpv) {
		if(tbpv.getId() == -1) {
			list.add(tbpv);
		} else {
			list.removeIf(o->tbpv.getId() == o.getId());
			list.add(tbpv);
		}
		
	}

	@PostConstruct
	public void init() {
			list = new ArrayList<TextBoxPropValue>();
			TextBoxPropService tbps = new TextBoxPropService();
			
			list.add(new TextBoxPropValue(1, "abc", 0, tbps.getTextBoxPropertyForId(3)));
			list.add(new TextBoxPropValue(0, null, 0, tbps.getTextBoxPropertyForId(4)));
//			list.add(new ComboBoxProp(2, "Combo prop 2", (short)0, 25, false, cbcs.getComboBoxFieldForId(2)));

	}

}
