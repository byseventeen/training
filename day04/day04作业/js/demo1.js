
function setText1(){
    var parent= document.getElementById("one");
    if(document.getElementById("check1").checked==true){
        var textElement = document.createElement("p");
        textElement.textContent = "结点方法处理隐藏内容";
        textElement.id="blue";   
        //parent.appendChild(textElement);
        parent.appendChild(textElement);
    }
    else{
         var child=document.getElementById("blue");
        parent.removeChild(child);
    }
}

function setText2(obj){
    if (obj.checked) {
        document.getElementById("aa").style.display="";
    }
    else{
        document.getElementById("aa").style.display="none";
    }  
}

function insert(){
   var parent= document.getElementById("twoul");
   var li_1 = document.createElement("li");
   var tt= document.getElementById("tt").value;
   li_1.textContent = tt;  
   parent.appendChild( li_1);
   document.getElementById("tt").value="";
}

function checkall() {
    
}

function nocheck() {
    // body...
}

function backcheck() {
    // body...
}


