package demo02域对象;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/contextA")
public class ServletContextServletA extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取ServletContext对象
		ServletContext context = request.getServletContext();
		// 往ServletContext中存储数据
		context.setAttribute("name", "aa");
	}

}
