<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	List<String> books = (List<String>) request.getAttribute("books");

	${requestScope.books} --> ${books}

	pageContext.setAttribute("name", "aa");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>图书列表：</h1>
	<ul>
		<% 
			for (String book : books) {
		%>
		<li>
			<%-- <% out.write(book);%> --%>
			
			<%=book%>
			
		</li>
		<% 
			}
		%>
		
	</ul>
	
</body>
</html>