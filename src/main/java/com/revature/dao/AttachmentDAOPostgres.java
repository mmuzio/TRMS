package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.domain.Attachment;
import com.revature.util.ConnectionFactory;

public class AttachmentDAOPostgres implements AttachmentDAO {
	
	private static final String ATTACHMENTS_TABLE = "attachments";
	
	private static final String SELECT_ATTACHMENT_BY_ID = "select * from " + ATTACHMENTS_TABLE + " WHERE reimbursementid = ?";
	
	private static final String INSERT_NEW_ATTACHMENT = "insert into " + ATTACHMENTS_TABLE + " VALUES(?, ?, ?, ?, ?)";

	@Override
	public List<Attachment> retrieveAttachmentsById(int reimbursementid) {
		
		List<Attachment> myAttachmentsList = new ArrayList<Attachment>();
		
		try (Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ATTACHMENT_BY_ID);

	        preparedStatement.setInt(1, reimbursementid);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				myAttachmentsList.add(
						new Attachment(rs.getInt(1),
								rs.getString(2), rs.getString(3),
								rs.getString(4), rs.getString(5)
								
								));
				
//				ret.setReimbursementid(rs.getInt(1));
//				
//				ret.setAttachmenttype(rs.getString(2));
//				
//				ret.setDescription(rs.getString(3));
//				
//				ret.setName(rs.getString(4));
//				
//				ret.setAttachmentlink(rs.getString(5));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return myAttachmentsList;
	}

	@Override
	public void insertAttachment(Attachment attachment) {
		
		try (Connection conn = ConnectionFactory.getConnection()) {
			
			PreparedStatement stmt = conn.prepareStatement(INSERT_NEW_ATTACHMENT);
			
			stmt.setInt(1, attachment.getReimbursementid());
			
			stmt.setString(2, attachment.getAttachmenttype());
			
			stmt.setString(3, attachment.getDescription());
			
			stmt.setString(4, attachment.getAttachmentname());
			
			stmt.setString(5, attachment.getAttachmentlink());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}

	}

}
