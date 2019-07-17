package com.chinasofti.demo02比较器;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
	使用Collections.sort方法对集合进行排序时候，可以传入比较器对象。在比较器的compare方法中定义比较规则。
	如果调用Collections.sort方法时候没有传入比较器，那么比较对象就要实现Comparable接口，并实现compare方法。
*/
class Person {
	String name;
	int age;
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
}

class AgeComparator implements Comparator<Person> {

	/*
		比较两个元素的大小：
			如果返回0，代表第一个参数等于第二个参数；
			如果返回负数，代表第一个参数小于第二个参数；
			如果返回正数，代表第一个参数大于第二个参数；
	*/
	@Override
	public int compare(Person o1, Person o2) {
		//return o1.age - o2.age;
		return o2.age - o1.age;
	}
	
}

public class Demo02 {

	public static void main(String[] args) {
		ArrayList<Person> personList = new ArrayList<Person>();
		personList.add(new Person("aa", 19));
		personList.add(new Person("bb", 18));
		personList.add(new Person("cc", 20));
		personList.add(new Person("dd", 25));
		personList.add(new Person("ss", 21));
		
		Collections.sort(personList, new AgeComparator());
		
		for (Person person : personList) {
			System.out.println(person);
		}
		
	}

}
