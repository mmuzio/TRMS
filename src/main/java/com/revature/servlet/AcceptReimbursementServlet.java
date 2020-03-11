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
import com.revature.domain.Acceptance;
import com.revature.domain.Reimbursement;
import com.revature.domain.User;
import com.revature.service.ReimbursementService;
import com.revature.service.ReimbursementServiceImpl;

/**
 * Servlet implementation class AcceptReimbursementServlet
 */
public class AcceptReimbursementServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ReimbursementService reService = new ReimbursementServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptReimbursementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession(false);
		
		User user = (User) sess.getAttribute("user");
		
		if (sess != null && user != null) {
			
			List<Reimbursement> reimbursementList = new ArrayList<Reimbursement>();
			
			if (user.getUsertype() == 2) {
				
				reimbursementList = reService.getReportsReimbursements(user.getUsername());
				
			} else if (user.getUsertype() == 3) {
				
				reimbursementList = reService.getAllReimbursements();
				
			} else {
				
				System.out.println("Possible hacking attempt! Unauthorized user tried to accept reimbursements!");
				
			}
			
			System.out.println("User type is \n" + user.getUsertype());
			
			//List<Reimbursement> reimbursementList = reService.getAllReimbursements();
			
			String reimbursementListJSON = new GsonBuilder().create().toJson(reimbursementList);
			
			PrintWriter pw = response.getWriter();
			
			pw.write(reimbursementListJSON);
			
			System.out.println("reimbursementList written to printWriter");
			
			System.out.println("reimbursementList is \n" + reimbursementListJSON);
			
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
			
			String acceptReimbursementJson = request.getReader().readLine();
			
			System.out.println(acceptReimbursementJson);
			
			Acceptance accept = new GsonBuilder().create().fromJson(acceptReimbursementJson, Acceptance.class);
			
			try {
				
				if (accept.getAccept() == true) {
					
					reService.acceptReimbursement(accept.getReimbursementid());
					
					System.out.println("Reimbursement successfully accepted for id = " + accept.getReimbursementid());
					
					
					
				} else {
					
					reService.rejectReimbursement(accept.getReimbursementid());
					
					System.out.println("Reimbursement successfully rejected for id = " + accept.getReimbursementid());
					
				}
				
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
