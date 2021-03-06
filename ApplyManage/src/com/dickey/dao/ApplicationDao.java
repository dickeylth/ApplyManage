package com.dickey.dao;

import java.util.List;

import com.dickey.domain.Application;
import com.dickey.domain.User;

public interface ApplicationDao {
	
	Application get(String id);
	
	String save(Application application);
	
	void update(Application application);
	
	void delete(Application application);
	
	void delete(String id);
	
	List<Application> getAll();
	
	List<Application> findByUser(User user);
	
	List<Application> findByProp(String property, String keyword, boolean userRel, User user);

	List<Application> findByRef(String refClass, String refId);
}
