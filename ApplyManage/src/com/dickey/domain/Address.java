package com.dickey.domain;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Address implements Serializable{

	/**
	 * 默认序列化UID
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id;
	
	@Column
	private String country;
	
	@Column
	private String city;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinTable(name = "USER_ADDRESS", joinColumns = { @JoinColumn(name = "ADDRESS_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	private User user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", country=" + country + ", city=" + city
				+ ", user=" + user + "]";
	}
	

}
