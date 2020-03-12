package com.revature.service;

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

}
