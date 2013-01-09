package com.dickey.dao;

import java.util.List;

import com.dickey.domain.Authority;

public interface AuthorityDao {
	
	Authority get(String id);
	
	String save(Authority authority);
	
	void update(Authority authority);
	
	void delete(Authority authority);
	
	void delete(String id);
	
	List<Authority> findAll();
	
}
