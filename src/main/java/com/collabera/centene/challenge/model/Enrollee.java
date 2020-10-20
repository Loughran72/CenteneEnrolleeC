package com.collabera.centene.challenge.model;

import java.sql.Date;

//import java.util.Date;

public class Enrollee {

	private int id;
	private String name;
	private int activationStatus;
	private Date dob;
	private String phoneNum;
	
	public Enrollee() {
		
	}
	
	public Enrollee(int id, String name, int activationStatus, Date dob, String phoneNum) {
		super();
		this.id = id;
		this.name = name;
		this.activationStatus = activationStatus;
		this.dob = dob;
		this.phoneNum = phoneNum;
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

	public int getActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(int activationStatus) {
		this.activationStatus = activationStatus;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	@Override
	public String toString() {
		return "enrollee [id=" + id + ", name=" + name + ", activationStatus=" + activationStatus 
				+ ", dob=" + dob + ", phoneNum=" + phoneNum + "]";
	}
}
