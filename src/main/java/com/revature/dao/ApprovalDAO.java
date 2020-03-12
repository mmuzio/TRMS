package com.revature.dao;

import java.util.List;

import com.revature.domain.Approval;

public interface ApprovalDAO {
	
	public void acceptReimbursementBySuperior(int reimbursementid, int usertype);
	
	public void acceptReimbursement(int reimbursementid);

	public void insertNewApproval(Approval approval);

	public List<Approval> retrieveMyApprovals(String username);

	public Approval retrieveApprovalById(int reimbursementid);

}
