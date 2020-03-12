package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.revature.domain.Approval;
import com.revature.domain.Reimbursement;
import com.revature.util.ConnectionFactory;

public class ApprovalDAOPostgres implements ApprovalDAO {
	
private Connection conn = ConnectionFactory.getConnection();
	
	private static final String APPROVAL_TABLE = "approvals";
	
	//private static final String REJECTION_TABLE = "rejections";
	
	private static final String INSERT_NEW_APPROVAL = "insert into " + APPROVAL_TABLE + " VALUES(?, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT)";
	
	//private static final String INSERT_NEW_REJECTION = "insert into " + REJECTION_TABLE + " VALUES(?, ?, ?, ?)";
	
	private static final String SELECT_APPROVAL_BY_ID = "select * from " + APPROVAL_TABLE + " WHERE reimbursementid = ?";
	
	private static final String SELECT_APPROVALS_BY_USERNAME = "select * from " + APPROVAL_TABLE + " WHERE username = ?";
	
	//private static final String SELECT_REJECTION_BY_ID = "select * from " + APPROVAL_TABLE + " WHERE reimbursementid = ?";
	
	private static final String UPDATE_APPROVAL_BY_SUPERIOR = "update " + APPROVAL_TABLE + " SET ? = ? WHERE reimbursementid = ?";;
	
	//private static final String UPDATE_APPROVAL = "update " + APPROVAL_TABLE + " SET accepted = ? WHERE reimbursementid = ?";

	//private static final String UPDATE_REJECTION = "update " + REJECTION_TABLE + " SET accepted = ? WHERE reimbursementid = ?";

	@Override
	public void insertNewApproval(Approval approval) {
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(INSERT_NEW_APPROVAL);
			
			stmt.setInt(1, approval.getReimbursementid());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}

	@Override
	public Approval retrieveApprovalById(int reimbursementid) {
		
		Approval ret = new Approval();
		
		try (Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_APPROVAL_BY_ID);

	        preparedStatement.setInt(1, reimbursementid);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				ret.setReimbursementid(rs.getInt(1));
				
				ret.setSupervisorAccepted(rs.getBoolean(2));
				
				ret.setHeadAccepted(rs.getBoolean(3));
				
				ret.setBencoAccepted(rs.getBoolean(4));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return ret;
	}

	@Override
	public List<Approval> retrieveMyApprovals(String username) {
		
		List<Approval> myApprovalList = new ArrayList<Approval>();
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(SELECT_APPROVALS_BY_USERNAME);
			
			stmt.setString(1, username);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				myApprovalList.add(
						new Approval(
								));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return myApprovalList;
	}

	@Override
	public void acceptReimbursementBySuperior(int reimbursementid, int usertype) {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public void acceptReimbursement(int reimbursementid) {
		// TODO Auto-generated method stub
		return;
	}

}
