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
import com.revature.service.ReimbursementService;
import com.revature.service.ReimbursementServiceImpl;

/**
 * Servlet implementation class FundsServlet
 */
public class FundsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ReimbursementService reService = new ReimbursementServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FundsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sess = request.getSession(false);
		
		User user = (User) sess.getAttribute("user");
		
		int fundAmount = 0;
		
		if (sess != null && user != null) {
			
			String username = user.getUsername();
			
			String isPendingString = request.getParameter("isPending");
			
			System.out.println(username);
			
			System.out.println(isPendingString);
			
			boolean isPending = Boolean.parseBoolean(isPendingString);
			
			if (isPending) {
				
				fundAmount = reService.getPendingAmount(username);
				
				System.out.println("Pending");
				
			} else {
				
				fundAmount = reService.getAwardedAmount(username);
				
				System.out.println("Accepted");
				
			}
			
			String fundsJSON = new GsonBuilder().create().toJson(fundAmount);
			
			PrintWriter pw = response.getWriter();
			
			pw.write(fundsJSON);
			
			System.out.println("approval written to printWriter");
			
			System.out.println("funds are \n" + fundsJSON);
			
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
