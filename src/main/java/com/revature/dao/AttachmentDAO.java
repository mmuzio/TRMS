package com.revature.dao;

import java.util.List;

import com.revature.domain.Attachment;

public interface AttachmentDAO {
	
	public List<Attachment> retrieveAttachmentsById(int reimbursementid);
	
	public void insertAttachment(Attachment attachment);

}
