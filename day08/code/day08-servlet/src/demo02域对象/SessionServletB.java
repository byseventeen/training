package demo02域对象;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionB")
public class SessionServletB extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取Session对象
		HttpSession session = request.getSession();
		// 从session中获取数据
		Object o = session.getAttribute("name");
		System.out.println(o);
		
	}


}
