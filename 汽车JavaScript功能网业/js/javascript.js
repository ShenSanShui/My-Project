function $(elementId){
	return document.getElementById(elementId);
}
function checkUser(){
	var user=$("user").value;
	var userId=$("user1");
	if(user.length<3||user.length>16)
	{
		userId.style.color="red";
		userId.innerHTML="输入的长度为"+user.length+",请重新输入3-16位用户名";
		return false;
	}
	/*for(var i=0;i<user.length;i++)
		{
		if(!isNaN(user.charAt(i)))
		{
			userId.style.color="red";
			userId.innerHTML="您输入第"+(i+1)+"个字符为数字,用户名中不能包含数字,请重新输入 !";
			return false;
		}*/
		else{
		userId.style.color="green";
		userId.innerHTML="√";
		return true;
		}
}
		function checkPwd(){
			var pwd=$("pwd").value;
			var pwdId=$("pwd1");
			if(pwd.length<3||pwd.length>10){
				pwdId.style.color="red";
				pwdId.innerHTML="密码长度为"+pwd.length+"密码必须在3-10之间";
			return flase;
			}
			    pwdId.style.color="green";
				pwdId.innerHTML="√";
				return ture;
		}
		function checkRepwd(){
			var repwd=$("repwd").value;
			var pwd=$("pwd").value;
			var repwdId=$("repwd1");
			repwdId.innerHTML="";
			if(pwd!=repwd){
				repwdId.innerHTML="两次输入的密码不一致,请重新输入";
				return false;
			}
			return true;
		}
		function checkEmail(){
			var email=$("email").value;
			var email1=$("email1");
			email1.innerHTML="";
			var index=email.indexOf("@",1);
			if(index==-1){
				email1.innerHTML="输入包含‘@’符号";
				return false;
			}
			if(email.indexOf(".",index)==-1){
			email1.innerHTML="输入符号‘.’且在‘@’后面";
             email.style.color="red";
			return false;
			}
			email1.style.color="green";
			email1.innerHTML="√";
                return true;
		}
		
		function checkNumber(){
			var number=$("number").value;
			var numberId=$("number1");
			numberId.innerHTML="";
			if(number.charAt(0)!=1){
				numberId.innerHTML="手机号开头为1";
				numberId.style.color="red";
				return false;
			}
			for(var i=0;i<number.length;i++){
				if(isNaN(number.charAt(i))){
					numberId.innerHTML="手机不能包含字符";
						return false;
						}
					}
            for(var i=0; i<number.length;i++){
				if(number.length<11||number.length>11){
					numberId.innerHTML="手机号码为11位数";
					return false;
				}
			}
					numberId.style.color="green";
					numberId.innerHTML="√";
					return true;
		}
function checkBirth(){
          var birth=$("birth").value;
          var birthId=$("birth1");
        var reg=/^(19\d{2})|(200\d|(201[0-4]))-(0?[1-9]|1[]0-2)-(0?[1-9]|[1-2]\d|3[0-1])$/;
		if(reg.test(birth)==false){
			birthId.innerHTML="生日格式不正确，例如1990-05-02";
			return false;
		}
		birthId.innerHTML="";
		return true;
}
function Submit(){
	var submit=checkUser();
	if (submit==true){
		alert("注册成功!");
		return true;
	}else{
	alert("请完善信息！");
}
}