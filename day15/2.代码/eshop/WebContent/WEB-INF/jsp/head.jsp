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
    