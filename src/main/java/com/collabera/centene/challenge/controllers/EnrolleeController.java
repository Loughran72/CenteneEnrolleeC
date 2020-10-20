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
//		ArrayList<Dependent> deps = depService.findDependents(id);
		
//		model.put("enr", enrList);
//		model.put("deps", deps);
//		return "enrollees";
		return enrList;
	}
	
	@GetMapping("/findenrollee/{id}")
	public Enrollee findEnrollee(@PathVariable int id, ModelMap model) throws SQLException {
		Enrollee enr = enrService.findEnrollee(id);
//		ArrayList<Dependent> deps = depService.findDependents(id);
		
		
//		model.put("enr", enr);
//		model.put("deps", deps);
//		return "enrollee";
		return enr;
	}
	
	@GetMapping("/activeenrollees")
	public ArrayList<Enrollee> findActiveEnrollees(ModelMap model) throws SQLException {
		ArrayList<Enrollee> actEnr = enrService.findActiveEnrollees();
//		depSort(actEnr, model);
		
//		model.put("actEnr", actEnr);
//		return "ActiveEnrollees";
		return actEnr;
	}
	
	@GetMapping("/inactiveenrollees")
	public ArrayList<Enrollee> findInactiveEnrollees(ModelMap model) throws SQLException {
		ArrayList<Enrollee> inactEnr = enrService.findInactiveEnrollees();
//		depSort(inactEnr, model);
		
//		model.put("inactEnr", inactEnr);
//		return "InactiveEnrollees";
		return inactEnr;
	}
	
	private void depSort(ArrayList<Enrollee> listEnr, ModelMap model) throws SQLException {
		for(int i=0; i<listEnr.size(); i++) {
			int id = listEnr.get(i).getId();
			ArrayList<Dependent> deps = depService.findDependents(id);
//			model.put("deps", deps);
		}
	}
	
	@GetMapping("/removeenrollee/{id}")
	public Enrollee removeEnrollee(@PathVariable int id, ModelMap model) throws SQLException {
		Enrollee remEnr = enrService.removeEnrollee(id);
		
//		model.put("enr", remEnr);
//		return "removeEnrollee";
		return remEnr;
	}
	
	@GetMapping("/addenrollee/{name}/{status}/{dob}/{phoneNum}")
	public Enrollee addEnrollee(@PathVariable(value="name") String name, @PathVariable(value="status") int status, @PathVariable(value="dob") Date dob, @PathVariable(value="phoneNum") String phoneNum, ModelMap model) throws SQLException {
		Enrollee addEnr = enrService.addEnrollee(name, status, dob, phoneNum);
		
//		model.put("enr", addEnr);
//		return "removeEnrollee";
		return addEnr;
	}
	
	@GetMapping("/modifyenrollee/{id}/{name}/{status}/{dob}/{phoneNum}")
	public Enrollee modifyEnrollee(@PathVariable(value="id") int id, @PathVariable(value="name") String name, @PathVariable(value="status") int status, @PathVariable(value="dob") Date dob, @PathVariable(value="phoneNum") String phoneNum, ModelMap model) throws SQLException {
		Enrollee modEnr = enrService.modifyEnrollee(id, name, status, dob, phoneNum);
		
//		model.put("enr", modEnr);
//		return "removeEnrollee";
		return modEnr;
	}
}
