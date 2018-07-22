var picsArr=new Array();
picsArr[0]="image/2.jpg";
picsArr[1]="image/3.jpg";

var timer,index=0;
window.onload=showPic;
function showPic()
{
	document.getElementById("pic").src=picsArr[index];
	document.getElementById("num").innerHTML=index+1+"/2";
	if(index<1)
		index++;
	else
		index=0;
	timer=setTimeout("showPic()",2000);
}
function showNext()
{
	clearTimeout(timer);
	showPic();
}
function showPrepic()
{
	if(index>0)
		index--;
	else
		index=1;
	document.getElementById("pic").src=picsArr[index];
	document.getElementById("num").innerHTML=index+1+"/2";
	timer=setTimeout("showPrepic()",2000);
}
function showPre()
{
	clearTimeout(timer);
    showPrepic();
}

function showTime()
{
   var myDate=new Date();
   document.getElementById("li1").innerHTML="北京时间："+myDate.toLocaleString();
   setTimeout("showTime()",1000);
}
window.onload=showTime;

