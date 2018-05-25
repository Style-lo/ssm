<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
<title>Insert title here</title>
</head>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery1.9.min.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js" ></script>
<script type="text/javascript">
	$("#QueryBtn").click(function(){
		var k = $("#file").val();
		
		window.location.href="audit/selectInfo?currentPage=1&startIndex=0&pageSize=10&types="+x+"&names="+k;
		
	})
		function testUpload(){  
			alert("----")
		    var form = new FormData(document.getElementById("file"));
		    var k = $("#file").val();
			console.log(k);
		    alert(k) ;
		    $.ajax({
		        url : "${pageContext.request.contextPath}/user/upload",
		        data : form,
		        type : 'post',
		        processData:false,
		        contentType:false,
		        success : function(data){
		            alert("成功")
		        },
		        error : function(data){
		        	alert("失败")
		        }  
		    }); 
		} 
		
</script>
<body>
	<form method="post" action="${pageContext.request.contextPath}/user/upload" enctype="multipart/form-data">
		<input type="file" name="file" value="file">
		<input type="submit" value="确定">
	</form>
	<form id="test" enctype="multipart/form-data">    
	    选择文件:<input data-role="none" type="file" name="file" width="120px" id="file">    
	    <button data-role="none" onclick="testUpload();">测试</button>  
	</form>
</body>
</html>