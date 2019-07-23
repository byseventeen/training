package com.chinasofti.day07servlet.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	Content-Encoding响应头
*/
@WebServlet("/demo03")
public class Demo03Servlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置Content-Encoding响应头
		response.setHeader("Content-Encoding", "gzip");
		// 获取response对象的输出流
		OutputStream outputStream = response.getOutputStream();
		GZIPOutputStream gout = new GZIPOutputStream(outputStream);
		// 向浏览器发送4Kb数据
		for (int i = 0; i < 1000; i++) {
			gout.write("abcd".getBytes());
		}
		// 把压缩数据发送浏览器
		gout.finish();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}
	

}
