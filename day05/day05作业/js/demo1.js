window.onload = function(){
	var second=5;
    var timer= setInterval(function(){
        if(second>0){
            document.getElementById("read").value=second;
            second--;
            document.getElementById("read").value="请仔细阅读协议"+second;
        }
        else{
        	document.getElementById("read").value="确定";
            document.getElementById("read").disabled=false;
            clearInterval();          
        }
    },1000)
}

function onmouseover1(){
	document.getElementById("hidediv").style.display="none";
	//document.getElementsByClassName("hidediv").style.display="none";
}
function onmouseout1(){
	document.getElementById("hidediv").style.display="block";
}

function checkname(){
	var username=document.getElementById("username").value;
	if(username.length>=3){
		document.getElementById("usernameMag").innerHTML="正确";
	}
	else if(username.length==0){
		document.getElementById("usernameMag").innerHTML="用户名不可为空";
	}
	else{
		document.getElementById("usernameMag").innerHTML="用户名格式错误";
	}
}

function checkpwd(){
	var pwd=document.getElementById("password").value;
	var regx=/[a-z0-9]{5,10}/i;
	var isValid = regx.test(pwd);
	if(!isValid){
		document.getElementById("pwdMsg").innerHTML="密码格式错误"
	}
	else{
		document.getElementById("pwdMsg").innerHTML="正确"
	}
}

function checkrepwd(){
	var pwd=document.getElementById("password").value;
	var repwd=document.getElementById("repassword").value;
	if(pwd==repwd){
		document.getElementById("repwdMsg").innerHTML="正确"
	}
	else if(repwd.length==0){
		document.getElementById("repwdMsg").innerHTML="用户名不可为空";
	}
	else{
		document.getElementById("repwdMsg").innerHTML="密码不一致"
	}
}

function checkphnum(){
	var checkphnum=document.getElementById("password").value;
	var regx=/^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\d{8}$/;
	var isValid = regx.test(checkphnum);
	if(!isValid){
		document.getElementById("phoneMsg").innerHTML="手机号码格式错误"
	}
	else if(checkphnum.length==0){
		document.getElementById("phoneMsg").innerHTML="手机号码不可为空";
	}
	else{
		document.getElementById("phoneMsg").innerHTML="正确"
	}	
}

function checkqqnum(){
	var checkqqnum=document.getElementById("qqnum").value;
	var regx=/^[1-9][0-9]{4,10}$/;
	var isValid = regx.test(checkqqnum);
	if(!isValid){
		document.getElementById("qqMsg").innerHTML="QQ号码格式错误"
	}
	else if(checkqqnum.length==0){
		document.getElementById("qqMsg").innerHTML="qq号码不可为空";
	}
	else{
		document.getElementById("qqMsg").innerHTML="正确"
	}		
}

function checkall(){
	checkname();
	checkpwd();
	checkrepwd();
	checkphnum();
	checkqqnum();
}

function getcommand(){
	var uname=document.getElementById("uname").value;
	var commandtxt=document.getElementById("commandtxt").value;	
	var ulNode = document.getElementById("command");
	var newLiNode = document.createElement("li");
	newLiNode.textContent=uname+":"+commandtxt;
	var lastLiNode = ulNode.lastChild;
	ulNode.insertBefore(newLiNode, lastLiNode);
}

// 数组下标对应省份下拉选择框的第i个选项
var citys = ["请选择","a","b","c"];
			
			function selectCity() {
				var citySelectNode = document.getElementById("city");
				citySelectNode.options.length = 1;
				for (var i = 0; i < citys.length; i++) {
					var city = citys[i];
					// 创建option元素
					var optionNode = document.createElement("option");
					// 设置option元素的文本内容
					optionNode.textContent = city;
					// 把option添加到城市select元素中
					citySelectNode.appendChild(optionNode);
				}
				
			}



