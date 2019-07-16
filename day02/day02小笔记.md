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
        function format(date){
            var year=date.getFullYear();
            var month=date.getMonth();
            var day=date.getDate();
            return "<br>"+ year + "年" + (month+1) + "月" + day +"日<br>";
        }

        var date=new Date();
        date.setDate(14);
        document.write(format(date));

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
          );     return fmt;
    };

```

 

```javascript
        //dayOfWeek返回指定日期是星期几
         function dayOfWeek(){
            var weekDay = new Array
                ("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
                return weekDay[date.getDay()];
         }

         var date=new Date();
         document.write(dayOfWeek(date));
```

