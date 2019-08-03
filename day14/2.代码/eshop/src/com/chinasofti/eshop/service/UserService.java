package com.chinasofti.eshop.service;

import java.sql.SQLException;
import java.util.List;

import com.chinasofti.eshop.beans.User;
import com.chinasofti.eshop.dao.UserDao;

/*
	用户管理模块的业务组件
*/
public class UserService {
	UserDao userDao = new UserDao();
	
	/**
	 * 根据用户名和密码查询用户信息
	 * @param userName 用户名
	 * @param userPass 密码
	 * @return 如果查询到就返回该用户的User对象，否则返回null。
	 * @throws SQLException 
	 */
	public User getUser(String userName, String userPass) throws SQLException {
		return userDao.getUser(userName, userPass);
	}

	/**
	 * 查询用户
	 * @return
	 * @throws SQLException 
	 */
	public List<User> findUsers() throws SQLException {
		return userDao.getUsers();
	}

	/**
	 * 添加用户
	 * @param username 用户名
	 * @param password 密码
	 * @param email 邮箱
	 * @param mobile 手机
	 * @throws SQLException 
	 */
	public void addUser(String username, String password, String email,
			String mobile) throws SQLException {
		userDao.addUser(username, password, email, mobile);
	}
	
}
