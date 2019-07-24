package demo03ServletConfig对象;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Demo03Servlet extends HttpServlet {
	String uploadPath;
	String imageType;  
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("执行Demo03Servlet init方法...");
		uploadPath = config.getInitParameter("uploadPath");
		imageType = config.getInitParameter("imageType");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("uploadPath = " + uploadPath);
		System.out.println("imageType = " + imageType);
	}

}
