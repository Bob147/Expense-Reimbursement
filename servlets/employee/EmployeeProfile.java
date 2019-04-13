package com.proj.servlets.employee;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj.servlets.utility.User;


public class EmployeeProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EmployeeProfile() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rq;
		if (!User.isEmployeeNull()) {
			rq = request.getRequestDispatcher("Views/Employee/EmployeeProfile.html");
		} else {
			rq = request.getRequestDispatcher("Views/Login.html");
		}
		rq.forward(request, response);
	}

}
