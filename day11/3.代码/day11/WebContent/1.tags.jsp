<%@ page language="java" import="java.util.*,java.text.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 引入标签库 -->
<%@taglib uri="/mytags" prefix="etc" %>    

<% 
	// 获取系统的当前时间
	//Date d = new Date();
	//SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
	//String dateStr = sdf.format(d);
	//pageContext.setAttribute("currentTime", dateStr);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- 当前时间：${currentTime} --%>
	
	<!-- 自定义标签的使用格式：前缀名:标签名 -->
	<etc:currentTime/><br/>
	
	<!-- 输入body内容 -->
	<etc:showBody>
		hello world<br/>
	</etc:showBody>
	
	
	<a href="">中软国际</a>
	
	<etc:loop count="10">
		java<br/>
	</etc:loop>	
</body>
</html>