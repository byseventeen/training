package demo03jsp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/demo03")
public class Demo03Servlet extends HttpServlet {
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 定义一个集合，保存所有图书
		List<String> books = new ArrayList<String>();
		books.add("java");
		books.add("android");
		books.add("go");
		books.add("分布式");
		
		// 把集合发送给浏览器显示集合的数据
		request.setAttribute("books", books);
		// 请求转发
		request.getRequestDispatcher("/books.jsp").forward(request, response);
	}

}
