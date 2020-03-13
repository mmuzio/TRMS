package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.GsonBuilder;
import com.revature.domain.Attachment;
import com.revature.domain.User;
import com.revature.service.AttachmentService;
import com.revature.service.AttachmentServiceImpl;

/**
 * Servlet implementation class AttachmentServlet
 */
public class AttachmentServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private AttachmentService attService = new AttachmentServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttachmentServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sess = request.getSession(false);
		
		User user = (User) sess.getAttribute("user");
		
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		
		if (sess != null && user != null) {
			
			String reimbursementid = request.getParameter("reimbursementid");
			
			int reimbursementId = Integer.parseInt(reimbursementid);
				
			attachmentList = attService.getAttachmentsById(reimbursementId);
			
			String attachmentsJSON = new GsonBuilder().create().toJson(attachmentList);
			
			PrintWriter pw = response.getWriter();
			
			pw.write(attachmentsJSON);
			
			System.out.println("attachmentsJSON written to printWriter");
			
			System.out.println("attachmentsJSON is \n" + attachmentsJSON);
			
		} else {
			
			response.getWriter().write("user not logged in");
			
			response.sendRedirect("pages/reimbursements.html");
			
			System.out.println("user not logged in with session");
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sess = request.getSession(false);
		
		if (sess != null && sess.getAttribute("user") != null) {
			
			String attachmentJson = request.getReader().readLine();
			
			System.out.println("attachment is " + attachmentJson);
			
			Attachment attachment = new GsonBuilder().create().fromJson(attachmentJson, Attachment.class);
			
			try {
				
				attService.addAttachment(attachment);
				
				System.out.println("Reimbursement successfully added");
				
				System.out.println("Attempting to redirect...");
				
				response.sendRedirect("pages/reimbursements.html");
				
				return;
				
				
			} catch (Exception e) {
				
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				
				response.getWriter().write("Reimbursement could not be created");
				
				System.out.println("Reimbursement could not be created");
				
			}
			
		} else {
			
			response.getWriter().write("user not logged in");
			
			response.sendRedirect("pages/reimbursements.html");
			
			System.out.println("user not logged in with session");
			
		}
		
	}

}
