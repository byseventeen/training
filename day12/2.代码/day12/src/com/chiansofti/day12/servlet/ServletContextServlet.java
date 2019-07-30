package com.chiansofti.day12.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/demo01")
public class ServletContextServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ServletContext sc = req.getServletContext();
		sc.setAttribute("jdbcUrl", "jdbc:mysql://localhost:3306/aa");
		// 修改属性
		sc.setAttribute("jdbcUrl", "jdbc:oracle:thin://localhost:1521/aa");
		//sc.removeAttribute("jdbcUrl");
	}
	
}
