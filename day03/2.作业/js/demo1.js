


function getprice(){
	var bookName=document.getElementById("bookname").value;
	var author=document.getElementById("author").value;
	var price=document.getElementById("price").value;
	var cut=document.getElementById("cut").value;
	var total;
	
	total=price-price*cut;
	alert("这本书的书名为"+bookName+"，"+author+"编著，"+"折扣为："+cut+"折后价为："+total);
}


