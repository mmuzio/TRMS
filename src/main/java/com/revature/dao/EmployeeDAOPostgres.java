package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.domain.Employee;
import com.revature.util.ConnectionFactory;

public class EmployeeDAOPostgres implements EmployeeDAO {
	
	private Connection conn = ConnectionFactory.getConnection();
	
	private static final String EMPLOYEE_TABLE = "empinfo";
	
	private static final String SELECT_EMPLOYEE_BY_USERNAME = "select * from " + EMPLOYEE_TABLE + " WHERE username = ?";
	
	private static final String SELECT_EMPLOYEES_BETWEEN = "(WITH RECURSIVE subordinates AS ("
			+ " select username FROM users WHERE username = ? UNION"
			+ " select u.username FROM users u"
			+ " INNER JOIN subordinates s ON s.username = u.reportsto)"
			+ " SELECT * FROM subordinates where username <> ?)"
			+ " intersect ( WITH RECURSIVE superordinates AS ("
			+ " select reportsto FROM users WHERE username = ? UNION"
			+ " select u.reportsto FROM users u INNER JOIN "
			+ " superordinates s ON s.reportsto = u.username)"
			+ " SELECT * FROM superordinates)";

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

	@Override
	public List<String> retrieveEmployeesBetween(String employee, String manager) {
		
		List<String> employeeList = new ArrayList<String>();
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(SELECT_EMPLOYEES_BETWEEN);
			
			stmt.setString(1, manager);
			
			stmt.setString(2, manager);
			
			stmt.setString(3, employee);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				employeeList.add(rs.getString(1));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return employeeList;
		
	}

}
