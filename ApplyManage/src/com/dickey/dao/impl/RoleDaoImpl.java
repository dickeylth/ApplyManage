package com.dickey.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dickey.dao.RoleDao;
import com.dickey.domain.Role;


public class RoleDaoImpl extends HibernateDaoSupport implements RoleDao {

	@Override
	public Role get(String id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(Role.class, id);
	}

	@Override
	public String save(Role role) {
		// TODO Auto-generated method stub
		return (String) getHibernateTemplate().save(role);
	}

	@Override
	public void update(Role role) {
		// TODO Auto-generated method stub
		getHibernateTemplate().merge(role);
	}

	@Override
	public void delete(Role role) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(role);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(get(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAll() {
		// TODO Auto-generated method stub
		return (List<Role>)getHibernateTemplate().find(" from Role");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findByProp(String property, String keyword) {
		// TODO Auto-generated method stub
		return (List<Role>)getHibernateTemplate().find(" from Role as a where a." + property + " like '%" + keyword + "%'");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findByRef(String refClass, String refId) {
		// TODO Auto-generated method stub
		return (List<Role>)getHibernateTemplate().find(" from Role as a where a." + refClass + ".id = ?", refId);
	}


}
