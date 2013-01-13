package com.dickey.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Teacher {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false)
	private String name;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Address address;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Major major;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "Teacher_Student",
	joinColumns = {@JoinColumn(name = "Teacher_ID", referencedColumnName = "id")},
	inverseJoinColumns = {@JoinColumn(name = "Student_ID", referencedColumnName ="id")})
	private Set<Student> students = new HashSet<Student>();

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

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", address=" + address
				+ ", major=" + major + "]";
	}

	public Teacher(String name) {
		super();
		this.name = name;
	}
	
}
