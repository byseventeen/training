<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("num", 10);

	List<String> books = new ArrayList<String>();
	books.add("java");
	pageContext.setAttribute("books", books);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	${pageScope.num}<br/>
	${num + 5}<br/>
	${num < 100}<br/>
	${num > 5 and num < 20}<br/>
	${not(num > 5)}<br/>
	
	${num > 6 ? '大' : '小'}<br/>
	${empty age}<br/>
	${empty books}<br/>
</body>
</html>
