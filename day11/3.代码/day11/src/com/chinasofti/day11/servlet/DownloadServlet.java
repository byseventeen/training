package com.chinasofti.day11.servlet;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 文件下载的响应头Content-Disposition
		resp.setHeader("Content-Disposition", "attachment;filename=good.jpg");
		// 获取upload目录的绝对路径
		String uploadPath = req.getServletContext().getRealPath("/WEB-INF/upload");
		// 创建文件输入流对象
		FileInputStream fis = new FileInputStream(uploadPath + "/good.jpg");
		// 读取图片，把图片数据写出浏览器中
		ServletOutputStream outputStream = resp.getOutputStream();
		byte[] buf = new byte[1024];
		int len = -1;
		while ((len = fis.read(buf)) != -1) {
			outputStream.write(buf, 0, len);
		}
		// 关闭资源
		fis.close();
	}
	
}
