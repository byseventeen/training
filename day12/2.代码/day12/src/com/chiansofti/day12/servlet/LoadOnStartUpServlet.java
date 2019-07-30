package com.chiansofti.day12.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
	注意：使用注解的时候，必须要指定urlPatterns或value属性，loadOnStarup属性才起作用。
		但是使用配置文件方式，不需要指定url-pattern，只要配置load-on-startup,服务器启动的时候就会自动加载该Servlet。
*/
@WebServlet(value={"/load1", "/load2"}, loadOnStartup=1, initParams={
		@WebInitParam(name = "name", value = "jacky"), 
		@WebInitParam(name = "age", value = "20")
})
public class LoadOnStartUpServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("LoadOnStartUpServlet被创建了...");
		
		String name = config.getInitParameter("name");
		String age = config.getInitParameter("age");
		System.out.println("name = " + name + ", age = " + age);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}
	
}
