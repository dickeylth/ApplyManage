package com.dickey.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dickey.dao.AuthorityDao;
import com.dickey.domain.*;

public class AuthorityDaoImpl extends HibernateDaoSupport implements AuthorityDao {

	@Override
	public Authority get(String id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(Authority.class, id);
	}

	@Override
	public String save(Authority authority) {
		// TODO Auto-generated method stub
		return (String) getHibernateTemplate().save(authority);
	}

	@Override
	public void update(Authority authority) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(authority);
	}

	@Override
	public void delete(Authority authority) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(authority);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(get(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Authority> findAll() {
		// TODO Auto-generated method stub
		return (List<Authority>)getHibernateTemplate().find(" from Authority");
	}

	
}
