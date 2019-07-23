package com.chinasofti.day07servlet.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	refresh响应头
*/
@WebServlet("/demo06")
public class Demo06Servlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*// refresh头设置每隔1s刷新页面一次
		response.setHeader("refresh", "1");
		String dateStr = formatDate();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(dateStr);*/
		
		// 指定多少秒后跳转其他页面
		response.setHeader("refresh", "3;url=http://baidu.com");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	/**
	 * 把Date对象转换指定日期格式的字符串
	 */
	private String formatDate() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH时mm分ss秒");
		String dateStr = sdf.format(d);
		return dateStr;
	}
	

}
