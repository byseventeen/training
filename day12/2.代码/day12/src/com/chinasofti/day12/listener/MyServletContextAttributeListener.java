package com.chinasofti.day12.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/*
	监听ServletContext对象属性状态的变化
*/
@WebListener
public class MyServletContextAttributeListener implements ServletContextAttributeListener {

	// 往Servletcontext对象中添加属性的时候自动调用
	@Override
	public void attributeAdded(ServletContextAttributeEvent scae) {
		System.out.println("attributeAdded...");
		System.out.println("属性名：" + scae.getName());
		System.out.println("属性值：" + scae.getValue());
	}

	// 往Servletcontext对象中删除属性的时候自动调用
	@Override
	public void attributeRemoved(ServletContextAttributeEvent scae) {
		System.out.println("attributeRemoved...");
		
		System.out.println("删除的属性名：" + scae.getName());
		System.out.println("删除前属性的值：" + scae.getValue());
	}

	// 往Servletcontext对象中修改属性的时候自动调用
	@Override
	public void attributeReplaced(ServletContextAttributeEvent scae) {
		System.out.println("attributeReplaced...");
		
		System.out.println("修改前的值：" + scae.getValue());
		System.out.println("修改后的值：" + scae.getServletContext().getAttribute("jdbcUrl"));
		
	}


}
