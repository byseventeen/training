package com.chinasofti.demo01七天免登陆升级版;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	登录页面
*/
@WebServlet("/login.html")
public class LoginPageServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		// 输出一个登录表单
		resp.getWriter().write("<form action='" + req.getContextPath() + "/login' method='post'>");
		resp.getWriter().write("	用户名：<input name='userName'/><br/>");
		resp.getWriter().write("	密    码：<input type='password' name='userPass'/><br/>");
		resp.getWriter().write("	<input type='checkbox' name='autoLogin' value='true'/> 下次自动登录<br/>");
		resp.getWriter().write("	<input type='submit' value='登 录'/>");
		
		Object o = req.getAttribute("msg");
		if (o != null) {
			resp.getWriter().write("	<span style='color:red'>" + o + "</span>");
		}
		
		resp.getWriter().write("</form>");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
}
