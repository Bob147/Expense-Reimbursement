package com.proj.servlets.manager;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ManagerHomeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("username") == null) {
			request.getSession().invalidate();
			response.sendRedirect("login");
		} else {
			request.getRequestDispatcher("Views/Manager/ManagerHome.html").forward(request, response);
		}
	}

}
