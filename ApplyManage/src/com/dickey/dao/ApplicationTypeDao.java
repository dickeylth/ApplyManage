package com.dickey.dao;

import java.util.List;

import com.dickey.domain.ApplicationType;
import com.dickey.domain.User;

public interface ApplicationTypeDao {
	
	ApplicationType get(String id);
	
	String save(ApplicationType applicationType);
	
	void update(ApplicationType applicationType);
	
	void delete(ApplicationType applicationType);
	
	void delete(String id);
	
	List<ApplicationType> findAll();
	
	List<ApplicationType> findByUser(User user);
	
	List<ApplicationType> findByProp(String property, String keyword, boolean userRel, User user);

	List<ApplicationType> findByRef(String refClass, String refId);
}
