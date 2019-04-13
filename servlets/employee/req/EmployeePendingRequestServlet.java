package com.proj.servlets.employee.req;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.dao.RequestDao;
import com.proj.dao.RequestDaoImpl;
import com.proj.model.Request;
import com.proj.servlets.utility.User;

public class EmployeePendingRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if ((!User.isEmployeeNull() && !User.isManager())) {
			RequestDao rd = new RequestDaoImpl();
			ObjectMapper om = new ObjectMapper();
			PrintWriter pw = response.getWriter();
			List<Request> requests = rd.getPendingRequestsByEmployeeId(User.getEmployeeId());
			String requestString = om.writeValueAsString(requests);
			requestString = "{\"requests\":" + requestString + "}";
			pw.print(requestString);
		} else {
			request.getRequestDispatcher("Views/Login.html").forward(request, response);
		}
	}

}
