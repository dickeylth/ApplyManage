package com.dickey.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false)
	private String name;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Address address;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "Teacher_Student",
	joinColumns = {@JoinColumn(name = "Student_ID", referencedColumnName = "id")},
	inverseJoinColumns = {@JoinColumn(name = "Teacher_ID", referencedColumnName ="id")})
	private Set<Teacher> teachers = new HashSet<Teacher>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "Student_Phone",
	joinColumns = {@JoinColumn(name = "Student_ID", referencedColumnName = "id")},
	inverseJoinColumns = {@JoinColumn(name = "Phone_ID", referencedColumnName ="phoneId")})
	private Set<Phone> studentPhoneNumbers = new HashSet<Phone>(0);

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", address=" + address
				+ "]";
	}

	public Student(String name, Address address) {
		super();
		this.name = name;
		this.address = address;
	}

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Set<Phone> getStudentPhoneNumbers() {
		return studentPhoneNumbers;
	}

	public void setStudentPhoneNumbers(Set<Phone> studentPhoneNumbers) {
		this.studentPhoneNumbers = studentPhoneNumbers;
	}
	
}
