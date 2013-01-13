package com.dickey.dao;

import java.util.List;

import com.dickey.domain.Permission;

public interface PermissionDao {
	Permission get(String id);
	
	String save(Permission permission);
	
	void update(Permission permission);
	
	void delete(Permission permission);
	
	void delete(String id);
	
	List<Permission> findAll();
	
	List<Permission> findByProp(String property, String keyword);
}
