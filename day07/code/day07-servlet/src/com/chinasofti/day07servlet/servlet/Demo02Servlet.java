package com.chinasofti.day07servlet.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/demo02")
public class Demo02Servlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		System.out.println("doGet...");
		doPost(request, response);
	}
	
	@Override
	protected long getLastModified(HttpServletRequest req) {
		// 返回服务器文件的最后修改时间
		File f = new File("d:/good.jpg");
		return f.lastModified();
	}

}
