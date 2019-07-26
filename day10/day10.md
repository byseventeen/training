# 课程目标

1.  JSTL标签的基本使用
2.  自定义标签
3.  自定义分页标签

---

#  一、JSTL标签库

jstl：JSP标准标签库。主要提供一些标签简化JSP代码。这些标签都有特定的功能。



## 1.1  使用jstl标签

- 第一步：引入jstl的jar包；

---

jstl-1.2.jar

standard-1.1.2.jar

---

- 第二步：在JSP页面使用taglib指令引入标签库文件；

核心标签库：http://java.sun.com/jsp/jstl/core   推荐前缀名：c

格式化标签库：http://java.sun.com/jsp/jstl/fmt 推荐前缀名：fmt

函数标签库：http://java.sun.com/jsp/jstl/functions 推荐前缀名：fn

```jsp
<%@taglib uri="标签库的url" prefix="前缀名" %>
```

标签库的url自己指定的，可以是任意的url路径。

如果要访问jstl标签库的标签，必须要通过“前缀名”进行访问。而前缀名可以是任意名字。

---

- 第三步：在JSP页面中使用jstl标签。

通过查看jstl.jar/META-INF/xxx.tld，tld文件就是jstl标签定义文件。每一个标签库都有一个标签定义文件。比如核心标签库有一个c.tld文件。

```xml
<taglib xmlns="http://java.sun.com/xml/ns/javaee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-jsptaglibrary_2_1.xsd"
    version="2.1">
  <!-- 标签库的介绍 -->
  <description>JSTL 1.1 core library</description>
  <!-- 标签库的名字 -->
  <display-name>JSTL core</display-name>
  <!-- 标签库的版本号 -->
  <tlib-version>1.1</tlib-version>
  <!-- 标签库推荐使用的前缀名 -->
  <short-name>c</short-name>
  <!-- 标签库的uri地址 -->
  <uri>http://java.sun.com/jsp/jstl/core</uri>

  <!-- 一个tag用来定义一个标签 -->
  <tag>
    <!-- 标签的介绍 【可选】-->
    <description>
        Catches any Throwable that occurs in its body and optionally
        exposes it.
    </description>
    <!-- 标签名 【必选】 -->
    <name>catch</name>
    <!-- 标签处理类【必选】 -->
    <tag-class>org.apache.taglibs.standard.tag.common.core.CatchTag</tag-class>
    <!-- 标签体类型【必选】 -->
    <body-content>JSP</body-content>
    <!-- 一个attribute定义一个标签的属性【可选】 -->
    <attribute>
        <!-- 属性的介绍 【可选】-->
        <description>
Name of the exported scoped variable for the
exception thrown from a nested action. The type of the
scoped variable is the type of the exception thrown.
        </description>
        <!-- 属性名【必选】 -->
        <name>var</name>
        <!-- 是否必须的【必选】 -->
        <required>false</required>
        <!-- 是否支持表达式【必选】 -->
        <rtexprvalue>false</rtexprvalue>
    </attribute>
  </tag>
</taglib>
```



## 1.2 常用标签的使用

### 1.2.1 核心标签

- if标签：条件判断

```jsp
<c:if test="${逻辑判断条件}"/>
	body...
</c:if>
```

- forEach标签：循环数组或集合

```
<c:forEach items="${数组或集合}" var="属性名">
	${pageCope.属性名}
</c:forEach>
```

forEach标签遍历数组或集合的时候，它会把遍历出来的每一个元素都保存在pageContext域对象的属性中。属性名就是使用var来指定的。

注意：遍历Map集合的时候，遍历出来的每一个元素是一个Entry对象。

```jsp
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
```

### 1.2.2 格式化标签

日期格式化标签：

```
<fmt:formatDate value="${日期对象}" pattern="日期模式"/>
```

日期模式与SimpleDateFormat对象所使用模式一样。

```jsp
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 引入jstl的格式化标签库 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% 
	Date d = new Date();
	pageContext.setAttribute("currentDate", d);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	当前时间：<fmt:formatDate value="${currentDate}" pattern="yyyy年MM月dd日 HH时mm分ss秒"/>	
</body>
</html>
```

### 1.2.3 函数标签

定义格式：

```
${fn:函数名(参数列表)}
```

函数可以在jstl文件下的/META-INF/fn.tld文件中查看。下面是该文件内容：

```
<?xml version="1.0" encoding="UTF-8" ?>

<taglib xmlns="http://java.sun.com/xml/ns/j2ee"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/web-jsptaglibrary_2_0.xsd"
  version="2.0">
    
  <description>JSTL 1.1 functions library</description>
  <display-name>JSTL functions</display-name>
  <tlib-version>1.1</tlib-version>
  <short-name>fn</short-name>
  <uri>http://java.sun.com/jsp/jstl/functions</uri>

  <function>
    <description>
      Tests if an input string contains the specified substring.
    </description>
    <name>contains</name>
    <function-class>org.apache.taglibs.standard.functions.Functions</function-class>
    <function-signature>boolean contains(java.lang.String, java.lang.String)</function-signature>
    <example>
      &lt;c:if test="${fn:contains(name, searchString)}">
    </example>
  </function>

  <function>
    <description>
      Tests if an input string contains the specified substring in a case insensitive way.
    </description>
    <name>containsIgnoreCase</name>
    <function-class>org.apache.taglibs.standard.functions.Functions</function-class>
    <function-signature>boolean containsIgnoreCase(java.lang.String, java.lang.String)</function-signature>
    <example>
      &lt;c:if test="${fn:containsIgnoreCase(name, searchString)}">
    </example>
  </function>

  <function>
    <description>
      Tests if an input string ends with the specified suffix.
    </description>
    <name>endsWith</name>
    <function-class>org.apache.taglibs.standard.functions.Functions</function-class>
    <function-signature>boolean endsWith(java.lang.String, java.lang.String)</function-signature>
    <example>
      &lt;c:if test="${fn:endsWith(filename, ".txt")}">
    </example>
  </function>

  <function>
    <description>
      Escapes characters that could be interpreted as XML markup.
    </description>
    <name>escapeXml</name>
    <function-class>org.apache.taglibs.standard.functions.Functions</function-class>
    <function-signature>java.lang.String escapeXml(java.lang.String)</function-signature>
    <example>
      ${fn:escapeXml(param:info)}
    </example>
  </function>

  <function>
    <description>
      Returns the index withing a string of the first occurrence of a specified substring.
    </description>
    <name>indexOf</name>
    <function-class>org.apache.taglibs.standard.functions.Functions</function-class>
    <function-signature>int indexOf(java.lang.String, java.lang.String)</function-signature>
    <example>
      ${fn:indexOf(name, "-")}
    </example>
  </function>

  <function>
    <description>
      Joins all elements of an array into a string.
    </description>
    <name>join</name>
    <function-class>org.apache.taglibs.standard.functions.Functions</function-class>
    <function-signature>java.lang.String join(java.lang.String[], java.lang.String)</function-signature>
    <example>
      ${fn:join(array, ";")}
    </example>
  </function>

  <function>
    <description>
      Returns the number of items in a collection, or the number of characters in a string.
    </description>
    <name>length</name>
    <function-class>org.apache.taglibs.standard.functions.Functions</function-class>
    <function-signature>int length(java.lang.Object)</function-signature>
    <example>
      You have ${fn:length(shoppingCart.products)} in your shopping cart.
    </example>
  </function>

  <function>
    <description>
      Returns a string resulting from replacing in an input string all occurrences
      of a "before" string into an "after" substring.
    </description>
    <name>replace</name>
    <function-class>org.apache.taglibs.standard.functions.Functions</function-class>
    <function-signature>java.lang.String replace(java.lang.String, java.lang.String, java.lang.String)</function-signature>
    <example>
      ${fn:replace(text, "-", "&#149;")}
    </example>
  </function>

  <function>
    <description>
      Splits a string into an array of substrings.
    </description>
    <name>split</name>
    <function-class>org.apache.taglibs.standard.functions.Functions</function-class>
    <function-signature>java.lang.String[] split(java.lang.String, java.lang.String)</function-signature>
    <example>
      ${fn:split(customerNames, ";")}
    </example>
  </function>

  <function>
    <description>
      Tests if an input string starts with the specified prefix.
    </description>
    <name>startsWith</name>
    <function-class>org.apache.taglibs.standard.functions.Functions</function-class>
    <function-signature>boolean startsWith(java.lang.String, java.lang.String)</function-signature>
    <example>
      &lt;c:if test="${fn:startsWith(product.id, "100-")}">
    </example>
  </function>

  <function>
    <description>
      Returns a subset of a string.
    </description>
    <name>substring</name>
    <function-class>org.apache.taglibs.standard.functions.Functions</function-class>
    <function-signature>java.lang.String substring(java.lang.String, int, int)</function-signature>
    <example>
      P.O. Box: ${fn:substring(zip, 6, -1)}
    </example>
  </function>

  <function>
    <description>
      Returns a subset of a string following a specific substring.
    </description>
    <name>substringAfter</name>
    <function-class>org.apache.taglibs.standard.functions.Functions</function-class>
    <function-signature>java.lang.String substringAfter(java.lang.String, java.lang.String)</function-signature>
    <example>
      P.O. Box: ${fn:substringAfter(zip, "-")}
    </example>
  </function>

  <function>
    <description>
      Returns a subset of a string before a specific substring.
    </description>
    <name>substringBefore</name>
    <function-class>org.apache.taglibs.standard.functions.Functions</function-class>
    <function-signature>java.lang.String substringBefore(java.lang.String, java.lang.String)</function-signature>
    <example>
      Zip (without P.O. Box): ${fn:substringBefore(zip, "-")}
    </example>
  </function>

  <function>
    <description>
      Converts all of the characters of a string to lower case.
    </description>
    <name>toLowerCase</name>
    <function-class>org.apache.taglibs.standard.functions.Functions</function-class>
    <function-signature>java.lang.String toLowerCase(java.lang.String)</function-signature>
    <example>
      Product name: ${fn.toLowerCase(product.name)}
    </example>
  </function>

  <function>
    <description>
      Converts all of the characters of a string to upper case.
    </description>
    <name>toUpperCase</name>
    <function-class>org.apache.taglibs.standard.functions.Functions</function-class>
    <function-signature>java.lang.String toUpperCase(java.lang.String)</function-signature>
    <example>
      Product name: ${fn.UpperCase(product.name)}
    </example>
  </function>

  <function>
    <description>
      Removes white spaces from both ends of a string.
    </description>
    <name>trim</name>
    <function-class>org.apache.taglibs.standard.functions.Functions</function-class>
    <function-signature>java.lang.String trim(java.lang.String)</function-signature>
    <example>
      Name: ${fn.trim(name)}
    </example>  
  </function>

</taglib>

```

下面列举了一些常用函数：

- toLowerCase()：把字符串全部转换小写；
- toUpperCase()：把字符串全部转换大写；
- ength()：获取字符串的长度；
- substring()：截取字符串； 

```jsp
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<% 
	String name = "ChinaSoft";
	pageContext.setAttribute("name", name);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 把name属性的内容全部转换成小写 -->
	${fn:toLowerCase(name)}	<br/>
	<!-- 把name属性的内容全部转换成大写 -->
	${fn:toUpperCase(name)}	<br/>
	<!-- 获取内容的长度 -->
	${fn:length(name)}	<br/>
	<!-- 截取字符串内容，第一个参数就是要截取的字符串，第二个参数代表开始截取的位置，第三个参数代表最后截取的位置 -->
	${fn:substring(name, 1, 5)}	<br/>
</body>
</html>
```















