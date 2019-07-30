package com.chinasofti.demo01七天免登陆升级版;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
	用户登录
*/
@WebServlet("/login")
public class DoLoginServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String userName = req.getParameter("userName");
		String userPass = req.getParameter("userPass");
		
		// 如果登录成功
		if ("admin".equals(userName) && "123".equals(userPass)) {
			// 把用户名保存在session中
			HttpSession session = req.getSession();
			session.setAttribute("loginUser", userName);
			// 如果autoLogin不为null，那么就把用户登录信息保存在Cookie中
			String autoLogin = req.getParameter("autoLogin");
			if (autoLogin != null) {
				Cookie c = new Cookie("userName", userName);
				c.setMaxAge(60 *60 * 24 * 7);
				resp.addCookie(c);
			} else {
				// 如果没有选择免登陆，那么就从cookie中删除用户的登录信息。
				Cookie c = new Cookie("userName", "");
				c.setMaxAge(0);
				resp.addCookie(c);
			}
			// 重定向到后台首页
			resp.sendRedirect(req.getContextPath() + "/index.html");
		} else {
			// 请求转发给登录页面
			req.setAttribute("msg", "用户名或密码不正确");
			req.getRequestDispatcher("/login.html").forward(req, resp);
		}
		
	}
	
}
