package day08;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heima.util.JdbcUtil;
import com.student.dao.studentDao;
import com.student.dao.impl.studentDaoImpl;
import com.sun.xml.internal.bind.v2.runtime.Location;

public class RegistServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String userName = request.getParameter("userName");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		int age1 = Integer.parseInt(age);
		String birthday = request.getParameter("birthday");
		String telphone = request.getParameter("telphone");
		String hobby = request.getParameter("hobby");
		/*System.out.println("userName="+userName+",gender="+gender+
				",age="+age+",birthday="+birthday+",telphone="+telphone+",hobby="+hobby);*/
		studentDao sd = new studentDaoImpl();
		Boolean b = sd.insert(userName, gender, age1, birthday, telphone, hobby);
		if(b) {
			response.setStatus(302);
			response.setHeader("Location", "/stumgr/html/showInfor.html");
		} else {
			response.getWriter().write("录入失败!");
		}
	}
}
