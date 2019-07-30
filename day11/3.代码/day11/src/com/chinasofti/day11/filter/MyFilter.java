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
public class MyFilter implements Filter {
	String name;
	
	// 创建过滤器时候自动调用。容器启动的时候创建过滤器。
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("执行MyFilter的init方法..");
		// 获取过滤器的配置参数
		name = filterConfig.getInitParameter("name");
	}

	// 每次请求Servlet时候，如果配置了过滤器，每次请求都会执行该方法
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("执行MyFilter的doFilter方法...");
		System.out.println("name = " + name);
		// 放行
		chain.doFilter(request, response);
	}

	// 过滤器被销毁时候自动执行
	@Override
	public void destroy() {
		System.out.println("执行MyFilter的destroy方法...");
	}

}
