package com.revature.domain;

public class Approval {
	
	private int reimbursementid;
	
	//private boolean accepted;
	
	//private boolean awarded;

	private boolean supervisorAccepted;
	
	private boolean headAccepted;
	
	private boolean bencoAccepted;
	
	public Approval() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Approval(int reimbursementid) {
		super();
		this.reimbursementid = reimbursementid;
	}

	public Approval(int reimbursementid, boolean supervisorAccepted,
			boolean headAccepted, boolean bencoAccepted) {
		super();
		this.reimbursementid = reimbursementid;
		//this.accepted = accepted;
		//this.awarded = awarded;
		this.supervisorAccepted = supervisorAccepted;
		this.headAccepted = headAccepted;
		this.bencoAccepted = bencoAccepted;
	}

	@Override
	public String toString() {
		return "Approval [reimbursementid=" + reimbursementid + ", accepted="
				+ ", supervisorAccepted=" + supervisorAccepted + ", headAccepted=" 
				+ headAccepted + ", bencoAccepted=" + bencoAccepted + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		//result = prime * result + (accepted ? 1231 : 1237);
		//result = prime * result + (awarded ? 1231 : 1237);
		result = prime * result + (bencoAccepted ? 1231 : 1237);
		result = prime * result + (headAccepted ? 1231 : 1237);
		result = prime * result + reimbursementid;
		result = prime * result + (supervisorAccepted ? 1231 : 1237);
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
		Approval other = (Approval) obj;
//		if (accepted != other.accepted)
//			return false;
//		if (awarded != other.awarded)
//			return false;
		if (bencoAccepted != other.bencoAccepted)
			return false;
		if (headAccepted != other.headAccepted)
			return false;
		if (reimbursementid != other.reimbursementid)
			return false;
		if (supervisorAccepted != other.supervisorAccepted)
			return false;
		return true;
	}

	public int getReimbursementid() {
		return reimbursementid;
	}

	public void setReimbursementid(int reimbursementid) {
		this.reimbursementid = reimbursementid;
	}

//	public boolean isAccepted() {
//		return accepted;
//	}
//
//	public void setAccepted(boolean accepted) {
//		this.accepted = accepted;
//	}
//
//	public boolean isAwarded() {
//		return awarded;
//	}
//
//	public void setAwarded(boolean awarded) {
//		this.awarded = awarded;
//	}

	public boolean isSupervisorAccepted() {
		return supervisorAccepted;
	}

	public void setSupervisorAccepted(boolean supervisorAccepted) {
		this.supervisorAccepted = supervisorAccepted;
	}

	public boolean isHeadAccepted() {
		return headAccepted;
	}

	public void setHeadAccepted(boolean headAccepted) {
		this.headAccepted = headAccepted;
	}

	public boolean isBencoAccepted() {
		return bencoAccepted;
	}

	public void setBencoAccepted(boolean bencoAccepted) {
		this.bencoAccepted = bencoAccepted;
	}

}
