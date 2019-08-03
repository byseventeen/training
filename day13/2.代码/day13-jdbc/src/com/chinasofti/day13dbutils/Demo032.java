package com.chinasofti.day13dbutils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Demo032 {
	// 创建QueryRunner对象，不需要每次执行数据库操作的时候都创建一个。
	static QueryRunner qr = new QueryRunner();
	// 创建连接池，一个应用只需要创建一个连接池即可
	static ComboPooledDataSource ds = new ComboPooledDataSource();

	public static void main(String[] args) throws SQLException {
		String sql = "delete from employee where id = ?";
		Connection conn = ds.getConnection();
		try {
			// 启动事务
			conn.setAutoCommit(false);
			qr.update(conn, sql, 110);
			qr.update(conn, sql, 220);
			// 提交事务
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// 事务回滚
			conn.rollback(); 
		}
	}
	
}
