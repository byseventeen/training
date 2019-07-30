package com.chiansofti.day12.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/load3", "/load4"}, loadOnStartup=10)
public class LoadOnStartUp2Servlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		System.out.println("LoadOnStartUp2Servlet被创建了...");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}
	
}
