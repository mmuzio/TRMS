package com.revature.service;

import java.util.List;

import com.revature.domain.Reimbursement;

public interface ReimbursementService {

	public List<Reimbursement> getAllReimbursements();
	
	public List<Reimbursement> getMyReimbursements(String username);
	
	public void addReimbursement(Reimbursement re);
	
	public void removeReimbursementById(int reimbursementid);
	
	public void acceptReimbursement(int reimbursementid);
	
	public void rejectReimbursement(int reimbursementid);

	public List<Reimbursement> getReportsReimbursements(String username);
	
	public boolean isApproved(int reimbursementid);
	
	public void awardReimbursement(int reimbursementid);
	
	public int getPendingAmount(String username);
	
	public int getAwardedAmount(String username);
	
}
