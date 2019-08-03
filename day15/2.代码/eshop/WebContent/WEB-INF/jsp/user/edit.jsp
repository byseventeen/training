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
        <!-- <tr  >
          <td align="left" > 描述:</td>
          <td><textarea name="desc" id="desc" cols="50" rows="10"></textarea>
          </td>
          
        </tr> -->
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
    