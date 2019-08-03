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

	/**
	 * 根据ID查询用户
	 * @param userId
	 * @return
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public User findUserById(String userId) throws NumberFormatException, SQLException {
		return userDao.getUser(Integer.parseInt(userId));
	}

	/**
	 * 修改用户
	 * @param userId 用户ID
	 * @param userName 用户名
	 * @param email 邮箱
	 * @param phone 手机号码
	 * @return 如果返回结果不为null，代表更新失败
	 * @throws SQLException 
	 */
	public String updateUser(String userId, String userName, String email,
			String phone) throws SQLException {
		// 查询用户名是否已经存在
		long total = userDao.getUser(userName);
		if (total > 0) {
			return "用户名已经存在，不能使用";
		}
		userDao.update(userId, userName, email, phone);
		return null;
	}

	/**
	 * 删除用户
	 * @param userIds 用户ID的数组
	 * @throws SQLException 
	 */
	public void deleteUsers(String[] userIds) throws SQLException {
		userDao.deleteUsers(userIds);
	}

	/**
	 * 分页查询用户
	 * @param userName 查询条件
	 * @param page 第几页
	 * @param pageSize 每页显示结果数
	 * @return
	 * @throws SQLException 
	 */
	public List<User> findUsers(String userName, String page, String pageSize) throws SQLException {
		// 计算分页查询的开始位置
		int startIndex = (Integer.parseInt(page) - 1) * Integer.parseInt(pageSize);
		// 调用dao方法执行查询
		List<User> userList = userDao.getUserByPage(userName, startIndex, Integer.parseInt(pageSize));
		return userList;
	}

	/**
	 * 获取所有用户的数量
	 * @param userName 查询条件
	 * @return
	 * @throws SQLException 
	 */
	public int getTotalOfUser(String userName) throws SQLException {
		return userDao.count(userName);
	}
	
}
