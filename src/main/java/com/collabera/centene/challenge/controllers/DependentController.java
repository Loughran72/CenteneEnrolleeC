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

import com.collabera.centene.challenge.model.Dependent;
import com.collabera.centene.challenge.services.DependentService;

@Controller
public class DependentController {

	@Autowired
	DependentService depService;
	
	@GetMapping("/findDependent/(id)")
	public String findDependent(@PathVariable int id, ModelMap model) throws SQLException {
		Dependent dep = depService.findDependent(id);
		
		model.put("depdendent", dep);
		return "dependent";
	}
	
	@GetMapping("/findDependents/(id)")
	public String findDependents(@PathVariable int id, ModelMap model) throws SQLException {
		ArrayList<Dependent> dep = depService.findDependents(id);
		
		model.put("depdendents", dep);
		return "dependents";
	}
	
	@GetMapping("/addDependentToEnrollee/(enrId, depId)")
	public String addDependentToEnrollee(int enrId, int depId, ModelMap model) throws SQLException {
		Dependent dep = depService.addDependentToEnrollee(enrId, depId);
		
		model.put("depdendent", dep);
		return "dependent";
	}
	
	@GetMapping("/removeDependentFromEnrollee/(id)")
	public String removeDependentFromEnrollee(int id, ModelMap model) throws SQLException {
		Dependent dep = depService.removeDependentFromEnrollee(id);
		
		model.put("depdendent", dep);
		return "dependent";
	}
	
	@GetMapping("/removeDependent/(id)")
	public String removeDependent(int id, ModelMap model) throws SQLException {
		Dependent dep = depService.removeDependent(id);
		
		model.put("depdendent", dep);
		return "dependent";
	}
	
	@GetMapping("/addDependent/(name, dob)")
	public String addDependent(String name, Date dob, ModelMap model) throws SQLException {
		Dependent dep = depService.addDependent(name, dob);
		
		model.put("depdendent", dep);
		return "dependent";
	}
	
	@GetMapping("/modifyDependent/(id, name, dob)")
	public String modifyDependent(int id, String name, Date dob, ModelMap model) throws SQLException {
		Dependent dep = depService.modifyDependent(id, name, dob);
		
		model.put("depdendent", dep);
		return "dependent";
	}
}
