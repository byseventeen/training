package com.chinasofti.demo01自定义ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

class StudentResultSetHandler implements ResultSetHandler {

	/*
		处理结果的方法
			rs参数：queryRunner会查询结果传入到rs参数中。
	*/
	@Override
	public Object handle(ResultSet rs) throws SQLException {
		// 定义一个集合保存多个学生信息
		List<Student> list = new ArrayList<Student>();
		// 遍历结果集
		while (rs.next()) {
			// 自己处理结果集
			int stuId = rs.getInt("stu_id");
			String stuName = rs.getString("stu_name");
			boolean gender = rs.getBoolean("gender");
			Date birthdate = rs.getDate("birthdate");
			String phone = rs.getString("phone");
			String hobby = rs.getString("hobby");
			int clsId = rs.getInt("cls_id");
			String clsName = rs.getString("clsName");
			// 把班级信息封装Cls对象中
			Cls cls = new Cls(clsId, clsName);
			// 把学生信息封装到Student对象中
			Student student = new Student(stuId, stuName, gender, birthdate, phone, hobby, cls);
			// 把学生对象添加到List集合中
			list.add(student);
		} 
		return list;
	}
	
}

public class Demo01 {
	static QueryRunner queryRunner = new QueryRunner(new ComboPooledDataSource());

	public static void main(String[] args) throws SQLException {
		/*Object o = queryRunner.query("select * from student", new BeanListHandler(Student.class));
		if (o != null) {
			List<Student> studentList = (List<Student>) o;
			for (Student student : studentList) {
				System.out.println(student);
			}
		}*/
		
		// 自定义结果集
		String sql = "select student.*, cls.cls_name as clsName from student, cls where student.cls_id = cls.cls_id";
		List<Student> studentList = (List<Student>) queryRunner.query(
				sql, new StudentResultSetHandler());
		for (Student student : studentList) {
			System.out.println(student);
		}
		
	}

}
