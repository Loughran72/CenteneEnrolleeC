package com.collabera.centene.challenge.services;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collabera.centene.challenge.daos.EnrolleeDAO;
import com.collabera.centene.challenge.model.Enrollee;

@Service
public class EnrolleeServiceImpl {
	
	@Autowired
	EnrolleeDAO enrolleeDAO;
	
	public ArrayList<Enrollee> showEnrollees() throws SQLException {
		return enrolleeDAO.showEnrollees();
	}
	
	public Enrollee findEnrollee(int id) throws SQLException {
		return enrolleeDAO.findEnrollee(id);
	}
	
	public ArrayList<Enrollee> findActiveEnrollees() throws SQLException {
		return enrolleeDAO.findActiveEnrollees();
	}
	
	public ArrayList<Enrollee> findInactiveEnrollees() throws SQLException {
		return enrolleeDAO.findInactiveEnrollees();
	}
	
	public Enrollee removeEnrollee(int id) throws SQLException {
		return enrolleeDAO.removeEnrollee(id);
	}
	
	public Enrollee addEnrollee(String name, int status, Date dob, String phoneNum) throws SQLException {
		return enrolleeDAO.addEnrollee(name, status, dob, phoneNum);
	}
	
	public Enrollee modifyEnrollee(int id, String name, int status, Date dob, String phoneNum) throws SQLException {
		return enrolleeDAO.modifyEnrollee(id, name, status, dob, phoneNum);
	}

}
