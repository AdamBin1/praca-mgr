package com.springmvc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.springmvc.dao.ComboBoxPropValueDAO;
import com.springmvc.data.model.ComboBoxPropValue;

@Repository
@EnableTransactionManagement
public class ComboBoxPropValueDaoImpl implements ComboBoxPropValueDAO{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<ComboBoxPropValue> getComboBoxPropValuesForStageId(int objectId, short stageId) {
		Query q = entityManager.createQuery("SELECT cbpv FROM ComboBoxPropValue cbpv WHERE cbpv.objectId = " + objectId + " AND cbpv.stageId = " + stageId);
		return q.getResultList();
	}
	
//	@Override
//	public List<ComboBoxPropValue> getNewComboBoxPropValuesForStageId(short stageId) {
//		return list.parallelStream().filter(cbpv -> cbpv.getProperty().getStage() == stageId).collect(Collectors.toList());
//	}

	@Override
	public void insertOrUpdateComboBoxPropValue(ComboBoxPropValue cbpv) {
		entityManager.merge(cbpv);
	}
}
