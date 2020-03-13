package com.revature.service;

import com.revature.domain.Awarded;

public interface AwardedService {
	
	public Awarded getAwardById(int reimbursementid);
	
	public void addAward(Awarded awarded);

}
