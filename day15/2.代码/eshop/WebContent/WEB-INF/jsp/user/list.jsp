<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户列表</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/content.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util.js"></script>
</head>

<body>
<div  id="title">
  <h1>用户列表</h1>
</div>
<div id="position"> 当前位置：用户管理 >> 用户列表</div>
<div id="content">
  <table class="list_table">
    <caption>
    用户列表
    </caption>
    <thead>
      <tr>
        <th width="6%">序号</th>
        <th width="4%">选择</th>
        <th width="20%">用户名</th>
        <th width="18%">Email</th>
        <th width="10%">手机</th>
        <th width="10%">操作</th>
      </tr>
    </thead>
    <tbody>
    
    	<c:forEach items="${userList}" var="user">
    		<tr align='center'  >
		        <td>${user.userId}</td>
		        <td><input name="id" type="checkbox"  value="${user.userId}"></td>
		        <td align="left"><a href='#'>
		           ${user.userName}
		          </a></td>
		        <td>${user.email}</td>
		        <td>${user.phone}</td>
		        <td><a href="${pageContext.request.contextPath}/admin/user?oper=load&userId=${user.userId}">
		            修改
		          </a>
		          |
		          <a href="">
		            详情
		          </a></td>
		     </tr>
    	</c:forEach>
      
 
      <tr class="operation">
        <td align="left" colspan="9">&nbsp;
          <a href="#" class="like_btn" onclick="checkAll('id')">
            全选
          </a>
          <a href="#" class="like_btn" onclick="cancelCheck('id')">
            取消选择
          </a>
          <a href="#" class="like_btn" onclick="reverseCheck('id')">
            反选
          </a>
          <a href="#" class="like_btn" onclick="deleteChecked('id','${pageContext.request.contextPath}/admin/user?oper=delete')">
            &nbsp;删除&nbsp;
          </a></td>
      </tr>
      
      <!-- 分页 -->
      <tr class="page">
        <td align="right" colspan="9">
        	 <jsp:include page="../page.jsp">
        	 	<jsp:param name="url" value="${pageContext.request.contextPath}/admin/user?oper=find"/>
        	 	<jsp:param name="curPage" value="${curPage}"/>
        	 	<jsp:param name="pageSize" value="${pageSize}"/>
        	 	<jsp:param name="total" value="${total}"/>
        	 	<jsp:param name="totalPage" value="${totalPage}"/>
        	 	<jsp:param name="searchName" value="${adminName}"/>
        	 </jsp:include>
        </td>
      </tr>
    </tbody>
  </table>
  <form action="${pageContext.request.contextPath}/admin/user?oper=find" method="post">
    <table class="search_table">
      <tr>
        <td>用户名:</td>
        <td><input type="text" name="adminName" value="${adminName}"/></td>
        <td>&nbsp;</td>
        <td>&nbsp; </td>
      </tr>
      <tr>
        <td colspan="4" align="center"><input type="submit" value="搜索"/></td>
      </tr>
    </table>
  </form>
</div>
</body>
</html>
    