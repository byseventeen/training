# 课程目标

1.  Number对象
2.  Array对象
3.  Math对象
4.  prototype原型
5.  BOM编程
6.  Js事件

---

# 一、Js对象

## 1.1 Number对象

Number对象代表一个数值。

```javascript
new Number(值)
Number(值)
```

例如：

```javascript
<script>
    var a = new Number(3.1415666);
    // 把Number转换成字符串，指定保留小数位数
    a = a.toFixed(2)
    document.write("a = " + a + "<br/>");

    var b = Number(10);
    // 把Number转换成二进制字符串
    b = b.toString(2);
    document.write("b = " + b + "<br/>");
</script>
```

## 1.2 Array对象

Array代表一个数组对象。

创建数组的方式：

```javascript
方式一：直接使用方括号。例如：var arr = [10, 20, 30];

方式二：使用new方式创建数组。
new Array();
new Array(值1, 值2, ...);
```

Array对象属性：

length：返回数组元素个数。

Array对象的常用方法：

| 方法名    | 作用                                               |
| --------- | -------------------------------------------------- |
| reverse() | 翻转数组                                           |
| shift()   | 删除并返回数组的第一个元素                         |
| pop()     | 删除并返回数组的最后一个元素                       |
| push()    | 向数组的末尾添加一个新元素，并返回新数组的长度     |
| sort()    | 对数组进行排序，默认升序                           |
| join()    | 把数组中所有元素放入到一个字符串中，可以指定分隔符 |
| splice()  | 删除元素，然后可以在删除位置添加新                 |

代码：

```javascript
<script>
    // 定义了一个数组
    var arr = new Array("java", "android", "go", "python", "spring");
    document.write("数组的长度：" + arr.length + "<br/>");

    // 翻转数组
    //arr.reverse();
    //document.write(arr);



    // 删除元素
    document.write("删除最后一个元素：" + arr.pop() + "<br/>");
    document.write("删除第一个元素：" + arr.shift() + "<br/>");

    document.write("添加元素后数组的长度：" + arr.push("php") + "<br/>");

    // 把数组转换成字符串
    var str = arr.join(" ");
    document.write(str + "<br/>");

    //			var arr2 = new Array("c", "a", "z", "h", "f", "b");
    var arr2 = new Array(20, 10, 4, 11, 8);
    //			arr2.sort();
    // 可以在sort函数中传入一个function
    // 如果函数返回0，代表a等于b
    // 如果函数返回值小于0，代表a小于b
    // 如果函数返回值大于0，代表a大于b
    arr2.sort(function(a, b) {
    //return a - b;
    return b - a;
    });
    document.write(arr2);
</script>
```

## 1.3 Math对象

Math对象提供一些简化数学运算的方法。

| 方法名       | 功能                   |
| ------------ | ---------------------- |
| abs()        | 绝对值                 |
| ceil()       | 向上取整               |
| floor()      | 向下取整               |
| random()     | 生成[0, 1)之间的随机数 |
| round()      | 四舍五入               |
| max()和min() | 求最大值               |

代码：

```javascript
<script>
    var pi = Math.PI;
    document.write("求绝对值：" + Math.abs(-10) + "<br/>");
    document.write("向上取整：" + Math.ceil(pi) + "<br/>");
    document.write("向下取整：" + Math.floor(pi) + "<br/>");
    document.write("生成随机数：" + Math.random() + "<br/>");
    document.write("四舍五入：" + Math.round(pi) + "<br/>");


    // 生成 0 ~ 9 之间的任意整数
    var num = Math.floor(Math.random() * 10)
    document.write("生成0~9之间的任意整数：" + num + "<br/>");

    document.write("返回最大值：" + Math.max(10, 5) + "<br/>");
    document.write("返回最小值：" + Math.min(10, 5) + "<br/>");
</script>
```



# 二、prototype原型

Object对象中的一个属性，所有对象都可以通过prototype属性扩展功能。

例如：

第一步：创建一个js文件，然后在文件中对Date对象添加一个formatDate方法。

```javascript
// 给Date对象扩展了一个formatDate方法
Date.prototype.formatDate = function() {
	// this代表该方法的调用者对象
	var year = this.getFullYear();
	var month = this.getMonth() + 1;
	var date = this.getDate();
	var hour = this.getHours();
	var min = this.getMinutes();
	var second = this.getSeconds();
	
	var dateStr = year + "年" + month + "月" + date + "日 " + hour + "时" + min + "分" + second + "秒";
	
	return dateStr;
}
```

第二步：在html文件中引入js文件。

```javascript
<script type="text/javascript" src="js/date.js"></script>
```

第三步：测试。

```javascript
var dateStr = d.formatDate()
document.write(dateStr);
```



# 三、BOM编程

BOM: Browser Object Model 浏览器对象模型。

Window：代表当前窗口，window也是一个全局的对象。



## 3.1 Window对象

Window对象是一个顶级对象，包含其他BOM对象。它提供了获取其他BOM对象的属性。例如：

window.location：代表地址栏。

window.history：代表前进后退按钮。

window.navigator：代表浏览器。

window.screen：代表当前屏幕。

window.document：代表当前页面。

Window对象也提供一些全局函数：

| 函数名            | 作用                                           |
| ----------------- | ---------------------------------------------- |
| alert()           | 弹出提示框                                     |
| close()           | 关闭窗口                                       |
| open()            | 打开窗口                                       |
| confirm()         | 弹出一个确认框                                 |
| setInterval()     | 定时器，指定多少毫秒重复执行某个函数           |
| setTimeout()      | 定时器，指定多少毫秒后执行某个函数，只执行一次 |
| clearInterval(id) | 取消定时器                                     |
| clearTimeout(id)  | 取消定时器                                     |

（1）open：打开一个窗口。

```javascript
命令格式：
window.open(url, name, features, replace)

例如：
window.open("http://www.baidu.com", "测试", "width=500,height=300,left=100,top=50,scrollbars=no");
```

其中，features（特征）包含以下内容：

| hannelmode=yes\|no\|1\|0  | 是否使用剧院模式显示窗口。默认为 no。                        |
| ------------------------- | ------------------------------------------------------------ |
| directories=yes\|no\|1\|0 | 是否添加目录按钮。默认为 yes。                               |
| fullscreen=yes\|no\|1\|0  | 是否使用全屏模式显示浏览器。默认是 no。处于全屏模式的窗口必须同时处于剧院模式。 |
| height=pixels             | 窗口文档显示区的高度。以像素计。                             |
| left=pixels               | 窗口的 x 坐标。以像素计。                                    |
| location=yes\|no\|1\|0    | 是否显示地址字段。默认是 yes。                               |
| menubar=yes\|no\|1\|0     | 是否显示菜单栏。默认是 yes。                                 |
| resizable=yes\|no\|1\|0   | 窗口是否可调节尺寸。默认是 yes。                             |
| scrollbars=yes\|no\|1\|0  | 是否显示滚动条。默认是 yes。                                 |
| status=yes\|no\|1\|0      | 是否添加状态栏。默认是 yes。                                 |
| titlebar=yes\|no\|1\|0    | 是否显示标题栏。默认是 yes。                                 |
| toolbar=yes\|no\|1\|0     | 是否显示浏览器的工具栏。默认是 yes。                         |
| top=pixels                | 窗口的 y 坐标。                                              |
| width=pixels              | 窗口的文档显示区的宽度。以像素计。                           |

注意：某些特征并不是所有浏览器都支持。比如scrollbars。

（2）confirm：弹出一个确认框。如果点击确认，该方法返回true；如果点击取消，返回false。

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script>
			function delUser() {
				var b = window.confirm("确认要删除吗？");
				if (b) {
					alert("删除用户....");
				}
			}
		</script>
	</head>
	<body>
		<button onclick="delUser()">删除用户</button>
	</body>
</html>
```

（2）定时器方法

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript" src="js/date.js" ></script>
		<script>			
			// 显示系统时间在页面
			function showTime() {
				var d = new Date();
				d = d.formatDate();
				
				// 把时间显示在aa标签。
				document.getElementById("aa").innerHTML = d;
			}
			
			var taskId;
			
			// 启动定时器
			function start() {
				// 每隔1s调用showTime函数一次
				taskId = window.setInterval("showTime()", 1000);
			}
			
			// 关闭定时器
			function stop() {
				window.clearInterval(taskId);
			}
		</script>
	</head>
	<body>		
		<button onclick="start()">启动定时器</button>
		<button onclick="stop()">关闭定时器</button>
		
		<!-- 块标签 
			每一个标签都有id属性，一个html页面id属性不能够重复。
		-->
		<div id="aa"></div>
		
		<script>
			//showTime();
		</script>
	</body>
</html>

```

## 3.2 Location对象

location代表浏览器的地址栏。

location对象属性：

href：设置或返回地址栏中地址。

location对象的方法：

reload()：刷新页面。

## 3.3 History对象

History代表浏览器的历史。

back()：加载浏览器历史的前一个URL；

forward()：加载浏览器历史的下一个URL；

go(n)：加载浏览器历史的第n个URL；

​	比如：1代表加载下一个URL；-1代表加载上一个URL。



# 四、事件

所有html元素都可以绑定事件。事件以on开头。例如：onclick、onload、onSubmit等等。

事件的三要素：

- 事件源：事件发生的地方。
- 事件监听器：监听事件的发生。例如：onclick、onkeydown、onload等等。
- 处理函数：响应事件的函数。



一个html元素可以绑定多个事件监听器。

4.1 事件类型

鼠标事件：

| 事件名      | 说明         |
| ----------- | ------------ |
| onclick     | 鼠标单击     |
| ondbclick   | 鼠标双击     |
| onmousedown | 鼠标按下事件 |
| onmouseup   | 鼠标松开事件 |
| onmousemove | 鼠标划过事件 |
| onmouseout  | 鼠标移出事件 |

焦点事件：

| 事件名   | 说明                     |
| -------- | ------------------------ |
| onfocus  | 元素获得焦点后触发       |
| onblur   | 元素失去焦点后触发       |
| onchange | 元素内容发生变化时候触发 |

其他事件：

| 事件名   | 说明                           |
| -------- | ------------------------------ |
| onload   | 页面加载完成后触发             |
| onsubmit | 提交表单前触发，例如：客户验证 |

onload事件一般绑定在body标签中。

onsubmit事件一般绑定在form标签中。













