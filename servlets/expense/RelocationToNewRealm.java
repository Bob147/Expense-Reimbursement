package com.proj.servlets.expense;

import java.io.IOException;

import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.dao.RequestDao;
import com.proj.dao.RequestDaoImpl;
import com.proj.servlets.utility.User;


public class RelocationToNewRealm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if ((!User.isManagerNull() && !User.isEmployee())) {
			RequestDao rd = new RequestDaoImpl();
			ObjectMapper om = new ObjectMapper();
			PrintWriter pw = response.getWriter();
			BigDecimal amount = rd.getSumByType("Relocation to new Realm");
			String requestString = om.writeValueAsString(amount);
			requestString = "{\"requests\":" + requestString + "}";
			pw.print(requestString);
		} else {
			request.getRequestDispatcher("Views/Login.html").forward(request, response);
		}
	}

}
