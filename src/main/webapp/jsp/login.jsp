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
		
	var login = function (){
			console.log("点击事件成功")
			var user_name = document.getElementById('user_name').value;
			var password = document.getElementById('password').value;
			console.log(password);
			/* var oBtn = document.getElementById('QueryBtn');
			function isnull(val) {

		        var str = val.replace(/(^\s*)|(\s*$)/g, '');//去除空格;

		        if (str == '' || str == undefined || str == null) {
		            console.log('空')
		        } else {
		            console.log('非空');
		        }
		    };
		   oBtn.onclick = function(){
			   isnull(user_name.val);
		   } 
		   oBtn.onclick = function(){
			   isnull(password.val);
		   } */ 
			$.ajax({
				url:"${pageContext.request.contextPath}/userlogin", 
				type:"POST",
				dataType:"json", 
				headers:{"Content-Type":"application/json"},
				/* data:{"jsonParams" : JSON.stringify(obj)}, */
				 data:{user_name:user_name,password:password}, 
				success:function(date){
					console.log("00000000000000000000000000")
					if(date == null || !date.equas("")){
						console.log("00000000000000000000000000")
						("#spenId").innerText = "账号或密码错误，请重新输入！";
					}
				}
			});
	}
</script>
<body>
	<form id="from2" action="${pageContext.request.contextPath}/userlogin">
		<input type="text" name="user_name" id="user_name"/>
		<input type="password" name="password" id="password"/>
		<span id="spenId"></span>
		<button type="button" onclick="login()" > test </button>
		<button type="submit" id ="QueryBtn"> 登录 </button>
	</form>
</body>
</html>