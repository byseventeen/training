- js基本语法
- BOM编程
- DOM编程



##### 1.引入方式

```javascript
<script> </script>
```

```javascript
<script type="javascript" src="路径"> </script>
```

undefine和任何数字相加 都是NAN

object js标准参考对象

##### work

```javascript
function getMax(a,b,c){
    var max;
    max=a>b?a:b;
    return max>c?max:c;  
}
var max=getMax(4,5,6);
console.log(max)
```

```javascript
function getMonthDays(year,month){    
	month = parseInt(month,10); 
    if(month>=1&&month<=12){
       var temp = new Date(year,month,0); 
	   return temp.getDate(); 
    }
    else{
        doucument.write("非法输入！请输入正确月份！")
    } 
}
```

```javascript
var d= new Date();


function formatDate(date){
    var yy=d.getFullYear();
	var mm=d.getMonth()+1;
	var dd=d.getDate(); 
    return yy+"年"+mm+"月"+dd+"日";
}


```

```javascript
 //日期格式化方法  
 //在原型上挂对象
    Date.prototype.Format = function(fmt) {
      var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate() //日
      };
      if (/(y+)/.test(fmt))
        fmt = fmt.replace(
          RegExp.$1,
          (this.getFullYear() + "").substr(4 - RegExp.$1.length)
        );
      for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
          fmt = fmt.replace(
            RegExp.$1,
            RegExp.$1.length == 1
              ? o[k]
              : ("00" + o[k]).substr(("" + o[k]).length)
          );
      return fmt;
    };

```

```javascript
 //日期格式化方法
    Date.prototype.Format = function(fmt) {
 
      var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        S: this.getMilliseconds() //毫秒
      };
      if (/(y+)/.test(fmt))
        fmt = fmt.replace(
          RegExp.$1,
          (this.getFullYear() + "").substr(4 - RegExp.$1.length)
        );
      for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
          fmt = fmt.replace(
            RegExp.$1,
            RegExp.$1.length == 1
              ? o[k]
              : ("00" + o[k]).substr(("" + o[k]).length)
          );
      return fmt;
    };

```

