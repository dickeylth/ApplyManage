package com.dickey.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dickey.dao.ApplicationDao;
import com.dickey.domain.Application;
import com.dickey.domain.User;

public class ApplicationDaoImpl extends HibernateDaoSupport implements ApplicationDao{

	@Override
	public Application get(String id) {
		// TODO Auto-generated method stub	
		return getHibernateTemplate().get(Application.class, id);
	}

	@Override
	public String save(Application application) {
		// TODO Auto-generated method stub
		return (String) getHibernateTemplate().save(application);
	}

	@Override
	public void update(Application application) {
		// TODO Auto-generated method stub
		getHibernateTemplate().merge(application);
	}

	@Override
	public void delete(Application application) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(application);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(get(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Application> findAll() {
		// TODO Auto-generated method stub
		return (List<Application>)getHibernateTemplate().find(" from Application");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Application> findByUser(User user) {
		// TODO Auto-generated method stub
		return (List<Application>)getHibernateTemplate().find(" from Application as a where a.user = ?", user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Application> findByProp(String property, String keyword, boolean userRel, User user) {
		// TODO Auto-generated method stub
		if(userRel){
			return (List<Application>)getHibernateTemplate().find(" from Application as a where a." + property + " like '%" + keyword + "%' and a.user = ?", user);
		}else{
			return (List<Application>)getHibernateTemplate().find(" from Application as a where a." + property + " like '%" + keyword + "%'");
		}
		
	}
	
}
