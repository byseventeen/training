package com.chinasofti.day07servlet.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	Content-Disposition响应头
*/
@WebServlet("/demo04")
public class Demo04Servlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 对文件名进行加密
		String filename = URLEncoder.encode("图片.jpg", "utf-8");
		System.out.println("filename = " + filename);
		// 设置Content-Disposition头
		response.setHeader("Content-Disposition", "attachment;filename=" + filename);
		// 读取图片
		FileInputStream fis = new FileInputStream("d:/good.jpg");
		// 从response对象中获取输出流对象，该输出流就是把数据输出给浏览器  
		OutputStream outputStream = response.getOutputStream();
		// 创建缓冲字节数组
		byte[] buf = new byte[1024];
		int len = fis.read(buf);
		while (len != -1) {
			// 向浏览器输出数据
			outputStream.write(buf, 0, len);
			// 继续读取图片的数据
			len = fis.read(buf);
		}
		// 关闭输入流
		fis.close();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}
	

}
