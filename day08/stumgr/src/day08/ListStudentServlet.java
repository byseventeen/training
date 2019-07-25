package day08;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.dao.studentDao;
import com.student.dao.impl.studentDaoImpl;

public class ListStudentServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		studentDao sd = new studentDaoImpl();
		sd.query();
		ArrayList<Student> list = studentDaoImpl.list;
		Iterator<Student> it = list.iterator();
		while(it.hasNext()) {
			Student stu = it.next();
			response.getOutputStream().write((stu.toString()+"\r\n").getBytes());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
