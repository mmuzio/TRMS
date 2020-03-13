package com.revature.dao;

import java.util.List;

import com.revature.domain.Reimbursement;

public interface ReimbursementDAO {
	
	public List<Reimbursement> retrieveAllReimbursements();
	
	public List<Reimbursement> retrieveMyReimbursements(String username);
	
	public List<Reimbursement> retrieveReportsReimbursements(String username);
	
	public void insertReimbursement(Reimbursement re);
	
	public void acceptReimbursement(int reimbursementid);
	
	public void rejectReimbursement(int reimbursementid);
	
	public Reimbursement getReimbursementById(int id);

	public int retrievePendingAmount(String username);

	public int retrieveAwardedAmount(String username);

}
