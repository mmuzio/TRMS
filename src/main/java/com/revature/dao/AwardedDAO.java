package com.revature.dao;

import com.revature.domain.Awarded;

public interface AwardedDAO {
	
	public void insertAward(Awarded awarded);
	
	public Awarded retrieveAwardById(int reimbursementid);

}
