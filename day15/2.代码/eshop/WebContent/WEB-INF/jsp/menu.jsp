<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
    	<title>菜单页面</title>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8"></meta>
        <link rel="stylesheet" type="text/css" href="css/menu.css"/>
        <script type="text/javascript" src="js/util.js"></script>
		<script type="text/javascript">
			function initMenu(){
				//所有父菜单,采用通用的getElementsByClassName()
				var supMenus = getElementsByClassName("sup_menu",null,"h1");
				//所有子菜单
				var subMenus = getElementsByClassName("sub_menu",null,"ul");
				
				
				//设置除第一个子菜单外的其它子菜单为隐藏状态
				for(var i=0;i<subMenus.length;i++){
						if(i==0){
							continue;
						}else{
							subMenus[i].style.display="none";	
						}
				}
				
				//为每一个sup_menu注册事件
				for(var i=0;i<supMenus.length;i++){
					supMenus[i].onclick=function(){
						//隐藏所有
						for(var j=0;j<subMenus.length;j++){
							subMenus[j].style.display="none";
						}
						//让选中的子菜单显示出来
						var idx = index(this,supMenus);
						subMenus[idx].style.display="";
					}	
				}
			}
			
			function index(obj,arr){
				for(var i=0;i<arr.length;i++){
					if(	obj==arr[i]){
						return i;	
					}
				}
				return -1;
			}
			
				
		</script>
    </head>
    <body onLoad="initMenu()">
    	<ul id="menu">
        	<li>
            	<!-- -->
            		<h1 class="sup_menu"><a herf="#">产品管理</a></h1>
                <ul class="sub_menu">
                    <li><a href="addCategory.jsp"  target="main">添加分类</a></li>
                    <li><a href="category.do?oper=query"  target="main">分类管理</a></li>
                    <li><a href="Product_add.html"  target="main">添加产品</a></li>
                    <li><a href="Product_list.html"  target="main">产品管理</a></li>
                </ul>
            </li>
            <li>
            		<h1 class="sup_menu"><a herf="#"  >会员管理</a></h1>
                <ul class="sub_menu">
                    <li><a href="${pageContext.request.contextPath}/admin/user/add.html" target="main">添加会员</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/user?oper=find" target="main">会员管理</a></li>
                </ul>
            </li>
    	</ul>                
    </body>

</html>