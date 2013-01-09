/*package com.dickey.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import com.dickey.domain.Application;
import com.dickey.domain.Authority;
import com.dickey.domain.User;

public class TestHibernate {
	public static void main(String[] args){
		Configuration configuration = new Configuration().configure();
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		User user = new User("admin", md5Code("admin"), true);
		Authority auth = new Authority();
		auth.setUser(user);
		auth.setAuthority("ROLE_ADMIN");
		session.save(auth);
		
		User user2 = new User("user", md5Code("user"), true);
		Authority auth2 = new Authority();
		auth2.setUser(user2);
		auth2.setAuthority("ROLE_USER");
		
		
		Application application = new Application();
		application.setReason("Oh, no, ...");
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(2011, 11, 11);
		Date start = cal.getTime();
		application.setStart(start);
		
		cal.set(2012, 11, 11);
		Date end = cal.getTime();
		application.setEnd(end);
		
		application.setUser(user2);
		
		//session.save(user);
		//session.save(auth);
		session.flush();
		session.save(auth2);
		session.save(application);
		
		tx.commit();
		session.close();
	}
	
	public static String md5Code(String orign){
		byte[] strByte = orign.getBytes();
		MessageDigest md = null;
		try {  
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();  
		}
		md.update(strByte);
		byte[] newByte = md.digest();
		StringBuilder sb = new StringBuilder();   //转换为16进制
		for (int i = 0; i < newByte.length; i++) {
			if ((newByte[i] & 0xff) < 0x10) {     
				sb.append("0");  
			}  
			sb.append(Long.toString(newByte[i] & 0xff, 16));  
		}   
		return sb.toString();   
	}
}
*/