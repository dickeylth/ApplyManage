package com.dickey.service.impl;

import java.util.List;

import org.apache.shiro.authz.annotation.*;

import com.dickey.dao.ApplicationDao;
import com.dickey.dao.UserDao;
import com.dickey.domain.Application;
import com.dickey.domain.User;
import com.dickey.service.UserService;

public class UserServiceImpl implements UserService{
	
	private UserDao userDao;
	private ApplicationDao applicationDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public ApplicationDao getApplicationDao() {
		return applicationDao;
	}

	public void setApplicationDao(ApplicationDao applicationDao) {
		this.applicationDao = applicationDao;
	}

	@Override
	@RequiresRoles("ROLE_ADMIN")
	public String addUser(User user) {
		return userDao.save(user);
	}

	@Override
	public void delUser(User user) {
		userDao.delete(user);
	}

	@Override
	public void delUser(String id) {
		userDao.delete(id);
	}

	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}

	@Override
	public User findUserByName(String username) {
		return userDao.findUserByName(username);
	}

	@Override
	public User findUser(String id) {
		// TODO Auto-generated method stub
		return userDao.get(id);
	}
	
	@Override
	public List<User> findUsers() {
		return userDao.findAll();
	}

	@Override
	public List<User> findUsersByProp(String property, String keyword, boolean userRel, User user) {
		// TODO Auto-generated method stub
		return userDao.findByProp(property, keyword, userRel, user);
	}

	@Override
	public String addApplication(Application application) {
		return applicationDao.save(application);
	}

	@Override
	public void delApplication(Application application) {
		applicationDao.delete(application);
	}

	@Override
	public void delApplication(String id) {
		applicationDao.delete(id);
	}

	@Override
	public void updateApplication(Application application) {
		applicationDao.update(application);
	}

	@Override
	public Application findApplication(String id) {
		return applicationDao.get(id);
	}
	
	@Override
	public List<Application> findApplications() {
		return applicationDao.findAll();
	}

	@Override
	public List<Application> findApplicationsByUser(User user) {
		return applicationDao.findByUser(user);
	}

	@Override
	public List<Application> findApplicationsByProp(String property, String keyword, boolean userRel, User user) {
		// TODO Auto-generated method stub
		return applicationDao.findByProp(property, keyword, userRel, user);
	}

}
