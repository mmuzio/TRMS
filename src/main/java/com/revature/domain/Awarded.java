package com.revature.domain;

public class Awarded {
	
	private int reimbursementid;
	
	private int amount;
	
	

	public Awarded(int reimbursementid, int amount) {
		super();
		this.reimbursementid = reimbursementid;
		this.amount = amount;
	}

	public int getReimbursementid() {
		return reimbursementid;
	}

	public void setReimbursementid(int reimbursementid) {
		this.reimbursementid = reimbursementid;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	

}
