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