package com.revature.domain;

import org.apache.log4j.Logger;

public class User {

	private static final Logger log = Logger.getLogger(User.class);
	
	//private String firstname;
	
	//private String lastname;
	
	//private String email;
	
	private String username;
	
	private String password;
	
	private int usertype;

//	public String getFirstname() {
//		return firstname;
//	}
//
//	public void setFirstname(String firstname) {
//		this.firstname = firstname;
//	}
//
//	public String getLastname() {
//		return lastname;
//	}
//
//	public void setLastname(String lastname) {
//		this.lastname = lastname;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}

	public int getUsertype() {

		return usertype;

	}

	public void setUsertype(int usertype) {

		this.usertype = usertype;

	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + usertype;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (usertype != other.usertype)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		
		return "User [usertype=" + usertype + ", username=" + username + ", password=" + password + "]";
		
	}
	public User(int usertype, String username, String password) {
		
		super();
		
		this.usertype = usertype;
		
		this.username = username;
		
		this.password = password;
		
		log.info("New user has been created ");
	}
	
	public User() {
		
		super();
		
		log.info("New user has been created ");
		
	}
	
}
