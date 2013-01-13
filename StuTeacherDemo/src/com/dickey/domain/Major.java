package com.dickey.domain;

import javax.persistence.*;

@Entity
public class Major {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false)
	private String name;

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

	@Override
	public String toString() {
		return "Major [id=" + id + ", name=" + name + "]";
	}

	public Major(String name) {
		super();
		this.name = name;
	}
	
}
