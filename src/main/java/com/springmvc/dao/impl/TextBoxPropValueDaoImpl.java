package com.springmvc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.springmvc.dao.TextBoxPropValueDAO;
import com.springmvc.data.model.TextBoxPropValue;

@Repository
@EnableTransactionManagement
public class TextBoxPropValueDaoImpl implements TextBoxPropValueDAO{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<TextBoxPropValue> getTextBoxPropValuesForStageId(int objectId, short stageId) {
		Query q = entityManager.createQuery("SELECT tbpv FROM TextBoxPropValue tbpv WHERE tbpv.objectId = " + objectId + "tbpv.stageId = " + stageId);
		return q.getResultList();
	}
	
//	@Override
//	public List<TextBoxPropValue> getNewTextBoxPropValuesForStageId(short stageId) {
//		return list.parallelStream().filter(tbpv -> tbpv.getProperty().getStage() == stageId).collect(Collectors.toList());
//	}

	@Override
	public void insertOrUpdateTextBoxPropValue(TextBoxPropValue tbpv) {
		entityManager.merge(tbpv);
	}

}
