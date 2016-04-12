<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="js/jquery.min.js"></script>
<!-- <html class="bg-white"> -->
	<head>
		<meta charset="utf-8">
		<title>错误</title>
		<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0" >
		<link rel="stylesheet" type="text/css" href="css/webdemo.css">
	</head>
	<body>
	<%String content = (String)request.getAttribute("content");%>
		<div class="container">
			<div class="upper">
				<img src="https://dn-oneapm.qbox.me/oneapmlogo.png" style="width:100px;">	
				<!-- 用于白色背景样式 began -->
				<!-- <img src="https://dn-oneapm.qbox.me/oneapmlogo_gray.svg" style="width:100px;"> -->
				<!-- 用于白色背景样式 end -->	
			</div>
			<div class="form-part">			
				<h2><%=content%></h2>
			</div>
			<div class="btn2-part">
				<button type="button" class="btn2" onclick="WeixinJSBridge.call('closeWindow');"> 确定</button>
			</div>
		</div>
	</body>
</html>