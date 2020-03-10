package com.revature.dao;

import com.revature.domain.User;

public interface UserDAO {
	
	public void createUser(User u);

	public User getUserByUsername(String username);
	
}
