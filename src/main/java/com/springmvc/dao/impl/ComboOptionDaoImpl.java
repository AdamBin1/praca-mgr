package com.springmvc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.springmvc.dao.ComboOptionDAO;
import com.springmvc.data.model.ComboBoxField;
import com.springmvc.data.model.ComboOption;


@Component
public class ComboOptionDaoImpl implements ComboOptionDAO {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	@Transactional
	public List<ComboOption> getComboOptionsForComboId(int comboId) {
	ComboBoxField cbf = entityManager.find(ComboBoxField.class, comboId);
		return cbf.getOptions();
	}

	@Override
	public void insertComboOption(ComboOption cbf) {
		entityManager.merge(cbf);	
	}

}
