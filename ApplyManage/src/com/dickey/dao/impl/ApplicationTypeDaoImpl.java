package com.dickey.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dickey.dao.ApplicationTypeDao;
import com.dickey.domain.ApplicationType;
import com.dickey.domain.User;

public class ApplicationTypeDaoImpl extends HibernateDaoSupport implements ApplicationTypeDao{

	@Override
	public ApplicationType get(String id) {
		// TODO Auto-generated method stub	
		return getHibernateTemplate().get(ApplicationType.class, id);
	}

	@Override
	public String save(ApplicationType applicationType) {
		// TODO Auto-generated method stub
		return (String) getHibernateTemplate().save(applicationType);
	}

	@Override
	public void update(ApplicationType applicationType) {
		// TODO Auto-generated method stub
		getHibernateTemplate().merge(applicationType);
	}

	@Override
	public void delete(ApplicationType applicationType) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(applicationType);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(get(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ApplicationType> findAll() {
		// TODO Auto-generated method stub
		return (List<ApplicationType>)getHibernateTemplate().find(" from ApplicationType");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ApplicationType> findByUser(User user) {
		// TODO Auto-generated method stub
		return (List<ApplicationType>)getHibernateTemplate().find(" from ApplicationType as a where a.user = ?", user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ApplicationType> findByProp(String property, String keyword, boolean userRel, User user) {
		// TODO Auto-generated method stub
		if(userRel){
			return (List<ApplicationType>)getHibernateTemplate().find(" from ApplicationType as a where a." + property + " like '%" + keyword + "%' and a.user = ?", user);
		}else{
			return (List<ApplicationType>)getHibernateTemplate().find(" from ApplicationType as a where a." + property + " like '%" + keyword + "%'");
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ApplicationType> findByRef(String refClass, String refId) {
		return (List<ApplicationType>)getHibernateTemplate().find(" from ApplicationType as a where a." + refClass + ".id = ?", refId);
	}
	
}
