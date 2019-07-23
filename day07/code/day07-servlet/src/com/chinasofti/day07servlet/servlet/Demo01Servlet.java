package com.chinasofti.day07servlet.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Demo01Servlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取请求行
		String method = request.getMethod();
		System.out.println("请求方式：" + method);
		
		String requestUrl = request.getRequestURL().toString();
		System.out.println("请求地址：" + requestUrl);
		
		// 获取请求头
		String accept = request.getHeader("Accept");
		System.out.println("acept: " + accept);
		
		// 获取所有请求头的名字
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			String headerValue = request.getHeader(headerName);
			System.out.println(headerName + ": " + headerValue);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//response.getWriter().write("doGet...");
		doPost(request, response);
	}

}
