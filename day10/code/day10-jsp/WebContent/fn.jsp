<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<% 
	String name = "ChinaSoft";
	pageContext.setAttribute("name", name);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 把name属性的内容全部转换成小写 -->
	${fn:toLowerCase(name)}	<br/>
	<!-- 把name属性的内容全部转换成大写 -->
	${fn:toUpperCase(name)}	<br/>
	<!-- 获取内容的长度 -->
	${fn:length(name)}	<br/>
	<!-- 截取字符串内容，第一个参数就是要截取的字符串，第二个参数代表开始截取的位置，第三个参数代表最后截取的位置 -->
	${fn:substring(name, 1, 5)}	<br/>
</body>
</html>