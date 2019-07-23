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
@WebServlet("/demo07")
public class Demo07Servlet extends GenericServlet {
	
	// Servlet容器被销毁或容器重新加载的时候都会执行destroy方法
	@Override
	public void destroy() {
		System.out.println("调用了destroy方法。。。");
	}

	// Servlet创建时候自动调用
	@Override
	public void init() throws ServletException {
		System.out.println("调用了init方法。。。");
	}

	// 每次请求都要执行一次，主要实现系统的业务功能
	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		System.out.println("调用了service方法。。。");
	}
	
}
