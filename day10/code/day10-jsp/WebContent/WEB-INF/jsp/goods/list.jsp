<%@ page language="java" import="java.util.*,com.chinasofti.day10jsp.beans.Goods" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String contextPath = request.getServletContext().getContextPath();
	List<Goods> goodsList = (List<Goods>) application.getAttribute("goods");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" width="600" cellspacing="0">
		<% for (Goods goods : goodsList) { %>
		<tr>
			<td><%=goods.getGoodsName()%></td>
			<td><a href="<%=contextPath%>/goods/detail?goodsId=<%=goods.getGoodsId()%>">查看</a></td>
		</tr>
		<% } %>
		<tr>
			<td colspan="2">
				<a href="/cart/list">查看购物查内容</a>
			</td>
		</tr>
	</table>
</body>
</html>