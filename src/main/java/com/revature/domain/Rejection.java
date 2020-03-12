package com.revature.domain;

public class Rejection {
	
	private int reimbursementid;
	
	private String username;
	
	private int usertype;
	
	private String reason;

	public Rejection() {
		
		super();
		
	}

	public Rejection(int reimbursementid, String username, int usertype, String reason) {
		super();
		this.reimbursementid = reimbursementid;
		this.username = username;
		this.usertype = usertype;
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "Rejection [reimbursementid=" + reimbursementid + ", username=" + username + ", usertype=" + usertype
				+ ", reason=" + reason + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + reimbursementid;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + usertype;
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
		Rejection other = (Rejection) obj;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (reimbursementid != other.reimbursementid)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (usertype != other.usertype)
			return false;
		return true;
	}

	public int getReimbursementid() {
		return reimbursementid;
	}

	public void setReimbursementid(int reimbursementid) {
		this.reimbursementid = reimbursementid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUsertype() {
		return usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	

}
