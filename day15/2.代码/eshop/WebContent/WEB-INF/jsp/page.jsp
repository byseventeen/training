<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="${param.url}&page=1&adminName=${param.searchName}" class="like_btn">
            首页
          </a>
          <a href="${param.url}&page=${param.curPage-1}&adminName=${param.searchName}" class="like_btn">
            上一页
          </a>
          <a href="${param.url}&page=${param.curPage+1}&adminName=${param.searchName}" class="like_btn">
            下一页
          </a>
          <a href="${param.url}&page=${param.totalPage}&adminName=${param.searchName}" class="like_btn">
            尾页
          </a>
          共${param.total}条纪录，当前第${param.curPage}/${param.totalPage}页，每页${param.pageSize}条纪录 
</body>
</html>