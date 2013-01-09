package com.dickey.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.dickey.domain.Permission;
import com.dickey.domain.Role;
import com.dickey.domain.User;

public class TestUserRolePerm {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Configuration configuration = new Configuration().configure();
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		User user = new User("Jim", "SSSSSS");
		User admin = new User("Admin", "admin");
		Role role = new Role();
		role.setRolename("Admin");
		admin.getRoles().add(role);
		
		Permission permission = new Permission();
		permission.setPermission("*");
		role.getPermissions().add(permission);
		
		Role userRole = new Role();
		userRole.setRolename("User");
		Permission userPermission = new Permission();
		userPermission.setPermission("/application/*");
		userRole.getPermissions().add(userPermission);
		user.getRoles().add(userRole);
		
		session.save(user);
		session.save(admin);
		
		tx.commit();
		session.close();

		
		String str = "SELECT B.PERMISSION FROM MY_ROLE T LEFT JOIN MY_ROLE_PERMISSION A ON T.ID = A.ROLE_ID LEFT JOIN MY_PERMISSION B ON A.PERMISSION = B.ID WHERE T.ROLENAME = ? ";
		System.out.println(str.toLowerCase());
	}

}
