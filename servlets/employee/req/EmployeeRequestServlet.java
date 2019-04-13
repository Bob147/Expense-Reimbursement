package com.proj.servlets.employee.req;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.dao.RequestDao;
import com.proj.dao.RequestDaoImpl;
import com.proj.model.Request;
import com.proj.servlets.utility.User;

public class EmployeeRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if ((!User.isEmployeeNull() && !User.isManager())) {
			RequestDao rd = new RequestDaoImpl();
			ObjectMapper om = new ObjectMapper();
			PrintWriter pw = response.getWriter();
			List<Request> requests = rd.getRequestsByEmployeeId(User.getEmployeeId());
			String requestString = om.writeValueAsString(requests);
			requestString = "{\"requests\":" + requestString + "}";
			pw.print(requestString);
		} else {
			RequestDispatcher rq = request.getRequestDispatcher("Views/Login.html");
			rq.forward(request, response);
		}
	}

}
