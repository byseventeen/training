package com.chinasofti.day11.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*
	过滤器
*/
public class CharacterEncodingFilter implements Filter {
	String charset;
	
	// 创建过滤器时候自动调用。容器启动的时候创建过滤器。
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 获取过滤器的配置参数
		charset = filterConfig.getInitParameter("charset");
	}

	// 每次请求Servlet时候，如果配置了过滤器，每次请求都会执行该方法
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 处理请求的中文乱码
		request.setCharacterEncoding(charset);
		// 处理响应的中文乱码
		response.setContentType("text/html;charset=" + charset);
		// 放行
		chain.doFilter(request, response);
	}

	// 过滤器被销毁时候自动执行
	@Override
	public void destroy() {
		System.out.println("执行过滤器的destroy方法...");
	}

}
