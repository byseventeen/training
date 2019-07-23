package com.chinasofti.day07servlet.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	Servlet生命周期
*/
@WebServlet("/login")
public class LoginServlet extends GenericServlet {
	
	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		String userName = req.getParameter("userName");
		String userPass = req.getParameter("userPass");
		
		res.setContentType("text/html;charset=utf-8");
		if ("admin".equals(userName) && "123".equals(userPass)) {
			res.getWriter().write("登录成功!");
		} else {
			res.getWriter().write("用户名或密码不正确!");
		}
	}
	
}
