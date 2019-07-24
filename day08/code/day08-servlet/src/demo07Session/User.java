package demo07Session;

import java.io.Serializable;

public class User implements Serializable {
	String userName;
	int age;
	
	public User(String userName, int age) {
		super();
		this.userName = userName;
		this.age = age;
	}
}
