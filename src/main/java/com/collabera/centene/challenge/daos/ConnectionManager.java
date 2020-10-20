package com.collabera.centene.challenge.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

public class ConnectionManager {
	
	String myConnection = "jdbc:mysql://localhost:3306/centene";
	String userName = "root";
	String password = "root";
	
	Connection connection = null;
	
	public ConnectionManager() {
		super();
		try {
			this.connection = DriverManager.getConnection(myConnection, userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		if(this.connection != null) {
			this.connection = connection;
		}
	}

}
