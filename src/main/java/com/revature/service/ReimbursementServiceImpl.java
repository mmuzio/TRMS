package com.revature.service;

import java.util.List;

import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDAOPostgres;
import com.revature.domain.Approval;
import com.revature.domain.Reimbursement;

public class ReimbursementServiceImpl implements ReimbursementService {
	
	private ReimbursementDAO reDao = new ReimbursementDAOPostgres();
	
	private ApprovalService appService = new ApprovalServiceImpl();

	@Override
	public List<Reimbursement> getAllReimbursements() {
		return reDao.retrieveAllReimbursements();
	}

	@Override
	public void addReimbursement(Reimbursement re) {
		reDao.insertReimbursement(re);
		System.out.println("addRe id is " + re.getReimbursementId());
		

	}
	
	public void setReimbursementDao(ReimbursementDAO reDao) {
		this.reDao = reDao;
	}

	@Override
	public List<Reimbursement> getMyReimbursements(String username) {
		return reDao.retrieveMyReimbursements(username);
	}

	@Override
	public List<Reimbursement> getReportsReimbursements(String username) {
		return reDao.retrieveReportsReimbursements(username);
	}

	@Override
	public void acceptReimbursement(int reimbursementid) {
		if (isApproved(reimbursementid)) {
			reDao.acceptReimbursement(reimbursementid);
		}
		return;
		
	}

	@Override
	public void rejectReimbursement(int reimbursementid) {
		reDao.rejectReimbursement(reimbursementid);
		
	}

	@Override
	public boolean isApproved(int reimbursementid) {
		return appService.isApproved(reimbursementid);
	}

	@Override
	public void awardReimbursement(int reimbursementid) {
		//reDao.awardReimbursement(reimbursementid);
		return;
		
	}

}
