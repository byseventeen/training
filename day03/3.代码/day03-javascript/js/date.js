// 把Date对象转换成“年月日时分秒”格式的字符串
/*function formatDate(d) {
	var year = d.getFullYear();
	var month = d.getMonth() + 1;
	var date = d.getDate();
	var hour = d.getHours();
	var min = d.getMinutes();
	var second = d.getSeconds();
	
	var dateStr = year + "年" + month + "月" + date + "日 " + hour + "时" + min + "分" + second + "秒";
	
	return dateStr;
}*/

// 给Date对象扩展了一个formatDate方法
Date.prototype.formatDate = function() {
	// this代表该方法的调用者对象
	var year = this.getFullYear();
	var month = this.getMonth() + 1;
	var date = this.getDate();
	var hour = this.getHours();
	var min = this.getMinutes();
	var second = this.getSeconds();
	
	var dateStr = year + "年" + month + "月" + date + "日 " + hour + "时" + min + "分" + second + "秒";
	
	return dateStr;
}


