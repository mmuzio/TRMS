package com.revature.service;

import com.revature.domain.Awarded;

public class AwardedServiceImpl implements AwardedService {
	
	AwardedDAO awDao = new AwardedDAOPostgres();

	@Override
	public Awarded getAwardById(int reimbursementid) {
		return awDao.retrieveAwardById(reimbursementid);
	}

	@Override
	public void addAward(Awarded awarded) {
		awDao.insertAward(awarded);
		
	}
	
	

}
