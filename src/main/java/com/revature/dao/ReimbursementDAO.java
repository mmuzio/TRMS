package com.revature.dao;

import java.util.List;

import com.revature.domain.Reimbursement;

public interface ReimbursementDAO {
	
	public List<Reimbursement> retrieveAllReimbursements();
	
	public List<Reimbursement> retrieveMyReimbursements(String username);
	
	public List<Reimbursement> retrieveReportsReimbursements(String username);
	
	public void insertReimbursement(Reimbursement re);
	
	public Reimbursement getReimbursementById(int id);

}
