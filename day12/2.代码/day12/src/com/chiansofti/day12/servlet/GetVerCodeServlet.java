package com.chiansofti.day12.servlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	 生成四位数图形验证码的步骤：
		第一步：定义一个数组保存字符；
		第二步：生成四个随机数组下标，通过下标获取数组的字符，最后拼接起来；
		第三步：创建一个BufferedImage对象，该对象代表一张图像；
		第四步：创建一个Graphics对象，该对象代表画笔；
		第五步：通过调用Graphics对象的方法把验证码绘制在图像上；
		第六步：把整个图像写入到输出流中；
*/
@WebServlet("/getVerCode")
public class GetVerCodeServlet extends HttpServlet {
	char[] data = "abcdef123456阿萨德看风景阿卡".toCharArray();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 处理响应中文乱码
		resp.setContentType("image/jpeg");
		// 生成随机数工具类
		Random rand = new Random();
		/*String vercode = "";
		for (int i = 0; i < 4; i++) {
			// 生成随机下标
			int index = rand.nextInt(data.length);
			vercode += data[index] + "  ";
		}*/
		//resp.getWriter().write(vercode);
		
		// 创建一个图像
		BufferedImage bi = new BufferedImage(100, 30, BufferedImage.TYPE_INT_RGB);
		// 创建画笔
		Graphics g = bi.getGraphics();
		
		// 设置画笔颜色
		g.setColor(Color.GRAY);
		// 绘制表框
		g.fillRect(0, 0, 100, 30);
		
		// 把验证码绘制在图像上
		String vc = "";
		for (int i = 0; i < 4; i++) {
			// 生成随机下标
			int index = rand.nextInt(data.length);
			// 通过指定RGB的值生成不同的颜色
			g.setColor(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
			g.drawString(data[index] + "", i * 15 + 15, 20);
			
			vc += data[index];
		}
		
		req.getSession().setAttribute("vercode", vc);
		
		// 把整个图像写入输出到浏览器ServletOutputStream
		ImageIO.write(bi, "JPG", resp.getOutputStream());
	}
	
}
