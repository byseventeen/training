package demo01请求转发和重定向;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/b.do")
public class ServletB extends HttpServlet {
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("执行ServletB...");
		// 从request对象中获取数据
		//String name = (String) request.getAttribute("name");
		
		String name = request.getParameter("name");
		System.out.println("name = " + name);
		
		List<String> books = (List<String>) request.getAttribute("books");
		for (String book : books) {
			System.out.println(book);
		}
	}

}
