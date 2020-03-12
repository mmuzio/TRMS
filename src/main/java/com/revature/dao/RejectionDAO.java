package com.revature.dao;

import java.util.List;

import com.revature.domain.Rejection;

public interface RejectionDAO {

	public void rejectReimbursementBySuperior(Rejection rejection);

	public List<Rejection> retrieveRejectionsByUsername(String username);

	public List<Rejection> retrieveRejectionsById(int reimbursementid);

}
