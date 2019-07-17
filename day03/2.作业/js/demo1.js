
function getprice(){
	var bookName=document.getElementById("bookname").value;
	var author=document.getElementById("author").value;
	var price=document.getElementById("price").value;
	var cut=document.getElementById("cut").value;
	var total;
	
	total=price-price*cut;
	alert("这本书的书名为"+bookName+"，"+author+"编著，"+"折扣为："+cut+"折后价为："+total);
}


//var second=6;
function start(){
    
    /*if(second>=0){
        second--;
        document.getElementById("stime").innerHTML=second;
    }
    else{
        history.back();
    }
    setInterval("start()", 1000);*/
    var second=4;
    var timer= setInterval(function(){
        if(second>0){
            document.getElementById("stime").innerHTML=second;
            second--;
        }
        else{
            history.back();
            clearInterval();          
        }
    },1000)
}


function setRandom(min,max){
    var ran=max-min;
    var rdm = Math.random();
    var num=min+Math.floor(ran*rdm)
    document.getElementById("num").innerHTML=num;
}

var  taskId;
function start() {
            // 每隔1s调用showTime函数一次
            taskId = window.setInterval(function () {
                setRandom(100000,999999);
            }, 1000);
}

function stop(){
    window.clearInterval(taskId);
}



