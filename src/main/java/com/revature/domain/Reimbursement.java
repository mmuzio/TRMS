package com.revature.domain;

import java.time.LocalDateTime;

public class Reimbursement {
	
	private String reimbusementId;
	
	private String username;
	
	private String description;
	
//	private LocalDateTime eventtime;
//	
//	private int eventtype;
//	
//	private int gradeformat;
//	
//	private int missedworktime;
	
	private int price;

	public Reimbursement(String username, String description, LocalDateTime eventtime, int eventtype, int gradeformat,
			int missedworktime, int price) {
		super();
		this.username = username;
		this.description = description;
//		this.eventtime = eventtime;
//		this.eventtype = eventtype;
//		this.gradeformat = gradeformat;
//		this.missedworktime = missedworktime;
		this.price = price;
	}

	public Reimbursement() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
//		result = prime * result + ((eventtime == null) ? 0 : eventtime.hashCode());
//		result = prime * result + eventtype;
//		result = prime * result + gradeformat;
//		result = prime * result + missedworktime;
		result = prime * result + price;
		result = prime * result + ((reimbusementId == null) ? 0 : reimbusementId.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Reimbursement other = (Reimbursement) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
//		if (eventtime == null) {
//			if (other.eventtime != null)
//				return false;
//		} else if (!eventtime.equals(other.eventtime))
//			return false;
//		if (eventtype != other.eventtype)
//			return false;
//		if (gradeformat != other.gradeformat)
//			return false;
//		if (missedworktime != other.missedworktime)
//			return false;
		if (price != other.price)
			return false;
		if (reimbusementId == null) {
			if (other.reimbusementId != null)
				return false;
		} else if (!reimbusementId.equals(other.reimbusementId))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbusementId=" + reimbusementId + ", username=" + username + ", description="
				+ description + ", eventtime=" + 
//				eventtime + ", eventtype=" + eventtype + ", gradeformat=" + gradeformat
//				+ ", missedworktime=" + missedworktime + 
				", price=" + price + "]";
	}

	public String getReimbusementId() {
		return reimbusementId;
	}

	public void setReimbusementId(String reimbusementId) {
		this.reimbusementId = reimbusementId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public LocalDateTime getEventtime() {
//		return eventtime;
//	}
//
//	public void setEventtime(LocalDateTime eventtime) {
//		this.eventtime = eventtime;
//	}
//
//	public int getEventtype() {
//		return eventtype;
//	}
//
//	public void setEventtype(int eventtype) {
//		this.eventtype = eventtype;
//	}
//
//	public int getGradeformat() {
//		return gradeformat;
//	}
//
//	public void setGradeformat(int gradeformat) {
//		this.gradeformat = gradeformat;
//	}
//
//	public int getMissedworktime() {
//		return missedworktime;
//	}
//
//	public void setMissedworktime(int missedworktime) {
//		this.missedworktime = missedworktime;
//	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
}
