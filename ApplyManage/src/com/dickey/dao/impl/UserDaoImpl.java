package com.dickey.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dickey.dao.UserDao;
import com.dickey.domain.User;


public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public User get(String id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(User.class, id);
	}
	
	@Override
	public User findUserByName(String username) {
		//根据用户名查找用户
		return (User) getHibernateTemplate().find(" from User where username = ?", username).get(0);
	}

	@Override
	public String save(User user) {
		// TODO Auto-generated method stub
		return (String) getHibernateTemplate().save(user);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		getHibernateTemplate().merge(user);
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(user);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(get(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return (List<User>)getHibernateTemplate().find(" from User");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByProp(String property, String keyword) {
		// TODO Auto-generated method stub
		return (List<User>)getHibernateTemplate().find(" from User as a where a." + property + " like '%" + keyword + "%'");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByRef(String refClass, String refId) {
		// TODO Auto-generated method stub
		return (List<User>)getHibernateTemplate().find(" from User as a where a." + refClass + ".id = ?", refId);
	}


}
