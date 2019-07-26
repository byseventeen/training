## JSTL标签库

1. 引入jar包

2. 在jsp页面中使用tablib标签库文件

   c fmt fn

   http://java.sun.com/jsp/jstl/core

   标签库url自己指定

   要访问jstl标签库的标签 必须通过前缀名进行访问，前缀名是任意名字 自定义的

3. 在jsp页面中使用jstl标签



## 常用标签的使用

### 核心标签

if

```jsp
<c:if test="${pageScope.gender=='male'}"/>男</c:if>
```

forEach

会把数组或者集合里面的东西都放在一个域对象里面

```jsp
<c:forEach items="${数组或者集合}" var="域对象的属性名">
    ${pageScope.name}
<!--循环体-->
</c:forEach>
```



### 格式化标签 fmt

日期格式化标签

```jsp
<fmt :formateDate value="${日期对象}" pattern="yyyyMMddHHss" ></fmt>
```



### 函数标签 fn

主要是处理字符串的                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                

