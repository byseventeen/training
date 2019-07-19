# 课程目标

1.  Number对象
2.  Array对象
3.  Math对象
4.  prototype原型
5.  BOM编程

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
| pop       | 删除并返回数组的最后一个元素                       |
| push      | 向数组的末尾添加一个新元素，并返回新数组的长度     |
| sort      | 对数组进行排序，默认升序                           |
| join      | 把数组中所有元素放入到一个字符串中，可以指定分隔符 |
| splice    | 删除元素，然后可以在删除位置添加新                 |

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









