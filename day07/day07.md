# 课程目标

1.  Http请求详解
2.  Get请求和Post请求的区别
3.  Http响应详解
4.  Servlet生命周期
5.  配置请求路径

---

# 一、继承HttpServlet

HttpServlet是GenericServlet的子类，专门用于处理Http请求。



## 1.1 使用HttpServlet创建Servlet的步骤



第一步：定义一个Java类，继承HttpServlet。

第二步：重写doGet或doPost方法。

- doGet方法：接收Get请求；
- doPost方法：接收Post请求；

```java
package com.chinasofti.day07servlet.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Demo01Servlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().write("doPost...");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//response.getWriter().write("doGet...");
		doPost(request, response);
	}

}

```



## 1.2 Servlet的访问过程

- 当浏览器请求服务器的时候，浏览器会根据url地址的ip和端口号找到对应的服务器。

- 服务器接收到浏览器发送过来的请求后，会根据url地址的上下文路径查找webapps目录下是否存在与上下文名称相同的文件夹

- 如果存在，那么服务器就会在文件夹下查找web.xml文件，然后在该文件中查找所有的url-pattern节点。

- 如果url-pattern节点内容与url地址的访问路径相匹配，那么就通过servlet-name节点找到对应的Servlet-class节点。

- 获取Servlet-class节点内容，使用反射技术创建Servlet对象，并调用service方法。



Servlet访问过程如下图所示：

![1563845050730](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1563845050730.png)



## 1.3 在Eclipse中查看Servlet源码

第一步：按下Ctrl + t ，输入HttpServlet。

第二步：点击attachment按钮，在弹出对话框中选择external file，然后选择apache-tomcat-8.0.47-src.zip文件。最后点击确定即可。

> 通过查看HttpServlet源码看到，service方法会从request中获取请求方式，然后再根据不同的请求方式调用不同的doXxx方法。比如说，如果是get请求，就调用doGet方法，如果是Post请求，就调用doPost方法。



# 二、Http请求详解

如果一个资源里面还要加载其他资源，那么每个资源都会向服务器发起一次请求。

Http请求的组成：请求行、请求头和请求正文。

## 2.1 请求行

请求行包含请求的基本信息。比如请求地址、请求方式等等。

Request对象提供了获取请求行信息的方法：

- getMethod: 获取请求方式

- getRequestUrl：获取请求的URL地址

```java
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    String method = request.getMethod();
    System.out.println("请求方式：" + method);

    String requestUrl = request.getRequestURL().toString();
    System.out.println("请求地址：" + requestUrl);
}
```

## 2.2 请求头

浏览器向服务器发起请求，浏览器会把相关信息通过请求头发送给服务器。

Accept：浏览器支持的文本格式；
Accept-Encoding：浏览器支持文本压缩格式；
Accept-Language：浏览器所支持的语言环境；
Cache-Control：跟浏览器缓存相关的请求头；
Connection：请求的连接方式；
Host：请求的主机名和端口号；

Request提供了获取请求头的方法：

- String getHeader(String headerName)：获取指定名称的请求头
- Enumeration getHeaderNames()：获取所有的请求头的名字

Enumeration是一个比较老旧的迭代器。它的功能与Iterator的功能相同。它的内部也维护了一个指针。

- hasMoreElement：判断指针是否指向元素，如果有就返回true，否则返回false。

- nextElement：获取指针指向的数据。



```java
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    // 获取请求头
    String accept = request.getHeader("Accept");
    System.out.println("acept: " + accept);

    // 获取所有请求头的名字
    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
        String headerName = headerNames.nextElement();
        String headerValue = request.getHeader(headerName);
        System.out.println(headerName + ": " + headerValue);
    }
}
```



## 2.3 请求正文

请求正文中存放了Post请求提交的数据。

Request对象提供了获取请求正文数据的方法：

String getParameter(String name): 获取指定名字的请求参数，name参数就是表单项的name属性值；

String[] getParameterValues(String name)：获取指定名字的参数，返回一个字符串数组；

```java
protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
    // 获取请求参数
    String userName = request.getParameter("userName");
    String userPass = request.getParameter("userPass");
    String gender = request.getParameter("gender");
    String[] favors = request.getParameterValues("favors");
    String province = request.getParameter("province");
    String city = request.getParameter("city");
    String introduce = request.getParameter("introduce");

    System.out.println("userName = " + userName);
    System.out.println("userPass = " + userPass);
    System.out.println("gender = " + gender);
    System.out.println("favors = " + Arrays.toString(favors));
    System.out.println("province = " + province);
    System.out.println("city = " + city);
    System.out.println("introduce = " + introduce);
}
```



如果提交Post表单的数据包含中文，就可能会出现中文乱码。

因为浏览器发送数据前对数据编码所使用的的字符集与服务器接收到参数后进行解码操作所使用的的字符集不一致所导致的。

解决办法：在获取参数之前修改Request对象默认的字符集。

```java
// Request处理Post请求中文乱码的方法：
setCharacterEncoding(String charsetName)：设置Request对象字符集的名字。
```

例如：

```java
@WebServlet("/regist")
public class RegistServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("doGet...");
		doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 设置request对象的字符集
		request.setCharacterEncoding("UTF-8");
				
		// 获取请求参数
		String userName = request.getParameter("userName");
		String userPass = request.getParameter("userPass");
		String gender = request.getParameter("gender");
		String[] favors = request.getParameterValues("favors");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String introduce = request.getParameter("introduce");
		
		System.out.println("userName = " + userName);
		System.out.println("userPass = " + userPass);
		System.out.println("gender = " + gender);
		System.out.println("favors = " + Arrays.toString(favors));
		System.out.println("province = " + province);
		System.out.println("city = " + city);
		System.out.println("introduce = " + introduce);
	}

}
```



# 三、Get和Post请求的区别

- Get请求参数显示在地址栏，而Post请求参数通过请求正文发送给服务器；
- Get请求会保存在浏览器历史记录中，而Post请求的数据默认不会保存浏览器历史记录，Get请求的安全性相对较低；
- Get请求发送数据的长度不超过2Kb，Post请求发送的数据大小没有限制；
- 浏览器默认会缓存Get请求的数据，Post请求没有缓存；



如果浏览器请求服务器静态资源，当第一次请求的时候，服务器会把资源文件发送给浏览器，浏览器接收到文件后会保存在本地缓存目录中。下一次再请求服务器的时候，浏览器就会先检查本地缓存目录是否存在该文件，如果存在就会请求服务器。



如果浏览器请求服务器动态资源，默认情况下，因此Servlet的getLastModified方法总是返回-1，所以每次都会调用doGet方法获取服务器的资源文件，浏览器不会访问本地缓存文件。所以，如果让浏览器访问本地缓存的文件，需要重写getLastModifed方法，该方法返回服务器资源文件的最后修改时间即可。



# 四、Http响应详解

Http响应就是服务器给浏览器发送数据的过程。

Http响应的主要组成：响应状态、响应头、响应正文。

## 4.1 响应状态

200：服务器响应成功

302：告诉浏览器重新发起请求（重定向）

304：告诉浏览器从本地缓存目录中读取资源文件

404：没有找到该资源

406：服务器返回浏览器无法解析的结果。（了解）

500：服务器发生异常



## 4.2 响应正文

请求正文就是服务器给浏览器发送的数据。

响应数据的时候出现中文乱码的原因：

服务器对数据加密所使用的编码与浏览器解密时候使用的编码不一致。

解决办法：

在向浏览器输出数据之前，修改Response对象默认的字符集。

Response对象提供了修改字符集的方法：

setCharacterEncoding(String charsetName)：设置Response对象的字符集。



## 4.3 响应头

### 4.3.1 Content-Type

告诉浏览器使用指定的字符集显示网页内容。



Response对象提供了setHeader()方法，用于设置响应头。

例如：

```java
// 修改response对象的字符集
response.setCharacterEncoding("utf-8");
// 设置Content-Type响应头
response.setHeader("Content-Type", "text/html;charset=utf-8");
```

或者

setContentType()：resposne对象提供的方法，用于修改response对象的字符键，并且设置Content-Type响应头。

```java
// 修改response对象的字符集，并且告诉浏览器使用utf-8显示网页的内容
response.setContentType("text/html;charset=utf-8");
```



### 4.3.2 Content-Encoding

告诉浏览器使用指定的压缩格式对数据进行压缩处理。

```java
package com.chinasofti.day07servlet.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	Content-Encoding响应头
*/
@WebServlet("/demo03")
public class Demo03Servlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置Content-Encoding响应头
		response.setHeader("Content-Encoding", "gzip");
		// 获取response对象的输出流
		OutputStream outputStream = response.getOutputStream();
		GZIPOutputStream gout = new GZIPOutputStream(outputStream);
		// 向浏览器发送4Kb数据
		for (int i = 0; i < 1000; i++) {
			gout.write("abcd".getBytes());
		}
		// 把压缩数据发送浏览器
		gout.finish();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}
	

}

```

### 4.3.3 Content-Disposition

告诉浏览器以文件下载的方式打开资源。

```java
package com.chinasofti.day07servlet.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	Content-Disposition响应头
*/
@WebServlet("/demo04")
public class Demo04Servlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 对文件名进行加密
		String filename = URLEncoder.encode("图片.jpg", "utf-8");
		System.out.println("filename = " + filename);
		// 设置Content-Disposition头
		response.setHeader("Content-Disposition", "attachment;filename=" + filename);
		// 读取图片
		FileInputStream fis = new FileInputStream("d:/good.jpg");
		// 从response对象中获取输出流对象，该输出流就是把数据输出给浏览器  
		OutputStream outputStream = response.getOutputStream();
		// 创建缓冲字节数组
		byte[] buf = new byte[1024];
		int len = fis.read(buf);
		while (len != -1) {
			// 向浏览器输出数据
			outputStream.write(buf, 0, len);
			// 继续读取图片的数据
			len = fis.read(buf);
		}
		// 关闭输入流
		fis.close();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}
}

```

### 4.3.4 location

Location头设置重定向的地址。一般会结合302状态码一起使用。

```java
@WebServlet("/demo05")
public class Demo05Servlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置响应状态302
		response.setStatus(302);
		// 设置重定向的地址
		response.setHeader("Location", "http://baidu.com");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}
	

}
```

执行重定向操作的时候，实际上浏览器会发起两次请求，第一次就是请求我们的Servlet，Servlet会把状态码302和location头发送给浏览器。浏览器会向location头指定地址发送第二次请求。

另外，Response对象提供了sendRedirect方法实现重定向。

```java
// 重定向
response.sendRedirect("http://baidu.com");
```

### 4.3.4 refresh

作用：1）刷新页面；2）实现指定时间后跳转页面；

```java
package com.chinasofti.day07servlet.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	refresh响应头
*/
@WebServlet("/demo06")
public class Demo06Servlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*// refresh头设置每隔1s刷新页面一次
		response.setHeader("refresh", "1");
		String dateStr = formatDate();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(dateStr);*/
		
		// 指定多少秒后跳转其他页面
		response.setHeader("refresh", "3;url=http://baidu.com");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	/**
	 * 把Date对象转换指定日期格式的字符串
	 */
	private String formatDate() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH时mm分ss秒");
		String dateStr = sdf.format(d);
		return dateStr;
	}
	

}

```



# 五、Servlet生命周期方法

init()：Servlet被创建时候自动调用该方法，只会调用一次。

service()：每次请求的时候都会调用该方法。

destroy()：Servlet被销毁的时候自动调用该方法，只会调用一次。

Servlet是一个单例对象，在服务器中一个Servlet类只会被创建一次。该Servlet对象被多个用户所共享。因此，不能够把用户相关的信息保存Servlet成员属性中。否则会出现安全问题。



# 六 、配置多个路径























