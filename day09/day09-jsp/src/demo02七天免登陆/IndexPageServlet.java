package demo02七天免登陆;

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
@WebServlet("/index.html")
public class IndexPageServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		
		Object o = req.getSession().getAttribute("loginUser");
		// 如果o为null，代表用户没有登录，那么从cookie中读取用户信息
		if (o == null) {
			Cookie[] cookies = req.getCookies();
			// 记录cookie中是否存在userName属性
			// 如果存在为true，否则为false
			boolean hasUserName = false;
			if (cookies != null) {
				for (Cookie c : cookies) {
					if ("userName".equals(c.getName())) {
						// 从cookie获取用户之前登录过的信息
						String userName = c.getValue();
						// 把用户登录信息重新保存到Session中
						req.getSession().setAttribute("loginUser", userName);
						hasUserName = true;
						resp.getWriter().write("欢迎回来：" + userName);
					}
				}
			}
			// 如果hasUserName为false，代表cookie中没有用户的登录信息
			if (!hasUserName) {
				resp.sendRedirect("/day09/login.html");
			}
		} else {
			resp.getWriter().write("欢迎回来：" + o);
		}
		
		
	}
	
}
