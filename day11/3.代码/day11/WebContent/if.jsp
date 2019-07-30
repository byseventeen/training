<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/mytags" prefix="etc" %>
<% 
	pageContext.setAttribute("week", 2);
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<etc:if test="${week == 1}">星期一</etc:if>
	<etc:if test="${week == 2}">星期二</etc:if>
	<etc:if test="${week == 3}">星期三</etc:if>
</body>
</html>