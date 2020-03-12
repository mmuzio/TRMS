package com.revature.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Reimbursement {
	
	private int reimbursementId;
	
	private String username;
	
	private String eventtype;
	
	private String description;
	
	private String location;
	
	private LocalDateTime eventtime;
	
	private String eventtimestring;
	
	private Timestamp eventtimestamp;
	
	private LocalDateTime submittime;
	
	private String gradeformat;
	
	private String justification;
	
	private int missedwork;
	
	private int price;
	
	private String approvalstatus;
	
	
	// Use this constructor when retrieving from database
	// You will need all fields when retrieving
	public Reimbursement(int reimbursementid, String username, 
			String eventtype, String description, String location, 
			LocalDateTime eventtime, LocalDateTime submittime, String gradeformat,
			String justification, int missedwork, int price, String approvalstatus) {
		
		super();
		
		this.reimbursementId = reimbursementid;
		
		this.username = username;
		
		this.eventtype = eventtype;
		
		this.description = description;
		
		this.location = location;
		
		this.eventtime = eventtime;
		
		this.submittime = submittime;
		
		this.gradeformat = gradeformat;
		
		this.justification = justification;
		
		this.missedwork = missedwork;
		
		this.price = price;
		
		this.approvalstatus = approvalstatus;
		
	}
	
	// Use this constructor when inserting into database
	// reimbursementid, submittime and approvalstatus have default values on insert
	// so they don't need to be set in constructor
	public Reimbursement(String username, String eventtype, 
			String description, String location, LocalDateTime eventtime, 
			String gradeformat, String justification, int missedwork, int price) {
		
		super();
		
		this.username = username;
		
		this.eventtype = eventtype;
		
		this.description = description;
		
		this.location = location;
		
		this.eventtime = eventtime;
		
		this.gradeformat = gradeformat;
		
		this.justification = justification;
		
		this.missedwork = missedwork;
		
		this.price = price;
		
	}
	
	// Use this constructor when retrieving from form (doPost in reimbursementServlet)
	// eventtime comes in as String, needs to be converted to localdatetime
	public Reimbursement(String username, String eventtype, 
			String description, String location, String eventtimestring, 
			String gradeformat, String justification, int missedwork, int price) {
		
		super();
		
		this.username = username;
		
		this.eventtype = eventtype;
		
		this.description = description;
		
		this.location = location;
		
		this.eventtimestring = eventtimestring;
		
		this.gradeformat = gradeformat;
		
		this.justification = justification;
		
		this.missedwork = missedwork;
		
		this.price = price;
		
	}

	public Reimbursement() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approvalstatus == null) ? 0 : approvalstatus.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((eventtime == null) ? 0 : eventtime.hashCode());
		result = prime * result + ((eventtype == null) ? 0 : eventtype.hashCode());
		result = prime * result + ((gradeformat == null) ? 0 : gradeformat.hashCode());
		result = prime * result + ((justification == null) ? 0 : justification.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + missedwork;
		result = prime * result + price;
		result = prime * result + reimbursementId;
		result = prime * result + ((submittime == null) ? 0 : submittime.hashCode());
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
		if (approvalstatus == null) {
			if (other.approvalstatus != null)
				return false;
		} else if (!approvalstatus.equals(other.approvalstatus))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (eventtime == null) {
			if (other.eventtime != null)
				return false;
		} else if (!eventtime.equals(other.eventtime))
			return false;
		if (eventtype == null) {
			if (other.eventtype != null)
				return false;
		} else if (!eventtype.equals(other.eventtype))
			return false;
		if (gradeformat == null) {
			if (other.gradeformat != null)
				return false;
		} else if (!gradeformat.equals(other.gradeformat))
			return false;
		if (justification == null) {
			if (other.justification != null)
				return false;
		} else if (!justification.equals(other.justification))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (missedwork != other.missedwork)
			return false;
		if (price != other.price)
			return false;
		if (reimbursementId != other.reimbursementId)
			return false;
		if (submittime == null) {
			if (other.submittime != null)
				return false;
		} else if (!submittime.equals(other.submittime))
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
		return "Reimbursement [reimbursementId=" + reimbursementId + ", username=" + username + ", eventtype="
				+ eventtype + ", description=" + description + ", location=" + location + ", eventtime=" + eventtime
				+ ", submittime=" + submittime + ", gradeformat=" + gradeformat + ", justification=" + justification
				+ ", missedwork=" + missedwork + ", price=" + price + ", approvalstatus=" + approvalstatus + "]";
	}

	public int getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
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

	public String getEventtype() {
		return eventtype;
	}

	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDateTime getEventtime() {
		return eventtime;
	}

	public void setEventtime(LocalDateTime eventtime) {
		this.eventtime = eventtime;
	}

	public String getEventtimestring() {
		return eventtimestring;
	}

	public void setEventtimestring(String eventtimestring) {
		this.eventtimestring = eventtimestring;
	}

	public Timestamp getEventtimestamp() {
		return eventtimestamp;
	}

	public void setEventtimestamp(Timestamp eventtimestamp) {
		this.eventtimestamp = eventtimestamp;
	}

	public LocalDateTime getSubmittime() {
		return submittime;
	}

	public void setSubmittime(LocalDateTime submittime) {
		this.submittime = submittime;
	}

	public String getGradeformat() {
		return gradeformat;
	}

	public void setGradeformat(String gradeformat) {
		this.gradeformat = gradeformat;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public int getMissedwork() {
		return missedwork;
	}

	public void setMissedwork(int missedwork) {
		this.missedwork = missedwork;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getApprovalstatus() {
		return approvalstatus;
	}

	public void setApprovalstatus(String approvalstatus) {
		this.approvalstatus = approvalstatus;
	}
	
	
	
}
