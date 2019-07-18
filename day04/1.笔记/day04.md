# 课程目标

1.  DOM编程
2.  Js正则表达式

---

# 一、DOM编程

## 1.1 dom介绍

DOM(Document Object Model) 文档对象模型。当浏览器解析html页面的时候，html解析器会对页面中每一个元素（标签、属性、文本、注释等）创建一个对象来描述，这些对象称为“DOM对象”。

Node节点：

​	标签节点：Element

​	属性节点：Attribute

​	文本节点：Text

​	注释节点：Comment

​	文档树：Document



文档树：浏览器在解析html的时候，html解析器会按照元素的顺序在内存中生成一颗文档树对象。我们在浏览器上看到的内容，实际上是文档树对象中元素的属性。所以，如果要访问html页面内容，可以通过元素对象的属性进行访问。所谓dom编程，就是通过文档树中元素对象的属性操作页面的内容。



## 1.2 节点的访问

document.getElementById()：通过元素节点的id属性查询元素节点，该方法返回一个Element对象。

document.getElementsByTagName()：通过元素名查询元素节点，该方法返回包含指定元素的数组。

document.getElementsByName()：通过元素的name属性查找节点，该方法返回包含指定元素的数组。

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<input type="text" id="userName" value="jacky"/><br/>
		<input type="password" id="userPass" value=""/><br/>
		<input type="checkbox" name="hobby"/>吃
		<input type="checkbox" name="hobby"/>喝
		<input type="checkbox" name="hobby"/>玩<br/>
		
		<script>
			/*// 注意：获取元素对象之前必须要保证该元素标签被加载完。
			var userNameNode = document.getElementById("userName");
			//alert(userNameNode);
			document.write(userNameNode.nodeName + "<br/>");
			document.write(userNameNode.nodeValue + "<br/>");
			document.write(userNameNode.nodeType + "<br/>");
			
			// 根据标签名获取元素
			var inputNodes = document.getElementsByTagName("input");
			for (var i = 0; i < inputNodes.length; i++) {
				var inputNode = inputNodes[i];
				document.write(inputNode.nodeName + "<br/>");
			}*/
			
			var hobbyNodes = document.getElementsByName("hobby");
			for (var i = 0; i < hobbyNodes.length; i++) {
				var inputNode = hobbyNodes[i];
				document.write(inputNode.nodeName + "<br/>");
			}
		</script>
	</body>
</html>

```

通过关系查找节点：

父子关系：

parentNode：获取当前节点父节点；

firstChild：获取当前节点的第一个子节点；

lastChild：获取当前节点的最后一个子节点；

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body><form>
			<input id="userName"/>
			<input type="submit"/>
		</form>
		<script>
			var userNameNode = document.getElementById("userName");
			// 获取父节点
			var formNode = userNameNode.parentNode;
			//document.write(formNode.nodeName + "<br/>");
			
			var bodyNode = document.getElementsByTagName("body")[0];
			// 获取第一个子节点
			var formNode = bodyNode.firstChild;
			document.write(formNode.nodeName + "<br/>");
			// 获取最后一个子节点
			var scriptNode = bodyNode.lastChild;
			document.write(scriptNode.nodeName + "<br/>");
		</script></body>
</html>

```

兄弟关系：

nextSibling：获取下一个兄弟节点；

previousSibling：获取上一个兄弟节点；

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<h3>用户注册</h3>
		<form>
			<input id="userName"/>
			<input type="submit"/>
		</form>
		<script>
			// ------------- 兄弟关系查找节点 ---------------
			var formNode = document.getElementsByTagName("form")[0];
			// 获取上一级的兄弟节点
			var h3Node = formNode.previousSibling.previousSibling;
			document.write(h3Node.nodeName + "<br/>");
			
			var scriptNode = formNode.nextSibling.nextSibling;
			document.write(scriptNode.nodeName + "<br/>");
		</script></body>
</html>
```

document也提供获取一些对象的属性：

body：获取body对象；

forms：获取页面中所有的form元素，返回一个数组；

## 1.3 节点操作

```javascript
# 创建元素节点，该方法返回一个Element对象
document.createElement("标签名")

# 追加节点
Element对象.appendChild(newElement)

# 在Element元素中，往childElement前面插入newElement
Element对象.insertBefore(newElement, childElement)

# 删除Element元素下的child元素
Element对象.removeChild(child)
```

例如：

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<ul><li>宫保鸡丁</li>
			<li>海南鸡</li>
			<li>东波肉</li></ul>
		<script>
			// 获取ul节点
			var ulNode = document.getElementsByTagName("ul")[0];
			// 获取最后一个li节点
			var lastLiNode = ulNode.lastChild;
			// 构建新的li
			var newLiNode = document.createElement("li");
			newLiNode.textContent = "口水鸡";
			// 在最后li节点前插入新节点
			ulNode.insertAfter(newLiNode, lastLiNode);
		</script>
	</body>
</html>
```

## 1.4  设置或获取元素节点的内容

Element对象提供一些属性：

innerHTML : 设置或获取元素节点的文本；例如：<span></span>

textContext ：设置或获取元素节点的文本；

value：设置或获取元素的value属性值；

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<div>
			<p>hello</p>	
			<input type="text" id="userName" />
		</div>
		<script>
			
			var divNode = document.getElementsByTagName("div")[0];
			//alert(divNode.innerHTML);
			//alert(divNode.textContent);
			//divNode.innerHTML = "<h1>world</h1>";
			
			document.getElementById("userName").value = "jacky";
		</script>
	</body>
</html>
```



# 二、正则表达式

## 2.1 创建正则表达式

创建正则表达式：

方式一：/正则表达式/模式

方式二：new RegExp("正则表达式", "模式")

i模式：如果指定了i模式，那么就忽略大小写。

g模式：如果指定了g模式，如果使用正则表达式查找字符串的内容，它会进行全文查找。



## 2.2 正则表达式的应用场景

### 2.2.1 客户端验证

test(): 匹配指定内容是否符合正则表达式。如果匹配就返回true，否则返回false。

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script>
			function checkUserName() {
				// 获取用户输入
				var userName = document.getElementById("userName").value;
				// 验证用户输入（非空、长度不能小于5位、必须是英文或数字组成）
				var regex = /[a-z0-9]{5,}/i;
				// 匹配
				var isValid = regex.test(userName);
				if (!isValid) {
					document.getElementById("userNameMsg").textContent = "X 用户名格式不正确";
				} else {
					document.getElementById("userNameMsg").textContent = "√";
				}
			}
			
			
			checkUserName();
		</script>
	</head>
	<body>
		<input type="text" id="userName" onblur="checkUserName()"/>
		<span id="userNameMsg"></span>
	</body>
</html>

```

### 2.2.2 查找字符串内容

exec：查找字符串符合正则表达式的内容。

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<script>
			var data = "请联系我13232345232请联系我13232345243请联系我132323245232请联系我13232341232请联系我12323234523请联系我15932345232";
			// 定义规则
			var regex = /1\d{10}/g;
			// 根据正则表达式查找指定字符串的内容
			var phone = regex.exec(data)
			while (phone != null) {
				alert(phone);
				phone = regex.exec(data)
			}
		</script>
	</body>
</html>
```



### 2.2.3 替换字符串的内容

字符串replace方法。

replace(regexp，new)：把符合正则表达式的内容全部替换成new的内容。

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script>
			var blog = "请联系我13232345232请联系我13232345243请联系我132323245232请联系我13232341232请联系我12323234523请联系我15932345232";
			// 定义规则
			var regexp = /1\d{10}/g;
			// 替换
			blog = blog.replace(regexp, "##########");
			alert(blog);
		</script>
	</head>
	<body>
	</body>
</html>

```























