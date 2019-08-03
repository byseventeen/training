package com.chinasofti.day13jdbc.demo02c3p0;

import java.sql.Connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Demo02 {

	public static void main(String[] args) throws Exception {
		// 创建连接池对象
		ComboPooledDataSource ds = new ComboPooledDataSource();
		/*// 配置数据库信息 
		ds.setDriverClass("com.mysql.jdbc.Driver");
		ds.setJdbcUrl("jdbc:mysql://localhost:3306/aa");
		ds.setUser("root");
		ds.setPassword("root");
		// 配置连接池参数
		ds.setInitialPoolSize(3);
		ds.setMaxPoolSize(5);
		ds.setMinPoolSize(3);
		ds.setAcquireIncrement(1);
		ds.setCheckoutTimeout(5000);*/
		
		
		// Connection con = ds.getConnection()
		for (int i = 0; i < 5; i++) {
			// 从连接池中获取一个连接
			Connection conn = ds.getConnection();
			System.out.println(conn);
			
			if (i == 3) {
				// 把当前的链接释放到连接池中
				conn.close();
			}
		}
		
		ds.getConnection();
	}
	
}
