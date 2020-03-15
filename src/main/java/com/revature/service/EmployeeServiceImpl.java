package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOPostgres;
import com.revature.domain.Employee;

public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDAO empDao = new EmployeeDAOPostgres();

	@Override
	public Employee getEmployeeByUsername(String username) {
		
		Employee emp = empDao.retrieveEmployeeByUsername(username);
		
		return emp;
		
	}

	@Override
	public List<Employee> getEmployeesBetween(List<String> usernames) {
		
		List<Employee> employeesBetween = new ArrayList<Employee>();
		
		for (String username: usernames) {
			
			Employee employee = getEmployeeByUsername(username);
			
			employeesBetween.add(employee);
			
		}
		
		return employeesBetween;
		
	}

	@Override
	public List<String> getUsernamesOfEmployeesBetween(String employee, String manager) {
		return empDao.retrieveEmployeesBetween(employee, manager);
	}

}
