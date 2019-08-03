package com.chinasofti.eshop.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.eshop.beans.User;
import com.chinasofti.eshop.service.UserService;

/**
 * 用户登录
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	UserService userService = new UserService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 获取表单的参数
			String userName = request.getParameter("userName");
			String userPass = request.getParameter("userPass");
			
			// 调用service方法获取用户信息
			User user = userService.getUser(userName, userPass);
			if (user == null) {
				request.setAttribute("msg", "用户名或密码不正确！");
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp")
					.forward(request, response);
			} else {
				request.getSession().setAttribute("loginUser", user);
				response.sendRedirect(request.getContextPath() + "/index.html");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
