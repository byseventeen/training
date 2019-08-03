package com.chinasofti.day13dbutils;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Demo03 {
	// 创建QueryRunner对象，不需要每次执行数据库操作的时候都创建一个。
	static QueryRunner qr = new QueryRunner(new ComboPooledDataSource()); // 不支持事务 

	public static void main(String[] args) throws SQLException {
		//addEmployee();
		//queryEmployeeById(110);
		//queryEmployees();
		getTotalOfEmployee();
		
	}
	
	private static void getTotalOfEmployee() throws SQLException {
		String sql = "select count(*) from employee";
		Object o = qr.query(sql, new ScalarHandler());
		System.out.println("结果总数：" + o);
	}
	
	private static void queryEmployees() throws SQLException {
		String sql = "select * from employee";
		Object o = qr.query(sql, new BeanListHandler(Employee.class));
		if (o != null) {
			List<Employee> empList = (List<Employee>) o;
			for (Employee employee : empList) {
				System.out.println(employee);
			}
		}
	}
	
	private static void queryEmployeeById(int id) throws SQLException {
		String sql = "select * from employee where id = ?";
		Object o = qr.query(sql, new BeanHandler(Employee.class), new Object[]{110});
		if (o != null) {
			Employee emp = (Employee) o;
			System.out.println(emp);
		}
	}
	
	private static void deleteEmployee() throws SQLException {
		/*
		 * 调用方法
		 * 	update()：执行添加、删除、修改操作
		 *  query()：执行查询操作
		 */
		String sql = "delete from employee where id = ?";
		qr.update(sql, 110);
	}
	
	private static void updateEmployee() throws SQLException {
		/*
		 * 调用方法
		 * 	update()：执行添加、删除、修改操作
		 *  query()：执行查询操作
		 */
		String sql = "update employee set name = ? where id = ?";
		qr.update(sql, new Object[]{"mickey", 110});
	}

	private static void addEmployee() throws SQLException {
		/*
		 * 调用方法
		 * 	update()：执行添加、删除、修改操作
		 *  query()：执行查询操作
		 */
		String sql = "insert into employee(id, name, dept, job) values(?, ?, ?, ?)";
		qr.update(sql, new Object[]{220, "mickey", "行政部", "行政主管"});
	}

}
