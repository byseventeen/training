package com.chinasofti.demo01七天免登陆升级版;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	首页页面
*/
@WebServlet(value="/index.html", name="IndexServlet")
public class IndexPageServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		Object o = req.getSession().getAttribute("loginUser");
		resp.getWriter().write("欢迎回来：" + o);
		
	}
	
}
