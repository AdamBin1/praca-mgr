package com.springmvc.dao;

import org.springframework.data.repository.Repository;

import com.springmvc.model.TextBoxProp;

public interface TextBoxPropDAO extends Repository<TextBoxProp, Integer> {
	public TextBoxProp findById(Integer id);
}
