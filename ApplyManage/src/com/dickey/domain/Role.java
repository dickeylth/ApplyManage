package com.dickey.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Role {
	
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id;
	
	@Column(unique=true, nullable=true)
	private String rolename;
	
	@ManyToMany(cascade = {CascadeType.ALL},fetch=FetchType.EAGER, mappedBy="roles")
	private Set<User> users = new HashSet<User>();
	
	@ManyToMany(cascade = {CascadeType.ALL},fetch=FetchType.EAGER)  
    @JoinTable(name = "ROLE_PERMISSION", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "PERMISSION_ID") })
	private Set<Permission> permissions = new HashSet<Permission>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
	
}
