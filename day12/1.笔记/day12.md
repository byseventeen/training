# 课程内容

1.  监听器
2.  Servlet零配置
3.  实现图形验证码
4.  商城后台管理系统需求分析
5.  商城后台管理系统数据库设计

---

# 一、监听器

监听器也是Servlet三大组件之一。监听器可以监听一些对象或对象属性状态的变化。

Web事件：

- 事件源：Web对象，例如：HttpSession对象、ServletRequest对象、ServletContext对象。

- 事件类型：ServletContextEvent、ServletRequestEvent、HttpSessionEvent、ServletContextAttributeEvent、ServletRequestAttributeEvent、HttpSessionAttributeEvent。

- 事件监听器：ServletContextListener、ServletRequestListener、HttpSessionListener、ServletContextAttributeListener、ServletRequestAttributeListener、HttpSessionAttributeListener。

- 处理函数：监听器中的一些处理方法。

## 1.1 监听对象状态的监听器

ServletContextListener：监听Servlet对象状态的监听器。

HttpSessionListener：监听Session对象状态的监听器。

ServletRequestListener：监听Request对象状态的监听器。

---

**下面以ServletContextListener为例介绍：**

ServletContextListener用于监听ServletContext对象状态的变化。例如：创建ServletContext，销毁ServletContext。当项目启动的时候，容器会创建ServletContext对象，如果配置了ServletContextListener监听器，那么就可以在项目启动的时候加入一些处理。

- 使用监听器的步骤：

第一步：定义一个类，实现监听器接口，并实现监听器的所有方法；

```java
/*
	ServletContextListener
*/
public class MyServletContextListener implements ServletContextListener {

	// 创建servletContext对象时候自动调用
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("ServletContext对象被创建了..");
	}

	// ServletContext对象被销毁时候自动调用
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("ServletContext对象被销毁了..");
	}

}
```

第二步：在web.xml中配置监听器；

```xml
<!-- 配置监听器 -->
<listener>
    <listener-class>com.chinasofti.day12.listener.MyServletContextListener</listener-class>
</listener>
```



## 1.2  监听对象属性状态的监听器

ServletContextAttributeListener：监听Servlet对象属性状态的监听器。

HttpSessionAttributeListener：监听Session对象属性状态的监听器。

ServletRequestAttributeListener：监听Request对象属性状态的监听器。

---

**下面以ServletContextAttributeListener为例介绍：**

ServletContextAttributeListener用于监听ServletContext对象属性状态的变化。例如：添加属性、删除属性、修改属性。

```java
/*
	监听ServletContext对象属性状态的变化
*/
public class MyServletContextAttributeListener implements ServletContextAttributeListener {

	// 往Servletcontext对象中添加属性的时候自动调用
	@Override
	public void attributeAdded(ServletContextAttributeEvent scae) {
		System.out.println("attributeAdded...");
		System.out.println("属性名：" + scae.getName());
		System.out.println("属性值：" + scae.getValue());
	}

	// 往Servletcontext对象中删除属性的时候自动调用
	@Override
	public void attributeRemoved(ServletContextAttributeEvent scae) {
		System.out.println("attributeRemoved...");
		
		System.out.println("删除的属性名：" + scae.getName());
		System.out.println("删除前属性的值：" + scae.getValue());
	}

	// 往Servletcontext对象中修改属性的时候自动调用
	@Override
	public void attributeReplaced(ServletContextAttributeEvent scae) {
		System.out.println("attributeReplaced...");
		System.out.println("修改前的值：" + scae.getValue());
		System.out.println("修改后的值：" + scae.getServletContext().getAttribute("jdbcUrl"));
		
	}

}
```

如果有多个监听器同时起作用，那么会按照它们在web.xml 中定义的顺序来执行。



# 二、Servlet零配置

定义格式：

```
@注解名(属性...)
```

使用注解配置的好处：

- 可读性更强。

- 相对配置文件更加简单

Servlet3.0以后开支持使用注解配置方式。



## 2.1 @WebServlet注解

作用：@WebServlet配置Servlet。

@WebServlet注解的属性：

- value：配置url-pattern。如果只有一个value属性，可以不写value属性名。

- urlPatterns：同上，不能够与value共存。

- loadOnStartup：指定Servlet的加载顺序，值越小，越先加载。但是该属性必须要指定urlPatterns或value属性一起使用；

- initParams: 指定servlet配置参数。它是一个数组，数组元素是@WebInitParam类型。

@WebInitParam: 指定一个配置参数，它有两个属性：

- name：参数名

- value：参数值

```java
@WebServlet(value={"/load1", "/load2"}, loadOnStartup=1, initParams={
		@WebInitParam(name = "name", value = "jacky"), 
		@WebInitParam(name = "age", value = "20")
})
public class LoadOnStartUpServlet extends HttpServlet {

}
```



## 2.2  @WebFilter注解 

作用：@WebFilter配置过滤器。

@WebFilter注解的属性：

- value：配置url-pattern。如果只有一个value属性，可以不写value属性名。

- urlPatterns：同上，不能够与value共存。

- servletNames：指定要拦截的Servlet的name属性。

- initParams: 指定servlet配置参数。它是一个数组，数组元素是@WebInitParam类型。

@WebInitParam: 指定一个配置参数，它有两个属性：

- name：参数名

- value：参数值

```java
/*@WebFilter(value="/index.html", initParams={
		@WebInitParam(name="charset", value="utf-8")
})*/
@WebFilter(servletNames={"IndexServlet"}, initParams={
		@WebInitParam(name="charset", value="utf-8")
})
public class LoginFilter implements Filter {

}
```



## 2.3 @WebListener注解

@WebListener配置监听器。

```java
@WebListener
public class MyServletContextListener implements ServletContextListener {
    
}
```



# 三、图形验证码

BufferedImage： 该对象代表一个图像；

Graphics：该对象用来绘制图案或文字；

ImageIO：把Image对象写入到输出流中。



生成四位数图形验证码的步骤：

第一步：定义一个数组保存字符；

第二步：生成四个随机数组下标，通过下标获取数组的字符，最后拼接起来；

第三步：创建一个BufferedImage对象，该对象代表一张图像；

第四步：创建一个Graphics对象，该对象代表画笔；

第五步：通过调用Graphics对象的方法把验证码绘制在图像上；

第六步：把整个图像写入到输出流中；

```java
package com.chiansofti.day12.servlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	 生成四位数图形验证码的步骤：
		第一步：定义一个数组保存字符；
		第二步：生成四个随机数组下标，通过下标获取数组的字符，最后拼接起来；
		第三步：创建一个BufferedImage对象，该对象代表一张图像；
		第四步：创建一个Graphics对象，该对象代表画笔；
		第五步：通过调用Graphics对象的方法把验证码绘制在图像上；
		第六步：把整个图像写入到输出流中；
*/
@WebServlet("/getVerCode")
public class GetVerCodeServlet extends HttpServlet {
	char[] data = "abcdef123456阿萨德看风景阿卡".toCharArray();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 处理响应中文乱码
		resp.setContentType("image/jpeg");
		// 生成随机数工具类
		Random rand = new Random();
		/*String vercode = "";
		for (int i = 0; i < 4; i++) {
			// 生成随机下标
			int index = rand.nextInt(data.length);
			vercode += data[index] + "  ";
		}*/
		//resp.getWriter().write(vercode);
		
		// 创建一个图像
		BufferedImage bi = new BufferedImage(100, 30, BufferedImage.TYPE_INT_RGB);
		// 创建画笔
		Graphics g = bi.getGraphics();
		
		// 设置画笔颜色
		g.setColor(Color.GRAY);
		// 绘制表框
		g.fillRect(0, 0, 100, 30);
		
		// 把验证码绘制在图像上
		String vc = "";
		for (int i = 0; i < 4; i++) {
			// 生成随机下标
			int index = rand.nextInt(data.length);
			// 通过指定RGB的值生成不同的颜色
			g.setColor(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
			g.drawString(data[index] + "", i * 15 + 15, 20);
			
			vc += data[index];
		}
		
		req.getSession().setAttribute("vercode", vc);
		
		// 把整个图像写入输出到浏览器ServletOutputStream
		ImageIO.write(bi, "JPG", resp.getOutputStream());
	}
	
}

```

在JSP页面上显示图形验证码：

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	// 刷新验证码
	function change() {
		document.getElementsByTagName("img")[0].src = "/day12/getVerCode?d=" + new Date().getTime();
	}
</script>
</head>
<body>
	<img src="/day12/getVerCode" onclick="change()" title="换一张"/>
</body>
</html>
```



# 四、数据库设计步骤

需求：酒店入住登记。

## 4.1  定义实体

在定义实体前要充分了解项目的各个功能需求。

客人实体：姓名、证件类型、证件号码、手机号码....

房间实体：房号、入住状态、房间类型、入住时间、入住天数...

## 4.2 绘制E-R图

Entity-Relation 实体关系图，用于把实体之间的关系描述出来。

![1564471211586](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1564471211586.png)



## 4.3 创建模型

根据E-R图绘制物理数据模型。创建物理模型的工具：visio、powerdesigner、在线画图亿图等等。



表之间的关系：

一对一：用户和身份证表，在一对多的基础上，把外键列设置为唯一，外键列可以设置在任意一方。

一对多：员工和部门、产品和产品类型等，可以在多的一方添加外键引入一方的主键来实现。

多对多：客人和房间、学生和课程等，可以通过创建一个中间表记录每张表的主键来实现的。



例如：酒店入住登记。

![1564472827118](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1564472827118.png)



创建物理模型后，需要检查模型是否满足第三范式的要求。

第一范式：一个表的每一个字段都是不可分割的；

第二范式：在第一范式的基础上，保证每个字段都与该表业务相关；

第三范式：在满足第二范式的基础上，保证每个字段与该表有直接关系，而不能够是间接关系；

按照第三范式对上面物理模型进行改造：

![1564474744458](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1564474744458.png)



## 5.5 创建数据库

定义sql语句













