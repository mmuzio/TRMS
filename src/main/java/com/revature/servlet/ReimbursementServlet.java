package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	
	//private ApprovalService appService = new ApprovalServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimbursementServlet() {
    	
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sess = request.getSession(false);
		
		User user = (User) sess.getAttribute("user");
		
		if (sess != null && user != null) {
			
			//List<Reimbursement> reimbursementList = new ArrayList<Reimbursement>();
				
			List<Reimbursement> reimbursementList = reService.getMyReimbursements(user.getUsername());
			
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
		
		User user = (User) sess.getAttribute("user");
		
		if (sess != null && sess.getAttribute("user") != null) {
			
			String reimbursementJson = request.getReader().readLine();
			
			System.out.println(reimbursementJson);
			
			Reimbursement reimbursement = new GsonBuilder().create().fromJson(reimbursementJson, Reimbursement.class);
			
			String eventtimestring = reimbursement.getEventtimestring();
			
			//System.out.println(eventtimestring);
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			
			LocalDate eventdate = LocalDate.parse(eventtimestring, formatter);
			
			LocalDateTime eventtime = (LocalDateTime) eventdate.atStartOfDay();
			
			//System.out.println(eventtime.toString());
			
			reimbursement.setEventtime(eventtime);
			
			reimbursement.setUsername(user.getUsername());
			
			try {
				
				reService.addReimbursement(reimbursement);
				
				//Approval approval = new Approval(reimbursement.getReimbursementId());
				
				//System.out.println("Your approval is " + approval.toString());
				
				//appService.addNewApproval(approval);
				
				//response.getWriter().write("Success");
				
				System.out.println("Reimbursement successfully added");
				
				System.out.println("Attempting to redirect...");
				
				//response.sendRedirect("pages/reimbursements.html");
				
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
