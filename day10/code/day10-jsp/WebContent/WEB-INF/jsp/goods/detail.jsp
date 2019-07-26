<%@ page language="java" import="com.chinasofti.day10jsp.beans.Goods" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
Goods goods = (Goods) request.getAttribute("goods");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/day10-jsp/cart/add" method="post">
		<table border="0" width="600" cellspacing="0">
			<tr>
				<td>编号：</td>
				<td><input name="goodsId" value="<%=goods.getGoodsId()%>"/></td>
			</tr>
			<tr>
				<td>名称：</td>
				<td><%=goods.getGoodsName()%></td>
			</tr>
			<tr>
				<td>简介：</td>
				<td><%=goods.getGoodsDesc()%></td>
			</tr>
			<tr>
				<td>价格：</td>
				<td><%=goods.getPrice()%></td>
			</tr>
			<tr>
				<td>数量：</td>
				<td><input name="amount"/></td>
			</tr>
			<tr>
				<td colspan=2>
					<input type="submit" value="加入购物车"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>