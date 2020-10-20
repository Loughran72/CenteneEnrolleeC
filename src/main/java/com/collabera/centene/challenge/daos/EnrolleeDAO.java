package com.collabera.centene.challenge.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.collabera.centene.challenge.CenteneEnrolleeApplication;
import com.collabera.centene.challenge.model.Dependent;
import com.collabera.centene.challenge.model.Enrollee;

@Component
public class EnrolleeDAO {

	Connection conn = null;
	CallableStatement cStmt;
	PreparedStatement pStmt;
	ResultSet resSet;
	
	public ArrayList<Enrollee> showEnrollees() throws SQLException {
		conn = CenteneEnrolleeApplication.connMgr.getConnection();
		cStmt = conn.prepareCall("(SELECT * FROM centene.enrollees)");
		resSet = cStmt.executeQuery();
		
		ArrayList<Enrollee> listEnr = new ArrayList<Enrollee>();
		int count = 0;
		while(resSet.next()) {
			Enrollee enr = populateEnrollee(resSet);
			listEnr.add(count, enr);
		}
		return listEnr;
	}
	public Enrollee findEnrollee(int id) throws SQLException {
		conn = CenteneEnrolleeApplication.connMgr.getConnection();
		cStmt = conn.prepareCall("(SELECT * FROM centene.enrollees WHERE enrollees.id = ?)");
		cStmt.setInt(1, id);
		resSet = cStmt.executeQuery();
		resSet.next();
		return populateEnrollee(resSet);
	}
	
	private Enrollee populateEnrollee(ResultSet resSet) throws SQLException {
		Enrollee enr = new Enrollee();
		enr.setId(resSet.getInt("id"));
		enr.setName(resSet.getString("name"));
		enr.setActivationStatus(resSet.getInt("activation_status"));
		enr.setDob(resSet.getDate("dob"));
		enr.setPhoneNum(resSet.getString("phone"));
		
		return enr;
	}
	
	public ArrayList<Enrollee> findActiveEnrollees() throws SQLException {
		conn = CenteneEnrolleeApplication.connMgr.getConnection();
		pStmt = conn.prepareCall("SELECT * FROM centene.enrollees WHERE enrollees.activation_status = 1");
		resSet = pStmt.executeQuery();
		
		ArrayList<Enrollee> listEnr = new ArrayList<Enrollee>();
		
		int count = 0;
		while(resSet.next()) {
			Enrollee enr = new Enrollee();
			enr.setId(resSet.getInt("id"));
			enr.setName(resSet.getString("name"));
			enr.setActivationStatus(resSet.getInt("activation_status"));
			enr.setDob(resSet.getDate("dob"));
			enr.setPhoneNum(resSet.getString("phone"));
			listEnr.add(count, enr);
		}
		
		return listEnr;
	}
	
	public ArrayList<Enrollee> findInactiveEnrollees() throws SQLException {
		conn = CenteneEnrolleeApplication.connMgr.getConnection();
		pStmt = conn.prepareCall("(SELECT * FROM centene.enrollees WHERE enrollees.activation_status = 0)");
		resSet = pStmt.executeQuery();
		
		ArrayList<Enrollee> listEnr = new ArrayList<Enrollee>();
		
		int count = 0;
		while(resSet.next()) {
			Enrollee enr = new Enrollee();
			enr.setId(resSet.getInt("id"));
			enr.setName(resSet.getString("name"));
			enr.setActivationStatus(resSet.getInt("activation_status"));
			enr.setDob(resSet.getDate("dob"));
			enr.setPhoneNum(resSet.getString("phone"));
			listEnr.add(count, enr);
		}
		
		return listEnr;
	}
	
	public Enrollee removeEnrollee(int id) throws SQLException {
		conn = CenteneEnrolleeApplication.connMgr.getConnection();
		Enrollee remEnr = findEnrollee(id);
		cStmt = conn.prepareCall("DELETE FROM centene.enrollees WHERE enrollees.id = ?");
		cStmt.setInt(1, id);
		int numRowsChanged = cStmt.executeUpdate();
		return remEnr;
	}
	
	public Enrollee addEnrollee(String name, int status, Date dob, String phoneNum) throws SQLException {
		conn = CenteneEnrolleeApplication.connMgr.getConnection();
		cStmt = conn.prepareCall("call addEnrollee(?, ?, ?, ?)");
		cStmt.setString(1, name);
		cStmt.setInt(2, status);
		cStmt.setDate(3, dob);
		cStmt.setString(4, phoneNum);
		cStmt.executeUpdate();
		cStmt = conn.prepareCall("(SELECT * FROM enrollees WHERE enrollees.id = LAST_INSERT_ID())");
		resSet = cStmt.executeQuery();
		resSet.next();
		return populateEnrollee(resSet);
	}
	
	public ArrayList<Enrollee> modifyEnrollee(int id, String name, int status, Date dob, String phoneNum) throws SQLException {
		ArrayList<Enrollee> enrList = new ArrayList<>();
		enrList.add(findEnrollee(id));
		conn = CenteneEnrolleeApplication.connMgr.getConnection();
		cStmt = conn.prepareCall("call modifyEnrollee(?, ?, ?, ?, ?)");
		cStmt.setInt(1, id);
		cStmt.setString(2, name);
		cStmt.setInt(3, status);
		cStmt.setDate(4, dob);
		cStmt.setString(5, phoneNum);
		cStmt.executeUpdate();
		enrList.add(findEnrollee(id));
		return enrList;
	}
}
