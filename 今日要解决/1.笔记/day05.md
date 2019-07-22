# 课程目标

1.  Java复习（正则表达式）
2.  掌握不同JQuery选择器的用法
3.  掌握JQuery的基本操作
4.  JQuery的DOM操作
5.  JQuery事件绑定
6.  JQuery插件介绍

---

# 一、Java正则表达式

Java提供了Pattern类，该类代表正则表达式对象。

## 1.1 正则表达式的规则

字符类：

[abc] 代表匹配abc其中任意一个字符。

[^abc] 代表匹配除abc以外的任意一个字符。

[a-c] 代表匹配abc其中任意一个字符。

[a-cA-C] 代表匹配小写abc或大写ABC中任意一个字符。



预定义字符：

\d 代表匹配0-9之间任意一个数字。

\D 代表非数字。

\w 代表匹配[a-zA-Z_0-9]中任意一个字符。

\W 代表匹配除了[a-zA-Z_0-9]以外的任意一个字符。

\s 代表匹配一个空格。

\S 代表匹配一个非空格。

. 代表匹配任意一个字符。



边界词：

^ 代表行的开头；

$ 代表行的结尾；

\b 代表单词的边界；



数量词：

?代表匹配0次或1次；

+代表匹配1次或多次；

*匹配0次或多次； 

{n}匹配n次

{n,}匹配至少n次

{n,m}匹配至少n次，不超过m次。



注意：正则表达式是一个字符匹配一位。



## 1.2 正则表达式的应用

### 1.2.1 匹配内容

第一步：定义规则；

第二步：通过字符串的matches方法进行匹配。如果该方法返回true，代表匹配成功；否则匹配失败。

```java
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

```

### 1.2.2 替换

第一步：定义规则；

第二步：调用字符串的replaceAll方法替换符合正则表达式的内容；

```java
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

```

### 1.2.3 字符串切割

第一步：定义规则；

第二步：调用字符串的split方法按照正则表达式进行切割，该方法返回一个String[]数组。

```java
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

```

### 1.2.4 查找

第一步：把正则表达式编译成Pattern对象；

第二步：创建匹配器；

第三步：调用匹配器的find()和group()方法查找符合正则表达式的内容；

find（）: 查找下一个符合正则表达式的内容，如果找到返回true，否则返回false；

group()：获取以前匹配到的字符串内容；

```javascript
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
```



## 1.3 爬虫实现

### 1.3.1 网络资源的分析

网站: http://tieba.baidu.com/f?kw=%E4%B8%AD%E5%9B%BD%E5%A5%BD%E5%A3%B0%E9%9F%B3

需求：爬虫上面贴吧的所有标题。

第一步：查看网页的源码。

第二步：查找标题对应的标签。

```html
<a rel="noreferrer" href="/p/6198751550" title="整天尴尬扯国籍黑的人，有王力宏在的卫视是不是都要抵制?" target="_blank" class="j_th_tit ">整天尴尬扯国籍黑的人，有王力宏在的卫视是不是都要抵制?</a>
<a rel="noreferrer" href="/p/6197308075" title="自从王力宏发出加入中国好声音的消息，吧内谢霆锋的部分粉丝开始" target="_blank" class="j_th_tit ">自从王力宏发出加入中国好声音的消息，吧内谢霆锋的部分粉丝开始</a>
<a rel="noreferrer" href="/p/6197557059" title="王粉不要再碰瓷谢霆锋" target="_blank" class="j_th_tit ">王粉不要再碰瓷谢霆锋</a>
```

第三步：分析标题所使用标签的规则。例如

```java
String regex = "target=\"_blank\" class=\"j_th_tit\">.*？</a>";
```

上面.*代表匹配任意0个或多个任意字符。默认情况下，正则表达式会尝试匹配尽可能多的字符，我们称为贪婪模式。而上面在.\*后面加上?，代表匹配尽量少的字符。

> # 正则表达式默认是贪婪模式，如果在数量词后面加上一个问号，代表非贪婪模式。



### 1.3.2 爬取网页

第一步：创建一个URL对象，该对象可以用于绑定网络资源的地址。

```java
URL url = new URL（"URL地址"）;
```

第二步：创建URLConnection对象，该对象用于与URL资源建立连接。

```java
URLConnection connection = url.openConnection();
```

第三步：获取资源的内容；

```java
InputStream inputStream = connection.getInputStream();
```

第四步：通过InputStream对象读取资源的内容（I/O操作）。

第五步：关闭connection。

```java
connection.disconnect();
```

例如：爬取百度贴吧（http://tieba.baidu.com/f?kw=中国好声音）。

```java
package demo02爬虫;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Demo02 {

	public static void main(String[] args) throws IOException {
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
		System.out.println(tieba);
		
		// 关闭连接
		connection.disconnect();
	}

}

```

### 1.3.3 抓取网页的内容

这里通过上面正则表达式查找的步骤相同。

```java
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

```



# 二、JQuery

JQuery是一个js的工具。它对原生的js进行封装，帮助开发人员提供js的开发效率。jQuery有大量插件提供给开发人员使用，解决浏览器的兼容问题，实现简单的动画效果。

## 2.1 JQuery的用法

第一步：在html文件中引入jQuery库；

第二步：创建jQuery对象；

第三步：调用JQuery对象方法操作网页的内容；

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript" src="js/jquery-3.3.1.min.js" ></script>
		<script type="text/javascript" src="js/jquery-migrate-1.4.1.min.js"></script>
		<script>
			$(document).ready(function(){
				alert("页面加载完成...");
			});
		</script>
	</head>
	<body>
	</body>
</html>
```



## 2.2 JQuery对象和DOM对象的区别

与DOM对象不同，JQuery对象是对DOM对象的封装后产生的对象。

创建jQuery对象的语法格式：

```

```

如果需要把jQuery对象转换成DOM对象，可以调用jQuery对象的get()方法。例如：

```javascript
jQuery对象.get(0)
jQuery对象.get()[0]
```

注意：DOM对象不能够使用jQuery对象的属性和方法，同样地，jQuery对象也无法使用DOM对象的属性和方法。

## 2.3 选择器

作用：查找页面中的元素。

### 2.3.1 基本选择器

1）ID选择器：通过元素的ID属性查找元素，返回元素的JQuery对象。

语法格式：$("#ID")

2）标签选择器：通过标签名查找元素。

语法格式：$("标签名")

3）组合选择器：同时把多个选择器的结果组合在一起，返回一个包含所有结果的数组。

$(“selector1,selector2,..., selectorN”)

4）后代选择器：可以获取指定元素的后代元素。

$("selector1 selector2") : 获取selector1下的所有后代selector2。

$("selector1 > selector2")：获取selector1下所有的直接后代selector2。



### 2.3.2 添加筛选条件

$("selector:first")：获取第一个selector元素； 

$("selector:last")：获取最后一个selector元素； 

$("selector:eq(i)")：根据索引获取selector元素； 

$("selector:checked")：查询有 checked 属性的selector元素； 

$("selector:selected")：查询有 selected 属性的selector元素； 

$("selector:hidden"); 查询隐藏的selector元素。

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript" src="js/jquery-3.3.1.min.js" ></script>
		<script type="text/javascript" src="js/jquery-migrate-1.4.1.min.js" ></script>
		<script>
			$(document).ready(function() {
				// 获取第一个input元素
				//alert($("input:first").val());
				// 获取最后一个input元素
				// alert($("input:last").val());
				// 获取指定索引的input元素
				// alert($("input:eq(1)").val());
				// 获取有checked属性的input元素
				// alert($("input:checked").val());
				
				// 获取有selected属性的元素
				//alert($("option:selected").val());
				
				// 获取隐藏的元素
				alert($("select:hidden").html());
			});
		</script>
	</head>
	<body>
		用户名:<input type="text" id="userName" value="mickey"/><br/>
		密码:<input type="text" id="userPass" value="123"/><br/>
		性别:<input type="radio" name="gender" value="male"/>男
		<input type="radio" name="gender" value="female" checked/>女<br/>
		所选课程:<select style="display:none">
			<option value="java">Java</option>
			<option value="front">前端</option>
			<option value="php" selected>PHP</option>
		</select>
	</body>
</html>

```



### 2.3.3 筛选方法

eq(i): 根据索引获取元素 

first(): 取第一个 

last(): 取最后一个 

prev()：获取上一个同辈的元素； 

next()：获取下一个同辈的原素； 

parent()：获取父元素； 

children()：获取所有子元素的集合；

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript" src="js/jquery-3.3.1.min.js" ></script>
		<script type="text/javascript" src="js/jquery-migrate-1.4.1.min.js" ></script>
		<script>
			$(document).ready(function() {
				// 获取第一个input
				//alert($("input").first().val());
				// 获取最后一个input
				// alert($("input").last().val());
				// 获取指定索引的input
				// alert($("input").eq(1).val());
				
				// 获取下一个同辈的元素
				// alert($("#userName").next());
				// 获取上一个同辈的元素
				// alert($("#userPass").prev().get(0).nodeName);
				// 获取父元素
				// alert($("#userPass").parent().get(0).nodeName);
				// 获取所有子元素
				$(document.body).children().each(function(i) {
					alert(this.nodeName);
				});
			});
		</script>
	</head>
	<body>
		用户名:<input type="text" id="userName" value="mickey"/><br/>
		密码:<input type="text" id="userPass" value="123"/><br/>
		性别:<input type="radio" name="gender" value="male"/>男
		<input type="radio" name="gender" value="female" checked/>女<br/>
		所选课程:<select style="display:none">
			<option value="java">Java</option>
			<option value="front">前端</option>
			<option value="php" selected>PHP</option>
		</select>
	</body>
</html>

```

### 2.3.4 操作属性

$("selector").prop("属性名")：获取元素的属性

$("selector").prop("属性名"， “属性值”)：设置元素属性

$("selector").prop({"属性名": "属性值", "属性名": "属性值 "}) ：设置多个属性



**prop和attr的区别？**

prop设置或获取元素的固有属性，而attr能够设置或获取元素的任何属性。



### 2.3.4 设置或获取文本

html()：相当原生js的innerHTML属性。

text()：相当原生js的textContent属性。

val()：相当原生js的value属性。



### 2.3.5 文档处理

$(selector).append("html元素")：往selector中追加子元素。

$("html元素").appendTo(selector)：同上

$(selector).prepend("html元素")：往selector中的最前面添加子元素。

$("html元素").prependTo(selector)：同上

$(selector).before("html元素")：在selector前面添加新元素。

$("html元素").insertBefore(selector)：同上

$(selector).after("html元素")：在selector后面添加新元素。

$("html元素").insertAfter(selector)：同上

$(selector).remove()：删除元素

$(selector).empty()：把selector下所有子元素删除。





























