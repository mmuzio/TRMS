package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.domain.User;
import com.revature.service.UserService;
import com.revature.service.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static UserService authService = new UserServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
    	
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		
		String password = request.getParameter("password");
		
		User user = authService.validateUser(username, password);
		
		if (user == null) {
			
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			
		} else {
			
			HttpSession sess = request.getSession(true);
			
			sess.setAttribute("user", user);
			
			if (user.getUsertype() == 1) {
				
				response.sendRedirect("pages/addreimbursement.html");
				
			} else {
				
				response.sendRedirect("pages/reimbursements.html");
				
			}
			
			
		}
	}

}
