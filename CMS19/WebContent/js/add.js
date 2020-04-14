
function check(){
var userName = document.getElementById('username').value;
var userPwd = document.getElementById('password').value;
var userAge = document.getElementById('age').value;
if(userName==""){
	alert("请输入用户名");
	return false;
}else if(userPwd==''){
	alert("请输入密码");
	return false;
}else if(userAge==''){
	alert("请输入年龄");
	return false;
}
}