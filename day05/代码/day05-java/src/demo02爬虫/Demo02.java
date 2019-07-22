package demo02爬虫;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo02 {

	public static void main(String[] args) throws IOException {
		// 爬取网页
		String tieba = getHtmlContent();
		
		// 定义规则，然后编译成Pattern
		String regex = "target=\"_blank\" class=\"j_th_tit \">.*?</a>";
		Pattern p = Pattern.compile(regex);
		
		// 创建匹配器对象
		Matcher m = p.matcher(tieba);
		
		// 调用find和group方法获取匹配内容
		while (m.find()) {
			String title = m.group();
			title = title.replace("target=\"_blank\" class=\"j_th_tit \">", "");
			title = title.replace("</a>", "");
			System.out.println(title);
		}
	}

	// 爬取网页
	private static String getHtmlContent() throws MalformedURLException,
			IOException {
		// 创建URL对象
		URL url = new URL("http://tieba.baidu.com/f?kw=%E4%B8%AD%E5%9B%BD%E5%A5%BD%E5%A3%B0%E9%9F%B3");
		// 创建URLConnection对象，建立远程连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		// 获取InputStream
		InputStream inputStream = connection.getInputStream();
		// 把inputStream对象转换BufferedReader对象
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		
		// 使用StringBuilder字符串缓冲类，用于拼接字符串。
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();
		while (line != null) {
			// 把读取到的内容先保存在StringBuilder内部维护的字符串数组中。
			// 当调用tostring方法的时候，StringBuilder才会把字符串数组中的内容放入在内存中。
			sb.append(line);
			line = br.readLine();
		}
		
		String tieba = sb.toString();
		//System.out.println(tieba);
		
		// 关闭连接
		connection.disconnect();
		return tieba;
	}

}
