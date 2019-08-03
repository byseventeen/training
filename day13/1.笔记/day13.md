# 课程内容

1.  JDBC回顾
2.  数据库连接池技术
3.  自定义数据库工具类
4.  DbUtil工具使用

---

# 一、JDBC入门

## 1.1 JDBC 介绍

Java DataBase Connectivity: Java数据库连接技术，用于操作数据库。例如：mysql、oracle、sql-server。

JDBC实际上一套用于连接数据库的接口规范。



## 1.2 JDBC常用接口

java.sql.*：所有JDBC接口都保存在这个包下面。

- Connection：数据库连接

- Statement：执行sql命令；

- ResultSet：结果集

- DriverManager：获取Connection的工具。



## 1.3 JDBC增删查改

使用JDBC的步骤：

- 第一步：导入驱动包；
- 第二步：加载驱动；
- 第三步：获取数据库连接；
- 第四步：获取Statement对象，调用execute方法把sql命令发送数据库执行；

​	数据库接收到发送过来的sql命令后，会先检查sql语法是否正确，如果正确编译sql，最后执行sql。

- 第五步：处理ResultSet结果集；

- 第六步：关闭资源；（先开后关，后开先关：ResultSet、Statement、Connection）

```java
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
```

从JDK1.7版本开始，JDK支持资源自动回收操作。如果一个资源实现了AutoCloseable接口，那么就可以在try(自动回收资源定义)括号中定义该资源。JVM会自动回收括号中的资源，不需要用户手动关闭。

# 二、数据库连接池

## 2.1 数据库连接池介绍

数据库连接池就是用来保存数据库连接的容器。当服务器启动的时候，JVM就会预先创建一批的数据库连接，然后保存数据库连接池中。当用户访问数据库的时候，jvm就会从数据库连接池中获取一个连接出来给用户使用。用户访问数据库结束后，jvm就会连接返回给连接池中，给下一个用户继续使用。

使用数据库连接池的好处：

- 减少数据库连接创建的数量；
- 提供资源的利用率；
- 提供系统性能；

## 2.2 自定义数据库连接池(了解)

第一步：定义一个类，实现DataSource接口，重写getConnection方法，该方法用于获取一个数据库的连接；

第二步：定义一个release方法，用于把Connection对象释放回连接池中；

```java
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
```



## 2.3 C3P0连接池技术

### 2.3.1 使用步骤：

第一步：导入jar包；

第二步：创建数据库连接池对象；

```java
ComboPooledDataSource ds = new ComboPooledDataSource();
```

第三步：配置连接池；

- setDriverClass()：指定驱动的名字；
- setJdbcUrl()：指定数据库的URL；
- setUser()：指定数据库的用户名；
- setPassword() ：指定数据库的密码；
- setInitialPoolSize()：连接池的默认连接数
- setMaxPoolSize()：连接池的最大连接数
- setMinPoolSize()：连接池的最少连接数
- setAcquireIncrement()：每次增加多少个连接

第四步：使用连接池（获取连接，使用连接访问数据库，释放连接）；

```java
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
```



### 2.3.2 使用配置文件配置连接池

C3P0支持xml和properties格式的配置文件。我们只需要在src目录下定义好配置文件，那么创建连接池对象的时候，它会自动在src目录下查找c3p0.properties 或 c3p0-config.xml配置文件加载配置信息。

```xml
c3p0.driverClass=com.mysql.jdbc.Driver
c3p0.jdbcUrl=jdbc:mysql://localhost:3306/aa
c3p0.user=root
c3p0.password=root
c3p0.initialPoolSize=3
c3p0.maxPoolSize=5
c3p0.minPoolSize=3
c3p0.acquireIncrement=2
c3p0.checkoutTimeout=3000
```

如果同时定义了c3p0.properties 和 c3p0-config.xml文件，优先加载c3p0-config.xml文件。

# 三、DbUtil工具

## 3.1 DbUtil工具介绍

DbUtil是Commons组件之一，用于简化数据库的操作。

## 3.2 DbUtil工具的使用

第一步：导入DbUtil核心jar包；

第二步：创建QueryRunner对象；

```java
QueryRunner qr = new QueryRunner(); // 可以手动指定事务
QueryRunner qr = new QueryRunner(数据源); // 不支持事务
```

第三步：使用QueryRunner对象方法访问数据库。

```java
# 更新操作
update(sql)：传入一个sql字符串
update(sql, Object param)：sql代表要执行的sql，param代表要传入到sql中的参数
update(sql, Object[] params)：sql代表要执行的sql，params代表要传入到sql中的多个参数

# 查询操作
query(sql, ResultSetHandler, Object[] params)：sql代表要执行的sql，ResultSetHandler代表结果处理器，params代表要传入的多个参数
```

> ResultSetHandler：结果处理器 。

- BeanHandler：把一行的数据封装一个实体对象中。
- BeanListHandler：把多行的数据封装到一个List集合中。
- ScalarHandler：如果结果返回单个值，该处理器就返回它的值。

```java
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

```

## 3.3 在事务环境中使用QueryRunner

```java
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

```



















