package com.chinasofti.day13jdbc.demo01增删查改;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Demo01 {
	
	static {
		// 加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//testUpdate(5, "aaa");
		// testDelete(5);
		
		int[] ids = {2, 3};
		testDelete(ids);
	}
	
	// 批量删除
	private static void testDelete(int[] stuIds) {
		try (
			// 需要关闭的资源定义在这里
			// 获取数据库连接
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/aa",  // 数据库URL
					"root",  // 用户名
					"root"); // 密码
			// 创建PreparedStatement对象
			PreparedStatement pstmt = conn.prepareStatement(
					"delete from student where stu_id = ?");
		) {
			for (int id : stuIds) {
				pstmt.setInt(1, id);
				// 把参数添加到PreparedStatement对象中
				pstmt.addBatch();
			}
			// 执行sql命令
			pstmt.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	// 删除
	private static void testDelete(int studentId) {
		try (
			// 需要关闭的资源定义在这里
			// 获取数据库连接
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/aa",  // 数据库URL
					"root",  // 用户名
					"root"); // 密码
			// 创建PreparedStatement对象
			PreparedStatement pstmt = conn.prepareStatement(
					"delete from student where stu_id = ?");
		) {
			// 设置sql的参数
			pstmt.setInt(1, 5);
			// 执行sql命令
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	// 修改
	private static void testUpdate(int studentId, String name) {
		try (
			// 需要关闭的资源定义在这里
			// 获取数据库连接
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/aa",  // 数据库URL
					"root",  // 用户名
					"root"); // 密码
			// 创建PreparedStatement对象
			PreparedStatement pstmt = conn.prepareStatement(
					"update student set stu_name = ? where stu_id = ?");
		) {
			// 设置sql的参数
			pstmt.setString(1, name);
			pstmt.setInt(2, studentId);
			// 执行sql命令
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	// 查询
	private static void testQuery() {
		try (
			// 获取数据库连接
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/aa", 
					"root", 
					"root");
			// 创建PreparedStatement对象
			PreparedStatement pstmt = conn.prepareStatement(
					"select * from student");
			// 执行查询
			ResultSet rs = pstmt.executeQuery();
		) {
			// 遍历结果集
			while (rs.next()) { 
				int stuId = rs.getInt("stu_id");
				String stuName = rs.getString("stu_name");
				boolean gender = rs.getBoolean("gender");
				Date birthdate = rs.getDate("birthdate");
				String phone = rs.getString("phone");
				String hobby = rs.getString("hobby");
				System.out.println(stuId + ", " + stuName + ", "
					+ gender + "," + birthdate + "," + phone + "," + hobby);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 添加(JDK1.7提供资源自动关闭的功能，所有实现Closable接口的资源，jvm会自动回收站这些资源)
	public static void testAdd() {
		try (
			// 需要关闭的资源定义在这里
			// 获取数据库连接
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/aa",  // 数据库URL
					"root",  // 用户名
					"root"); // 密码
			// 创建PreparedStatement对象
			PreparedStatement pstmt = conn.prepareStatement(
					"insert into student(stu_name, gender, birthdate, phone, hobby) values(?, ?, ?, ?, ?)");
		) {
			// 设置sql的参数
			pstmt.setString(1, "su");
			pstmt.setBoolean(2, true);
			pstmt.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			pstmt.setString(4, "3333");
			pstmt.setString(5, "吃,喝");
			// 执行sql命令
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
