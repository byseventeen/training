### java正则

一个字符匹配一位

Java提供了Pattern类，该类代表正则表达式对象。



第一步：定义规则

第二步：调用matches（）方法。



#### 正则表达式的规则

字符类：

[abc] 

[a-c]	代表匹配abc其中任意一个字符

[^abc]	反匹配



预定义字符类：

\d 代表匹配0-9之间任意一个数字。

\D	非数字

\w	(word)代表匹配[a-zA-Z_0-9]中任意一个字符。

\W 	匹配和上面相反的字符

\s 代表匹配一个空格。

\S 代表匹配一个非空格。

. 代表匹配任意一个字符。



边界词：

^代表行开头

$代表行的结尾

\b代表单词的边界



数量词：

？代表匹配0或者1次

+代表匹配一次或者多次

*代表匹配0或者多次

{n} 匹配n次

{n,}匹配至少n次

{n,m}匹配至少n次，不超过m次。



#### 正则匹配内容



### JQuery

#### JQuery对象和DOM对象的区别

```javascript
var userNameNode = document.getElementById("userName");

var userNameObj = $(userNameNode);
//相互转换 Query对象转换成dom对象
var inputNode = userNameObj.get()[0];
```



#### 选择器

定位页面元素位置



##### 普通选择器

1. ID选择器： $("id")

2. 标签选择器：通过标签名查找元素。 $("tagname")

3. 组合选择器：同时把多个选择器的结果组合在一起，返回一个包含所有结果的数组。 $("id,tagname")

4. 后代选择器：可以获取指定元素的后代元素。 

   $("id  div")	获取selector1下的所有后代selector2 一直往下走

   $("selector1 > selector2")：获取selector1下所有的直接后代selector2。会把子节点兄弟节点页拿出来

##### 筛选条件

- $("selector:first")：获取第一个selector元素； 

- $("selector:last")：获取最后一个selector元素； 

- $("selector:eq(i)")：根据索引获取selector元素； 

- $("selector:checked")：查询有 checked 属性的selector元素； 

- $("selector:selected")：查询有 selected 属性的selector元素； 

- $("selector:hidden"); 查询隐藏的selector元素。

  

  ##### 

  eq(i): 根据索引获取元素 

  first(): 取第一个 

  last(): 取最后一个 

  prev()：获取上一个同辈的元素； 

  next()：获取下一个同辈的原素； 

  parent()：获取父元素； 

  children()：获取所有子元素的集合；

  















