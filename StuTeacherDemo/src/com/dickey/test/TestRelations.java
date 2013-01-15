package com.dickey.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.dickey.domain.Address;
import com.dickey.domain.Major;
import com.dickey.domain.Phone;
import com.dickey.domain.Student;
import com.dickey.domain.Teacher;

public class TestRelations {

	/**
	 * @param args
	 */
	public static void main2(String[] args) {
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
		
		Student student4 = new Student();
		address3.setStudent(student4);
		
		Set<Phone> phoneNumbers = new HashSet<Phone>();
		phoneNumbers.add(new Phone("house","32354353"));
		phoneNumbers.add(new Phone("mobile","9889343423"));
		Phone phone = new Phone("house", "010223344");
		phone.setStudent(student3);
		
		student1.setStudentPhoneNumbers(phoneNumbers);
		
		
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
		session.save(phone);
		//session.save(student3);
		
		tx.commit();
		session.close();
	}

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
		Student student3 = new Student();
		student3.setName("Jack");
		
		address3.setStudent(student3);
		
		
		session.save(address3);
		//session.save(student3);
		
		tx.commit();
		session.close();
	}
}
