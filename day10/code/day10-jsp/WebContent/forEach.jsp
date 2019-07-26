<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!-- 引入jstl的核心标签库 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% 
	//String[] books = {"三国演义", "西游记", "红楼梦"};
	//pageContext.setAttribute("books", books);
	
	
	//List<String> books = new ArrayList<String>();
	//books.add("西游记");
	//books.add("海底总动员");
	//books.add("海底三万里");
	//pageContext.setAttribute("books", books);
	
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
	<!-- 遍历数组或List集合 
	<c:forEach items="${pageScope.books}" var="book">
		${pageScope.book}<br/>
	</c:forEach>-->
	
	<!-- 遍历Map集合, 遍历出来每一个元素是一个Map.Entry对象 -->
	<c:forEach items="${pageScope.books}" var="book">
		<%-- ${pageScope.book.getKey()} --> ${pageScope.book.getValue()}<br/> --%>
		${pageScope.book.key} --> ${pageScope.book.value}<br/>
	</c:forEach>
	
	
</body>
</html>