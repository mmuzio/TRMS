package com.revature.domain;

public class Acceptance {
	
	private int reimbursementid;
	
	private boolean accept;
	
	public Acceptance() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Acceptance(int reimbursementid, boolean accept) {
		super();
		this.reimbursementid = reimbursementid;
		this.accept = accept;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (accept ? 1231 : 1237);
		result = prime * result + reimbursementid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Acceptance other = (Acceptance) obj;
		if (accept != other.accept)
			return false;
		if (reimbursementid != other.reimbursementid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Acceptance [reimbursementid=" + reimbursementid + ", accept=" + accept + "]";
	}

	public int getReimbursementid() {
		return reimbursementid;
	}

	public void setReimbursementid(int reimbursementid) {
		this.reimbursementid = reimbursementid;
	}

	public boolean getAccept() {
		return accept;
	}

	public void setAccept(boolean accept) {
		this.accept = accept;
	}

}
