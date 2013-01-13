package com.dickey.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.dickey.domain.Address;
import com.dickey.domain.Major;
import com.dickey.domain.Student;
import com.dickey.domain.Teacher;

public class TestRelations {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration configuration = new Configuration().configure();
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		Address address1 = new Address("中国", "北京");
		Address address2 = new Address("俄罗斯", "莫斯科");
		Address address3 = new Address("英国", "伦敦");
		
		Student student1 = new Student("李磊", address1);
		Student student2 = new Student("韩梅梅", address2);
		Student student3 = new Student("莎拉", address3);
		
		Major major1 = new Major("英语");
		Major major2 = new Major("计算机");
		
		Teacher teacher1 = new Teacher("Jack");
		teacher1.setMajor(major1);
		teacher1.setAddress(address2);
		teacher1.getStudents().add(student1);
		teacher1.getStudents().add(student2);
		
		Teacher teacher2 = new Teacher("马小云");
		teacher2.setMajor(major2);
		teacher2.setAddress(address3);
		teacher2.getStudents().add(student3);
		
		session.save(teacher1);
		session.save(teacher2);
		//session.save(student3);
		
		tx.commit();
		session.close();
	}

}
