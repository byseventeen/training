# 课程内容

1.  项目功能演示
2.  SVN项目管理工具介绍

---

# 一、项目功能演示

## 1.1 修改用户

### 1.1.1  加载用户

#### 1.1.1.1 前端页面

（1）修改list.jsp文件，指定“修改”按钮的地址。

```jsp
<a href="${pageContext.request.contextPath}/admin/user?oper=load&userId=${user.userId}">修改</a>
```

（2）修改/user/edit.jsp文件。

第一步：指定表单的action提交地址。

第二步：在form标签里面添加一个隐藏域，该隐藏域保存要修改用户的ID。

第三步：为每一个表单项都指定value属性。

```jsp
<%@ page language="java" import="java.util.Date" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>用户修改</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/content.css"/>
</head>

<body>
<div  id="title">
  <h1>用户添加</h1>
</div>
<div id="position"> 当前位置：用户管理 >> 用户修改 </div>
<div id="content">
  <form action="${pageContext.request.contextPath}/admin/user?oper=update" method="post">
  	<input type="hidden" name="userId" value="${user.userId}"/>
    <table class="add_table" >
      <caption>
      用户添加
      </caption>
      <thead>
      </thead>
      <tbody>
        <tr  >
          <td  width="15%"> 用户名:</td>
          <td ><input name="username" type="text" value="${user.userName}" ><span>*</span></td>
        </tr>
        <tr>
          <td  width="15%"> Email:</td>
          <td ><input name="email" type="text" size="40" value="${user.email}" ><span>*</span></td>
        </tr>
       <tr  >
          <td  width="15%"> 手机:</td>
          <td ><input name="mobile" type="text" value="${user.phone}"><span>*</span></td>
        </tr>
        <tr  style="background-color:#EEF4EA">
          <td colspan="2" align="center">
          	<input type="submit" value="保存"/>
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



#### 1.1.1.2 后台实现

（1）实现Servlet。

第一步：修改doPost方法，增加load参数的判断。

```java
 else if ("load".equals(oper)) {
     // 加载用户
     loadUser(req, resp);
 } 
```



第二步：定义loadUser方法，根据用户ID加载用户信息，并把用户信息发送给修改页面显示。

```java
// 加载用户数据
private void loadUser(HttpServletRequest req, HttpServletResponse resp) {
    try {
        // 获取用户ID
        String userId = req.getParameter("userId");
        // 根据用户ID查询用户信息
        User user = userService.findUserById(userId);
        // 把用户信息保存request中
        req.setAttribute("user", user);
        // 跳转页面
        req.getRequestDispatcher("/WEB-INF/jsp/user/edit.jsp").forward(req, resp);
    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("服务器发生异常，请稍后再试！");
    }
}
```



（2）修改UserServcie。

```java
/**
	 * 根据ID查询用户
	 * @param userId
	 * @return
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
public User findUserById(String userId) throws NumberFormatException, SQLException {
    return userDao.getUser(Integer.parseInt(userId));
}
```

（3）实现UserDao。

```java
/**
	 * 根据ID查询用户
	 * @param parseInt
	 * @return
	 * @throws SQLException 
	 */
public User getUser(int id) throws SQLException {
    String sql = "select * from user where userId = ?";
    Object o = qr.query(sql, new BeanHandler(User.class), new Object[]{id});
    if (o != null) {
        return (User) o;
    }
    return null;
}
```



### 1.1.2 保存用户

（1）修改UserServlet，实现updateUser方法。

```java
// 修改用户
private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // 获取表单数据
    String userId = req.getParameter("userId");
    String userName = req.getParameter("username");
    String email = req.getParameter("email");
    String phone = req.getParameter("mobile");
    try {
        // 修改用户的操作
        String msg = userService.updateUser(userId, userName, email, phone);
        if (msg == null) {
            req.setAttribute("msg", "修改成功！");
        } else {
            req.setAttribute("msg", msg);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        req.setAttribute("msg", "修改失败");
    }
    req.setAttribute("user", new User(Integer.parseInt(userId), userName, phone, email));
    req.getRequestDispatcher("/WEB-INF/jsp/user/edit.jsp").forward(req, resp);
}
```

**注意：修改成功后，需要把用户的信息回传给JSP页面。**

（2）修改UserService，定义一个updateUser方法。

修改用户功能的实现思路：先检查用户名是否已经被使用。如果已经被使用，那么就返回提示信息；如果没有被使用，那么就执行更新操作，并返回null。

```java
/**
	 * 修改用户
	 * @param userId 用户ID
	 * @param userName 用户名
	 * @param email 邮箱
	 * @param phone 手机号码
	 * @return 如果返回结果不为null，代表更新失败
	 * @throws SQLException 
	 */
public String updateUser(String userId, String userName, String email,
                         String phone) throws SQLException {
    // 查询用户名是否已经存在
    long total = userDao.getUser(userName);
    if (total > 0) {
        return "用户名已经存在，不能使用";
    }
    userDao.update(userId, userName, email, phone);
    return null;
}
```

（3）实现UserDao。

```java
/**
	 * 根据用户名查询
	 * @param userName
	 * @return 返回查询到结果数，0代表没有查询结果，大于0代表有查询结果
	 * @throws SQLException 
	 */
public long getUser(String userName) throws SQLException {
    String sql = "select count(*) from user where userName = ?";
    long count = (long) qr.query(sql, new ScalarHandler(), new Object[]{userName});
    return count;
}

/**
	 * 修改用户信息
	 * @param userId
	 * @param userName
	 * @param email
	 * @param phone
	 * @throws SQLException 
	 */
public void update(String userId, String userName, String email,
                   String phone) throws SQLException {
    StringBuilder sql = new StringBuilder("update user set ");
    sql.append("userName = ?, ");
    sql.append("email = ?, ");
    sql.append("phone = ? ");
    sql.append("where userId = ? ");
    System.out.println("修改用户的SQL：" + sql.toString());

    qr.update(sql.toString(), new Object[]{userName, email, phone, userId});
}
```



## 1.2 自定义token标签

第一步：创建标签处理类。

```java
package com.chinasofti.eshop.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/*
	生成token标签的处理类
*/
public class TokenTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		// 生成一个Token
		String token = new java.util.Date().getTime() + "";
		// 保存在Session中，并且保存一个表单隐藏域中
		PageContext pageContext = (PageContext) this.getJspContext();
		pageContext.getSession().setAttribute("token", token);
		// 生成隐藏域标签
		this.getJspContext().getOut().write(
				"<input type=\"hidden\" name=\"user_token\" value=\"" + token + "\"/>");
	}

	
	
}

```

第二步：在WEB-INF目录下新建标签文件。

```xml
<?xml version="1.0" encoding="UTF-8" ?>

<taglib xmlns="http://java.sun.com/xml/ns/javaee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-jsptaglibrary_2_1.xsd"
    version="2.1">
    
  <description>JSTL 1.1 core library</description>
  <display-name>JSTL core</display-name>
  <tlib-version>1.1</tlib-version>
  <short-name>etc</short-name>
  <uri>/etc-tags</uri>
  
  <tag>
    <name>token</name>
    <tag-class>com.chinasofti.eshop.tags.TokenTag</tag-class>
    <body-content>scriptless</body-content>
  </tag>

</taglib>

```

第三步：修改/WEB-INF/jsp/user/add.jsp文件。

```jsp
<!-- 引入标签库 -->
<%@ taglib uri="/etc-tags" prefix="etc"%>

<!-- 在form标签里面使用token标签 -->
<etc:token/>
```



## 1.3 删除用户

### 1.3.1 前端分析

（1）删除按钮

```jsp
<a href="#" class="like_btn" onclick="deleteChecked('id','User_list.html')">&nbsp;删除&nbsp;</a>
```

（2）deleteChecked方法实现

```js
/*删除所选项*/
function deleteChecked(checkboxName,url){
	// 根据name属性查询checkbox元素
	var objs = document.getElementsByName(checkboxName);
	
	var param = '';
	// 遍历所有的checkbox
	for(i=0;i<objs.length;i++){
		 // 判断元素是否是checkbox类型
		 if(objs[i].type.toLowerCase() == "checkbox" ){
			// 判断checkbox元素的选中状态，如果true代表选中，false代表没有被选中
		 	if(objs[i].checked){
		 		// 参数格式：id=1&id=2&id=3&
				param += ('id='+objs[i].value+'&');
				
				
			}
		 }
	}
	
	if(param.length >0){
		// 如果点击确定，就返回true，否则返回false。
		var bool = window.confirm('您确定删除所选的项吗？');
		if(bool){
			//这里提交到删除的地方
			//alert(url+"?"+param);
			window.location.href=url+"&"+param;
		}
	}
}
```

通过分析上面代码，我们可以得到以下信息：

删除时候的url：User_list.html

删除提交的参数格式：id=xx&id=xx&id=xx

请求方式：Get

响应结果：无



### 1.3.2 功能实现

第一步：修改deleteChecked方法的url参数。

```jsp
<a href="#" class="like_btn" onclick="deleteChecked('id','${pageContext.request.contextPath}/admin/user?oper=delete')">&nbsp;删除&nbsp;</a>
```

第二步：实现UserServlet的deleteUser方法。

```java
// 删除用户
private void deleteUser(HttpServletRequest req, HttpServletResponse resp) 
    throws ServletException, IOException { 
    try {
        // 获取参数
        String[] userIds = req.getParameterValues("id");
        userService.deleteUsers(userIds);
        // 返回用户查询页面
        //resp.sendRedirect(req.getContextPath() + "/admin/user?oper=find");
        findUser(req, resp);
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        throw new RuntimeException("服务器发生错误，删除失败！");
    }
}
```

删除成功后，重新跳转回查询用户的页面。

第三步：在UserService类中定义deleteUsers方法，实现删除用户的功能；

```java
/**
	 * 删除用户
	 * @param userIds 用户ID的数组
	 * @throws SQLException 
	 */
public void deleteUsers(String[] userIds) throws SQLException {
    userDao.deleteUsers(userIds);
}
```

第四步：修改BaseDao，初始化数据库连接池。

```java
public abstract class BaseDao {
	static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	static QueryRunner qr = new QueryRunner(dataSource);
	static QueryRunner qr2 = new QueryRunner();
}
```

第五步：在UserDao中定义deleteUsers方法，删除用户。

删除用户的操作应该放入到一个事务中执行。要么全部删除，要么全部不删除。

```java
/**
	 * 根据用户ID删除
	 * @param userId 要删除用户的ID的数组
	 * @throws SQLException 
	 */
public void deleteUsers(String[] userIds) throws SQLException {
    // 从连接池中获取一个链接
    Connection connection = dataSource.getConnection();
    try {
        // 开启事务
        connection.setAutoCommit(false);
        for (String userId : userIds) {
            String sql = "delete from user where userId = ?";
            qr2.update(connection, sql, new Object[]{userId});
        }
        // 提交事务
        connection.commit();
    } catch (Exception e) {
        e.printStackTrace();
        // 回滚事务
        connection.rollback();
        throw new RuntimeException("删除时候发生异常");
    } finally {
        // 释放连接
        connection.close();
    }
}
```



## 1.4 实现分页查询

### 1.4.1 前端页面

修改/WEB-INF/jsp/user/list.jsp文件，找到分页的tr标签，指定分页按钮的url地址，以及分页的信息。比如说，总记录数、当前页数、总页数等等。

```jsp
<!-- 分页 -->
<tr class="page">
    <td align="right" colspan="9">
        <a href="${pageContext.request.contextPath}/admin/user?oper=find?page=1" class="like_btn">
            首页
        </a>
        <a href="${pageContext.request.contextPath}/admin/user?oper=find?page=${curPage-1}" class="like_btn">
            上一页
        </a>
        <a href="${pageContext.request.contextPath}/admin/user?oper=find?page=${curPage+ 1}" class="like_btn">
            下一页
        </a>
        <a href="${pageContext.request.contextPath}/admin/user?oper=find?page=${totalPage}" class="like_btn">
            尾页
        </a>
        共${total}条纪录，当前第${curPage}/${totalPage}页，每页${pageSize}条纪录 </td>
</tr>
```



### 1.4.2 后台实现

（1）修改UserServlet的findUser方法。

```java
// 分页查询
private void findUser(HttpServletRequest req, HttpServletResponse resp) {
    // 获取查询条件
    String adminName = req.getParameter("adminName");
    try {
        // 获取分页信息
        String page = req.getParameter("page");
        if (page == null) {
            page = "1";
        }
        String pageSize = req.getParameter("pageSize");
        if (pageSize == null) {
            pageSize = "5";
        }
        // 分页查询用户
        List<User> userList = userService.findUsers(page, pageSize);

        // 总记录数
        int total = userService.getTotalOfUser();

        // 总页数
        int totalPage = 0;
        if (total % Integer.parseInt(pageSize) == 0) {
            totalPage = total / Integer.parseInt(pageSize);
        } else {
            totalPage = total / Integer.parseInt(pageSize) + 1;
        }

        // 保存request对象中
        req.setAttribute("userList", userList);
        req.setAttribute("curPage", page); // 当前页码
        req.setAttribute("pageSize", pageSize); // 每页显示结果数
        req.setAttribute("total", total); // 总记录数
        req.setAttribute("totalPage", totalPage); // 总页数

        // 请求转发给list.jsp
        req.getRequestDispatcher("/WEB-INF/jsp/user/list.jsp").forward(req, resp);
    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("服务器发生异常，请稍后再试！");
    }
}
```

（2）修改UserService，定义findUsers方法，该方法用于实现分页的业务功能。

```java
/**
	 * 分页查询用户
	 * @param page 第几页
	 * @param pageSize 每页显示结果数
	 * @return
	 * @throws SQLException 
	 */
public List<User> findUsers(String page, String pageSize) throws SQLException {
    // 计算分页查询的开始位置
    int startIndex = (Integer.parseInt(page) - 1) * Integer.parseInt(pageSize);
    // 调用dao方法执行查询
    List<User> userList = userDao.getUserByPage(startIndex, Integer.parseInt(pageSize));
    return userList;
}

/**
	 * 获取所有用户的数量
	 * @return
	 * @throws SQLException 
	 */
public int getTotalOfUser() throws SQLException {
    return userDao.count();
}
```

（3）修改UserDao，实现findUserByPage方法。

```java
/**
	 * 分页查询用户
	 * @param startIndex 查询的开始位置
	 * @param pageSize 每页结果数
	 * @return
	 * @throws SQLException 
	 */
public List<User> getUserByPage(int startIndex, int pageSize) throws SQLException {
    String sql = "select * from user limit ?, ?";
    Object o = qr.query(sql, new BeanListHandler(User.class), new Object[]{startIndex, pageSize});
    if (o != null) {
        return (List<User>) o;
    }
    return null;
}

/**
	 * 查询所有用户的数量
	 * @return
	 * @throws SQLException 
	 */
public int count(String userName) throws SQLException {
    String sql = "select count(*) from user";
    Long total = (Long) qr.query(sql, new ScalarHandler());
    return total.intValue();
}
```

## 1.5 增加分页条件

### 1.5.1 前端页面

修改用户查询的jsp文件，找到查询表单，然后指定表单的action属性。

```jsp
<form action="${pageContext.request.contextPath}/admin/user?oper=find" method="post">
    <table class="search_table">
      <tr>
        <td>用户名:</td>
        <td><input type="text" name="adminName" value="${adminName}"/></td>
        <td>&nbsp;</td>
        <td>&nbsp; </td>
      </tr>
      <tr>
        <td colspan="4" align="center"><input type="submit" value="搜索"/></td>
      </tr>
    </table>
  </form>
```

从查询表单可以看出：

提交地址：${pageContext.request.contextPath}/admin/user?oper=find

提交参数：adminName

请求方式：post

### 1.5.2 后台实现

（1）修改UserServlet的findUser方法

第一步：获取表单参数；

```java
// 获取查询条件
String adminName = req.getParameter("adminName");
```

第二步：调用Service组件方法的时候把查询条件传给Service方法中；

```java
// 分页查询用户
List<User> userList = userService.findUsers(adminName, page, pageSize);
// 总记录数
int total = userService.getTotalOfUser(adminName);
```



（2）修改UserService。

第一步：修改findUsers方法，添加查询条件；

```java
public List<User> findUsers(String userName, String page, String pageSize) throws SQLException {
    // 计算分页查询的开始位置
    int startIndex = (Integer.parseInt(page) - 1) * Integer.parseInt(pageSize);
    // 调用dao方法执行查询
    List<User> userList = userDao.getUserByPage(userName, startIndex, Integer.parseInt(pageSize));
    return userList;
}

```

第二步：修改getTotalOfUser方法，添加查询条件；

```java
public int getTotalOfUser(String userName) throws SQLException {
    return userDao.count(userName);
}
```



（3）修改UserDao

第一步：修改getUserByPage方法，判断查询条件是否为空，如果不为空就添加查询条件。

```java
public List<User> getUserByPage(String userName, int startIndex, int pageSize) throws SQLException {
    Object o = null;
    if (userName != null && !"".equals(userName)) {
        String sql = "select * from user where userName like ? limit ?, ?";
        o = qr.query(sql, new BeanListHandler(User.class)
                     , new Object[]{"%" + userName + "%", startIndex, pageSize});
    } else {
        String sql = "select * from user limit ?, ?";
        o = qr.query(sql, new BeanListHandler(User.class), new Object[]{startIndex, pageSize});
    }
    if (o != null) {
        return (List<User>) o;
    }
    return null;
}
```

第二步：修改count方法，也是判断查询条件是否为空，如果不为空就添加查询条件；

```java
public int count(String userName) throws SQLException {
    Long total = 0l;
    if (userName != null && !"".equals(userName)) {
        String sql = "select count(*) from user where userName like ?";
        total = (Long) qr.query(sql, new ScalarHandler(), new Object[]{"%" + userName + "%"});
    } else {
        String sql = "select count(*) from user";
        total = (Long) qr.query(sql, new ScalarHandler());
    }
    return total.intValue();
}
```



<%@ include file="" %> 静态导入

jsp:include： 动作指令   动态导入



静态导入和动态导入的区别：

1）静态导入会把被包含页面与当前JSP内容放在一起编译；如果使用动态导入，被包含的JSP页面在访问JSP的使用才会把被包含的JSP页面包含进来 ；

2）静态导入只会生成一个java文件，而动态导入会生成连个java文件；













