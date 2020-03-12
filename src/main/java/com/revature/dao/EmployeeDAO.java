package com.revature.dao;

import com.revature.domain.Employee;

public interface EmployeeDAO {
	
	public Employee retrieveEmployeeByUsername(String username);

}
