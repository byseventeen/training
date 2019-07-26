# 课程目标

1.  Cookie实现七天免登录
2.  JSP介绍
3.  JSP处理指令
4.  JSP内置对象
5.  EL表达式

---

# 一、Java序列化和反序列化

序列化：就是把一个对象写入到文件中的过程。

反序列化：就是从文件中把对象的信息读取到一个对象中的过程。



（1）实现序列化的步骤：

第一步：定义一个实体类，实现Seriablizable接口。

第二步：创建ObjectOutputStream对象。

第三步：调用ObjectOutputStream对象的方法实现序列化。

第四步：关闭资源。

```java
/*
	实现序列化的步骤：
		第一步：定义一个实体类，实现Seriablizable接口。
		第二步：创建ObjectOutputStream对象。
		第三步：调用ObjectOutputStream对象的writeObject方法实现序列化。
		第四步：关闭资源。
*/
public static void writeObj() {
    try {
        // 创建ObjectOutputStream对象
        FileOutputStream fos = new FileOutputStream("d:/user.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        // 创建User对象，调用ObjectOutputStream的writeObject方法
        User user = new User("aa", 18);
        oos.writeObject(user);
        // 关闭资源
        oos.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```

序列化和反序列化的对象必须要实现Serializable接口。



（2）实现反序列化的步骤：

第一步：创建ObjectInputStream对象
第二步：调用ObjectInputStream对象的readObject()方法
第三步：关闭资源

```java
/*
	反序列化的步骤：
        第一步：创建ObjectInputStream对象
		第二步：调用ObjectInputStream对象的readObject()方法
		第三步：关闭资源
*/
public static void readObj() {
    try {
        // 创建ObjectInputStream对象
        FileInputStream fis = new FileInputStream("d:/user.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        // 调用ObjectInputStream对象的readObject()方法
        User user = (User) ois.readObject();
        System.out.println("user = " + user);
        // 关闭资源
        ois.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

}
```



注意事项：

1）序列化和反序列的对象必须是同一个对象。

2）每次序列化和反序列的时候，JVM都会为序列化生成一个序列化ID号。该ID号是根据序列化对象的类名、属性、方法等成员信息计算出来。所以，如果序列化时候生成ID和反序列化生成的ID不一致，就会导致反序列化失败。这时候，我们可以手动指定一个默认的序列化ID号。

```java
// 默认序列化ID
private static final long serialVersionUID = 1L;
```

# 二、Cookie实现七天免登陆

## 2.1 实现过程分析



![1564022140353](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1564022140353.png)



## 2.2 创建一个登录页面

定义一个LoginPageServlet，向页面输出一个登录表单。

```java
/*
	登录页面
*/
@WebServlet("/login.html")
public class LoginPageServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		// 输出一个登录表单
		resp.getWriter().write("<form action='/day09/login' method='post'>");
		resp.getWriter().write("	用户名：<input name='userName'/><br/>");
		resp.getWriter().write("	密    码：<input type='password' name='userPass'/><br/>");
		resp.getWriter().write("	<input type='checkbox' name='autoLogin' value='true'/> 下次自动登录<br/>");
		resp.getWriter().write("	<input type='submit' value='登 录'/>");
		
		Object o = req.getAttribute("msg");
		if (o != null) {
			resp.getWriter().write("	<span style='color:red'>" + o + "</span>");
		}
		
		resp.getWriter().write("</form>");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
}
```



## 2.3 实现登录功能

第一步：定义一个DoLoginServlet类；

第二步：获取登录表单参数；

第三步：判断用户名和密码是否正确；

​	1）如果登录成功，那么把用户名保存在Sessioni对象中。

​		a）获取autoLogin参数，如果autoLogin不为空，代表用户勾选了“下次自动登录”，那么就把用户名保存在Cookie对象中；如果autoLogin为空，代表用户没有勾选“下次自动登录”，那么就从cookie中把用户登录信息删除掉。

​		b）重定向到首页；

​	2）如果登录失败，请求转发到登录页面，提示用户“用户名或密码不正确”。

```java
/*
	用户登录
*/
@WebServlet("/login")
public class DoLoginServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String userName = req.getParameter("userName");
		String userPass = req.getParameter("userPass");
		
		// 如果登录成功
		if ("admin".equals(userName) && "123".equals(userPass)) {
			// 把用户名保存在session中
			HttpSession session = req.getSession();
			session.setAttribute("loginUser", userName);
			// 如果autoLogin不为null，那么就把用户登录信息保存在Cookie中
			String autoLogin = req.getParameter("autoLogin");
			if (autoLogin != null) {
				Cookie c = new Cookie("userName", userName);
				c.setMaxAge(60 *60 * 24 * 7);
				resp.addCookie(c);
			} else {
				// 如果没有选择免登陆，那么就从cookie中删除用户的登录信息。
				Cookie c = new Cookie("userName", "");
				c.setMaxAge(0);
				resp.addCookie(c);
			}
			// 重定向到后台首页
			resp.sendRedirect("/day09/index.html");
		} else {
			// 请求转发给登录页面
			req.setAttribute("msg", "用户名或密码不正确");
			req.getRequestDispatcher("/login.html").forward(req, resp);
		}
		
	}
	
}
```

## 2.4 实现首页功能 

首先从session中获取用户登录信息。

​	1）如果用户登录信息为空，代表用户没有登录，那么就要从cookie对象中获取用户登录信息。

​		a）如果cookie对象中有用户登录信息，代表用户之前已经登录过，那么就把用户登录信息读取出来，然后保存到session对象中，输出首页内容。

​		b）如果cookie对象中也没有用户登录信息，代表用户之前没有登陆过，那么就跳转到登录页面；

​	2）如果用户登录信息不为空，代表用户已经登录，那么输出首页内容。

```java
/*
	首页页面
*/
@WebServlet("/index.html")
public class IndexPageServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		
		Object o = req.getSession().getAttribute("loginUser");
		// 如果o为null，代表用户没有登录，那么从cookie中读取用户信息
		if (o == null) {
			Cookie[] cookies = req.getCookies();
			// 记录cookie中是否存在userName属性
			// 如果存在为true，否则为false
			boolean hasUserName = false;
			if (cookies != null) {
				for (Cookie c : cookies) {
					if ("userName".equals(c.getName())) {
						// 从cookie获取用户之前登录过的信息
						String userName = c.getValue();
						// 把用户登录信息重新保存到Session中
						req.getSession().setAttribute("loginUser", userName);
						hasUserName = true;
						resp.getWriter().write("欢迎回来：" + userName);
					}
				}
			}
			// 如果hasUserName为false，代表cookie中没有用户的登录信息
			if (!hasUserName) {
				resp.sendRedirect("/day09/login.html");
			}
		} else {
			resp.getWriter().write("欢迎回来：" + o);
		}
		
		
	}
	
}
```

# 三、JSP技术

## 3.1 JSP介绍

JSP实际上是一个Servlet+HTML的结合体。我们可以在JSP上面编写html代码也可以编写java代码。

> Servlet+JSP的最佳实践：

JSP只负责页面内容的展示，Servlet只负责后台的业务处理。



实际上，JSP也是一个Servlet类。当浏览器第一次访问JSP的时候，JVM会把JSP翻译成一个Java文件，文件的名称就是“JSP文件名_后缀名.java”。这个Java文件实际上就是一个Servlet文件。它也继承了HttpServlet类。我们在浏览器上所看到的内容，实际上就是该Servlet文件的service方法的out.write()方法的输出。

```
public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");

	// 获取系统当前时间
	Date d = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
	String dateStr = sdf.format(d);

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<h1>欢迎来到中软国际</h1>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t");

		response.getWriter().write("当前时间：" + dateStr);
	
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
```

上面是JSP翻译文件_jspService方法的源代码。



## 3.2 JSP基本用法

### 3.2.1 JSP文件的组成

一个JSP文件可以包含代码区、HTML标签、JSP指令、脚本表达式、注释等等。

#### 3.2.1.1 代码区

定义格式：

```jsp
<% java代码... %>
```

一个JSP文件可以有多个代码区。多个代码区之间可以互相访问。



#### 3.2.1.2  JSP指令

- page指令：指定当前JSP页面的参数信息。

```
<%@page language="java" import="包名,包名,..." pageEncoding="jsp页面使用的字符集"
	isErrorPage="true|false" errorPage="异常处理页面的路径"%>
```

一个JSP页面至少有一个page指令，而且一般page指令放在jsp文件的开始位置。

---

- include指令：包含其他jsp页面

```
<%@include file="JSP文件路径"%>
```

注意： 使用include指令包含其他页面，那么被包含页面会与包含的JSP页面会被编译成一个Servlet类。因此，为了避免相同处理指令引起的冲突，被包含页面中不能够出现与包含页面中相同的处理指令。

---

- taglib指令：引入标签库文件。

```
<%@taglib uri="标签库文件地址" prefix="前缀名"%>
```

#### 3.2.1.3 注释

JSP文件支持两个注释：

```
HTML：<!-- html注释 -->

JSP：<%-- JSP注释内容 --%>
```

因为HTML注释可以通过浏览器查看源代码看到，而JSP注释不可以在浏览器的源代码中查看，所有JSP注释的安全性较高。在实际开发中，尽量使用JSP注释。

#### 3.2.1.4 脚本表达式

作用：替代out.write(输出内容)方法。

定义格式：

```
<%=输出内容%>
```



### 3.2.3 JSP内置对象

JSP九大内置对象：

pageContext：相当于Servlet的pageContext；

request：相当于Servlet的HttpServletRequest对象

session：相当于Servlet的HttpSession对象

application：相当于Servlet的ServletContext对象 

config：相当于Servlet的ServletConfig对象

out：带缓存的PrintWriter对象

page：当前当前的JSP页面

response：相当Servlet的HttpServletResponse对象

exception：异常对象。只有jsp是一个异常处理页面的时候才有该对象的。

---

pageContext的作用：1）作为域对象；2）可以获取其他对象；



### 3.2.3 EL表达式

#### 3.2.3.1 EL介绍

EL: 表达式语言。使用EL表达式可以替换脚本表达式，使得JSP页面的代码更加简洁。

语法格式：

```jsp
${EL内置对象.属性名}
```



#### 3.2.3.2 EL内置对象

JSP内置对象            EL内置对象

pageContext           pageScope

request                    requestScope

session                    sessionScope

application              applicationScope

EL表达式的内置对象名可以不写。如果不写，默认就是从pageScope > requestScope > sessionScope > applicationScope查找域对象中的属性，直到找到为止。



#### 3.2.3.3 EL运算符

算术运算符：+ - * / %

逻辑运算符：and or  not

比较运算符：> < >= <= == !=

empty运算符：在字符串中使用empty，那么如果字符串为空，那么empty返回true，否则返回false。在集合中使用empty，如果集合没有元素，那么empty返回true，否则返回false。

三目运算符：${EL条件表达式 ? '内容1'：'内容2'}





































