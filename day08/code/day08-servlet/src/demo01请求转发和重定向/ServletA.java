package demo01请求转发和重定向;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/a.do")
public class ServletA extends HttpServlet {
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("执行ServetA...");
		
		// 往request对象中存储数据
		request.setAttribute("name", "aa");
		
		List<String> books = new ArrayList<String>();
		books.add("java基础");
		books.add("java框架");
		books.add("分布式");
		request.setAttribute("books", books);
		
		// 第一步：创建一个请求分发器对象
		//RequestDispatcher rd = request.getRequestDispatcher("/b.do");
		// 第二步：调用请求分发器对象的forward方法执行请求转发
		//rd.forward(request, response);
		
		// 向当前页面输出ServletA
		// 注意：请求转发不能够与response.getWriter().write()方法一起使用
		// response.getWriter().write("ServletA");
		
		// 重定向
		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);
		response.sendRedirect(contextPath + "/b.do?name=bb&age=18");
	
	}
}
