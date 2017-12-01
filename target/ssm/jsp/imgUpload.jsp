<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>	
<script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/jquery1.9.min.js" ></script>
    <script src="js/ajaxfileupload.js" ></script>
<script type="text/javascript">
		function testUpload(){  
			console.log("----")
		    var form = new FormData(document.getElementById("test"));  
		    console.log(form) ;
		    $.ajax({
		        url : "${pageContext.request.contextPath}/file/upload",
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
		
		
		
		
		
		$(document).ready(function(e) {  
		    $('#ImportPicInput ').live( 'change', function(){  
		  
		        })  
		});
		
		$.ajaxFileUpload({
		    type: "POST",  
		    url: "/file/upload2",  
		    data:{picParams:text},//要传到后台的参数，没有可以不写  
		    secureuri : false,//是否启用安全提交，默认为false  
		    fileElementId:'ImportPicInput',//文件选择框的id属性  
		    dataType: 'json',//服务器返回的格式  
		    async : false,  
		    success: function(data){  
		        if(data.result=='success'){  
		            console.log("ok") 
		        }else{  
		        	 console.log("no") 
		        }  
		    },  
		    /* error: function (data, status, e){  
		        /coding  
		    }  */ 
		});
</script>
<body>
	
	<input type ="file" id="ImportPicInput" name= "myfile" />  
      <div class ="input-append">  
             <label for ="importPicName"> 上传原始图片：</label >  
             <input type ="text" class="input-large" id= "importPicName" />  
             <a class ="btn btn-default" onclick= "$('#ImportPicInput').click();" > 打开</ a>  
      </div >

	<br />
	<form id="test">    
	    选择文件:<input data-role="none" type="file" name="file" width="120px">    
	    <button data-role="none" onclick="testUpload();">测试</button>  
	</form>
</body>
</html>