package com.proj.servlets.employee;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj.dao.EmployeeDao;
import com.proj.dao.EmployeeDaoImpl;
import com.proj.model.Employee;
import com.proj.servlets.utility.User;

public class UpdateEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!User.isEmployeeNull() && !User.isManager()) {
			
			int eId = new EmployeeDaoImpl().getEmployeeById(User.getEmployeeId()).getId();
			int rId = new EmployeeDaoImpl().getEmployeeById(User.getEmployeeId()).getReportsto();
			String firstname = request.getParameter("fName");
			String lastname = request.getParameter("lName");
			String username = request.getParameter("uName");
			String password = request.getParameter("password");
			String address = request.getParameter("address");
			
			EmployeeDao ed = new EmployeeDaoImpl();
			Employee e = new Employee();
			e.setId(eId);
			e.setFirstname(firstname);
			e.setLastname(lastname);
			e.setUsername(username);
			e.setPassword(password);
			e.setReportsto(rId);
			e.setAddress(address);
			
			ed.updateEmployee(e);;
			response.sendRedirect("employeeprofile");
		} else {
			response.sendRedirect("login");
		}

	}

}
