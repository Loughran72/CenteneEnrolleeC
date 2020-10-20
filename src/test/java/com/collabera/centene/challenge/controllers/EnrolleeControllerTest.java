package com.collabera.centene.challenge.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ModelMap;

import com.collabera.centene.challenge.services.DependentService;
import com.collabera.centene.challenge.services.EnrolleeServiceImpl;

@SpringBootTest
public class EnrolleeControllerTest {
	@Mock
	public EnrolleeController controller;
	@InjectMocks
	EnrolleeServiceImpl enrService;
	@InjectMocks
	DependentService depService;
	
//	@Test
//	void findEnrolleeTest() throws SQLException {
//		int id = 11;
//		ModelMap model = new ModelMap();
//		String responseData = null;
//		responseData = controller.findEnrollee(id, model);
//		assertNotNull(responseData);
//	}
}
