package com.chinasofti.demo01自定义ResultSetHandler;

import java.util.Date;

public class Student {
	private Integer userId;
	private String userName;
	private Boolean gender;
	private Date birthdate;
	private String phone;
	private String hobby;
	private Cls cls; // 学生所在班级
	
	public Student() {}
	
	public Student(Integer userId, String userName, Boolean gender,
			Date birthdate, String phone, String hobby, Cls cls) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.gender = gender;
		this.birthdate = birthdate;
		this.phone = phone;
		this.hobby = hobby;
		this.cls = cls;
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

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Cls getCls() {
		return cls;
	}

	public void setCls(Cls cls) {
		this.cls = cls;
	}

	@Override
	public String toString() {
		return "Student [userId=" + userId + ", userName=" + userName
				+ ", gender=" + gender + ", birthdate=" + birthdate
				+ ", phone=" + phone + ", hobby=" + hobby + ", cls = " + cls + "]";
	}
	
}
