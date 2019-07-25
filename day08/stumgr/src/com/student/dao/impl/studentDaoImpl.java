package com.student.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.heima.util.JdbcUtil;
import com.student.dao.studentDao;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import day08.Student;

public class studentDaoImpl implements studentDao {
	
	public static ArrayList<Student> list = new ArrayList<>();
	
	public boolean insert(String username,String gender,int age,String birthday,
			String telphone,String hobby) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "insert into student(stu_id,stu_name,gender,birthday,phone,hobby,age)values"
					+ "(null,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, gender);
			ps.setString(3, birthday);
			ps.setString(4, telphone);
			ps.setString(5, hobby);
			ps.setInt(6, age);
			int i = ps.executeUpdate();
			if(i>0) {
				System.out.println("��ӳɹ�");
				flag = true;
			} else {
				System.out.println("���ʧ��");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(ps, conn);
		}
		return flag;
	}

	@Override
	public void query() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtil.getConnection();
			String sql = "select * from student";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("stu_id");
				int age = rs.getInt("age");
				String name = rs.getString("stu_name");
				String gender = rs.getString("gender");
				String birthday = rs.getString("birthday");
				String telphone = rs.getString("phone");
				String hobby = rs.getString("hobby");
				list.add(new Student(id, name, gender, birthday, age, telphone, hobby));
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(rs,ps,conn);
			
		}
		
	}

}
