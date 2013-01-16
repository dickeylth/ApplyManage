package com.dickey.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class User implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id;
	
	@Column(unique=true, nullable=true)
	private String username;
	
	@Column(nullable=true)
	private String password;
	
	@ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	private Set<Role> roles = new HashSet<Role>();
	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy="user")
	private Set<Application> applications = new HashSet<Application>(0);
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinTable(name = "USER_ADDRESS", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ADDRESS_ID") })
	private Address address;
	
	@ElementCollection
	private List<String> phones = new LinkedList<String>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public Set<Application> getApplications() {
		return applications;
	}
	public void setApplications(Set<Application> applications) {
		this.applications = applications;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public User(){
		
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public List<String> getPhones() {
		return phones;
	}
	public void setPhones(List<String> phones) {
		this.phones = phones;
	}
	
	@Override
	public String toString() {
		String roles = "[";
		for (Role role : this.roles) {
			roles += "\t" + role;
		}
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", roles=" + roles + "]]";
	}
	
}
