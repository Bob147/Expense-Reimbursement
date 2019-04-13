package com.proj.servlets.manager.req;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.dao.EmployeeDao;
import com.proj.dao.EmployeeDaoImpl;
import com.proj.model.Employee;
import com.proj.servlets.utility.User;

public class GetEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!User.isManagerNull() && !User.isEmployee()) {
			EmployeeDao ed = new EmployeeDaoImpl();
			ObjectMapper om = new ObjectMapper();
			PrintWriter pw = response.getWriter();
			List<Employee> employees = ed.getEmployees();
			String requestString = om.writeValueAsString(employees);
			requestString = "{\"requests\":"+requestString+"}";
			pw.print(requestString);
		} else {
			request.getRequestDispatcher("Views/Login.html").forward(request, response);
		}
	}

}
