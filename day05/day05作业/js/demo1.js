window.onload = function(){
	var second=5;
    var timer= setInterval(function(){
        if(second>0){
            document.getElementById("read").value=second;
            second--;
            document.getElementById("read").value="请仔细阅读协议"+second;
        }
        else{
        	document.getElementById("read").value="请仔细阅读协议";
            document.getElementById("read").disabled=false;
            clearInterval();          
        }
    },1000)
}

function onmose(){
	document.getElementsByClassName("onmouseover").style.display=none;
}

