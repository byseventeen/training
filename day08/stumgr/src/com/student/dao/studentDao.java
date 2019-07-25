package com.student.dao;

public interface studentDao {
	boolean insert(String username,String gender,int age,String birthday,
			String telphone,String hobby);
	
	void query();
}
