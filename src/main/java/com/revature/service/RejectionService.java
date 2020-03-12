package com.revature.service;

import java.util.List;

import com.revature.domain.Rejection;

public interface RejectionService {

	public void rejectReimbursementBySuperior(Rejection rejection);
	
	public List<Rejection> getRejectionsByUsername(String username);
	
	public List<Rejection> getRejectionsById(int reimbursementid);
	
}
