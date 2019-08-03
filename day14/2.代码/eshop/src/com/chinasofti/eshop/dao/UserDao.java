package com.chinasofti.eshop.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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
	
}
