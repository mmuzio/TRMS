package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.domain.User;
import com.revature.util.ConnectionFactory;

public class UserDAOPostgres implements UserDAO {
	
	private static final String USER_TABLE = "users";
	
	private static final String SELECT_USER_BY_USERNAME = "SELECT * FROM " + USER_TABLE + " WHERE USERNAME = ?";
	
	private static final String INSERT_NEW_USER = "INSERT INTO " + USER_TABLE + " (username, password, usertype) VALUES (?, ?, ?)";

	public void createUser(User u) {
		
		String username = u.getUsername();
		
		String password = u.getPassword();
		
		int usertype = u.getUsertype();
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			
			PreparedStatement preparedStatement = conn.prepareStatement(INSERT_NEW_USER);
	
	        preparedStatement.setString(1, username);
	        
	        preparedStatement.setString(2, password);
	        
	        preparedStatement.setInt(3, usertype);
	
	        preparedStatement.executeUpdate();
	        
	        //log.info("User with username: " + username + " has been added to Users table.");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
	
	
	}

	public User getUserByUsername(String username) {
		
		User user = new User();
		
		try (Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_USER_BY_USERNAME);
			
			preparedStatement.setString(1, username);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {

				user.setUsername(rs.getString(1));
				
				user.setPassword(rs.getString(2));
				
				user.setUsertype(rs.getInt(3));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return user;
	}

}
