package com.chinasofti.day13dbutils;

public class Employee {
	private Integer id;
	private String name;
	private String dept;
	private String job;
	
	public Employee() {}
	
	public Employee(Integer id, String name, String dept, String job) {
		this.id = id;
		this.name = name;
		this.dept = dept;
		this.job = job;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", dept=" + dept
				+ ", job=" + job + "]";
	}
	
}
