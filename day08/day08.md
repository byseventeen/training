# 课程目标

1.  Request对象详解
2.  请求转发和请求重定向的区别
3.  ServletConfig对象
4.  ServletContext对象
5.  会话技术

---

# 一、Request对象详解

## 1.1 获取请求行

getRemoteAddress: 获取请求地址

getMethod()：获取请求方式

## 1.2 获取请求头

String getHeader(String name)：获取请求头的内容

String[] getHeaderNames()：获取所有请求头的名字，返回一个字符串数组

## 1.3 获取请求参数

String getParameter(String name)：获取指定的请求参数。

String[] getParameterValues(String name)：获取相同name属性的多个请求参数。

## 1.4 实现请求转发

请求转发就是可以实现Servlet之间的数据通信。

> 实现请求转发的步骤：

第一步：创建请求分发器对象；

```java
RequestDispatcher rd = request.getRequestDispatcher("请求路径")
```

注意：请求路径以“/”开头，不需要写上下文名称。

第二步：调用请求分发器对象的forward方法；

```java
rd.forward(request, response)
```

例如：在ServletA中请求转发给ServletB处理。

```java
@WebServlet("/a.do")
public class ServletA extends HttpServlet {
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("执行ServetA...");
		// 第一步：创建一个请求分发器对象
		RequestDispatcher rd = request.getRequestDispatcher("/b.do");
		// 第二步：调用请求分发器对象的forward方法执行请求转发
		rd.forward(request, response);
	}
}

@WebServlet("/b.do")
public class ServletB extends HttpServlet {
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("执行ServletB...");
	}

}

```

注意：请求转发不能够与response.getWriter().write()方法一起使用。



> 请求转发和请求重定向的区别？

- 请求转发地址栏不会发生变化，请求重定向的地址栏会发生变化。
- 如果是请求转发，浏览器只会发起一次请求，而请求重定向浏览器会发起两次请求。
- 请求转发的地址不需要指定上下文路径，而请求重定向的地址需要指定上下文路径。
- 请求转发可以通过Request对象实现数据的传递，而请求重定向不可以通过Request对象实现数据传递。、



> 一般情况，如果需要把数据从一个Servlet传递到另外一个Servlet中，建议使用请求转发。



## 1.5 实现不同Servlet之间的数据传递

### 1.5.1 Request对象实现Servlet之间数据传递

Request提供了用于实现Servlet之间数据传递的方法：

void setAttribute(String name, Object value)：把数据存放在Request对象的name属性中；

Object getAttribute(String name)：从Request对象中获取指定name属性的值；

```java
@WebServlet("/a.do")
public class ServletA extends HttpServlet {
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		// 往request对象中存储数据
		request.setAttribute("name", "aa");
		
		List<String> books = new ArrayList<String>();
		books.add("java基础");
		books.add("java框架");
		books.add("分布式");
		request.setAttribute("books", books);
		
		// 第一步：创建一个请求分发器对象
		RequestDispatcher rd = request.getRequestDispatcher("/b.do");
		// 第二步：调用请求分发器对象的forward方法执行请求转发
		rd.forward(request, response);
	
	}
}

@WebServlet("/b.do")
public class ServletB extends HttpServlet {
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 从request对象中获取数据
		String name = (String) request.getAttribute("name");
		System.out.println("name = " + name);
		
		List<String> books = (List<String>) request.getAttribute("books");
		for (String book : books) {
			System.out.println(book);
		}
	}

}
```



> 面试题：Servlet有哪些域对象？

域对象：实现不同Servlet之间数据传递的对象。

Servlet的四大域对象：

- PageContext：在一个JSP页面中有效。

- Request：在一次请求中有效。（实现Servlet之间数据传递）

- Session：在一次会话中有效，与浏览器相关的。（存储用户相关的数据）

- ServletContext：在整个Web应用范围内有效。（存储项目相关的数据，比如项目所使用的字符集）

> 域对象都有的方法：

void setAttribute(String name, Object value)：把数据存放在域对象的name属性中；

Object getAttribute(String name)：从域对象中获取指定name属性的值；

void removeAttribute(String name)：从域对象中删除指定属性；

### 1.5.2 Session对象的使用

在实际开发中，使用Session对象存储用户相关的数据。

第一步：获取Session对象。

```java
HttpSession session = request.getSession();
```

第二步：往Session存放数据。

```java
// 往Session中存储数据
session.setAttribute("name", "aa");
```

第三步：从Session中获取数据。

```java
// 从session中获取数据
Object o = session.getAttribute("name");
System.out.println(o);
```

因为Session对象的作用范围与浏览器相关。所以理论上，只要浏览器关闭，Session中的数据就会被清空。

### 1.5.2 ServletContext对象的使用

在实际开发中，使用ServletContext对象存储与Web项目相关的数据。

第一步：获取ServletContext对象。

```java
ServletContext context = request.getServletContext();
```

第二步：往ServletContext存放数据。

```java
// 往ServletContext对象中存储数据
context.setAttribute("name", "aa");
```

第三步：从context中获取数据。

```java
// 从ServletContext对象中中获取数据
Object o = context.getAttribute("name");
System.out.println(o);
```

当Web应用被卸载的时候，由于ServletContext对象会被卸载，所以保存在ServletContext对象中的数据也会被清空。

# 二、ServletConfig对象

作用：获取Servlet中的配置参数。

如果要使用ServletConfig对象，需要重写Servlet的init(ServletConfig config)方法。例如：

```java
public class Demo03Servlet extends HttpServlet {
	String uploadPath;
	String imageType;  
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("执行Demo03Servlet init方法...");
        // 根据参数名获取配置参数的值
		uploadPath = config.getInitParameter("uploadPath");
		imageType = config.getInitParameter("imageType");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("uploadPath = " + uploadPath);
		System.out.println("imageType = " + imageType);
	}

}
```

在web.xml文件中配置参数：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" id="WebApp_ID" version="3.1">
  
  <servlet>
  	<servlet-name>Demo03Servlet</servlet-name>
  	<servlet-class>demo03ServletConfig对象.Demo03Servlet</servlet-class>
  	<!-- servlet配置参数，一个init-param配置一个参数 -->
  	<init-param>
  		<!-- 参数名 -->
  		<param-name>uploadPath</param-name>
  		<!-- 参数值 -->
  		<param-value>d:/upload</param-value>
  	</init-param>
  	<init-param>
  		<!-- 参数名 -->
  		<param-name>imageType</param-name>
  		<!-- 参数值 -->
  		<param-value>jpg,png,jpeg</param-value>
  	</init-param>
  </servlet>
  
  <servlet-mapping>
  	<servlet-name>Demo03Servlet</servlet-name>
  	<url-pattern>/demo03</url-pattern>
  </servlet-mapping>
  
</web-app>
```

在实际开发中，如果一些数据可能会发生变化，那么这些数据建议在配置文件中进行定义，尽量不要写死在代码中。比如：项目所使用的字符集，文件上传路径、上传文件类型等等。

# 三、ServletContext对象

## 3.1  作为域对象使用

略。。。

## 3.2 获取上下文路径

通过Request对象的getContextPath()方法来获取项目的上下文路径。

```java
String contextPath = request.getContextPath();
System.out.println("contextPath = " + contextPath);
```

## 3.3 获取项目中资源文件

String getRealPath(String path)：获取资源文件所在文件夹在磁盘上的绝对路径；

InputStream getResourceAsStream(String filePath)：把资源文件读取到输入流中；

```java
@WebServlet("/demo04")
public class Demo04Servlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 设置响应头
		resp.setHeader("Content-Disposition", "attachment;filename=good.jpg");
		// 获取images文件夹在磁盘上路径
		ServletContext servletContext = req.getServletContext();
		/*String imagesPath = servletContext.getRealPath("/images");
		System.out.println("imagesPath = " + imagesPath);
		// 读取图片到输入流中
		FileInputStream fis = new FileInputStream(imagesPath + "/good.jpg");*/
		
		// 把资源文件读取到输入流中
		InputStream inputStream = servletContext.getResourceAsStream("/images/good.jpg");
		
		// 创建字节数组
		byte[] buf = new byte[1024];
//		int len = fis.read(buf);
		int len = inputStream.read(buf);
		while (len != -1) {
			// 把读取到的数据输出到浏览器
			resp.getOutputStream().write(buf, 0, len);
//			len = fis.read(buf);
			len = inputStream.read(buf, 0, len);
		}
		// 关闭输入流
		//fis.close();
		inputStream.close();
	}

}

```

注意：文件路径必须要以“/”开头，“/” 代表项目的根路径。



# 四、会话技术

会话：从用户打开浏览器并访问服务器开始，直到浏览器关闭为止，这个过程称为一次会话。

## 4.1 Cookie

简单来说，Cookie其实就是保存在浏览器中的一个小文件而已。每次浏览器请求服务器的时候，浏览器会带着这个小文件的数据一起发送给服务器。

![1563953562412](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1563953562412.png)

### 4.1.1 Cookie的基本操作

#### 4.1.1.1 添加数据

第一步：创建一个Cookie对象；

```java
Cookie c = new Cookie("name", value);
```

第二步：设置Cookie有效时间；

每一个Cookie都有一个默认的有效时间，以秒为单位。如果没有指定有效时间，默认就是浏览器关闭，Cookie的数据就会消失。

```java
c.setMaxAge(int second)
```

第三步：设置Cookie有效路径【可选】；

```java
c.setPath("路径");
```

如果没有设置有效路径，默认为当前项目的上下文路径。

第四步：把Cookie输入出客户端；

```java
response.addCookie(cookie);
```

【代码】

```java
@WebServlet("/setCookie")
public class SetCookieServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 创建Cookie对象
		Cookie c = new Cookie("name", "jacky");
		// 设置cookie的有效时间
		c.setMaxAge(120);
		// 设置有效路径
		// c.setPath("/day08-servlet/getCookie");
		// 把cookie输出到客户端
		resp.addCookie(c);
	}
	
}
```



#### 4.1.1.2 获取数据

第一步：调用request对象的getCookies方法，该方法用于获取客户端请求中包含的所有Cookie。

```java
Cookie[] cookies = request.getCookies();
```

第二步：遍历Cookie数组；

```java
for (Cookie c : cookies) {
    ...
}
```

第三步：调用Cookie的方法获取Cookie数据；

```java
getName()：获取Cookie的名字
getValue()：获取Cookie的值
```

【代码】

```java
@WebServlet("/getCookie")
public class GetCookieServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 获取客户端请求中包含的所有Cookie
		Cookie[] cookies = req.getCookies();
		// 遍历Cookie数组
		for (Cookie cookie : cookies) {
			String cookieName = cookie.getName();
			if ("name".equals(cookieName)) {
				String cookieValue = cookie.getValue();
				System.out.println(cookieName + ":" + cookieValue);
				break;
			}
		}
	}
	
}

```



#### 4.1.1.3 修改数据

创建一个同名的Cookie，它会覆盖原来的Cookie。

```java
@WebServlet("/updateCookie")
public class UpdateCookieServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 创建Cookie对象
		Cookie c = new Cookie("name", "mickey");
		// 设置cookie的有效时间
		c.setMaxAge(120);
		// 设置有效路径
		c.setPath("/day08-servlet/getCookie");
		// 把cookie输出到客户端
		resp.addCookie(c);
	}
	
}
```



 #### 4.1.1.4 删除数据

创建一个同名的Cookie，设置有效时间为0。

```java
@WebServlet("/deleteCookie")
public class DelCookieServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 创建Cookie对象
		Cookie c = new Cookie("name", "");
		// 设置cookie的有效时间
		c.setMaxAge(0);
		// 设置有效路径
		c.setPath("/day08-servlet/getCookie");
		// 把cookie输出到客户端
		resp.addCookie(c);
	}
	
}

```



## 4.2 Session

> Session与Cookie的区别？

- Cookie是客户端的会话技术，而Session服务器的会话技术；
- Cookie安全性相对Session的安全性较低；
- Cookie存储数据的大小不超过4Kb，而Session没有限制；
- Cookie只能够存储字符串的数据，而Session可以任意类型数据；

每一个用户都会独享一个Session。所以，可以使用Session保存用户相关的信息。



### 4.2.1 Session的基本操作

void setAttribute(String name, Object value)：往Session中添加数据。

Object getAttribute(String name)：从Session中获取当前用户的数据。

void removeAttribute(String name)：从Session中删除当前用户的指定数据。

void invalidate()：使当前用户的Session失效。



### 4.2.2 Session使用的细节问题

- 服务器创建Session对象的时候，它会把session对象的ID发送给浏览器，并保存在一个Cookie文件中。当浏览器再次请求服务器的时候，就会带着session对象的ID请求服务器。服务器就可以根据该ID找到服务器中的session对象。但是，如果浏览器禁用了cookie，那么服务器就无法把session对象的ID发送给浏览器。这时候程序需要把session的id保存起来，下次请求服务器的时候需要在url后面加上jsessionid参数。例如：

```java
// 获取sesion对象的ID
String jsessionId = session.getId();
// 对包含jsessionid的url进行加密处理
String url = response.encodeURL("/day08-servlet/sessionB?JSESSIONID=" + jsessionId);
// 重定向时候使用加密后的url
response.sendRedirect(url);
```



- session对象与浏览器相关。当浏览器关闭时候，服务器中的session对象并没有消失。但是由于浏览器中保存jsessionid的cookie没有设置有效时间，所以浏览器关闭时候，浏览器的cookie被清空掉，导致服务器无法通过jsessionid找到对应的session对象。所以，如果想让浏览器关闭后，还能够访问到服务器的session对象，那么可以修改jsession的有效时间。如下：

```java
// 修改jessionid的有效时间，保证jesessionid不会随着浏览器关闭而消失
Cookie c = new Cookie("JSESSIONID", session.getId());
c.setMaxAge(60 * 60 * 24);
response.addCookie(c);
```

除此以外，每一个session对象都有一个有效时间，如果超过了有效时间，那么服务器的对象也会被清空。可以通过在web.xml文件中修改session的有效时间。

![img](https://img-blog.csdnimg.cn/20190721005114443.png)



- 当服务器重启时候，服务器会把session中的数据保存在磁盘上，这个过程称为session的钝化。当服务器重新启动时候，服务器会从磁盘上把session数据重新加载回来，这个过程称为session的活化。但是，只要这些数据实现了序列化接口，才能够被执行钝化和活化操作。



五、Java回顾（JDBC）

第一步：导入mysql驱动。

第二步：获取数据库连接；

```java
Connection conn = DriverManager.getConnection(url, username, password);
```



























