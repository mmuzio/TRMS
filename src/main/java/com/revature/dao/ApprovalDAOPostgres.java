package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.domain.Approval;
import com.revature.util.ConnectionFactory;

public class ApprovalDAOPostgres implements ApprovalDAO {
	
	private Connection conn = ConnectionFactory.getConnection();
	
	private static final String APPROVAL_TABLE = "approvals";
	
	private static final String INSERT_NEW_APPROVAL = "insert into " + APPROVAL_TABLE + " VALUES(?, DEFAULT, DEFAULT, DEFAULT)";
	
	private static final String SELECT_APPROVAL_BY_ID = "select * from " + APPROVAL_TABLE + " WHERE reimbursementid = ?";
	
	private static final String SELECT_APPROVALS_BY_USERNAME = "select * from " + APPROVAL_TABLE + " WHERE username = ?";
	
	private static final String UPDATE_APPROVAL_BY_SUPERVISOR = "update " + APPROVAL_TABLE + " SET supervisoraccepted = ? WHERE reimbursementid = ?";
	
	private static final String UPDATE_APPROVAL_BY_HEAD = "update " + APPROVAL_TABLE + " SET headaccepted = ? WHERE reimbursementid = ?";
	
	private static final String UPDATE_APPROVAL_BY_BENCO = "update " + APPROVAL_TABLE + " SET bencoaccepted = ? WHERE reimbursementid = ?";

	@Override
	public void insertNewApproval(Approval approval) {
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(INSERT_NEW_APPROVAL);
			
			System.out.println("insert postgres reID is " + approval.getReimbursementid());
			
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
		
		try (Connection conn = ConnectionFactory.getConnection()){
			
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
		
		System.out.println("arbs postg " + reimbursementid);
		
		PreparedStatement stmt = null;
		
		try (Connection conn = ConnectionFactory.getConnection()){
			
			if (usertype == 1) {
				
				System.out.println("Something went wrong with accepting reimbursement, usertype error - lowly employees can't accept reimbursements");
				
				return;
				
			} else if (usertype == 2) {
				
				stmt = conn.prepareStatement(UPDATE_APPROVAL_BY_SUPERVISOR);
				
				System.out.println("supervisor");
				
			} else if (usertype == 3) {
				
				stmt = conn.prepareStatement(UPDATE_APPROVAL_BY_HEAD);
				
				System.out.println("head");
				
			} else if (usertype == 4) {
				
				stmt = conn.prepareStatement(UPDATE_APPROVAL_BY_BENCO);
				
				System.out.println("benco");
				
			} else {
				
				System.out.println("Something went wrong with accepting reimbursement, usertype error");
				
				return;
			}
			
			System.out.println("arbs: " + reimbursementid);
			
			stmt.setBoolean(1,  true);
			
			stmt.setInt(2,  reimbursementid);
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}

}
