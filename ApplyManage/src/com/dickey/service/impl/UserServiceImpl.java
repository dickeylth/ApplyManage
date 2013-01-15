package com.dickey.service.impl;

import java.util.List;

import org.apache.shiro.authz.annotation.*;

import com.dickey.dao.ApplicationDao;
import com.dickey.dao.ApplicationTypeDao;
import com.dickey.dao.PermissionDao;
import com.dickey.dao.RoleDao;
import com.dickey.dao.UserDao;
import com.dickey.domain.Application;
import com.dickey.domain.ApplicationType;
import com.dickey.domain.Permission;
import com.dickey.domain.Role;
import com.dickey.domain.User;
import com.dickey.service.UserService;

public class UserServiceImpl implements UserService{
	
	private UserDao userDao;
	private ApplicationDao applicationDao;
	private RoleDao roleDao;
	private PermissionDao permissionDao;
	private ApplicationTypeDao applicationTypeDao;
	
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

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public PermissionDao getPermissionDao() {
		return permissionDao;
	}

	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}

	public ApplicationTypeDao getApplicationTypeDao() {
		return applicationTypeDao;
	}

	public void setApplicationTypeDao(ApplicationTypeDao applicationTypeDao) {
		this.applicationTypeDao = applicationTypeDao;
	}

	@Override
	@RequiresPermissions("user:add")
	public String addUser(User user) {
		return userDao.save(user);
	}

	@Override
	@RequiresPermissions("user:delete")
	public void deleteUser(User user) {
		userDao.delete(user);
	}

	@Override
	@RequiresPermissions("user:delete")
	public void deleteUser(String id) {
		userDao.delete(id);
	}

	@Override
	@RequiresPermissions("user:update")
	public void updateUser(User user) {
		userDao.update(user);
	}

	@Override
	public User findUserByName(String username) {
		return userDao.findUserByName(username);
	}

	@Override
	@RequiresPermissions("user:find")
	public User findUser(String id) {
		// TODO Auto-generated method stub
		return userDao.get(id);
	}
	
	@Override
	@RequiresPermissions("user:find")
	public List<User> findUsers() {
		return userDao.findAll();
	}

	@Override
	@RequiresPermissions("user:find")
	public List<User> findUsersByProp(String property, String keyword) {
		// TODO Auto-generated method stub
		return userDao.findByProp(property, keyword);
	}

	@Override
	public List<User> findUsersByRef(String refClass, String refId) {
		// TODO Auto-generated method stub
		return userDao.findByRef(refClass, refId);
	}
	

	@Override
	public String addRole(Role role) {
		// TODO Auto-generated method stub
		return roleDao.save(role);
	}

	@Override
	public void deleteRole(Role role) {
		// TODO Auto-generated method stub
		roleDao.delete(role);
	}

	@Override
	public void deleteRole(String id) {
		// TODO Auto-generated method stub
		roleDao.delete(id);
	}

	@Override
	public void updateRole(Role role) {
		// TODO Auto-generated method stub
		roleDao.update(role);
	}

	@Override
	public Role findRole(String id) {
		// TODO Auto-generated method stub
		return roleDao.get(id);
	}

	@Override
	public List<Role> findRoles() {
		// TODO Auto-generated method stub
		return roleDao.findAll();
	}

	@Override
	public List<Role> findRolesByProp(String property, String keyword) {
		// TODO Auto-generated method stub
		return roleDao.findByProp(property, keyword);
	}

	@Override
	public List<Role> findRolesByRef(String refClass, String refId) {
		// TODO Auto-generated method stub
		return roleDao.findByRef(refClass, refId);
	}


	@Override
	public String addPermission(Permission permission) {
		// TODO Auto-generated method stub
		return permissionDao.save(permission);
	}

	@Override
	public void deletePermission(Permission permission) {
		// TODO Auto-generated method stub
		permissionDao.delete(permission);
	}

	@Override
	public void deletePermission(String id) {
		// TODO Auto-generated method stub
		permissionDao.delete(id);
	}

	@Override
	public void updatePermission(Permission permission) {
		// TODO Auto-generated method stub
		permissionDao.update(permission);
	}

	@Override
	public Permission findPermission(String id) {
		// TODO Auto-generated method stub
		return permissionDao.get(id);
	}

	@Override
	public List<Permission> findPermissions() {
		// TODO Auto-generated method stub
		return permissionDao.findAll();
	}

	@Override
	public List<Permission> findPermissionsByProp(String property, String keyword) {
		// TODO Auto-generated method stub
		return permissionDao.findByProp(property, keyword);
	}

	@Override
	public List<Permission> findPermissionsByRef(String refClass, String refId) {
		// TODO Auto-generated method stub
		return permissionDao.findByRef(refClass, refId);
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
	
	@Override
	public List<Application> findApplicationsByRef(String refClass, String refId) {
		// TODO Auto-generated method stub
		return applicationDao.findByRef(refClass, refId);
	}
	
	
	@Override
	public String addApplicationType(ApplicationType applicationType) {
		return applicationTypeDao.save(applicationType);
	}

	@Override
	public void delApplicationType(ApplicationType applicationType) {
		applicationTypeDao.delete(applicationType);
	}

	@Override
	public void delApplicationType(String id) {
		applicationTypeDao.delete(id);
	}

	@Override
	public void updateApplicationType(ApplicationType applicationType) {
		applicationTypeDao.update(applicationType);
	}

	@Override
	public ApplicationType findApplicationType(String id) {
		return applicationTypeDao.get(id);
	}
	
	@Override
	public List<ApplicationType> findApplicationTypes() {
		return applicationTypeDao.findAll();
	}

	@Override
	public List<ApplicationType> findApplicationTypesByProp(String property, String keyword, boolean userRel, User user) {
		// TODO Auto-generated method stub
		return applicationTypeDao.findByProp(property, keyword, userRel, user);
	}

	@Override
	public List<ApplicationType> findApplicationTypesByRef(String refClass, String refId) {
		// TODO Auto-generated method stub
		return applicationTypeDao.findByRef(refClass, refId);
	}




}
