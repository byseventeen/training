package demo01正则表达式;

/*
 * 使用正则实现字符串切割
*/
public class Demo03 {

	public static void main(String[] args) {
		String ip = "192.168.0.100";
		
		// 定义规则
		String regex = "\\.";
		// 按照ip的一点进行切割
		String[] arr = ip.split(regex);
		System.out.println("开始打印...");
		for (String s : arr) {
			System.out.println(s);
		}
		
	}

}
