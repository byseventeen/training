package com.chinasofti.demo01七天免登陆升级版;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*@WebFilter(value="/index.html", initParams={
		@WebInitParam(name="charset", value="utf-8")
})*/
@WebFilter(servletNames={"IndexServlet"}, initParams={
		@WebInitParam(name="charset", value="utf-8")
})
public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String charset = filterConfig.getInitParameter("charset");
		System.out.println("charset = " + charset);
	}

	//实现登录认证
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
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
						
						// 放行
						chain.doFilter(request, response);
					}
				}
			}
			// 如果hasUserName为false，代表cookie中没有用户的登录信息
			if (!hasUserName) {
				resp.sendRedirect(req.getContextPath() + "/login.html");
			}
		} else {
			// 放行
			chain.doFilter(request, response);	
		}
		
	}

	@Override
	public void destroy() {
		
	}


}
