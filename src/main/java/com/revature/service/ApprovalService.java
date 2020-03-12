package com.revature.service;

import com.revature.domain.Approval;

public interface ApprovalService {
	
	public void addNewApproval(Approval approval);
	
	public Approval getApprovalById(int reimbursementid);
	
	public void acceptReimbursementBySuperior(int reimbursementid, int usertype);
	
	boolean isAccepted(int reimbursementid);
	
	public void acceptReimbursement(int reimbursementid);
	
	public boolean isApproved(int reimbursementid);

	public void awardReimbursement(int reimbursementid);

}
