window.onload=function(){//页面加载完执行这个匿名函数
	//获取图片
	var logo=document.getElementById("logo");
    //鼠标经过图片
	logo.onmouseover=function(){
		this.src="images/logo1.png"
	}
	//鼠标离开图片
	logo.onmouseout=function(){
		this.src="images/logo.png"
	}
}
function tx(){
	var tx=document.getElementById("demo").value;
	if (tx=="") {
		alert("请输入内容，例如AIR JORDAN");
	}
}
// window.onload=function(){
// 	var btn_top=document.getElementById("btn_top");
// 	window.scroll(function(){
// 		if (window.scrollTop()>=50) {
// 			btn_top.fadeIn();
// 		}else{
//             btn_top.fadeOut();
// 		}
// 	})
// }