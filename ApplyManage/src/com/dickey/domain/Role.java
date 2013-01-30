package com.dickey.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.jbpm.api.identity.Group;

@Entity
public class Role implements Serializable, Group{
	
	/**
	 * 默认序列化UID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id;
	
	@Column(unique=true, nullable=false)
	private String rolename;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	private List<User> users = new LinkedList<User>();
	
	@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "ROLE_PERMISSION", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "PERMISSION_ID") })
	private List<Permission> permissions = new LinkedList<Permission>();

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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", rolename=" + rolename + ", users=" + users + ", permissions=" + permissions + "]";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return rolename;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "candidate";
	}
	
	
}
