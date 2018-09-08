package com.springmvc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.springmvc.dao.DateTextBoxPropValueDAO;
import com.springmvc.data.model.DateTextBoxPropValue;

@Repository
@EnableTransactionManagement
public class DateTextBoxPropValueDaoImpl implements DateTextBoxPropValueDAO{

	@PersistenceContext
	EntityManager entityManager;
		
	@Override
	public List<DateTextBoxPropValue> getDateTextBoxPropValuesForStageId(int objectId, short stageId) {
		Query q = entityManager.createQuery("SELECT dtbpv FROM DateTextBoxPropValue dtbpv WHERE dtbpv.objectId = " + objectId + "dtbpv.stageId = " + stageId);
		return q.getResultList();
	}
	
//	@Override
//	public List<DateTextBoxPropValue> getNewDateTextBoxPropValuesForStageId(short stageId) {
//		return list.parallelStream().filter(ctbpv -> ctbpv.getProperty().getStage() == stageId).collect(Collectors.toList());
//	}

	@Override
	public void insertOrUpdateDateTextBoxPropValue(DateTextBoxPropValue dtbpv) {
		entityManager.merge(dtbpv);
	}

}
