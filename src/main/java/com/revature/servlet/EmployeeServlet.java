package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.GsonBuilder;
import com.revature.domain.Employee;
import com.revature.domain.Reimbursement;
import com.revature.domain.User;
import com.revature.service.EmployeeService;
import com.revature.service.EmployeeServiceImpl;

/**
 * Servlet implementation class EmployeeServlet
 */
public class EmployeeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private EmployeeService empService = new EmployeeServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
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
				
			//Employee emp = empService.getEmployeeByUsername(user.getUsername());
			
			String employeeJSON = request.getReader().readLine();
			
			System.out.println("empJSON is " + employeeJSON);
			
			Employee emp = new GsonBuilder().create().fromJson(employeeJSON, Employee.class);
			
			System.out.println("username should be mike: " + emp.getUsername());
			
			Employee ret = empService.getEmployeeByUsername(emp.getUsername());
			
			String newEmployeeJSON = new GsonBuilder().create().toJson(ret);
			
			PrintWriter pw = response.getWriter();
			
			pw.write(newEmployeeJSON);
			
			System.out.println("Employee written to printWriter");
			
			System.out.println("Employee is \n" + employeeJSON);
			
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
		
		if (sess != null && user != null) {
				
			//Employee emp = empService.getEmployeeByUsername(user.getUsername());
			
			String employeeJSON = request.getReader().readLine();
			
			System.out.println("empJSON is " + employeeJSON);
			
			//Employee emp = new GsonBuilder().create().fromJson(employeeJSON, Employee.class);
			
			//System.out.println("username should be mike: " + emp.getUsername());
			
			Employee ret = empService.getEmployeeByUsername(employeeJSON);
			
			//Employee ret = empService.getEmployeeByUsername("mike");
			
			System.out.println("Employee object: " + ret.toString());
			
			String newEmployeeJSON = new GsonBuilder().create().toJson(ret);
			
			//System.out.println(newEmployeeJSON);
			
			PrintWriter pw = response.getWriter();
			
			pw.write(newEmployeeJSON);
			
			System.out.println("Employee written to printWriter");
			
			System.out.println("Employee is \n" + newEmployeeJSON);
			
		} else {
			
			response.getWriter().write("user not logged in");
			
			response.sendRedirect("pages/reimbursements.html");
			
			System.out.println("user not logged in with session");
			
		}
	}

}
