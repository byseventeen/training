<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/day11/upload" method="post" enctype="multipart/form-data">
		用户名：<input name="userName"/><br/>
		大头照：<input type="file" name="profile"/><br/>
		<input type="submit" value="上传"/>
		<span style="color:red">${msg}</span>
	</form>
</body>
</html>