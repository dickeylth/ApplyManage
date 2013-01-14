package com.dickey.domain;

import javax.persistence.*;

@Entity
@Table
public class Phone {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int phoneId;
	
	@Column
	private String phoneType;
	
	@Column
	private String phoneNumber;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name = "Student_Phone",
	joinColumns = {@JoinColumn(name = "Phone_ID", referencedColumnName = "phoneId")},
	inverseJoinColumns = {@JoinColumn(name = "Student_ID", referencedColumnName ="id")})
	private Student student;

	public Phone() {
	}

	public Phone(String phoneType, String phoneNumber) {
		this.phoneType = phoneType;
		this.phoneNumber = phoneNumber;
	}

	public int getPhoneId() {
		return this.phoneId;
	}

	public void setPhoneId(int phoneId) {
		this.phoneId = phoneId;
	}


	public String getPhoneType() {
		return this.phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
