package demo01jdbc例子;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Demo01 {

	public static void main(String[] args) throws Exception {
		query();
		
//		add();
	}
	
	private static void add() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		// 获取数据库连接
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/aa", 
				"root", 
				"root");
		// 创建PreparedStatement对象
		PreparedStatement pstmt = conn.prepareStatement(
				"insert into student(stu_name, gender, birthdate, phone, hobby) values(?, ?, ?, ?, ?)");
		
		pstmt.setString(1, "jacky");
		pstmt.setBoolean(2, true);
		pstmt.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
		pstmt.setString(4, "1111111111111");
		pstmt.setString(5, "吃,喝,玩");
		pstmt.execute();
		// 关闭资源
		pstmt.close();
		conn.close();
	}

	private static void query() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
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
		// 关闭资源
		rs.close();
		pstmt.close();
		conn.close();
	}

}
