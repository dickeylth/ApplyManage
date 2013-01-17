package com.dickey.dao;

import java.util.List;

import com.dickey.domain.Role;

public interface RoleDao {
	Role get(String id);
	
	String save(Role role);
	
	void update(Role role);
	
	void delete(Role role);
	
	void delete(String id);
	
	List<Role> getAll();
	
	List<Role> findByProp(String property, String keyword);

	List<Role> findByRef(String refClass, String refId);
}
