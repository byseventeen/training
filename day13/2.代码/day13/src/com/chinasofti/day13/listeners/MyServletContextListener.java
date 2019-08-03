package com.chinasofti.day13.listeners;

import java.lang.reflect.Field;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.chinasofti.day13.beans.Employee;

import java.sql.Connection;

@WebListener
public class MyServletContextListener implements ServletContextListener {
	
	static {
		try {
			// 加载驱动
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// 创建ServletContext对象的时候自动执行该方法
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 获取Employee的Class对象
		Class clazz = Employee.class;
		// 获取类名
		String className = clazz.getSimpleName();
		//System.out.println("className = " + className);
		
		// 保存所有成员属性，key代表属性名，value代表类型的Class对象
		Map<String, Class> fieldMap = new HashMap<String, Class>();
		
		
		// 获取Employee实体中所有的成员属性的Field对象
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			// 获取属性名
			String fieldName = field.getName();
			// 获取类型的Class对象，每一个类型都有一个Class对象
			Class typeClass = field.getType();
			//System.out.println("fieldName = " + fieldName + "，typeClass = " + typeClass);
			
			fieldMap.put(fieldName, typeClass);
		}
		
		// 创建表
		createTable(className, fieldMap);
	}

	/**
	 * 创建表
	 * @param className 类名
	 * @param fieldMap 存储了所有成员属性信息的Map集合
	 */
	private void createTable(String className, Map<String, Class> fieldMap) {
		try (
			// 获取数据库连接
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/aa", "root", "root");
		) {
			// 构建创建表的sql语句
			//StringBuffer sql = new StringBuffer(); //线程安全的，性能较差
			StringBuilder sql = new StringBuilder(); //线程不安全的，性能较好
			
			/*
			 *	创建ddl命令格式：create table 表名(字段名 字段类型,字段名 字段类型,...)
			*/
			sql.append("create table ");
			sql.append(className.toLowerCase());
			sql.append("(");
			// 遍历Map集合
			for (Map.Entry<String, Class> fieldEntry : fieldMap.entrySet()) {
				// 获取属性名
				String fieldName = fieldEntry.getKey();
				// 获取属性类型的Class对象
				Class typeClass = fieldEntry.getValue();
				
				sql.append(fieldName);
				sql.append(" ");
				
				if (typeClass == Integer.class) {
					sql.append("int,");
				} else if (typeClass == String.class) {
					sql.append("varchar(255),");
				}
			}
			// 删除最后字段的逗号
			sql.deleteCharAt(sql.length() - 1);
			sql.append(")");
			// 创建Statement对象
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			// 执行sql
			pstmt.execute();
			
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
