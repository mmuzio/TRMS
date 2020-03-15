package com.revature.dao;

import java.util.List;

import com.revature.domain.Employee;

public interface EmployeeDAO {
	
	public Employee retrieveEmployeeByUsername(String username);
	
	public List<String> retrieveEmployeesBetween(String employee, String manager);

}
