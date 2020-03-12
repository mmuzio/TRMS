package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.domain.Rejection;
import com.revature.util.ConnectionFactory;

public class RejectionDAOPostgres implements RejectionDAO {
	
	private static final String REJECTION_TABLE = "rejections";
	
	private static final String REJECT_REIMBURSEMENT_BY_SUPERIOR = "insert into " + REJECTION_TABLE + " VALUES(?,?,?,?)";
	
	private static final String GET_REJECTIONS_BY_USERNAME = "select * from " + REJECTION_TABLE + " WHERE username = ?";
	
	private static final String GET_REJECTIONS_BY_ID = "select * from " + REJECTION_TABLE + " WHERE reimbursementid = ?";

	@Override
	public void rejectReimbursementBySuperior(Rejection rejection) {
		
		try (Connection conn = ConnectionFactory.getConnection()) {
			
			PreparedStatement stmt = conn.prepareStatement(REJECT_REIMBURSEMENT_BY_SUPERIOR);
			
			stmt.setInt(1, rejection.getReimbursementid());
			
			stmt.setString(2, rejection.getUsername());
			
			stmt.setInt(3, rejection.getUsertype());
			
			stmt.setString(4, rejection.getReason());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}

	@Override
	public List<Rejection> retrieveRejectionsByUsername(String username) {
		
		List<Rejection> myRejectionList = new ArrayList<Rejection>();
		
		try (Connection conn = ConnectionFactory.getConnection()) {
			
			PreparedStatement stmt = conn.prepareStatement(GET_REJECTIONS_BY_USERNAME);
			
			stmt.setString(1, username);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				myRejectionList.add(
						new Rejection(rs.getInt("reimbursementid"),
								rs.getString("username"),
								rs.getInt("usertype"),
								rs.getString("reason")));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return myRejectionList;
	}

	@Override
	public List<Rejection> retrieveRejectionsById(int reimbursementid) {
		
		List<Rejection> myRejectionList = new ArrayList<Rejection>();
		
		try (Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement preparedStatement = conn.prepareStatement(GET_REJECTIONS_BY_ID);

	        preparedStatement.setInt(1, reimbursementid);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				myRejectionList.add(
						new Rejection(rs.getInt("reimbursementid"),
								rs.getString("username"),
								rs.getInt("usertype"),
								rs.getString("reason")));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return myRejectionList;
	}

}
