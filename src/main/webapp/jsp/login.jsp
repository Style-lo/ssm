<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
<title>Insert title here</title>
</head>	
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/commonJs.js" ></script>
<script type="text/javascript">
var login = {};
login.QueryBtn = function(){
	login.testUpload();
}

login.testUpload = function(){
	var obj=commonJs.getFormData("#from2");
	console.log(obj);
	var url="http://localhost:8090/ssm/userlogin";
	
    $.ajax({
		/* url:"${pageContext.request.contextPath}/userlogin", */
		 url:url, 
		type:"POST",
		dataType:"json", 
		data:{"jsonParams" : JSON.stringify(obj)},
		success:function(date){
			if(date == null || date.equas("")){
				("#spenId").innerText = "账号或密码错误，请重新输入！";
			}
			
		}
	});
}
$(document).ready(function(){
	console.log("页面加载成功！");
	$('#QueryBtn').click(function(){
		login.QueryBtn();
	});
});
		
</script>
<body>
	<form id="from2" action="${pageContext.request.contextPath}/userlogin">
		<input type="text" name="user_name" />
		<input type="password" name="password" />
		<span id="spenId"></span>
		<button type="button" id ="QueryBtn"> 登录 </button>
	</form>
</body>
</html>