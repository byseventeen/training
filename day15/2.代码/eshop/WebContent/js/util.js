/*去掉2端空白*/
String.prototype.trim=function(){
        return this.replace(/(^\s*)|(\s*$)/g, "");
}

" sdf".trim();

/*去掉左边空白 */
String.prototype.ltrim=function(){
        return this.replace(/(^\s*)/g,"");
}
/*去掉右边空白 */
String.prototype.rtrim=function(){
        return this.replace(/(\s*$)/g,"");
}
/*设置class*/
function setClass(el, classname){
	el.className = classname;
}
/*判断是否包含指定class*/
function hasClass(ele, cls){
	return ele.className.match(new RegExp('(\\s|^)' + cls + '(\\s|$)'));
}

/*添加一个class*/
function addClass(ele, cls){
	if(!hasClass(ele, cls)){
		ele.className += " " + cls;
	}
}
/*删除一个class*/
function removeClass(ele, cls){
	if(hasClass(ele, cls)) {
		var reg = new RegExp('(\\s|^)' + cls + '(\\s|$)');
		ele.className = ele.className.replace(reg, ' ');
	}
}


/*全选*/
function checkAll(checkboxName){
	var objs = document.getElementsByName(checkboxName);
	for(i=0;i<objs.length;i++){
		 if(objs[i].type.toLowerCase() == "checkbox" ){
		 	objs[i].checked = true;
		 }
	}
}

/*取消选择*/
function cancelCheck(checkboxName){
	var objs = document.getElementsByName(checkboxName);
	for(i=0;i<objs.length;i++){
		 if(objs[i].type.toLowerCase() == "checkbox" ){
		 	objs[i].checked = false;
		 }
	}
}

/*反选*/
function reverseCheck(checkboxName){
	var objs = document.getElementsByName(checkboxName);
	for(i=0;i<objs.length;i++){
		 if(objs[i].type.toLowerCase() == "checkbox" ){
		 	if(objs[i].checked){
				objs[i].checked= false;
			}else{
				objs[i].checked = true;	
			}
		 }
	}
}

/*删除所选项*/
function deleteChecked(checkboxName,url){
	// 根据name属性查询checkbox元素
	var objs = document.getElementsByName(checkboxName);
	
	var param = '';
	// 遍历所有的checkbox
	for(i=0;i<objs.length;i++){
		 // 判断元素是否是checkbox类型
		 if(objs[i].type.toLowerCase() == "checkbox" ){
			// 判断checkbox元素的选中状态，如果true代表选中，false代表没有被选中
		 	if(objs[i].checked){
		 		// 参数格式：id=1&id=2&id=3&
				param += ('id='+objs[i].value+'&');
				
				
			}
		 }
	}
	
	if(param.length >0){
		// 如果点击确定，就返回true，否则返回false。
		var bool = window.confirm('您确定删除所选的项吗？');
		if(bool){
			//这里提交到删除的地方
			//alert(url+"?"+param);
			window.location.href=url+"&"+param;
		}
	}
}



/*兼容IE的getElementsByClassName(),node和tag两2上参数可选*/
var getElementsByClassName = function(searchClass,node,tag) {
        var classElements = new Array();
        if ( node == null )
                node = document;
        if ( tag == null )
                tag = '*';
        var els = node.getElementsByTagName(tag);
        var elsLen = els.length;
        var pattern = new RegExp("(^|\\s)"+searchClass+"(\\s|$)");
        for (var i = 0, j = 0; i < elsLen; i++) {
                if ( pattern.test(els[i].className) ) {
                        classElements[j] = els[i];
                        j++;
                }
        }
        return classElements;
}

/*隐藏，可以传入单个或者数组*/
function hide(o){
		if(o){
			if(o instanceof Array){
				for(var i=0;i<o.length;i++){
					o[i].style.display ="none";
				}
			}else{
					o.style.display = "none";
					
			}
		}
	}
/*显示，可以传入单个或者数组*/
function show(o){
	if(o){
			if(o instanceof Array){
				for(var i=0;i<o.length;i++){
					o[i].style.display ="block";
			}
		}else{
			o.style.display = "block";
		}
	}
}

/*- 跨浏览器的事件处理工具--*/
var EventUtil = {
    addHandler: function(element, type, handler) {
        if (element.addEventListener) {
            element.addEventListener(type, handler, false);
        } else if (element.attachEvent) {
            element.attachEvent("on" + type, handler);
        } else {
            element["on" + type] = handler;
        }
    },
    removeHandler: function(element, type, handler) {
        if (element.removeEventListener) {
            element.removeEventListener(type, handler, false);
        } else if (element.attachEvent) {
            element.detachEvent("on" + type, handler);
        } else {
            element["on" + type] = null;
        }
    },
    getEvent:function(event) {
        return event ? event: window.event;
    },
    getTarget: function(event) {
        return event.target || event.srcElement;
    },
    removeHandler: function(element, type, handler) {
        if (element.removeEventListener) {
            element.removeEventListener(type, handler, false);
        } else if (element.attachEvent) {
            element.detachEvent("on" + type, handler);
        } else {
            element["on" + type] = null;
        }
    }
};