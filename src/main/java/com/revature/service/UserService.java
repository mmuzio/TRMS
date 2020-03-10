package com.revature.service;

import com.revature.domain.User;

public interface UserService {
	
	public User validateUser(String username, String password);

}
