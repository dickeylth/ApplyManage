package com.dickey.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.dickey.dao.PermissionDao;
import com.dickey.dao.RoleDao;
import com.dickey.dao.UserDao;
import com.dickey.domain.Permission;
import com.dickey.domain.Role;
import com.dickey.service.UserService;

public class InitParamsTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext act = new FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml");
		/*UserService userService = act.getBean("userService", UserService.class);
		
		Permission permission = new Permission();
		permission.setPermission("TEST_PERMISSION");
		
		userService.addPermission(permission);
		
		Role role = new Role();
		role.setRolename("ROLE_TEST");
		role.getPermissions().add(permission);
		
		userService.addRole(role);*/
		UserDao userDao = act.getBean("userDao", UserDao.class);
		RoleDao roleDao = act.getBean("roleDao", RoleDao.class);
		PermissionDao permissionDao = act.getBean("permissionDao", PermissionDao.class);
		
		Permission permission = new Permission();
		permission.setPermission("TEST_PERMISSION");
		permissionDao.save(permission);
		
		Role role = new Role();
		role.setRolename("ROLE_TEST");
		role.getPermissions().add(permission);
		
		roleDao.save(role);
		
		

	}

}
