function leftTimer(year,month,day,hour,minute,second){ 
  var leftTime = (new Date(year,month-1,day,hour,minute,second)) - (new Date()); 
 var days = parseInt(leftTime / 1000 / 60 / 60 / 24 , 10); 
 var hours = parseInt(leftTime / 1000 / 60 / 60 % 24 , 10); 
 var minutes = parseInt(leftTime / 1000 / 60 % 60, 10);
 var seconds = parseInt(leftTime / 1000 % 60, 10);
 days = checkTime(days); 
 hours = checkTime(hours); 
 minutes = checkTime(minutes); 
 seconds = checkTime(seconds); 
 setInterval("leftTimer(2018,02,15,00,00,00)",1000); 
 document.getElementById("timer").innerHTML = days+"天" + hours+"小时" + minutes+"分"+seconds+"秒"; 
} 

function checkTime(i){ //将0-9的数字前面加上0，例1变为01 
 if(i<10) 
 { 
  i = "0" + i; 
 } 
 return i; 
} 
 
