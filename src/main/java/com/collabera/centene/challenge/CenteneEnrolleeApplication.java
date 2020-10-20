package com.collabera.centene.challenge;

import java.sql.Connection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.collabera.centene.challenge.daos.ConnectionManager;

@SpringBootApplication
public class CenteneEnrolleeApplication {

	public static ConnectionManager connMgr = null;
	public static void main(String[] args) {
		SpringApplication.run(CenteneEnrolleeApplication.class, args);
		
		connMgr = new ConnectionManager();
		Connection conn = connMgr.getConnection();
	}
}
