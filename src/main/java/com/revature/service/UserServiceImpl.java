package com.revature.service;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOPostgres;
import com.revature.domain.User;

public class UserServiceImpl implements UserService {
	
	private static UserDAO userDao = new UserDAOPostgres();

	@Override
	public User validateUser(String username, String password) {
		
		User user = userDao.getUserByUsername(username);
		
		if (user != null && user.getPassword().equals(password)) {
			
			return user;
			
		}
		
		return null;
	}

}
