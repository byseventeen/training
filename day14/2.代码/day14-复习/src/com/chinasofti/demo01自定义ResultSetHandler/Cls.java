package com.chinasofti.demo01自定义ResultSetHandler;

/*
	班级实体类
*/
public class Cls {
	private Integer clsId;
	private String clsName;
	
	public Cls() {}
	
	public Cls(Integer clsId, String clsName) {
		super();
		this.clsId = clsId;
		this.clsName = clsName;
	}

	public Integer getClsId() {
		return clsId;
	}

	public void setClsId(Integer clsId) {
		this.clsId = clsId;
	}

	public String getClsName() {
		return clsName;
	}

	public void setClsName(String clsName) {
		this.clsName = clsName;
	}

	@Override
	public String toString() {
		return "Cls [clsId=" + clsId + ", clsName=" + clsName + "]";
	}
	
}
