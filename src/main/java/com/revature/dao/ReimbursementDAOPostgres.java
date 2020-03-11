package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.domain.Reimbursement;
import com.revature.util.ConnectionFactory;

public class ReimbursementDAOPostgres implements ReimbursementDAO {
	
	private Connection conn = ConnectionFactory.getConnection();
	
	private static final String REIMBURSEMENT_TABLE = "reimbursements";
	
	private static final String SELECT_ALL_REIMBURSEMENTS = "select * from " + REIMBURSEMENT_TABLE;
	
	private static final String SELECT_REPORTS_REIMBURSEMENTS = "select * from " + REIMBURSEMENT_TABLE + " WHERE username in ( select username from users where reportsto = ?)";
	
	private static final String SELECT_MY_REIMBURSEMENTS = "select * from " + REIMBURSEMENT_TABLE + " WHERE username = ?";
	
	private static final String SELECT_REIMBURSEMENT_BY_ID = "select * from " + REIMBURSEMENT_TABLE + " WHERE reimbursementid = ?";
	
	private static final String INSERT_REIMBURSEMENT = "call insert_reimbursement(?,?,?)";
	
	private static final String ACCEPT_REIMBURSEMENT = "update " + REIMBURSEMENT_TABLE + " SET approvalstatus = ? WHERE reimbursementid = ?";
	
	//private static final String REJECT_REIMBURSEMENT = "update " + REIMBURSEMENT_TABLE + " SET accepted = ? WHERE reimbursemendid = ?";

	@Override
	public List<Reimbursement> retrieveAllReimbursements() {
		
		List<Reimbursement> reimbursementList = new ArrayList<Reimbursement>();
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_REIMBURSEMENTS);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				reimbursementList.add(
						new Reimbursement(rs.getInt("reimbursementid"),
								rs.getString("username"), 								
								rs.getString("description"), 						
								rs.getInt("price"),
								rs.getString("approvalstatus")));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return reimbursementList;
		
	}

	@Override
	public void insertReimbursement(Reimbursement re) {
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(INSERT_REIMBURSEMENT);
			
			stmt.setString(1, re.getUsername());
			
			stmt.setString(2, re.getDescription());
			
			stmt.setInt(3, re.getPrice());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}

	@Override
	public Reimbursement getReimbursementById(int id) {
		
		Reimbursement ret = new Reimbursement();
		
		try (Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_REIMBURSEMENT_BY_ID);

	        preparedStatement.setInt(1, id);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				ret.setReimbursementId(rs.getInt(1));
				
				ret.setUsername(rs.getString(2));
				
				ret.setDescription(rs.getString(3));
				
				ret.setPrice(rs.getInt(4));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return ret;
	}

	@Override
	public List<Reimbursement> retrieveMyReimbursements(String username) {
		
		List<Reimbursement> myReimbursementList = new ArrayList<Reimbursement>();
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(SELECT_MY_REIMBURSEMENTS);
			
			stmt.setString(1, username);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				myReimbursementList.add(
						new Reimbursement(rs.getInt("reimbursementid"),
								rs.getString("username"), 								
								rs.getString("description"), 						
								rs.getInt("price"),
								rs.getString("approvalstatus")));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return myReimbursementList;
	}

	@Override
	public List<Reimbursement> retrieveReportsReimbursements(String username) {
		
		List<Reimbursement> reportsReimbursementList = new ArrayList<Reimbursement>();
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(SELECT_REPORTS_REIMBURSEMENTS);
			
			stmt.setString(1, username);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				reportsReimbursementList.add(
						new Reimbursement(rs.getInt("reimbursementid"),
								rs.getString("username"), 								
								rs.getString("description"), 						
								rs.getInt("price"),
								rs.getString("approvalstatus")));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return reportsReimbursementList;
	}

	@Override
	public void acceptReimbursement(int reimbursementid) {
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(ACCEPT_REIMBURSEMENT);
			
			stmt.setString(1, "Accepted");
			
			stmt.setInt(2,  reimbursementid);
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}

	@Override
	public void rejectReimbursement(int reimbursementid) {
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(ACCEPT_REIMBURSEMENT);
			
			stmt.setString(1, "Rejected");
			
			stmt.setInt(2,  reimbursementid);
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	

}
