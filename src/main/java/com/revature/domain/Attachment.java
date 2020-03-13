package com.revature.domain;

public class Attachment {
	
	private int reimbursementid;
	
	private String attachmenttype;
	
	private String description;
	
	private String attachmentname;
	
	private String attachmentlink;

	public Attachment() {
		super();
	}
	
//	public Attachment(String attachmenttype, String description, String attachmentname,
//			String attachmentlink) {
//		super();
//		this.attachmenttype = attachmenttype;
//		this.description = description;
//		this.attachmentname = attachmentname;
//		this.attachmentlink = attachmentlink;
//	}

	public Attachment(int reimbursementid, String attachmenttype, String description, String attachmentname,
			String attachmentlink) {
		super();
		this.reimbursementid = reimbursementid;
		this.attachmenttype = attachmenttype;
		this.description = description;
		this.attachmentname = attachmentname;
		this.attachmentlink = attachmentlink;
	}

	@Override
	public String toString() {
		return "Attachment [reimbursementid=" + reimbursementid + ", attachmenttype=" + attachmenttype
				+ ", description=" + description + ", attachmentname=" + attachmentname + ", attachmentlink=" + attachmentlink + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attachmentlink == null) ? 0 : attachmentlink.hashCode());
		result = prime * result + ((attachmenttype == null) ? 0 : attachmenttype.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((attachmentname == null) ? 0 : attachmentname.hashCode());
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
		Attachment other = (Attachment) obj;
		if (attachmentlink == null) {
			if (other.attachmentlink != null)
				return false;
		} else if (!attachmentlink.equals(other.attachmentlink))
			return false;
		if (attachmenttype == null) {
			if (other.attachmenttype != null)
				return false;
		} else if (!attachmenttype.equals(other.attachmenttype))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (attachmentname == null) {
			if (other.attachmentname != null)
				return false;
		} else if (!attachmentname.equals(other.attachmentname))
			return false;
		return true;
	}

	public int getReimbursementid() {
		return reimbursementid;
	}

	public void setReimbursementid(int reimbursementid) {
		this.reimbursementid = reimbursementid;
	}

	public String getAttachmenttype() {
		return attachmenttype;
	}

	public void setAttachmenttype(String attachmenttype) {
		this.attachmenttype = attachmenttype;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAttachmentname() {
		return attachmentname;
	}

	public void setAttachmentname(String attachmentname) {
		this.attachmentname = attachmentname;
	}

	public String getAttachmentlink() {
		return attachmentlink;
	}

	public void setAttachmentlink(String attachmentlink) {
		this.attachmentlink = attachmentlink;
	}
	
	

}
