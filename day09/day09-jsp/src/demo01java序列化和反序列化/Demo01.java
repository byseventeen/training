package demo01java序列化和反序列化;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class User implements Serializable {
	// 默认序列化ID
	private static final long serialVersionUID = 1L;
	String name;
	int age;
	
	public User(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]";
	}
	
}

public class Demo01 {

	public static void main(String[] args) {
		//writeObj();
		readObj();
	}
	
	
	/*
		反序列化的步骤：
			第一步：创建ObjectInputStream对象
			第二步：调用ObjectInputStream对象的readObject()方法
			第三步：关闭资源
	*/
	public static void readObj() {
		 try {
			// 创建ObjectInputStream对象
			 FileInputStream fis = new FileInputStream("d:/user.txt");
			 ObjectInputStream ois = new ObjectInputStream(fis);
			 // 调用ObjectInputStream对象的readObject()方法
			 User user = (User) ois.readObject();
			 System.out.println("user = " + user);
			 // 关闭资源
			 ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/*
		实现序列化的步骤：
		第一步：定义一个实体类，实现Seriablizable接口。
		第二步：创建ObjectOutputStream对象。
		第三步：调用ObjectOutputStream对象的writeObject方法实现序列化。
		第四步：关闭资源。
	*/
	public static void writeObj() {
		try {
			// 创建ObjectOutputStream对象
			FileOutputStream fos = new FileOutputStream("d:/user.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// 创建User对象，调用ObjectOutputStream的writeObject方法
			User user = new User("aa", 18);
			oos.writeObject(user);
			// 关闭资源
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
