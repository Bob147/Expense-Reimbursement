package com.proj.servlets.employee;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj.dao.EmployeeDaoImpl;
import com.proj.dao.RequestDao;
import com.proj.dao.RequestDaoImpl;
import com.proj.model.Employee;
import com.proj.model.Request;
import com.proj.servlets.utility.User;

public class SubmitRequestServlet extends HttpServlet {
	private static final long serialVersionUID = -7L;

	public SubmitRequestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!User.isEmployeeNull() && !User.isManager()) {
			double a = Double.parseDouble(request.getParameter("amount"));
			BigDecimal amount = BigDecimal.valueOf(a);
			RequestDao rd = new RequestDaoImpl();
			Request r = new Request();
			Employee e = new EmployeeDaoImpl().getEmployeeById(User.getEmployeeId());
			String type = "";
			if(!request.getParameter("expenseType").equals("Select a Type")) {
				type = request.getParameter("expenseType");
			}
			
			r.setType(type);
			r.setStatus("pending");
			r.setDecision("TBA");
			r.setAmount(amount);
			r.setEmployee(e);
			r.setManager(null);
			rd.addRequest(r);
			response.sendRedirect("employeehome");
		} else {
			response.sendRedirect("login");
		}

	}
	
}