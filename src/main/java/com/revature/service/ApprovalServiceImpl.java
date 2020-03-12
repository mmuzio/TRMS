package com.revature.service;

import com.revature.dao.ApprovalDAO;
import com.revature.dao.ApprovalDAOPostgres;
import com.revature.domain.Approval;

public class ApprovalServiceImpl implements ApprovalService {
	
	ApprovalDAO appDao = new ApprovalDAOPostgres();

	@Override
	public void acceptReimbursementBySuperior(int reimbursementid, int usertype) {
		appDao.acceptReimbursementBySuperior(reimbursementid, usertype);
		
	}
	
	@Override
	public boolean isAccepted(int reimbursementid) {
		
		Approval approval = getApprovalById(reimbursementid);
		
		boolean dsapproval = approval.isSupervisorAccepted();
		
		boolean dhapproval = approval.isHeadAccepted();
		
		boolean bcapproval = approval.isBencoAccepted();
		
		if (dsapproval == true & dhapproval == true & bcapproval == true) {
			
			return true;
			
		} else {
			
			return false;
			
		}
		
	}

	@Override
	public void acceptReimbursement(int reimbursementid) {
		appDao.acceptReimbursement(reimbursementid);
		
	}

	@Override
	public void addNewApproval(Approval approval) {
		appDao.insertNewApproval(approval);
		
	}
	
	@Override
	public Approval getApprovalById(int reimbursementid) {
		return appDao.retrieveApprovalById(reimbursementid);
	}

	@Override
	public boolean isApproved(int reimbursementid) {
		
		Approval approval = getApprovalById(reimbursementid);
		
		boolean dsapproval = approval.isSupervisorAccepted();
		
		boolean dhapproval = approval.isHeadAccepted();
		
		boolean bcapproval = approval.isBencoAccepted();
		
		if (dsapproval == true & dhapproval == true & bcapproval == true) {
			
			return true;
			
		} else {
			
			return false;
			
		}
		
	}
		
	

	@Override
	public void awardReimbursement(int reimbursementid) {
		
		
	}

}
