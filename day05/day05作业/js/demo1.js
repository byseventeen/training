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

function




