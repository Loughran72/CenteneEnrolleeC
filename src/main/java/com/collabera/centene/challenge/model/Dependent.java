package com.collabera.centene.challenge.model;

import java.sql.Date;

//import java.util.Date;

public class Dependent {

	private int id;
	private String name;
	private Date dob;
	
	public Dependent() {
		
	}
	
	public Dependent(int id, String name, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
	}
	
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "enrollee [id=" + id + ", name=" + name + ", dob=" + dob + "]";
	}
}
