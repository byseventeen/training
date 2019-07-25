package com.heima.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class JdbcUtil {
	public static String driverClass = null;
	public static String url = null;
	public static String root = null;
	public static String password = null;
	static {
		driverClass = propertiesUtil.getName("driverClass");
		url = propertiesUtil.getName("url");
		root = propertiesUtil.getName("root");
		password = propertiesUtil.getName("password");
	}

	public static void closeAll(ResultSet rs, Statement stat, Connection conn) {
		closeResultSet(rs);
		closeStatement(stat);
		closeConnnection(conn);
	}

	public static void closeAll(Statement stat, Connection conn) {

		closeStatement(stat);
		closeConnnection(conn);
	}
	
	public static void closeAll(PreparedStatement ps, Connection conn) {

		closePreparedStatement(ps);
		closeConnnection(conn);
	}
	
	public static Connection getConnection() throws Exception {
		
		DriverManager.registerDriver(new Driver()); 

		//Class.forName(driverClass); //ע�������ĵڶ��ַ���
		Connection conn = DriverManager.getConnection(url, root, password);
		return conn;
	}

	public static void closeResultSet(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			rs = null;
		}

	}

	public static void closeStatement(Statement stat) {
		try {
			if (stat != null) {
				stat.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			stat = null;
		}

	}

	public static void closeConnnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			conn = null;
		}

	}

	public static void closePreparedStatement(PreparedStatement ps) {
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			ps = null;
		}

	}

}
