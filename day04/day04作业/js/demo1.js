
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

var checked=document.getElementsByClassName("checkbox");
function checkall() {
    for (var i = 0; i < checked.length; i++){
        checked[i].checked=true;
    }
}

function nocheck() {
    for (var i = 0; i < checked.length; i++){
        checked[i].checked=false;
    }
}

function backcheck() {
    for (var i = 0; i < checked.length; i++){
        if (checked[i].checked==true) {
            checked[i].checked=false;
        }
        else{
            checked[i].checked=true;
        }
    }
}

// 数组下标对应省份下拉选择框的第i个选项
var citys = [
    ["请选择城市"],
    ["广州", "深圳", "珠海"],
    ["衡阳", "长沙", "张家界"],
    ["琼海", "三亚", "海口"]
];

function selectCity(selectNode) {
    var citySelectNode = document.getElementById("city");
    // 清空城市select中的选项清空
    citySelectNode.options.length = 1;
    // 获取选中选项的索引
    var index = selectNode.selectedIndex;
    //alert(index);
    // 根据索引获取城市
    var cityData = citys[index];
    // 遍历城市
    for (var i = 0; i < cityData.length; i++) {
        var city = cityData[i];
        // 创建option元素
        var optionNode = document.createElement("option");
        // 设置option元素的文本内容
        optionNode.textContent = city;
        // 把option添加到城市select元素中
        citySelectNode.appendChild(optionNode);
    }

}

function check() {
    // 获取用户输入
    var userName = document.getElementById("userName").value;
    // 验证用户输入（非空、长度不能小于5位、必须是英文或数字组成）
    var regex = /[a-z0-9]{5,}/i;
    // 匹配
    var isValid = regex.test(userName);
    if (!isValid) {
        document.getElementById("userNameMsg").textContent = "X 用户名格式不正确";
    } else {
        document.getElementById("userNameMsg").textContent = "√";
    }

    var pwd = document.getElementById("pwd").value;
    //至少8-16个字符，至少1个大写字母，1个小写字母和1个数字，其他可以是任意字符
    var pPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{8,16}$/;
    var isValid = pPattern.test(pwd);
    if (!isValid) {
        document.getElementById("pwdMsg").textContent = "X 密码强度不足";
    } else {
        document.getElementById("pwdMsg").textContent = "√";
    }

    var pwdd = document.getElementById("pwdd").value;
    if (pwd==pwdd) {
        document.getElementById("pwddMsg").textContent = "√";
    }
    else{
        document.getElementById("pwddMsg").textContent = "X 密码不一致";
    }

    

    //return true;
}


