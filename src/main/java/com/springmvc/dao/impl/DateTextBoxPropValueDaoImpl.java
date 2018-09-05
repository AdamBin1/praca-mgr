package com.springmvc.dao.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.springmvc.dao.DateTextBoxPropValueDAO;
import com.springmvc.dao.service.DateTextBoxPropService;
import com.springmvc.data.model.DateTextBoxPropValue;

@Component
public class DateTextBoxPropValueDaoImpl implements DateTextBoxPropValueDAO{

	private static List<DateTextBoxPropValue> list;
	
	@Override
	public List<DateTextBoxPropValue> getDateTextBoxPropValuesForStageId(int objectId, short stageId) {
		return list.parallelStream().filter(ctbpv -> ctbpv.getObjectId() == objectId && ctbpv.getProperty().getStage() == stageId).collect(Collectors.toList());
	}
	
	@Override
	public List<DateTextBoxPropValue> getNewDateTextBoxPropValuesForStageId(short stageId) {
		return list.parallelStream().filter(ctbpv -> ctbpv.getProperty().getStage() == stageId).collect(Collectors.toList());
	}

	@Override
	public void insertOrUpdateDateTextBoxPropValue(DateTextBoxPropValue dtbpv) {
		if(dtbpv.getId() == -1) {
			list.add(dtbpv);
		} else {
			list.removeIf(o->dtbpv.getId() == o.getId());
			list.add(dtbpv);
		}
		
	}

	@PostConstruct
	public void init() {
			list = new ArrayList<DateTextBoxPropValue>();
			DateTextBoxPropService dtbps = new DateTextBoxPropService();
			
			list.add(new DateTextBoxPropValue(1, LocalDate.of(2005, 05, 20), 0, dtbps.getDateTextBoxPropertyForId(7)));
			list.add(new DateTextBoxPropValue(0, null, 0, dtbps.getDateTextBoxPropertyForId(8)));
//			list.add(new ComboBoxProp(2, "Combo prop 2", (short)0, 25, false, cbcs.getComboBoxFieldForId(2)));

	}

}
