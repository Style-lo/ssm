<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="${pageContext.request.contextPath}/user/insertUser">
		用户名：<input type="text" name="user_name" />
		密码：<input type="password" name= "password" />
		年龄：<input type="text" name= "age" />
		
		<hr />
		存入余额：<input type="text" name= "money" />
		支付方式：<input type="text" name= "pay" />
		
		<hr/>
		日志说明：<input type="text" name="msg" />
		<button>提交</button>
	</form>

</body>
</html>