package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.domain.Employee;
import com.revature.util.ConnectionFactory;

public class EmployeeDAOPostgres implements EmployeeDAO {
	
	private Connection conn = ConnectionFactory.getConnection();
	
	private static final String EMPLOYEE_TABLE = "empinfo";
	
	private static final String SELECT_EMPLOYEE_BY_USERNAME = "select * from " + EMPLOYEE_TABLE + " WHERE username = ?";

	@Override
	public Employee retrieveEmployeeByUsername(String username) {
		
		Employee ret = new Employee();
		
		try {
			
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_EMPLOYEE_BY_USERNAME);

	        preparedStatement.setString(1, username);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				ret.setUsername(rs.getString(1));
				
				ret.setFirstname(rs.getString(2));
				
				ret.setLastname(rs.getString(3));
				
				ret.setEmail(rs.getString(4));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return ret;
		
	}

}
