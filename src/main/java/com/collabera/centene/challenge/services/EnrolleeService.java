package com.collabera.centene.challenge.services;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import com.collabera.centene.challenge.model.Enrollee;

public interface EnrolleeService {

	public ArrayList<Enrollee> showEnrollees();
	
	public Enrollee findEnrollee(int id);
	
	public ArrayList<Enrollee> findActiveEnrollees();
	
	public ArrayList<Enrollee> findInactiveEnrollees();
	
	public Enrollee removeEnrollee(int id);
	
	public Enrollee addEnrollee(String name, int status, Date dob, String phoneNum);
	
	public Enrollee modifyEnrollee(int id, String name, int status, Date dob, String phoneNum);
	}
