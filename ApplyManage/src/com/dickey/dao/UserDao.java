package com.dickey.dao;

import java.util.List;

import com.dickey.domain.User;

public interface UserDao {
	User get(String id);
	
	User findUserByName(String username);
	
	String save(User user);
	
	void update(User user);
	
	void delete(User user);
	
	void delete(String id);
	
	List<User> findAll();
	
	List<User> findByProp(String property, String keyword);

	List<User> findByRef(String refClass, String refId);
	
}
