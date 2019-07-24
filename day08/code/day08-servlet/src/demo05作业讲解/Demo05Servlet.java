package demo05作业讲解;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/demo05")
public class Demo05Servlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 获取浏览器和操作系统信息
		String userAgent = req.getHeader("User-Agent");
		System.out.println("userAgent = " + userAgent);
		String[] userAgentInfos = userAgent.split(";");
		System.out.println("浏览器：" + userAgentInfos[1]);
		System.out.println("操作系统：" + userAgentInfos[3]);
	}

}
