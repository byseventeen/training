package com.chinasofti.demo02比较器;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Student implements Comparable<Student> {
	String name;
	int age;
	
	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}

	@Override
	public int compareTo(Student o) {
		return this.age - o.age;
	}

	
	
}

public class Demo022 {

	public static void main(String[] args) {
		ArrayList<Student> studentList = new ArrayList<Student>();
		studentList.add(new Student("aa", 19));
		studentList.add(new Student("bb", 18));
		studentList.add(new Student("cc", 20));
		studentList.add(new Student("dd", 25));
		studentList.add(new Student("ss", 21));
		
		Collections.sort(studentList);
		
		for (Student student : studentList) {
			System.out.println(student);
		}
		
	}

}
