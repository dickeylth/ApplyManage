package com.dickey.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dickey.dao.PermissionDao;
import com.dickey.domain.Permission;


public class PermissionDaoImpl extends HibernateDaoSupport implements PermissionDao {

	@Override
	public Permission get(String id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(Permission.class, id);
	}

	@Override
	public String save(Permission permission) {
		// TODO Auto-generated method stub
		return (String) getHibernateTemplate().save(permission);
	}

	@Override
	public void update(Permission permission) {
		// TODO Auto-generated method stub
		getHibernateTemplate().merge(permission);
	}

	@Override
	public void delete(Permission Permission) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(Permission);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(get(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> findAll() {
		// TODO Auto-generated method stub
		return (List<Permission>)getHibernateTemplate().find(" from Permission");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> findByProp(String property, String keyword) {
		// TODO Auto-generated method stub
		return (List<Permission>)getHibernateTemplate().find(" from Permission as a where a." + property + " like '%" + keyword + "%'");
	}


}
