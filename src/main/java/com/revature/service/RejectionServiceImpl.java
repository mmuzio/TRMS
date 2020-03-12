package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDAOPostgres;
import com.revature.dao.RejectionDAO;
import com.revature.dao.RejectionDAOPostgres;
import com.revature.domain.Rejection;

public class RejectionServiceImpl implements RejectionService {
	
	RejectionDAO rejDao = new RejectionDAOPostgres();
	ReimbursementDAO reDao = new ReimbursementDAOPostgres();

	@Override
	public void rejectReimbursementBySuperior(Rejection rejection) {
		rejDao.rejectReimbursementBySuperior(rejection);
		reDao.rejectReimbursement(rejection.getReimbursementid());
		
	}

	@Override
	public List<Rejection> getRejectionsByUsername(String username) {
		List<Rejection> myRejections = new ArrayList<Rejection>();
		myRejections = rejDao.retrieveRejectionsByUsername(username);
		return myRejections;
	}

	@Override
	public List<Rejection> getRejectionsById(int reimbursementid) {
		List<Rejection> myRejections = new ArrayList<Rejection>();
		myRejections = rejDao.retrieveRejectionsById(reimbursementid);
		return myRejections;
	}

}
