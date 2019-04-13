package com.proj.servlets.employee;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmployeeHomeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("username")==null) {
			request.getSession().invalidate();
			response.sendRedirect("login");
		} else {
			request.getRequestDispatcher("Views/Employee/EmployeeHome.html").forward(request, response);
		}
	}
}
