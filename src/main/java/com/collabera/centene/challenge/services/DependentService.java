package com.collabera.centene.challenge.services;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collabera.centene.challenge.daos.DependentDAO;
import com.collabera.centene.challenge.model.Dependent;

@Service
public class DependentService {

	@Autowired
	DependentDAO depDAO;
	
	public Dependent findDependent(int id) throws SQLException {
		return depDAO.findDependent(id);
	}
	
	public ArrayList<Dependent> findDependents(int id) throws SQLException {
		return depDAO.findDependents(id);
	}
	
	public Dependent addDependentToEnrollee(int enrId, int depId) throws SQLException {
		return depDAO.addDependentToEnrollee(enrId, depId);
	}
	
	public Dependent removeDependentFromEnrollee(int id) throws SQLException {
		return depDAO.removeDependentFromEnrollee(id);
	}
	
	public Dependent removeDependent(int id) throws SQLException {
		return depDAO.removeDependent(id);
	}
	
	public Dependent addDependent(String name, Date dob) throws SQLException {
		return depDAO.addDependent(name, dob);
	}
	
	public Dependent modifyDependent(int id, String name, Date dob) throws SQLException {
		return depDAO.modifyDependent(id, name, dob);
	}
}
