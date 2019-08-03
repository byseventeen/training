<%@ page language="java" import="java.util.Date" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="/etc-tags" prefix="etc"%>    
    
   
<%-- <% 
	// 生成一个Token
	String token = new Date().getTime() + "";
	// 保存在Session中，并且保存一个表单隐藏域中
	session.setAttribute("token", token);
%> --%>
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
  	<!-- 自己定义token标签 -->
  	<etc:token/>
  	
  	<%-- <input type="hidden" name="user_token" value="<%=token%>"/> --%>
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
    