# 课程内容

1.  MVC模式
2.  项目介绍
3.  表设计
4.  项目框架搭建

---

# 一、MVC模式

面试题：说一下什么是MVC模式？

软件设计的思想，跟语言无关。

MVC：M- Model（业务模型）、V-View（JSP视图）、C-Controller（控制器）

Model：负责业务功能实现

View：负责数据展示

Controller：作为核心控制器，负责根据不同业务调用不同业务组件。例如：Servlet



Model1：JSP即用于展示数据，也做业务处理。

Model2：JSP负责数据的展示，Servlet负责业务处理。

MVC模式：model2 + 3层架构



# 二、表设计

根据前端页面分出表的结构。



```sql
# 产品分类表
create table category (
	categoryId int primary key auto_increment comment '类别编号',
    categoryName varchar(255) default '' not null comment '类别名称'
);

# 产品表
create table product (
	productId int primary key auto_increment comment '产品编号',
    productName varchar(255) not null default '' comment '产品名称',
    price int not null default 0 comment '产品价格',
    userId int not null comment '发布者的ID'，
    createTime datetime not null comment '发布时间',
    categoryId int comment '产品类别编号'
);

# 会员表
create table user (
	userId int primary key auto_increment comment '会员编号',
    userName varchar(255) not null default '' comment '会员名',
    userPass varchar(255) not null default '' comment '加密后登录密码',
    phone varchar(11) default '' comment '手机号码',
    email varchar(255) default '' comment '邮箱地址'
);

# 增加外键
alter table product
add constraint fk_product_user foreign key(userId) references user(userId);

alter table product
add constraint fk_product_user foreign key(categoryId) references category(categoryId);
```

# 三、搭建项目

在项目开发之前，我们先要把项目的整体结构搭建出来。项目的结构如下图所示：

![1564641264292](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1564641264292.png)



# 四、功能演示

## 4.1 首页

第一步：在/WEB-INF/jsp文件夹下新建jsp文件。

head.jsp 首页的头部

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>

<style>
body{
	margin:0;
	padding:0;
}
#head{
	background:url(images/top-bg.gif) repeat-x top left;
	height:64px;
}

#head p{
	float:right;
	font-family:Arial, Helvetica, sans-serif;
	font-size:12px;
	color:#CCC;
	padding-right:10px;
}

a{
	color:#CCC;
}
</style>
</head>

<body>
	
    <div id="head">
	<img src="images/logo.gif"/>
    <p>
    您好：<span class="username"></span>，欢迎使用后台管理系统！
        	[<a href="#" target="_blank">网站主页</a>]
        	[<a href="" target="_blank">修改密码</a>]
        	[<a href="${pageContext.request.contextPath}/exit" target="_top">注销退出</a>]&nbsp;
     </p>
    </div>   
    
</body>
</html>
    
```

index.jsp 首页

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理系统</title>
</head>
<frameset rows="64,*"  frameborder="NO" border="0" framespacing="0">
	<frame src="head.html" noresize="noresize" frameborder="NO" name="head" scrolling="no" marginwidth="0" marginheight="0" target="main" />
  	<frameset cols="200,*"   id="frame">
        <frame src="menu.html" name="menu" noresize="noresize" marginwidth="0" marginheight="0" frameborder="0" scrolling="yes" target="main" />
        <frame src="" name="main" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" target="_self" />
	</frameset>
</frameset>

<noframes>
	<body>您的浏览器不支持框架！</body>
</noframes>

</html>
```



menu.jsp 首页的菜单

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
    	<title>菜单页面</title>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8"></meta>
        <link rel="stylesheet" type="text/css" href="css/menu.css"/>
        <script type="text/javascript" src="js/util.js"></script>
		<script type="text/javascript">
			function initMenu(){
				//所有父菜单,采用通用的getElementsByClassName()
				var supMenus = getElementsByClassName("sup_menu",null,"h1");
				//所有子菜单
				var subMenus = getElementsByClassName("sub_menu",null,"ul");
				
				
				//设置除第一个子菜单外的其它子菜单为隐藏状态
				for(var i=0;i<subMenus.length;i++){
						if(i==0){
							continue;
						}else{
							subMenus[i].style.display="none";	
						}
				}
				
				//为每一个sup_menu注册事件
				for(var i=0;i<supMenus.length;i++){
					supMenus[i].onclick=function(){
						//隐藏所有
						for(var j=0;j<subMenus.length;j++){
							subMenus[j].style.display="none";
						}
						//让选中的子菜单显示出来
						var idx = index(this,supMenus);
						subMenus[idx].style.display="";
					}	
				}
			}
			
			function index(obj,arr){
				for(var i=0;i<arr.length;i++){
					if(	obj==arr[i]){
						return i;	
					}
				}
				return -1;
			}
			
				
		</script>
    </head>
    <body onLoad="initMenu()">
    	<ul id="menu">
        	<li>
            	<!-- -->
            		<h1 class="sup_menu"><a herf="#">产品管理</a></h1>
                <ul class="sub_menu">
                    <li><a href="addCategory.jsp"  target="main">添加分类</a></li>
                    <li><a href="category.do?oper=query"  target="main">分类管理</a></li>
                    <li><a href="Product_add.html"  target="main">添加产品</a></li>
                    <li><a href="Product_list.html"  target="main">产品管理</a></li>
                </ul>
            </li>
            <li>
            		<h1 class="sup_menu"><a herf="#"  >会员管理</a></h1>
                <ul class="sub_menu">
                    <li><a href="User_add.html" target="main">添加会员</a></li>
                    <li><a href="User_list.html" target="main">会员管理</a></li>
                </ul>
            </li>
    	</ul>                
    </body>

</html>
```

第二步：在web.xml文件中配置jsp路径。

```xml
<servlet>
    <servlet-name>Index_JSP</servlet-name>
    <jsp-file>/WEB-INF/jsp/index.jsp</jsp-file>
  </servlet>
  <servlet-mapping>
    <servlet-name>Index_JSP</servlet-name>
    <url-pattern>/index.html</url-pattern>
  </servlet-mapping>
  <servlet>
    <servlet-name>Head_JSP</servlet-name>
    <jsp-file>/WEB-INF/jsp/head.jsp</jsp-file>
  </servlet>
  <servlet-mapping>
    <servlet-name>Head_JSP</servlet-name>
    <url-pattern>/head.html</url-pattern>
  </servlet-mapping>
  <servlet>
    <servlet-name>Menu_JSP</servlet-name>
    <jsp-file>/WEB-INF/jsp/menu.jsp</jsp-file>
  </servlet>
  <servlet-mapping>
    <servlet-name>Menu_JSP</servlet-name>
    <url-pattern>/menu.html</url-pattern>
  </servlet-mapping>
  <servlet>
```



## 4.2 登录验证

第一步：在com.chinasofti.eshop.filter包下新建LoginFilter类，实现Filter接口。

第二步：重写doFilter方法；

```java
package com.chinasofti.eshop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter(value={"/index.html", "/admin/*"})
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		// 登录认证
		Object o = req.getSession().getAttribute("loginUser");
		if (o == null) {
			// 跳转登录页面
			resp.sendRedirect(req.getContextPath() + "/login.html");
		} else {
			chain.doFilter(request, response);
		}
	}
	
    ...
}

```

拦截路径：

/index.html 首页路径

/admin/*    后台资源的访问路径，以/admin开头



## 4.3 登录 

### 4.3.1 前端实现

第一步：在WEB-INF/jsp目录下新建login.jsp文件；

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 
		获取上下文路径的el：${pageContext.request.contextPath}
	 -->
	<form action="${pageContext.request.contextPath}/login" method="post">
		用户名：<input name="userName"/><br/>
		密码：<input name="userPass"/><br/>
		<input type="submit" value="登 录"/>
		<span>${msg}</span>
	</form>
</body>
</html>
```

第二步：在web.xml文件中配置jsp；

```xml
<servlet>
    <servlet-name>Login_JSP</servlet-name>
    <jsp-file>/WEB-INF/jsp/login.jsp</jsp-file>
</servlet>
<servlet-mapping>
    <servlet-name>Login_JSP</servlet-name>
    <url-pattern>/login.html</url-pattern>
</servlet-mapping>
```



### 4.3.2 后台实现

第一步：在com.chinasofti.eshop.beans包下新建User实体类。

```java
package com.chinasofti.eshop.beans;

public class User {
	private Integer userId;
	private String userName;
	private String userPass;
	private String phone;
	private String email;
	
	public User() {
		super();
	}

	public User(Integer userId, String userName, String userPass, String phone,
			String email) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPass = userPass;
		this.phone = phone;
		this.email = email;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", userPass=" + userPass + ", phone=" + phone + ", email="
				+ email + "]";
	}
	
}

```

第二步：在com.chinasofti.eshop.dao包下新建BaseDao类，作为所有Dao的模板；

```java
package com.chinasofti.eshop.dao;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public abstract class BaseDao {
	static QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
	static QueryRunner qr2 = new QueryRunner();
	
}

```



第三步：新建UserDao类，实现用户查询功能。

```java
package com.chinasofti.eshop.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;

import com.chinasofti.eshop.beans.User;

/*
	用户管理的数据访问层
*/
public class UserDao extends BaseDao {
	

	/**
	 * 查询用户
	 * @param name 用户名
	 * @param pass 密码
	 * @return
	 * @throws SQLException 
	 */
	public User getUser(String name, String pass) throws SQLException {
		String sql = "select * from user where userName = ? and userPass = password(?)";
		Object o = qr.query(sql, new BeanHandler(User.class), new Object[]{name, pass});
		if (o != null) {
			return (User) o;
		}
		return null;
	}
	
}

```

第四步：在com.chinasofti.eshop.service包下定义UserService，该类提供用户管理模块相关的业务方法；

```java
package com.chinasofti.eshop.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;

import com.chinasofti.eshop.beans.User;

/*
	用户管理的数据访问层
*/
public class UserDao extends BaseDao {
	

	/**
	 * 查询用户
	 * @param name 用户名
	 * @param pass 密码
	 * @return
	 * @throws SQLException 
	 */
	public User getUser(String name, String pass) throws SQLException {
		String sql = "select * from user where userName = ? and userPass = password(?)";
		Object o = qr.query(sql, new BeanHandler(User.class), new Object[]{name, pass});
		if (o != null) {
			return (User) o;
		}
		return null;
	}
	
}

```



第五步：定义一个LoginServlet，实现登录业务功能。

```java
package com.chinasofti.eshop.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.eshop.beans.User;
import com.chinasofti.eshop.service.UserService;

/**
 * 用户登录
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	UserService userService = new UserService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 获取表单的参数
			String userName = request.getParameter("userName");
			String userPass = request.getParameter("userPass");
			
			// 调用service方法获取用户信息
			User user = userService.getUser(userName, userPass);
			if (user == null) {
				request.setAttribute("msg", "用户名或密码不正确！");
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp")
					.forward(request, response);
			} else {
				request.getSession().setAttribute("loginUser", user);
				response.sendRedirect(request.getContextPath() + "/index.html");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

```



## 4.4 注销

第一步：修改head.jsp文件，执行注销退出的链接地址。

```jsp
<a href="${pageContext.request.contextPath}/exit" target="_top">注销退出</a>
```

第二步：创建一个LogoutServlet，实现注销功能。

```java
package com.chinasofti.eshop.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.eshop.beans.User;
import com.chinasofti.eshop.service.UserService;

/**
 * 用户注销
 */
@WebServlet("/exit")
public class LogoutServlet extends HttpServlet {
	UserService userService = new UserService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath() + "/login.html");
	}

}

```

## 4.5 用户管理

### 4.5.1 查询用户

#### 4.5.1.1 后台实现

第一步：创建一个UserServlet，该Servlet被设计为可以处理所有与用户管理相关的请求。

```java
package com.chinasofti.eshop.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.eshop.beans.User;
import com.chinasofti.eshop.service.UserService;

/*
	用户管理（添加用户、修改用户、查询用户）
*/
@WebServlet("/admin/user")
public class UserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 通过一个参数来指定具体操作
		// oper参数：add代表添加用户，update代表修改用户，delete代表删除用户，find代表查询用户
		
		String oper = req.getParameter("oper");
		if ("add".equals(oper)) {
			// 添加用户
			addUser(req, resp);
		} else if ("update".equals(oper)) {
			// 修改用户
			updateUser(req, resp);
		} else if ("delete".equals(oper)) {
			// 删除用户
			deleteUser(req, resp);
		} else if ("find".equals(oper)) {
			// 查询用户
			findUser(req, resp);
		}
	}

	// 查询用户
	private void findUser(HttpServletRequest req, HttpServletResponse resp) {
		
	}

	// 删除用户
	private void deleteUser(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException { 
		
	}

	// 修改用户
	private void updateUser(HttpServletRequest req, HttpServletResponse resp) {
		
	}

	// 添加用户
	private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
}

```

在doPost方法中，根据用户传入参数oper来确定执行哪些操作。

查询用户：localhost:8080/eshop/admin/user?oper=find

添加用户：localhost:8080/eshop/admin/user?oper=add

修改用户：localhost:8080/eshop/admin/user?oper=update

删除用户：localhost:8080/eshop/admin/user?oper=delete



第二步：实现findUser方法。

```java
// 查询用户
private void findUser(HttpServletRequest req, HttpServletResponse resp) {
    try {
        // 查询用户
        List<User> userList = userService.findUsers();
        // 保存request对象中
        req.setAttribute("userList", userList);
        // 请求转发给list.jsp
        req.getRequestDispatcher("/WEB-INF/jsp/user/list.jsp").forward(req, resp);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```



第三步：在UserService中定一个findUsers方法，调用UserDao组件查询所有用户。

```java
/**
 * 查询用户
 * @return
 * @throws SQLException 
 */
public List<User> findUsers() throws SQLException {
    return userDao.getUsers();
}
```

第四步：在UserDao中定义一个getUsers方法，查询数据库中用户的信息。

```java
/**
 * 查询用户
 * @return
 * @throws SQLException 
 */
public List<User> getUsers() throws SQLException {
    String sql = "select * from user";
    Object o = qr.query(sql, new BeanListHandler(User.class));
    if (o != null) {
        return (List<User>) o;
    }
    return null;
}
```

#### 4.5.1.2 前端页面

第一步：修改menu.jsp，指定会员管理链接的地址。

```jsp
<li><a href="${pageContext.request.contextPath}/admin/user?oper=find" target="main">会员管理</a></li>
```

第二步：修改/WEB-INF/jsp/user/list.jsp文件，导入jstl核心标签库。

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
```

第三步：使用foreach标签循环集合数据。

```jsp
<c:forEach items="${userList}" var="user">
    <tr align='center'  >
        <td>${user.userId}</td>
        <td><input name="id" type="checkbox"  value="1"></td>
        <td align="left"><a href='#'>
            ${user.userName}
            </a></td>
        <td>${user.email}</td>
        <td>${user.phone}</td>
        <td><a href="User_edit.html">
            修改
            </a>
            |
            <a href="User_edit.html">
                详情
            </a></td>
    </tr>
</c:forEach>
```



### 4.5.2 添加用户

#### 4.5.2.1 前端页面

第一步：在/WEB-INF/jsp/user文件夹下新建add.jsp文件，并指定表单的action和method属性。

```jsp
<%@ page language="java" import="java.util.Date" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>用户添加</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/content.css"/>
</head>

<body>
<div  id="title">
  <h1>用户添加</h1>
</div>
<div id="position"> 当前位置：用户管理 >> 用户添加 </div>
<div id="content">
  <form action="${pageContext.request.contextPath}/admin/user?oper=add" method="post">
  	<table class="add_table" >
      <caption>
      用户添加
      </caption>
      <thead>
      </thead>
      <tbody>
        <tr  >
          <td  width="15%"> 用户名:</td>
          <td ><input name="username" type="text" ><span>*</span></td>
        </tr>
        <tr  >
          <td  width="15%"> 密码:</td>
          <td ><input name="password" type="password" ><span>*</span></td>
        </tr>
         <tr  >
          <td  width="15%"> 确认密码:</td>
          <td ><input name="repassword" type="password" ><span>*</span></td>
        </tr>
        <tr  >
          <td  width="15%"> Email:</td>
          <td ><input name="email" type="text" size="40" ><span>*</span></td>
        </tr>
       <tr  >
          <td  width="15%"> 手机:</td>
          <td ><input name="mobile" type="text" ><span>*</span></td>
        </tr>
        <tr  >
          <td align="left" > 描述:</td>
          <td><textarea name="desc" id="desc" cols="50" rows="10"></textarea>
          </td>
          
        </tr>
        <tr  style="background-color:#EEF4EA">
          <td colspan="2" align="center">
          	<input type="submit" value="添加"/>
          	<span>${msg}</span>
          </td>
        </tr>
      </tbody>
    </table>
  </form>
</div>
</body>
</html>
    
```

第二步：在web.xml文件中配置jsp。

```xml
<servlet>
    <servlet-name>USER_ADD_JSP</servlet-name>
    <jsp-file>/WEB-INF/jsp/user/add.jsp</jsp-file>
</servlet>
<servlet-mapping>
    <servlet-name>USER_ADD_JSP</servlet-name>
    <url-pattern>/admin/user/add.html</url-pattern>
</servlet-mapping>
```

第三步：修改menu.jsp，指定添加会员的链接地址。

```jsp
<li><a href="${pageContext.request.contextPath}/admin/user/add.html" target="main">添加会员</a></li>
```



#### 4.5.2.2 后台实现

第一步：修改UserServlet，实现addUser方法。

```java
// 添加用户
private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
        
        // 处理中文乱码
        req.setCharacterEncoding("utf-8");
        // 获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");

        // 调用业务方法添加用户
        userService.addUser(username, password, email, mobile);

        req.setAttribute("msg", "添加成功");

        // 把session中token属性删除
        req.getSession().removeAttribute("token");
        
    } catch (Exception e) {
        e.printStackTrace();
        req.setAttribute("msg", "添加失败");
    }
    req.getRequestDispatcher("/WEB-INF/jsp/user/add.jsp").forward(req, resp);
}
```

第二步：修改UserService，调用UserDao组件实现添加用户操作。

```java
/**
 * 添加用户
 * @param username 用户名
 * @param password 密码
 * @param email 邮箱
 * @param mobile 手机
 * @throws SQLException 
 */
public void addUser(String username, String password, String email,
                    String mobile) throws SQLException {
    userDao.addUser(username, password, email, mobile);
}
```

第三步：修改UserDao，把用户信息保存到数据库中。

```java
/**
 * 添加用户
 * @param username
 * @param password
 * @param email
 * @param mobile
 * @throws SQLException 
 */
public void addUser(String username, String password, String email,
                    String mobile) throws SQLException {
    String sql = "insert into user(userName, userPass, phone, email) values(?, password(?), ?, ?)";
    qr.update(sql, new Object[]{username, password, mobile, email});
}
```





### 4.5.3 全局异常处理

```xml
<!-- 全局处理异常 -->
<error-page>
    <error-code>500</error-code>
    <location>/500.html</location>
</error-page>
```

如果在web.xml中配置了全局异常处理，那么当服务器发生了500异常的时候，就会跳转到500.html页面，服务器程序不会中断。

### 4.5.4 防止表单重复提交

实现思路：

1）在jsp页面中生成一个token，可以是一个伪随机数；

```java
// 生成一个Token
String token = new Date().getTime() + "";
```

2）把token保存中session对象中，并且保存在表单隐藏域中；

```java
// 保存在Session中，并且保存一个表单隐藏域中
session.setAttribute("token", token);
```

```jsp
<form action="..." method="post">
  	<input type="hidden" name="user_token" value="<%=token%>"/>
    ...
</form>
```

3）提交表单后，在Servlet中分别从session和隐藏域中获取token，然后进行比较，如果session中的token为空，

或者session中的token与隐藏域中的token不相等，代表重复提交；

```java
// 获取Session对象中的token
Object o = req.getSession().getAttribute("token");
// 获取隐藏域中的token
String userToken = req.getParameter("user_token");

// 记录是否重复提交表，false代表不是重复提交，true代表重复提交
boolean isDupSubmit = false;

// 如果session对象中的token为空，代表重复提交
if (o == null) {
    isDupSubmit = true;
} else {
    // 如果session对象的token和隐藏域的token不相等，代表重复提交
    String token = (String) o;
    if (!token.equals(userToken)) {
        isDupSubmit = true;
    }
}
// 判断是否重复提交
if (isDupSubmit) {
    req.setAttribute("msg", "不能够重复提交表单");
    req.getRequestDispatcher("/WEB-INF/jsp/user/add.jsp").forward(req, resp);
}  else {
    // 如果没有重复提交，就执行正常的业务功能
    
}
```



4）在Servlet的最后把Session中的token删除；

```java
 // 删除session中的token    
req.getSession().removeAttribute("token");
```





























