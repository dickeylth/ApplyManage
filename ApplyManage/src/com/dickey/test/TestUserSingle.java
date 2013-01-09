package com.dickey.test;

//import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dickey.domain.Application;
//import com.dickey.domain.User;



public class TestUserSingle extends HibernateDaoSupport{
	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
		String appid = "1f62d36c3c08ce0a013c08ce0e7qwrw3";
		Application application = test.getHibernateTemplate().get(Application.class, appid);
		
		//String userid = "1f62d36c3c08ce0a013c08ce0e7e0000";
		//User user = test.getHibernateTemplate().get(User.class, userid);
		System.out.println(application);
		//System.out.println(user);
	}
}
