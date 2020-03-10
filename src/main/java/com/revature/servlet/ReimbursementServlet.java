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
import com.revature.domain.Reimbursement;
import com.revature.domain.User;
import com.revature.service.ReimbursementService;
import com.revature.service.ReimbursementServiceImpl;

/**
 * Servlet implementation class ReimbursementServlet
 */
public class ReimbursementServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ReimbursementService reService = new ReimbursementServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimbursementServlet() {
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
			
			if (user.getUsertype() == 1) {
				
				reimbursementList = reService.getMyReimbursements(user.getUsername());
				
			} else if (user.getUsertype() == 2) {
				
				reimbursementList = reService.getReportsReimbursements(user.getUsername());
				
			} else {
				
				reimbursementList = reService.getAllReimbursements();
				
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
			
			String reimbursementJson = request.getReader().readLine();
			
			System.out.println(reimbursementJson);
			
			Reimbursement reimbursement = new GsonBuilder().create().fromJson(reimbursementJson, Reimbursement.class);
			
			try {
				
				reService.addReimbursement(reimbursement);
				
				//response.getWriter().write("Success");
				
				System.out.println("Reimbursement successfully added");
				
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
