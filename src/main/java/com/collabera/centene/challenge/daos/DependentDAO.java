package com.collabera.centene.challenge.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.collabera.centene.challenge.CenteneEnrolleeApplication;
import com.collabera.centene.challenge.model.Dependent;

@Component
public class DependentDAO {

	Connection conn = null;
	CallableStatement cStmt;
	ResultSet resSet;
	
	public ArrayList<Dependent> showDependents() throws SQLException {
		conn = CenteneEnrolleeApplication.connMgr.getConnection();
		cStmt = conn.prepareCall("(SELECT * FROM centene.dependents)");
		resSet = cStmt.executeQuery();
		
		ArrayList<Dependent> listDep = new ArrayList<Dependent>();
		int count = 0;
		while(resSet.next()) {
			Dependent dep = populateDependent(resSet);
			listDep.add(count, dep);
		}
		return listDep;
	}
	
	public Dependent findDependent(int id) throws SQLException {
		conn = CenteneEnrolleeApplication.connMgr.getConnection();
		cStmt = conn.prepareCall("SELECT * FROM centene.dependents WHERE dependents.id = ?;");
		cStmt.setInt(1, id);
		resSet = cStmt.executeQuery();
		resSet.next();
		return populateDependent(resSet);
	}
	
	public ArrayList<Dependent> findDependents(int id) throws SQLException {
		conn = CenteneEnrolleeApplication.connMgr.getConnection();
		cStmt = conn.prepareCall("call findDependents(?);");
		cStmt.setInt(1, id);
		resSet = cStmt.executeQuery();
		
		ArrayList<Dependent> listDep = new ArrayList<Dependent>();
		int count = 0;
		while(resSet.next()) {
			Dependent dep = populateDependent(resSet);
			listDep.add(count, dep);
		}
		return listDep;
	}
	
	public Dependent addDependentToEnrollee(int enrId, int depId) throws SQLException {
		conn = CenteneEnrolleeApplication.connMgr.getConnection();
		cStmt = conn.prepareCall("call addDependentToEnrollee(?, ?);");
		cStmt.setInt(1, enrId);
		cStmt.setInt(2, depId);
		cStmt.executeUpdate();
		return findDependent(depId);
	}
	
	public Dependent removeDependentFromEnrollee(int depId, int enrId) throws SQLException {
		Dependent dep = findDependent(depId);
		conn = CenteneEnrolleeApplication.connMgr.getConnection();
		cStmt = conn.prepareCall("call removeDependentFromEnrollee(?, ?);");
		cStmt.setInt(1, depId);
		cStmt.setInt(2, enrId);
		cStmt.executeUpdate();
		return dep;
	}
	
	public Dependent removeDependentFromAllEnrollees(int id) throws SQLException {
		Dependent dep = findDependent(id);
		conn = CenteneEnrolleeApplication.connMgr.getConnection();
		cStmt = conn.prepareCall("call removeDependentFromAllEnrollees(?);");
		cStmt.setInt(1, id);
		cStmt.executeUpdate();
		return dep;
	}
	
	private Dependent populateDependent(ResultSet resSet) throws SQLException {
		Dependent dep = new Dependent();
		dep.setId(resSet.getInt("id"));
		dep.setName(resSet.getString("name"));
		dep.setDob(resSet.getDate("dob"));
		return dep;
	}
	
	public Dependent removeDependent(int id) throws SQLException {
		Dependent remDep = findDependent(id);
		conn = CenteneEnrolleeApplication.connMgr.getConnection();
		cStmt = conn.prepareCall("call removeDependent(?);");
		cStmt.setInt(1, id);
		cStmt.executeUpdate();
		return remDep;
	}
	
	public Dependent addDependent(String name, Date dob) throws SQLException {
		conn = CenteneEnrolleeApplication.connMgr.getConnection();
		cStmt = conn.prepareCall("call addDependent(?, ?);");
		cStmt.setString(1, name);
		cStmt.setDate(2, dob);
		cStmt.executeUpdate();
		cStmt = conn.prepareCall("(SELECT * FROM dependents WHERE dependents.id = LAST_INSERT_ID())");
		resSet = cStmt.executeQuery();
		resSet.next();
		return populateDependent(resSet);
	}
	
	public ArrayList<Dependent> modifyDependent(int id, String name, Date dob) throws SQLException {
		ArrayList<Dependent> depList = new ArrayList<Dependent>();
		depList.add(findDependent(id));
		conn = CenteneEnrolleeApplication.connMgr.getConnection();
		cStmt = conn.prepareCall("call modifyDependent(?, ?, ?);");
		cStmt.setInt(1, id);
		cStmt.setString(2, name);
		cStmt.setDate(3, dob);
		cStmt.executeUpdate();
		depList.add(findDependent(id));
		return depList;
	}
}
