### JavaScript对象

#### prototype原型

Object对象中的一个属性，所有对象都可以通过prototype属性扩展功能。

使用对象构造器无法添加新的属性，若需要添加，则必须要将它添加到构造器函数中

所有的JavaScript对象都是从原型中继承属性和方法。Object。prototype位于原型链的顶端。



为对象构造器添加新的属性：

```javascript
funcion Person(first,last){
	this.firstName=first;
    this.lastName=last;
}
Person.prototype.language="English";
```



为对象构造器添加新的方法：

name方法

```javascript
Person.prototype.name = function() {
    return this.firstName + " " + this.lastName;
};
```



### BOM编程

BOM: Browser Object Model 浏览器对象模型。

Window：代表当前窗口，window也是一个全局的对象。

#### Window对象

所有浏览器均支持。

window对象 属性为全局变量（包括HTML DOM 的document 对象），方法是全局函数。



- window.open() - 打开新窗口

- window.close() - 关闭当前窗口

- window.moveTo() -移动当前窗口

- window.resizeTo() -重新调整当前窗口

- window.location：代表地址栏。

- window.history：代表前进后退按钮。

- window.navigator：代表浏览器。

- window.screen：代表当前屏幕。

- window.document：代表当前页面。

  

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



#### Location对象

location代表浏览器的地址栏。



location对象属性：

href：设置或返回地址栏中地址。

```javascript
<button onclick="location.href='http://baidu.com'">设置地址栏地址</button>
alert(location.href);
```



location对象的方法：

reload()：刷新页面。



#### History对象

History代表浏览器的历史。

back()：加载浏览器历史的前一个URL；

forward()：加载浏览器历史的下一个URL；

go(n)：加载浏览器历史的第n个URL；

​	比如：1代表加载下一个URL；-1代表加载上一个URL。

