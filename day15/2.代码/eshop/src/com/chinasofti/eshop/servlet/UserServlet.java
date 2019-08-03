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

import org.apache.jasper.tagplugins.jstl.core.ForEach;

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
		} else if ("load".equals(oper)) {
			// 加载用户
			loadUser(req, resp);
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

	// 加载用户数据
	private void loadUser(HttpServletRequest req, HttpServletResponse resp) {
		try {
			// 获取用户ID
			String userId = req.getParameter("userId");
			// 根据用户ID查询用户信息
			User user = userService.findUserById(userId);
			// 把用户信息保存request中
			req.setAttribute("user", user);
			// 跳转页面
			req.getRequestDispatcher("/WEB-INF/jsp/user/edit.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("服务器发生异常，请稍后再试！");
		}
	}

	/*// 查询用户
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
			throw new RuntimeException("服务器发生异常，请稍后再试！");
		}
	}*/
	
	// 分页查询
	private void findUser(HttpServletRequest req, HttpServletResponse resp) {
		// 获取查询条件
		String adminName = req.getParameter("adminName");
		try {
			// 获取分页信息
			String page = req.getParameter("page");
			if (page == null) {
				page = "1";
			}
			String pageSize = req.getParameter("pageSize");
			if (pageSize == null) {
				pageSize = "5";
			}
			// 分页查询用户
			List<User> userList = userService.findUsers(adminName, page, pageSize);
			// 总记录数
			int total = userService.getTotalOfUser(adminName);
			
			// 总页数
			int totalPage = 0;
			if (total % Integer.parseInt(pageSize) == 0) {
				totalPage = total / Integer.parseInt(pageSize);
			} else {
				totalPage = total / Integer.parseInt(pageSize) + 1;
			}
			
			// 保存request对象中
			req.setAttribute("userList", userList);
			req.setAttribute("curPage", page); // 当前页码
			req.setAttribute("pageSize", pageSize); // 每页显示结果数
			req.setAttribute("total", total); // 总记录数
			req.setAttribute("totalPage", totalPage); // 总页数
			req.setAttribute("adminName", adminName); // 把查询条件回传给页面
			
			// 请求转发给list.jsp
			req.getRequestDispatcher("/WEB-INF/jsp/user/list.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("服务器发生异常，请稍后再试！");
		}
	}
	
	

	// 删除用户
	private void deleteUser(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException { 
		try {
			// 获取参数
			String[] userIds = req.getParameterValues("id");
			userService.deleteUsers(userIds);
			// 返回用户查询页面
			//resp.sendRedirect(req.getContextPath() + "/admin/user?oper=find");
			findUser(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("服务器发生错误，删除失败！");
		}
	}

	// 修改用户
	private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取表单数据
		String userId = req.getParameter("userId");
		String userName = req.getParameter("username");
		String email = req.getParameter("email");
		String phone = req.getParameter("mobile");
		try {
			// 修改用户的操作
			String msg = userService.updateUser(userId, userName, email, phone);
			if (msg == null) {
				req.setAttribute("msg", "修改成功！");
			} else {
				req.setAttribute("msg", msg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			req.setAttribute("msg", "修改失败");
		}
		req.setAttribute("user", new User(Integer.parseInt(userId), userName, phone, email));
		req.getRequestDispatcher("/WEB-INF/jsp/user/edit.jsp").forward(req, resp);
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
				
				// TODO 判断用户名是否已经被使用了。
				// 如果被使用，就不能够添加用户，并且要提示用户。
				
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
			req.getRequestDispatcher("/WEB-INF/jsp/user/add.jsp").forward(req, resp);
		}
	}
	
}
