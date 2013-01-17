package com.dickey.dao;

import java.util.List;

import com.dickey.domain.Address;
import com.dickey.domain.User;

public interface AddressDao {
	
	Address get(String id);
	
	String save(Address address);
	
	void update(Address address);
	
	void delete(Address address);
	
	void delete(String id);
	
	List<Address> getAll();
	
	List<Address> findByUser(User user);
	
	List<Address> findByProp(String property, String keyword, boolean userRel, User user);

	List<Address> findByRef(String refClass, String refId);
}
