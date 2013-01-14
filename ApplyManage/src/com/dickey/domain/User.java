package com.dickey.domain;

import java.io.Serializable;
import java.util.HashSet;
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
	
	@ManyToMany(cascade = CascadeType.REFRESH, fetch=FetchType.EAGER)  
    @JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	private Set<Role> roles = new HashSet<Role>();
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy="user")
	private Set<Application> applications = new HashSet<Application>(0);
	
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
	public User(){
		
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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
