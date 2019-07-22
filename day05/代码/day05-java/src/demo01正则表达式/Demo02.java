package demo01正则表达式;

/*
 * 使用正则实现替换功能
*/
public class Demo02 {

	public static void main(String[] args) {
		String content = "请联系我13522234567请联系我13622234567请联系我14522234567请联系我18522234567";
		
		// 定义规则
		String regex = "1\\d{10}";
		// 使用replaceAll方法把手机号码替换成###########
		content = content.replaceAll(regex, "###########");
		
		System.out.println(content);
	}

}
