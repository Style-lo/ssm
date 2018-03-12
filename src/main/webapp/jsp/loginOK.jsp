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
<script type="text/javascript">

		
</script>
<body>
	<h1>登录成功jsp</h1>
	$request.getParameter("user")
	<a href="${pageContext.request.contextPath}/api/v1/test/ok">跳转</a>
	<a href="${pageContext.request.contextPath}/userloginOut">退出</a>
</body>
</html>