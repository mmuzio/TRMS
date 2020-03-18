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
import com.revature.domain.Employee;
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
			
			String employeeJSON = request.getReader().readLine();
			
			Employee emp = new GsonBuilder().create().fromJson(employeeJSON, Employee.class);
			
			Employee ret = empService.getEmployeeByUsername(emp.getUsername());
			
			String newEmployeeJSON = new GsonBuilder().create().toJson(ret);
			
			PrintWriter pw = response.getWriter();
			
			pw.write(newEmployeeJSON);
			
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
		
		String manager = user.getUsername();
		
		List<String> usernamesOfEmployeesBetween = new ArrayList<String>();
		
		List<Employee> employeesBetween = new ArrayList<Employee>();
		
		if (sess != null && user != null) {
			
			String employeeJSON = request.getReader().readLine();
			
			Employee ret = empService.getEmployeeByUsername(employeeJSON);
			
			usernamesOfEmployeesBetween = empService.getUsernamesOfEmployeesBetween(employeeJSON, manager);
			
			employeesBetween = empService.getEmployeesBetween(usernamesOfEmployeesBetween);
			
			employeesBetween.add(0, ret);
			
			String newEmployeeJSON = new GsonBuilder().create().toJson(employeesBetween);
			
			PrintWriter pw = response.getWriter();
			
			pw.write(newEmployeeJSON);
			
		} else {
			
			response.getWriter().write("user not logged in");
			
			response.sendRedirect("pages/reimbursements.html");
			
			System.out.println("user not logged in with session");
			
		}
	}

}
