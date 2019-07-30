package com.chinasofti.day12.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/*
	ServletContextListener
*/
@WebListener
public class MyServletContextListener implements ServletContextListener {

	// 创建servletContext对象时候自动调用
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("ServletContext对象被创建了..");
	}

	// ServletContext对象被销毁时候自动调用
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("ServletContext对象被销毁了..");
	}

}
