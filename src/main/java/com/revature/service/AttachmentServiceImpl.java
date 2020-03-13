package com.revature.service;

import java.util.List;

import com.revature.dao.AttachmentDAO;
import com.revature.dao.AttachmentDAOPostgres;
import com.revature.domain.Attachment;

public class AttachmentServiceImpl implements AttachmentService {
	
	AttachmentDAO attDao = new AttachmentDAOPostgres();

	@Override
	public List<Attachment> getAttachmentsById(int reimbursementid) {
		return attDao.retrieveAttachmentsById(reimbursementid);
	}

	@Override
	public void addAttachment(Attachment attachment) {
		attDao.insertAttachment(attachment);
		return;
		
	}

}
