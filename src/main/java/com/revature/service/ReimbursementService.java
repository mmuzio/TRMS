package com.revature.service;

import java.util.List;

import com.revature.domain.Reimbursement;

public interface ReimbursementService {

	public List<Reimbursement> getAllReimbursements();
	
	public List<Reimbursement> getMyReimbursements(String username);
	
	public void addReimbursement(Reimbursement re);

	public List<Reimbursement> getReportsReimbursements(String username);
	
}
