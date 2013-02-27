package com.dickey.test;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

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
		UserService userService = act.getBean("userService", UserService.class);
		
		//先处理permission
		Permission permission = new Permission();
		permission.setPermission("TEST_PERMISSION");
		
		//userService.addPermission(permission);
		
		//再处理role
		Role role = new Role();
		role.setName("ROLE_TEST");
		role.getPermissions().add(permission);
		
		userService.addRole(role);
		
		//最后处理user
		

	}

}
