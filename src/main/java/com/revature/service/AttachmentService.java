package com.revature.service;

import java.util.List;

import com.revature.domain.Attachment;

public interface AttachmentService {
	
	public List<Attachment> getAttachmentsById(int reimbursementid);
	
	public void addAttachment(Attachment attachment);

}
