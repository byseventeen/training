package day08;

public class Student {
	private int id;
	private String name;
	private String gender;
	private String birthday;
	private int age;
	private String telphone;
	private String hobby;
	public Student(int id, String name, String gender, String birthday, int age, String telphone, String hobby) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.age = age;
		this.telphone = telphone;
		this.hobby = hobby;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	@Override
	public String toString() {
		return "id:" + id + ", name:" + name + ", gender:" + gender + ", birthday:" + birthday + ", age:" + age
				+ ", telphone:" + telphone + ", hobby:" + hobby + "]";
	}
	
	
	
}
