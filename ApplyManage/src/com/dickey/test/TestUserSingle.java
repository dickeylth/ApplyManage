package com.dickey.test;

//import org.hibernate.Session;
//import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dickey.domain.Application;
//import com.dickey.domain.User;
import com.dickey.domain.User;



public class TestUserSingle extends HibernateDaoSupport{
	/**
	 * @param args
	 */
	public static void main2(String[] args) {
		/*Configuration configuration = new Configuration().configure();
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
	
		
		
		tx.commit();
		session.close();*/

		TestUserSingle test = new TestUserSingle();
		Configuration configuration = new Configuration().configure();
		SessionFactory sf = configuration.buildSessionFactory();
		test.setSessionFactory(sf);
		String appid = "1f62d2333c1e17aa013c1e18c0050000";
		Application application = test.getHibernateTemplate().get(Application.class, appid);
		
		//String userid = "1f62d36c3c08ce0a013c08ce0e7e0000";
		//User user = test.getHibernateTemplate().get(User.class, userid);
		System.out.println(application);
		try {
			
			System.out.println(application.getClass().getDeclaredField("user2"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Method method = application.getClass().getDeclaredMethod("setUser", User.class);
			User user = new User("KONka", "IMQQ");
			try {
				method.invoke(application, user);
				System.out.println(application);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
		
		
		
		//System.out.println(user);
	}
	
	public static void main(String[] args) {
		try {
			Application application = new Application();
			Object clazz = application.getClass().getDeclaredField("start").getType();
			System.out.println(clazz);
			System.out.println(clazz == Date.class);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
