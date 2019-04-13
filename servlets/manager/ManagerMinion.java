package com.proj.servlets.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ManagerMinion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ManagerMinion() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Views/Manager/ManagerMinion.html").forward(request, response);
	}


}
