# 课程内容

1.  自定义标签
2.  文件上传
3.  过滤器的使用

---

# 一、自定义标签(了解)

自定义标签的好处：可以简化JSP页面的代码，可以使用标签实现特定业务功能。

## 1.1 自定义标签的步骤

需求：定义一个标签，向页面输出系统的当前时间。

---

第一步：创建一个类，继承SimpleTagSupport，重写doTag方法。

```java
package com.chinasofti.day11.tags;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

/*
	获取系统的当前时间
*/
public class CurrentTimeTag extends SimpleTagSupport {
	
	// jsp页面解析标签的时候，会自动执行该标签对应处理类的doTag方法。
	@Override
	public void doTag() throws JspException, IOException {
		// 获取系统时间
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		String dateStr = sdf.format(d);
		// 把系统时间输出到JSP页面上
		this.getJspContext().getOut().write(dateStr);
	}
	
}

```



第二步：在WEB-INF目录下创建一个tld文件，用于定义标签，文件可以是任意的名字。

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<taglib xmlns="http://java.sun.com/xml/ns/javaee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-jsptaglibrary_2_1.xsd"
    version="2.1">
    
  <description>JSTL 1.1 core library</description>
  <display-name>JSTL core</display-name>
  <tlib-version>1.1</tlib-version>
  <!-- 前缀名 -->
  <short-name>etc</short-name>
  <!-- 该标签库的uri -->
  <uri>/mytags</uri>

	
  <tag>
  	<!-- 标签名 -->
    <name>currentTime</name>
    <!-- 标签处理类 -->
    <tag-class>com.chinasofti.day11.tags.CurrentTimeTag</tag-class>
    <body-content>scriptless</body-content>
  </tag>

</taglib>
```

第三步：使用标签；

```jsp
<%@ page language="java" import="java.util.*,java.text.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 引入标签库 -->
<%@taglib uri="/mytags" prefix="etc" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 自定义标签的使用格式：前缀名:标签名 -->
	<etc:currentTime/>
</body>
</html>


```



## 1.2 自定义标签的执行过程

当我们在jsp页面上使用标签的时候，jsp会根据taglib指令的uri地址查找/WEB-INF文件夹下的所有tld文件中是否存在指定uri的tld文件。如果找到，那么就查找该tld文件下的所有tag节点，判断是否存在一个指定名字的name属性。如果找到，那么就可以获取到tag-class节点的内容（标签处理类）。然后再通过反射技术创建标签处理类对象，并调用doTag方法完成特定的功能。

## 1.3 自定义标签的生命周期方法

当执行标签处理类的doTag方法之前，jsp会先通过下面方法执行一些初始化的操作。

- setJspContext()：初始化一个JspContext对象，JspContext代表了当前的JSP运行环境，它相当于PageContext对象。每次调用doTag方法前，JSP都会把当前JSP的运行环境通过该方法传入到标签处理类中。

- setJspBody()：初始化一个JspFragment对象，该对象代表标签体。只有当标签体存在的时候才会执行该方法；

- setParent()：初始化一个JspTag对象，该对象代表当前标签的父标签。只有当存在父标签时候才会执行该方法；

执行完成这些初始化操作后，那么我们就可以在doTag方法中通过调用它们对应的getter方法来获取这些对象。例如：

- getJspContext()：获取当前环境下的JspContext对象；

- getJspBody()：获取代表标签体的JspFragment对象；

- getParent()：获取父标签对象；



## 1.4 自定义标签的常见操作

### 1.4.1 控制标签体的内容

不输出：什么都不用做！

输出：this.getJspFragment().invoke(null);

```java
package com.chinasofti.day11.tags;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

public class ShowBodyTag extends SimpleTagSupport {
	
	@Override
	public void doTag() throws JspException, IOException {
		System.out.println("执行doTag方法...");
		// 输出标签体内容
		this.getJspBody().invoke(null);
	}

}
```





### 1.4.1 控制标签外面的内容

输出：什么都不做！

不输出：抛出SkipPageException异常。

```java
package com.chinasofti.day11.tags;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

public class ShowBodyTag extends SimpleTagSupport {
	
	@Override
	public void doTag() throws JspException, IOException {
		System.out.println("执行doTag方法...");
		// 输出标签体内容
		this.getJspBody().invoke(null);
		
		// 不输出外面的内容
		throw new SkipPageException();
	}

}
```



### 1.4.3 循环输出标签体内容

第一步：在JSP页面中定义一个标签。

```java
<etc:loop count="10">
	java<br/>
</etc:loop>	
```

第二步：创建标签处理类，并且定义一个count属性，并提供setter方法，用于获取标签的属性。

```java
package com.chinasofti.day11.tags;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

public class LoopTag extends SimpleTagSupport {
	// 定义成员属性获取标签的count属性值。属性名必须要与标签的属性名相同
	int count;
	
	// 提供setter方法
	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		System.out.println("count = " + count);
		// 根据count属性重换输出标签体内容。
		for (int i = 0; i < count; i++) {
			// 输出标签体内容
			this.getJspBody().invoke(null);
		}
	}
	
}
```

第三步：在tld文件中定义标签。

```xml
<tag>
    <name>loop</name>
    <tag-class>com.chinasofti.day11.tags.LoopTag</tag-class>
    <body-content>scriptless</body-content>
    <!-- 定义属性 -->
    <attribute>
        <!-- 属性名 -->
        <name>count</name>
        <!-- 是否是必须的 -->
        <required>true</required>
        <!-- 是否支持表达式 -->
        <rtexprvalue>true</rtexprvalue>
    </attribute>
</tag>
```



# 二、文件上传

## 2.1 文件上传表单和普通表单的区别

- 文件上传表单只能是Post请求；而普通表单可以是post，也可以是get；
- 文件上传表单需要指定enctype="multipart/form-data"属性，而普通表单不需要指定enctype属性；
- 提交文件上传表单的时候，每一个表单项都有自己独立的头信息，而普通表单项的头信息都是相同；
- 文件上传表单不能够通过request.getParameter方法获取表单数据，只能够通过getInputStream方法获取表单的数据；



## 2.2 使用FileUpload工具实现文件上传

第一步：在工程中引入fileupload相关的jar包；

commons-fileupload-1.4.jar

commons-io-2.6.jar

第二步：创建DiskFileItemFactory对象，该对象负责产生FileItem对象；

第三步：创建ServletFileUpload对象，该对象负责解析Request请求；

第四步：调用ServletFileUpload对象的parseRequest方法解析请求；

```java
package com.chinasofti.day11.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 文件上传
 */
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 创建DiskFileItemFactory对象
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 创建ServletFileUpload对象，该对象可以把Request中的表单数据解析出来
			ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
			// 设置上传文件的大小，如果上传文件超过了10Kb，就会抛出异常。
			servletFileUpload.setFileSizeMax(10240);
			// 调用parseRequest方法解析请求
			List<FileItem> fileItems = servletFileUpload.parseRequest(request);
			// 遍历集合
			for (FileItem fileItem : fileItems) {
                
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
	
}
```

FileItem代表了普通表单项和文件表单项。它提供了一些获取表单项信息的方法：

- getName()：获取上传文件的名称；
- getFieldName()：获取表单项的name属性值；
- isFormField()：判断该表单项是否是普通表单项，如果该方法返回true，那么就是普通的表单项；否则就是文件上传表单项；
- getString()：获取普通表单项的内容；
- getInputStream()：获取文件上传的输入流对象；
- getContentType()：获取表单项的类型，该方法可以用于校验上传文件的类型；
- getSize()：获取上传文件的大小，以字节为单位。该方法可以用于校验上传文件的大小；

完整的文件上传代码：

```java
package com.chinasofti.day11.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 文件上传
 */
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 创建DiskFileItemFactory对象
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 创建ServletFileUpload对象，该对象可以把Request中的表单数据解析出来
			ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
			// 调用parseRequest方法解析请求
			List<FileItem> fileItems = servletFileUpload.parseRequest(request);
			// 遍历集合
			for (FileItem fileItem : fileItems) {
				// 如果isFormField返回true，代表是一个普通表单项
				if (fileItem.isFormField()) {
					System.out.println(fileItem.getString("utf-8"));
				} else {
					// 如果isValid返回true，代表是一张图片
					// 如果isValid返回false，那么就不需要执行文件上传
					if (!isValid(fileItem)) {
						request.setAttribute("msg", "只能够上传图片");
						request.getRequestDispatcher("/upload.jsp").forward(request, response);
						return; //结束当前方法的执行
					}
					
					if (!checkSize(fileItem)) {
						request.setAttribute("msg", "上传图片超过了10Kb！");
						request.getRequestDispatcher("/upload.jsp").forward(request, response);
						return; //结束当前方法的执行
					}
					
					// 获取上传文件的输入流
					InputStream inputStream = fileItem.getInputStream();
					// 获取上传文件的名字
					String name = fileItem.getName();
					System.out.println("name = " + name);
					// 创建FileOutputStream对象
					String uploadPath = request.getServletContext().getRealPath(
							"/WEB-INF/upload");
					FileOutputStream fos = new FileOutputStream(uploadPath + "/" + name);
					// 读取图片数据，然后写入fos输出流中。
					byte[] buf = new byte[1024];
					int len = inputStream.read(buf);
					while (len != -1) {
						fos.write(buf, 0, len);
						len = inputStream.read(buf);
					}
					// 关闭资源
					fos.close();
				}
			}
		}
		catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断FileItem文件表单项是否超过了10Kb
	 * @param fi
	 * @return
	 */
	public boolean checkSize(FileItem fi) {
		long size = fi.getSize();
		/*if (size > 1024 * 10) {
			return false;
		}
		return true;*/
		return !(size > 10240);
	}
	
	/**
	 * 判断FileItem文件表单项是否是图片，如果是就返回true，否则返回false。
	 * @param fi
	 * @return
	 */
	public boolean isValid(FileItem fi) {
		String contentType = fi.getContentType(); //例如：image/jpeg
		String regex = "image/[a-zA-Z]{3,4}"; //验证图片的正则
		return contentType.matches(regex);
	}

}
```

我们也可以通过ServletFileUpload对象的setFileSizeMax方法，指定上传文件的大小。如果上传文件超过了指定大小，那么就会抛出异常。



# 三、过滤器

Servlet的三大组件之一。如果配置过滤器，那么当请求某一个Servlet的时候，先会进入到过滤器中。只有过滤器执行了放行操作的时候，才会继续请求目标的Servlet。



## 3.1 使用过滤器的步骤

第一步：定义一个类，继承Filter，重写doFilter方法。

```java
package com.chinasofti.day11.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*
	过滤器
*/
public class MyFilter implements Filter {
	
	// 创建过滤器时候自动调用。容器启动的时候创建过滤器。
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("执行MyFilter的init方法..");
	}

	// 每次请求Servlet时候，如果配置了过滤器，每次请求都会执行该方法
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("执行MyFilter的doFilter方法...");
		
	}

	// 过滤器被销毁时候自动执行
	@Override
	public void destroy() {
		System.out.println("执行MyFilter的destroy方法...");
	}

}

```



第二步：在web.xml中配置过滤器。

```xml
<!-- 配置过滤器 -->
<filter>
 	<!-- 过滤器的名字 -->
  	<filter-name>MyFilter</filter-name>
  	<!-- 过滤器的完整类名 -->
  	<filter-class>com.chinasofti.day11.filter.MyFilter</filter-class>
</filter>
  
<filter-mapping>
  	<filter-name>MyFilter</filter-name>
  	<url-pattern>/hello</url-pattern>
</filter-mapping>
```

上面url-pattern配置过滤器的路径。当用户请求/hello的时候，首先会进入到MyFilter过滤器中。只有当过滤器执行了放行操作的时候，才会执行目标Servlet。

过滤路径的写法与Servlet相同。

## 3.2 FilterConfig

该对象用于获取Filter的配置参数。例如：

```java
@Override
public void init(FilterConfig filterConfig) throws ServletException {
	System.out.println("执行MyFilter的init方法..");
	// 获取过滤器的配置参数
	name = filterConfig.getInitParameter("name");
}
```

我们可以在filter节点中指定配置参数：

```xml
<!-- 配置参数 -->
<init-param>
	<param-name>name</param-name>
	<param-value>jacky</param-value>
</init-param>
```

## 3.3 过滤器链

如果在同一个资源上配置了多个过滤器，那么访问资源的时候，就会按照下面顺序执行过滤器中的代码：

先配置的过滤器就会先启动，然后再执行放行，最后按照配置顺序的相反方向执行放行后的代码。比如有两个Filter，一个FilterA，另外一个FilterB。FilterA先配置，所以执行顺序是：

- 执行FilterA放行前代码；
- 执行FilterA放行代码；
- 执行FilterB放行前代码；
- 执行FilterB放行代码；
- 执行目标Servlet；
- 执行FilterB放行后代码；
- 执行FilterA放行后代码；



## 3.4 过滤器的应用场景

1）登录认证

2）项目全局中文乱码处理

3）七天免登陆



























