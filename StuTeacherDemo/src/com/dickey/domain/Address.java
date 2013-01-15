package com.dickey.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false)
	private String country;
	
	@Column(nullable=false)
	private String city;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinTable(name = "stu_addr", joinColumns = { @JoinColumn(name = "addr_id") }, inverseJoinColumns = { @JoinColumn(name = "stu_id") })
	private Student student;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public Address(String country, String city) {
		super();
		this.country = country;
		this.city = city;
	}
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", country=" + country + ", city=" + city + ", student=" + student + "]";
	}
	
}
