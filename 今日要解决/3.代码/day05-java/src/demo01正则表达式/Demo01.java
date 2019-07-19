package demo01正则表达式;

/*
 * 使用正则匹配手机号
*/
public class Demo01 {

	public static void main(String[] args) {
		// 定义规则
		String regex = "1\\d{10}";
		// 调用字符串的matches进行匹配
		String phone = "13a22223456";
		boolean isMatch = phone.matches(regex);
		System.out.println(isMatch ? "匹配" : "不匹配");
	}

}
