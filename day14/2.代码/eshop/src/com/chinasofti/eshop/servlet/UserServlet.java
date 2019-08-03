package com.chinasofti.eshop.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.eshop.beans.User;
import com.chinasofti.eshop.service.UserService;

/*
	用户管理（添加用户、修改用户、查询用户）
*/
@WebServlet("/admin/user")
public class UserServlet extends HttpServlet {
	UserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 通过一个参数来指定具体操作
		// oper参数：add代表添加用户，update代表修改用户，delete代表删除用户，find代表查询用户
		
		String oper = req.getParameter("oper");
		if ("add".equals(oper)) {
			// 添加用户
			addUser(req, resp);
		} else if ("update".equals(oper)) {
			// 修改用户
			updateUser(req, resp);
		} else if ("delete".equals(oper)) {
			// 删除用户
			deleteUser(req, resp);
		} else if ("find".equals(oper)) {
			// 查询用户
			findUser(req, resp);
		}
	}

	// 查询用户
	private void findUser(HttpServletRequest req, HttpServletResponse resp) {
		try {
			// 查询用户
			List<User> userList = userService.findUsers();
			// 保存request对象中
			req.setAttribute("userList", userList);
			// 请求转发给list.jsp
			req.getRequestDispatcher("/WEB-INF/jsp/user/list.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除用户
	private void deleteUser(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException { 
		
	}

	// 修改用户
	private void updateUser(HttpServletRequest req, HttpServletResponse resp) {
		
	}

	// 添加用户
	private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// 判断用户是不是重复提交，如果是重复提交，就跳转添加页面。
			// 把session对象中的token和隐藏域中的token比较，如果他们相等，就代表第一次提交表单
			// 如果不相等或session对象的token不存在，就代表重复提交
			Object o = req.getSession().getAttribute("token");
			
			String userToken = req.getParameter("user_token");
			
			// 记录是否重复提交表，默认不是重复提交
			boolean isDupSubmit = false;
			if (o == null) {
				isDupSubmit = true;
			} else {
				String token = (String) o;
				if (!token.equals(userToken)) {
					isDupSubmit = true;
				}
			}
			
			if (isDupSubmit) {
				req.setAttribute("msg", "不能够重复提交表单");
				req.getRequestDispatcher("/WEB-INF/jsp/user/add.jsp").forward(req, resp);
			} else {
				// 处理中文乱码
				req.setCharacterEncoding("utf-8");
				// 获取请求参数
				String username = req.getParameter("username");
				String password = req.getParameter("password");
				String email = req.getParameter("email");
				String mobile = req.getParameter("mobile");
				
				// 调用业务方法添加用户
				userService.addUser(username, password, email, mobile);
				
				req.setAttribute("msg", "添加成功");
				
				// 把session中token属性删除
				req.getSession().removeAttribute("token");
				req.getRequestDispatcher("/WEB-INF/jsp/user/add.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("msg", "添加失败");
		}
	}
	
}
