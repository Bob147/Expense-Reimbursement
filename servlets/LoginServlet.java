package com.proj.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.proj.dao.EmployeeDao;
import com.proj.dao.EmployeeDaoImpl;
import com.proj.dao.ManagerDao;
import com.proj.dao.ManagerDaoImpl;
import com.proj.model.Employee;
import com.proj.model.Manager;
import com.proj.servlets.utility.User;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 2L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.getRequestDispatcher("Views/Login.html").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		
		if(!request.getParameter("positionType").equals("Select a Position")) {
			if (request.getParameter("positionType").equals("Manager")) {
				ManagerDao md = new ManagerDaoImpl();
				Manager manager = md.getManagerByUsername(username);
				if (manager.login(password)) {
					User.setManager(manager.getId(), false);
					session.setAttribute("username", manager);
					response.sendRedirect("managerhome");
				} else {
					response.sendRedirect("login");
				}
			} else if (request.getParameter("positionType").equals("Employee")) {
				EmployeeDao ed = new EmployeeDaoImpl();
				Employee employee = ed.getEmployeeByUsername(username);
				if (employee.login(password)) {
					User.setEmployee(employee.getId(), false);
					session.setAttribute("username", employee);
					response.sendRedirect("employeehome");
				} else {
					response.sendRedirect("login");
				}
			} 
		} else {
		
			request.getRequestDispatcher("Views/Login.html").forward(request, response);
		}
	}

}
