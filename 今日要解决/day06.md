# 课程目标

1.  JQuery事件绑定
2.  JQuery插件介绍
3.  Tomcat服务器的安装和部署
4.  开发Web应用程序
5.  在Tomcat上部署项目

---

# 一、事件绑定

bind：给html元素绑定事件。

on：同上。

one：给html元素绑定事件，但是该事件只会执行一次。

```javascript
用法：
$(selector).bind(type, [data], function(eventObj) {
    事件处理函数...
})
```

unbind：解除元素中绑定的事件。

```javascript
$(selector).unbind(type, [data, fn]) 
```

JQuery也可以通过事件函数绑定事件：

- click(fn)：绑定单击事件

- change(fn)：绑定内容改变事件

- toggle(fn1, fn2, fn3, ...)：给元素绑定多个click事件。第一次click的时候会调用fn1函数，第二次click的时候回调用fn2函数，第三次click的时候回调用fn3函数，以此类推。

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript" src="js/jquery-3.3.1.min.js" ></script>
		<script type="text/javascript" src="js/jquery-migrate-1.4.1.min.js" ></script>
	</head>
	<body>
		<button>Click me</button>
		<button>unbind</button>
		<script>
			/*// 绑定事件
			var buttonNode = document.getElementsByTagName("button")[0];
			buttonNode.onclick = function() {
				alert("Click me");
			}
			
			$("button:first").bind("click", function(e) {
				alert(e.type);
			})
			
			// 使用事件函数绑定事件
			$("button:first").click("jacky", function(e) {
				alert(e.data);
			});
			
			
			$("button:last").bind("click", function(e) {
				$("button:first").unbind("click");
			});*/
			
			
			$("button:first").toggle(function(e) {
				alert("click1...");
			}, function(e) {
				alert("click2...");
			});
			
		</script>
	</body>
</html>
```

事件对象的方法：

- data：获取时间传入的参数；

- stopPropagation: 阻止事件向外传播；

- preventDefault：阻止事件的默认行为；

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript" src="js/jquery-3.3.1.min.js" ></script>
		<script type="text/javascript" src="js/jquery-migrate-1.4.1.min.js" ></script>
	</head>
	<body>
		<div>
			<button>Click me</button><br />
			<a href="http://baidu.com">百度</a>
		</div>
		<script>
			$("button").click(function(e) {
				alert("button...");
				// 阻止事件传播
				e.stopPropagation();
			});
			
			$("div").click(function(e) {
				alert("div...");
			});
			
			$("a").click(function(e) {
				alert("a click...");
				// 阻止元素的默认行为
				e.preventDefault();
			});
		</script>
	</body>
</html>
```

# 二、jQuery插件

## 2.1 日期插件

插件的使用：

第一步：下载插件，并把插件引入到项目中。

jquery-ui.css

jquery-ui.js

第二步：在html页面中引入jquery-ui.css和jquery-ui.js文件；

第三步：初始化插件；



## 2.2 validation插件

作用：表单验证

使用步骤：

第一步：下载插件并解压缩；

第二步：把jquery.validation.min.js文件拷贝到项目的js文件夹下；

第三步：初始化插件；

第四步：添加校验规则；



如果要显示中文提示，需要引入messages_cn.js库。

自定义错误消息：

```javascript
// 初始化消息
$.extend($.validator.messages, {
    required: "该字段不允许为空",
    minlength: "该字段的长度不能够少于{0}"
});
```

还有，可以通过js定义验证规则：

```javascript
$（表单控件）.validate({
    rules: {
        // 定义规则
    }, 
    messages: {
        //定义错误消息
    }
});
```

# 三、软件结构

C/S: Customer/Server 基于客户端和服务端的软件。例如：QQ，酷狗音乐等等。

B/S: Browser/Server  基于浏览器和服务器的软件。例如：网站、OA管理系统、ERP管理系统、售票系统等等。

C/S和B/S结构软件的优缺点：

1）CS结构：

​	优点：CS结构软件可以在本地上存储数据，减少数据在网络上传输；可以不依赖网络。

​	缺点：要安装客户端。不能够跨平台。

2）BS结构：

​	优点：不需要安装客户端，可以实现跨平台。

​	缺点：必须要依赖网络。



# 四、服务器

## 4.1 服务器介绍

服务器也是一个应用软件。任何一台计算机上只要安装了该软件，那么这台计算机就是一台服务器。

常见的服务器：Websphere、WebLogic、JBOSS、Tomcat等等。

## 4.2 自定义服务器（了解）

浏览器与服务器之间的通信使用的是HTTP协议。HTTP协议是TCP的一个子协议。下面演示如何使用TCP协议实现服务器的网络通信。

```java
package demo01实现简单服务器;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * tcp网络通信的实现步骤：
 * 	第一步：创建一个服务端的Socket，监听某一个端口。
 *  第二步：等待客户端的连接；
 *  第三步：如果有客户端连接进来，服务端就需要开启一个线程处理客户端的请求。
 *  第四步：处理完成后，向客户端返回数据；
 * 	第五步：关闭客户端的Socket。
 */
class ServerThread extends Thread {
	Socket socket;
	
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	// 运行要执行的任务代码
	// run方法不能够直接调用
	// 线程启动的时候自动调用
	@Override
	public void run() {
		// 如果浏览器请求过来，就给浏览器返回一张图片
		try {
			// 1.读取图片
			FileInputStream fis = new FileInputStream("d:/hello.html");
			// 2.获取客户端输出流
			OutputStream out = socket.getOutputStream();
			// 3.向客户端的输出写数据
			byte[] buf = new byte[8 * 1024];
			int len = fis.read(buf);
			// 如果len不等于-1，代表读取到图片数据，否则读完
			while (len != -1) {
				// 向客户端写数据
				out.write(buf, 0, len);
				// 读取图片数据
				len = fis.read(buf);
			}
			// 4.关闭文件输入流和客户端的socket
			fis.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}

public class ServerDemo {

	public static void main(String[] args) throws IOException {
		System.out.println("服务器启动了...");
		// 创建一个服务端的Socket，并监听8080端口
		ServerSocket ss = new ServerSocket(8080);
		while (true) {
			// 调用ServerSocket对象的accept方法监听客户端的连接，
			// 如果有客户端连接进来，该方法返回客户端的Socket
			Socket socket = ss.accept(); // 阻塞方法
			// 启动一条线程，处理客户端的请求
			ServerThread st = new ServerThread(socket);
			st.start();
		}
		
	}

}
```

## 4.3 Tomcat服务器安装

第一步：下载并解压缩apache-tomcat-8.0.49.zip到本地磁盘的任意目录下。

第二步：配置JAVA_HOME环境变量。

第三步：进入bin目录下，双击startup.bat启动tomcat服务器。

第四步：通过浏览器访问localhost:8080，如果可以看到tomcat管理平台，就代表安装成功。



## 4.4 Tomcat的目录结构

bin：存放可执行文件。

conf：存储Tomcat的配置文件。

lib：第三方jar包。

logs：Tomcat运行过程中产生的日志文件。

temp：存放临时文件的目录。

webapps：存放web项目的目录，该目录下的每一个文件夹都是一个Web项目。

work：存放jsp的翻译文件。



## 4.5 URL地址

URL地址的格式：

http://localhost:8888/aa/hello.html

http:// 协议名称

localhost: 域名，localhost代表本地IP地址（127.0.0.1）

8888：端口号，Tomcat默认端口号为8080

- mysql：3306

- Oracle：1521

- ftp：21

​	特殊的端口号：80，如果是80端口，URL地址可以不写端口号。

/aa：上下文路径，默认就是项目的名称。

/hello.html : 访问资源的路径。如果是静态资源，那么访问资源路径就是静态资源的目录所在路径。如果是动态资源（如Servlet），那么访问路径就是web.xml中url-pattern指定的路径。



> 静态资源和动态资源的区别？

静态资源的内容是写死在文件中，如果要修改文件的内容，就要修改源码。动态资源的数据一般都是保存数据库中，并且一般都会有一个后台管理系统。如果要修改动态资源的数据，一般都是通过后台管理系统进行修改。



# 五、在服务器上开发Web项目

## 5.1 Web项目的目录结构

项目文件夹

​	|- css、js、images、html等子文件夹，用于存放静态资源文件【可选】

​	|- WEB-INF【必选】

​		|- classes 存放编译后的Java字节码文件【必选】

​		|- web.xml 整个web项目的核心配置文件【必选】



## 5.2 编写Servlet实现Java后台

Servlet是运行服务器端的Java应用程序。可以实现浏览器与服务器之间的数据交互。

### 5.2.1 创建Web项目

在Tomcat的webapps目录下，按照以下结构创建一个Web项目。

```
etc

   |- WEB-INF

        |- classes
		 |- lib   # 存放项目依赖的第三方jar包

        |- web.xml


```

### 5.2.2 编写Servlet

第一步：定义一个Java类，继承GenericServlet。

```java
public class HelloServlet extends GenericServlet {

}

```

第二步：重写service方法。

```java
/*
	service方法用于处理客户端发送过来的请求
		request: 代表请求，它保存所有的请求的数据
		response：代表响应，可以向浏览器发送数据。
*/
@Override
public void service(ServletRequest request, ServletResponse response)
    throws ServletException, IOException {
    response.getWriter().write("<h1>Hello Servlet...</h1>");
}
```

### 5.2.3 配置Servlet

在web.xml文件中配置Servlet的请求路径。

```xml
<?xml version="1.0" encoding="ISO-8859-1"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
  version="3.1"
  metadata-complete="true">

    
	<!-- 配置Servlet -->
	<servlet>
		<!-- Servlet的名字，可以是任意 -->
		<servlet-name>HelloServlet</servlet-name>
		<!-- Servlet的完整类名(包名+类名) -->
		<servlet-class>demo02Servlet.HelloServlet</servlet-class>
	</servlet>
	
	<servlet-mapping>
		<!-- 必须要与上面的servlet-name相同 -->
		<servlet-name>HelloServlet</servlet-name>
		<!-- 访问资源路径 -->
		<url-pattern>/hello</url-pattern>
	</servlet-mapping>

</web-app>

```

### 5.2.4 测试

启动Tomcat服务器，然后在浏览器上访问localhost:8080/etc/hello。



## 5.3 在Eclipse工具中开发Web项目

### 5.3.1 环境准备

#### 5.3.1.1 配置JRE

首先点击菜单Window -> Preferences，然后选择Java -> Install JREs。

![1563785071698](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1563785071698.png)

​	点击Add按钮，在JRE home中指定JDK的根路径。如下图所示：

![1563785121737](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1563785121737.png)

​	

​	设置完成后点击Finish按钮即可。



#### 5.3.1.2 配置Tomcat服务器

首先点击菜单Window -> Preferences，然后选择Server -> RuntimeEnviorments。

![1563785209193](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1563785209193.png)

​	

​	点击Add按钮，选择Tomcat8，然后再点击Add按钮，指定Tomcat根目录的路径，如下图所示：

![1563785272717](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1563785272717.png)

​	配置完成后点击Finish按钮即可。



#### 5.3.1.3 创建服务器

​	首先选择菜单Window -> Show View，打开Servers窗口。

![1563785429857](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1563785429857.png)

然后，在Servers窗口中点击链接，创建服务器。

![1563785469351](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1563785469351.png)

​	创建完成后，效果如下图所示：

![1563785496708](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1563785496708.png)





### 5.3.2 在Eclipse中创建一个Web项目

第一步：创建Web项目。

Eclipse中创建Web项目的目录结构：

```
项目文件夹：

	|- JavaResources

		|- src ： 存放Java源文件

	|- build ：存放编译后字节码文件 

	|- WebContent  : 项目的根路径

		|- css、js、images等静态资源的文件夹

		|- WEB-INF

			|- lib ： 存放项目依赖的第三方jar包

			|- web.xml 项目的核心配置文件
```

第二步：在src目录下新建一个Servlet文件。

```java
package com.chinasofti.day06servlet.servlet;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		
		response.getWriter().write("<h1>Hello World</h1>");

	}

}

```



第三步：在web.xml文件中配置servlet。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" id="WebApp_ID" version="3.1">
  
  	<!-- 配置Servlet -->
	<servlet>
		<!-- Servlet的名字，可以是任意 -->
		<servlet-name>HelloServlet</servlet-name>
		<!-- Servlet的完整类名(包名+类名) -->
		<servlet-class>com.chinasofti.day06servlet.servlet.HelloServlet</servlet-class>
	</servlet>
	
	<servlet-mapping>
		<!-- 必须要与上面的servlet-name相同 -->
		<servlet-name>HelloServlet</servlet-name>
		<!-- 访问资源路径 -->
		<url-pattern>/hello</url-pattern>
	</servlet-mapping>
  
  
</web-app>
```

第四步：把项目部署到Tomcat服务器。

在Servers窗口中，鼠标右键选中服务器后，选择Add and Remove菜单。然后把项目从左边拖到右边，最后点击Finish即可。

![1563785572118](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1563785572118.png)



第五步：启动Tomcat服务器。

在Servers窗口中，鼠标右键选中服务器，然后点击Debug或Run按钮启动服务器。



























​	

  





