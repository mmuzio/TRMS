package com.revature.service;

import java.util.List;

import com.revature.domain.Employee;

public interface EmployeeService {
	
	public Employee getEmployeeByUsername(String username);
	
	public List<String> getUsernamesOfEmployeesBetween(String employee, String manager);
	
	public List<Employee> getEmployeesBetween(List<String> usernames);

}
