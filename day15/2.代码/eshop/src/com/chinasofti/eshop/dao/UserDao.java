package com.chinasofti.eshop.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.chinasofti.eshop.beans.User;

/*
	用户管理的数据访问层
*/
public class UserDao extends BaseDao {
	

	/**
	 * 查询用户
	 * @param name 用户名
	 * @param pass 密码
	 * @return
	 * @throws SQLException 
	 */
	public User getUser(String name, String pass) throws SQLException {
		String sql = "select * from user where userName = ? and userPass = password(?)";
		Object o = qr.query(sql, new BeanHandler(User.class), new Object[]{name, pass});
		if (o != null) {
			return (User) o;
		}
		return null;
	}

	/**
	 * 查询用户
	 * @return
	 * @throws SQLException 
	 */
	public List<User> getUsers() throws SQLException {
		String sql = "select * from user";
		Object o = qr.query(sql, new BeanListHandler(User.class));
		if (o != null) {
			return (List<User>) o;
		}
		return null;
	}

	/**
	 * 添加用户
	 * @param username
	 * @param password
	 * @param email
	 * @param mobile
	 * @throws SQLException 
	 */
	public void addUser(String username, String password, String email,
			String mobile) throws SQLException {
		String sql = "insert into user(userName, userPass, phone, email) values(?, password(?), ?, ?)";
		qr.update(sql, new Object[]{username, password, mobile, email});
		
	}

	/**
	 * 根据ID查询用户
	 * @param parseInt
	 * @return
	 * @throws SQLException 
	 */
	public User getUser(int id) throws SQLException {
		String sql = "select * from user where userId = ?";
		Object o = qr.query(sql, new BeanHandler(User.class), new Object[]{id});
		if (o != null) {
			return (User) o;
		}
		return null;
	}

	/**
	 * 根据用户名查询
	 * @param userName
	 * @return 返回查询到结果数，0代表没有查询结果，大于0代表有查询结果
	 * @throws SQLException 
	 */
	public long getUser(String userName) throws SQLException {
		String sql = "select count(*) from user where userName = ?";
		long count = (long) qr.query(sql, new ScalarHandler(), new Object[]{userName});
		return count;
	}

	/**
	 * 修改用户信息
	 * @param userId
	 * @param userName
	 * @param email
	 * @param phone
	 * @throws SQLException 
	 */
	public void update(String userId, String userName, String email,
			String phone) throws SQLException {
		StringBuilder sql = new StringBuilder("update user set ");
		sql.append("userName = ?, ");
		sql.append("email = ?, ");
		sql.append("phone = ? ");
		sql.append("where userId = ? ");
		System.out.println("修改用户的SQL：" + sql.toString());
		
		qr.update(sql.toString(), new Object[]{userName, email, phone, userId});
	}

	/**
	 * 根据用户ID删除
	 * @param userId 要删除用户的ID的数组
	 * @throws SQLException 
	 */
	public void deleteUsers(String[] userIds) throws SQLException {
		// 从连接池中获取一个链接
		Connection connection = dataSource.getConnection();
		try {
			// 开启事务
			connection.setAutoCommit(false);
			for (String userId : userIds) {
				String sql = "delete from user where userId = ?";
				qr2.update(connection, sql, new Object[]{userId});
			}
			// 提交事务
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// 回滚事务
			connection.rollback();
			throw new RuntimeException("删除时候发生异常");
		} finally {
			// 释放连接
			connection.close();
		}
	}

	/**
	 * 分页查询用户
	 * @param userName 查询条件
	 * @param startIndex 查询的开始位置
	 * @param pageSize 每页结果数
	 * @return
	 * @throws SQLException 
	 */
	public List<User> getUserByPage(String userName, int startIndex, int pageSize) throws SQLException {
		Object o = null;
		if (userName != null && !"".equals(userName)) {
			String sql = "select * from user where userName like ? limit ?, ?";
			o = qr.query(sql, new BeanListHandler(User.class)
				, new Object[]{"%" + userName + "%", startIndex, pageSize});
		} else {
			String sql = "select * from user limit ?, ?";
			o = qr.query(sql, new BeanListHandler(User.class), new Object[]{startIndex, pageSize});
		}
		if (o != null) {
			return (List<User>) o;
		}
		return null;
	}

	/**
	 * 查询所有用户的数量
	 * @param userName 查询条件
	 * @return
	 * @throws SQLException 
	 */
	public int count(String userName) throws SQLException {
		Long total = 0l;
		if (userName != null && !"".equals(userName)) {
			String sql = "select count(*) from user where userName like ?";
			total = (Long) qr.query(sql, new ScalarHandler(), new Object[]{"%" + userName + "%"});
		} else {
			String sql = "select count(*) from user";
			total = (Long) qr.query(sql, new ScalarHandler());
		}
		return total.intValue();
	}
	
}
