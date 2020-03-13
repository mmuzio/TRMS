package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.GsonBuilder;
import com.revature.domain.Approval;
import com.revature.domain.User;
import com.revature.service.ApprovalService;
import com.revature.service.ApprovalServiceImpl;

/**
 * Servlet implementation class ApprovalsServlet
 */
public class ApprovalServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ApprovalService appService = new ApprovalServiceImpl();
	
	//private RejectionService rejService = new RejectionServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApprovalServlet() {
    	
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sess = request.getSession(false);
		
		User user = (User) sess.getAttribute("user");
		
		if (sess != null && user != null) {
			
			String reimbursementid = request.getParameter("reimbursementid");
			
			int reimbursementId = Integer.parseInt(reimbursementid);
				
			Approval approval = appService.getApprovalById(reimbursementId);
			
			String approvalJSON = new GsonBuilder().create().toJson(approval);
			
			PrintWriter pw = response.getWriter();
			
			pw.write(approvalJSON);
			
			System.out.println("approval written to printWriter");
			
			System.out.println("approval is \n" + approvalJSON);
			
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
		
		User user = (User) sess.getAttribute("user");
		
		if (sess != null && sess.getAttribute("user") != null) {
			
			String approveReimbursementJson = request.getReader().readLine();
			
			System.out.println("approval post: \n" + approveReimbursementJson);
			
			Approval approval = new GsonBuilder().create().fromJson(approveReimbursementJson, Approval.class);
//			
//			if (appService.getApprovalById(approval.getReimbursementid()) == null) {
//				
//				appService.addNewApproval(approval);
//				
//			}
			
			try {
					
				appService.acceptReimbursementBySuperior(approval.getReimbursementid(), user.getUsertype());
					
				System.out.println("Reimbursement successfully accepted for id = " + approval.getReimbursementid() + "\nBy usertype " + user.getUsertype());
				
				response.sendRedirect("pages/reimbursements.html");
				
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
