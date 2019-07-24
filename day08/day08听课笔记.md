## Request对象详细信息

### 获取请求行



### 获取请求头



### 获取请求参数



### 实现请求转发

创建一个请求分发器对象

调用forward（res,rep）方法执行请求转发

当请求已经被转发出去的时候，后面不会再执行response.getWriter().write()。



重定向

![1563930849664](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1563930849664.png)



> #### 请求转发和重定向的区别？

两者都能实现页面的跳转。

请求转发的话地址栏不会发生变化而后者会变。

请求转发服务器只发出一次请求，重定向则是两次。

请求转发不需要写上下web路径，因为是内部转发，在本项目内去转发

请求转发可以通过request对象实现数据传递，后者不能

#### 实现不同servlet的数据传递

域对象

pagecontext

request

session

servletcontext

域对象方法 

setAttribute(objecy,value)



![1563934225122](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1563934225122.png)



获取context对象

往servlet中存储数据



## ServletConfig对象

作用：获取Servlet参数

> 解决request中文乱码问题？

![1563934359369](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1563934359369.png)

配置一个配置文件，不直接写死在源代码中 

在web.xml文件中配置servlet参数

![1563934883507](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1563934883507.png)



## 会话技术

### cookie

保存在浏览器中的一个小文件，当浏览器发出请求的时候，也会把小文件内容一并发送到服务器。

#### 添加数据

创建cookie对象

设置cookie有效时间setMaxAge（int second） 默认为浏览器关闭时间

设置cookie有效路径（可选）

限制cookie在什么路径下面使用

输出cookie到客户端

response.addCookie(cookie);

#### 获取cookie数据

调用request.getCookie() 返回客户端返回的所有cookie  数组来的

遍历cookie数组

调用cookie方法获取cookie数据

#### 修改数据

#### 删除数据

设置一个同名的，把有效时间设为0。如果创建的时候有有效路径 删除的时候也要有。



### session

session是服务端的会话技术