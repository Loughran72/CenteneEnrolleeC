package com.collabera.centene.challenge.controllers;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.collabera.centene.challenge.model.Dependent;
import com.collabera.centene.challenge.model.Enrollee;
import com.collabera.centene.challenge.services.DependentService;
import com.collabera.centene.challenge.services.EnrolleeServiceImpl;

@RestController
public class EnrolleeController {

	@Autowired
	EnrolleeServiceImpl enrService;
	@Autowired
	DependentService depService;
	
	@GetMapping("/enrollees/")
	public ArrayList<Enrollee> showEnrollees(ModelMap model) throws SQLException {
		ArrayList<Enrollee> enrList = enrService.showEnrollees();
		return enrList;
	}
	
	@GetMapping("/findenrollee/{id}")
	public Enrollee findEnrollee(@PathVariable int id, ModelMap model) throws SQLException {
		Enrollee enr = enrService.findEnrollee(id);
		return enr;
	}
	
	@GetMapping("/findenrwithdeps/{id}")
	public ArrayList<Object> findEnrWithDeps(@PathVariable int id, ModelMap model) throws SQLException {
		ArrayList<Object> enrWithDeps = new ArrayList<Object>();
		enrWithDeps.add(enrService.findEnrollee(id));
		enrWithDeps.add(depService.findDependents(id));
		return enrWithDeps;
	}
	
	@GetMapping("/findenrswithdeps/")
	public ArrayList<Object> showEnrsWithDeps(ModelMap model) throws SQLException {
		ArrayList<Object> enrWithDeps = new ArrayList<Object>();
		ArrayList<Enrollee> enrList = enrService.showEnrollees();
		for(int count=0; count<enrList.size(); count++) {
			enrWithDeps.add(enrList.get(count));
			enrWithDeps.add(depService.findDependents(enrList.get(count).getId() ));
		}
		return enrWithDeps;
	}
	
	@GetMapping("/adddeptoallenrs/{id}")
	public Dependent addDependentToAllEnrollees(@PathVariable int id, ModelMap model) throws SQLException {
		ArrayList<Enrollee> enrList = enrService.showEnrollees();
		for(int count=0; count<enrList.size(); count++) {
			depService.addDependentToEnrollee(enrList.get(count).getId(), id);
		}
		return depService.findDependent(id);
	}
	
	@GetMapping("/activeenrollees")
	public ArrayList<Enrollee> findActiveEnrollees(ModelMap model) throws SQLException {
		ArrayList<Enrollee> actEnr = enrService.findActiveEnrollees();
		return actEnr;
	}
	
	@GetMapping("/inactiveenrollees")
	public ArrayList<Enrollee> findInactiveEnrollees(ModelMap model) throws SQLException {
		ArrayList<Enrollee> inactEnr = enrService.findInactiveEnrollees();
		return inactEnr;
	}
	
	private void depSort(ArrayList<Enrollee> listEnr, ModelMap model) throws SQLException {
		for(int i=0; i<listEnr.size(); i++) {
			int id = listEnr.get(i).getId();
			ArrayList<Dependent> deps = depService.findDependents(id);
		}
	}
	
	@GetMapping("/removeenrollee/{id}")
	public Enrollee removeEnrollee(@PathVariable int id, ModelMap model) throws SQLException {
		Enrollee remEnr = enrService.removeEnrollee(id);
		return remEnr;
	}
	
	@GetMapping("/addenrollee/{name}/{status}/{dob}/{phoneNum}")
	public Enrollee addEnrollee(@PathVariable(value="name") String name, @PathVariable(value="status") int status, @PathVariable(value="dob") Date dob, @PathVariable(value="phoneNum") String phoneNum, ModelMap model) throws SQLException {
		Enrollee addEnr = enrService.addEnrollee(name, status, dob, phoneNum);
		return addEnr;
	}
	
	@GetMapping("/modifyenrollee/{id}/{name}/{status}/{dob}/{phoneNum}")
	public ArrayList<Enrollee> modifyEnrollee(@PathVariable(value="id") int id, @PathVariable(value="name") String name, @PathVariable(value="status") int status, @PathVariable(value="dob") Date dob, @PathVariable(value="phoneNum") String phoneNum, ModelMap model) throws SQLException {
		ArrayList<Enrollee> modEnrList = enrService.modifyEnrollee(id, name, status, dob, phoneNum);
		return modEnrList;
	}
}
