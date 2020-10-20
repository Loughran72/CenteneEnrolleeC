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
import com.collabera.centene.challenge.services.DependentService;

@RestController
public class DependentController {

	@Autowired
	DependentService depService;
	
	@GetMapping("/dependents/")
	public ArrayList<Dependent> showDependents(ModelMap model) throws SQLException {
		ArrayList<Dependent> depList = depService.showDependents();
		return depList;
	}
	
	@GetMapping("/finddependent/{id}")
	public Dependent findDependent(@PathVariable int id, ModelMap model) throws SQLException {
		Dependent dep = depService.findDependent(id);
		return dep;
	}
	
	@GetMapping("/finddependents/{id}")
	public ArrayList<Dependent> findDependents(@PathVariable int id, ModelMap model) throws SQLException {
		ArrayList<Dependent> deps = depService.findDependents(id);
		return deps;
	}
	
	@GetMapping("/adddependenttodnrollee/{enrId}/{depId}")
	public Dependent addDependentToEnrollee(@PathVariable(value="enrId") int enrId, @PathVariable(value="depId") int depId, ModelMap model) throws SQLException {
		Dependent dep = depService.addDependentToEnrollee(enrId, depId);
		return dep;
	}
	
	@GetMapping("/removedepfromenr/{depId}/{enrId}")
	public Dependent removeDependentFromEnrollee(@PathVariable(value="depId") int depId, @PathVariable(value="enrId") int enrId, ModelMap model) throws SQLException {
		return depService.removeDependentFromEnrollee(depId, enrId);
	}
	
	@GetMapping("/removedepfromallenrs/{id}/")
	public Dependent removeDependentFromAllEnrollees(@PathVariable int id, ModelMap model) throws SQLException {
		Dependent dep = depService.removeDependentFromAllEnrollees(id);
		return dep;
	}
	
	@GetMapping("/removedependent/{id}")
	public Dependent removeDependent(@PathVariable int id, ModelMap model) throws SQLException {
		Dependent dep = depService.removeDependent(id);
		return dep;
	}
	
	@GetMapping("/adddependent/{name}/{dob}")
	public Dependent addDependent(@PathVariable(value="name") String name, @PathVariable(value="dob") Date dob, ModelMap model) throws SQLException {
		Dependent dep = depService.addDependent(name, dob);
		return dep;
	}
	
	@GetMapping("/modifydependent/{id}/{name}/{dob}")
	public ArrayList<Dependent> modifyDependent(@PathVariable(value="id") int id, @PathVariable(value="name") String name, @PathVariable(value="dob") Date dob, ModelMap model) throws SQLException {
		ArrayList<Dependent> modDepList = depService.modifyDependent(id, name, dob);
		return modDepList;
	}
}
