package com.chinasofti.day07servlet.servlet;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取表单发送过来的数据
 */
@WebServlet("/regist")
public class RegistServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("doGet...");
		doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 设置request对象的字符集
		request.setCharacterEncoding("UTF-8");
				
		// 获取请求参数
		String userName = request.getParameter("userName");
		String userPass = request.getParameter("userPass");
		String gender = request.getParameter("gender");
		String[] favors = request.getParameterValues("favors");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String introduce = request.getParameter("introduce");
		
		System.out.println("userName = " + userName);
		System.out.println("userPass = " + userPass);
		System.out.println("gender = " + gender);
		System.out.println("favors = " + Arrays.toString(favors));
		System.out.println("province = " + province);
		System.out.println("city = " + city);
		System.out.println("introduce = " + introduce);
		
		/*// 修改response对象的字符集
		response.setCharacterEncoding("utf-8");
		// 设置Content-Type响应头
		response.setHeader("Content-Type", "text/html;charset=utf-8");*/
		
		// 修改response对象的字符集，并且告诉浏览器使用utf-8显示网页的内容
		response.setContentType("text/html;charset=utf-8");
		
		// 输出内容到浏览器
		response.getWriter().write("<h1>吃鸡</h1>");
		
	}

}
