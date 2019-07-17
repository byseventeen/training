package com.chinasofti.demo01可变参数;

public class Demo01 {
	
	/*
		可变参数实际上是一个数组
		可变参数必须是方法最后一个参数。
		一个方法只能够有一个可变参数。
	*/
	public static int sum(int... numbers) {
		int result = 0;
		for (int number : numbers) {
			result += number;
		}
		return result;
	}

	public static void main(String[] args) {
		int result = sum(10, 20, 30);
		System.out.println("result = " + result);
	}

}
