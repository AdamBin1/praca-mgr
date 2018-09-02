package com.springmvc.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.springmvc.dao.ComboOptionDAO;
import com.springmvc.data.model.ComboOption;

@Component
public class ComboOptionDaoImpl implements ComboOptionDAO {

	private static List<ComboOption> list;
	
	static{
		list = new ArrayList<ComboOption>();
		list.add(new ComboOption(1, "Opcja 1 z combo 1", 1, 1));
		list.add(new ComboOption(2, "Opcja 2 z combo 1", 2, 1));
		list.add(new ComboOption(3, "Opcja 3 z combo 1", 3, 1));
		list.add(new ComboOption(4, "Opcja 1 z combo 2", 1, 2));
	}
		
//	@Autowired
//	private SessionFactory sessionFactory;

	@Override
	public List<ComboOption> getComboOptionsForComboId(int comboId) {
		//Session session = sessionFactory.openSession();
		
		return list.stream().filter(cb->comboId == cb.getComboBoxFieldId()).collect(Collectors.toList());
		
	}

	@Override
	public void insertComboOption(ComboOption cbf) {
		// TODO Auto-generated method stub
		
	}

}
