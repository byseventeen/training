<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/mytags" prefix="etc" %>
<% 
	/* String[] books = {"java", "android", "php"};
	pageContext.setAttribute("books", books);
	
	ArrayList<String> books = new ArrayList<String>();
	books.add("java");
	books.add("go");
	books.add("python");
	pageContext.setAttribute("books", books); 
	
	HashSet<String> books = new HashSet<String>();
	books.add("java");
	books.add("go");
	books.add("python");
	pageContext.setAttribute("books", books);*/
	
	Map<String, Integer> books = new HashMap<String, Integer>();
	books.put("西游记", 99);
	books.put("海底总动员", 199);
	books.put("海底三万里", 79);
	pageContext.setAttribute("books", books);
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- <etc:forEach items="${books}" var="book">
		${book}<br/>
	</etc:forEach> --%>
	
	<etc:forEach items="${pageScope.books}" var="book">
		${pageScope.book.key} --> ${pageScope.book.value}<br/>
	</etc:forEach>
</body>
</html>