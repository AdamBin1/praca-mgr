package com.springmvc.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.springmvc.data.model.ComboOption;

@Component
public interface ComboOptionDAO {
	
	public List<ComboOption> getComboOptionsForComboId(int comboId);
	
	public void insertComboOption(ComboOption cbf);

}
