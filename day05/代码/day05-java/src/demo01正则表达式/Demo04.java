package demo01正则表达式;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo04 {

	public static void main(String[] args) {
		String content = "请联系我13522234567请联系我13622234567请联系我14522234567请联系我18522234567";
		
		// 定义规则，然后把规则编译成Pattern对象
		String regex = "1\\d{10}";
		Pattern p = Pattern.compile(regex);
		
		// 创建匹配器对象
		Matcher m = p.matcher(content);
		
		// 调用匹配器的find和group查找符合正则表达式的内容
		/*boolean b = m.find();
		if (b) {
			String s = m.group();
			System.out.println(s);
		}*/
		
		while (m.find()) {
			String s = m.group();
			System.out.println(s);
		}
	}

}
