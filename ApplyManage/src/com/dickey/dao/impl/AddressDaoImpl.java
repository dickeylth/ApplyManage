package com.dickey.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dickey.dao.AddressDao;
import com.dickey.domain.Address;
import com.dickey.domain.User;

public class AddressDaoImpl extends HibernateDaoSupport implements AddressDao{

	@Override
	public Address get(String id) {
		// TODO Auto-generated method stub	
		return getHibernateTemplate().get(Address.class, id);
	}

	@Override
	public String save(Address address) {
		// TODO Auto-generated method stub
		return (String) getHibernateTemplate().save(address);
	}

	@Override
	public void update(Address address) {
		// TODO Auto-generated method stub
		getHibernateTemplate().merge(address);
	}

	@Override
	public void delete(Address address) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(address);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(get(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Address> getAll() {
		// TODO Auto-generated method stub
		return (List<Address>)getHibernateTemplate().find(" from Address");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Address> findByUser(User user) {
		// TODO Auto-generated method stub
		return (List<Address>)getHibernateTemplate().find(" from Address as a where a.user = ?", user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Address> findByProp(String property, String keyword, boolean userRel, User user) {
		// TODO Auto-generated method stub
		if(userRel){
			return (List<Address>)getHibernateTemplate().find(" from Address as a where a." + property + " like '%" + keyword + "%' and a.user = ?", user);
		}else{
			return (List<Address>)getHibernateTemplate().find(" from Address as a where a." + property + " like '%" + keyword + "%'");
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Address> findByRef(String refClass, String refId) {
		return (List<Address>)getHibernateTemplate().find(" from Address as a where a." + refClass + ".id = ?", refId);
	}
	
}
