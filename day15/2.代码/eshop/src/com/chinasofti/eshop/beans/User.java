package com.chinasofti.eshop.beans;

public class User {
	private Integer userId;
	private String userName;
	private String userPass;
	private String phone;
	private String email;
	
	public User() {
		super();
	}
	
	public User(Integer userId, String userName, String phone,
			String email) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.phone = phone;
		this.email = email;
	}

	public User(Integer userId, String userName, String userPass, String phone,
			String email) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPass = userPass;
		this.phone = phone;
		this.email = email;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", userPass=" + userPass + ", phone=" + phone + ", email="
				+ email + "]";
	}
	
}
