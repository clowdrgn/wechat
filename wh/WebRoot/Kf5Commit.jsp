<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>请您填写工单信息</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<meta name="keywords" content="Flat Dark Web Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />
<link href="css/style.css" rel='stylesheet' type='text/css' />
<link href="css/weui.css" rel="stylesheet" type="text/css">
<!--webfonts-->
<link href='http://fonts.useso.com/css?family=PT+Sans:400,700,400italic,700italic|Oswald:400,300,700' rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Exo+2' rel='stylesheet' type='text/css'>
<!--//webfonts-->
<script src="http://ajax.useso.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
</head>
<body>
 <%String openId = (String)request.getAttribute("openId");%>
<div class="login-form">
	<div class="close"> </div>
		<div class="head-info">
		</div>
			<div class="clear"> </div>
	<div class="avtar">
		<img src="images/avtar.png" />
	</div>
	
		<form>
					<input type="text" class="text" value="标题"  id="title" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '标题';}" >
					<input type="hidden" value = "<%=openId%>" id="openId">
					<textarea rows="3" cols="20" style="margin-bottom:3em;margin-top:1em" id="content"  onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '简述';}">简述</textarea>
					<!-- <input type="text"  style="margin-bottom:3em;margin-top:1em" id="content" value="简述" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '简述';}"> -->
					
			</form>
	<div class="signin">
	<input type="submit" value="提交" id="submit">
	</div>
	
</div>
	 <div id="loadingToast" class="weui_loading_toast" style="display:none;">
    <div class="weui_mask_transparent"></div>
    <div class="weui_toast">
        <div class="weui_loading">
            <!-- :) -->
            <div class="weui_loading_leaf weui_loading_leaf_0"></div>
            <div class="weui_loading_leaf weui_loading_leaf_1"></div>
            <div class="weui_loading_leaf weui_loading_leaf_2"></div>
            <div class="weui_loading_leaf weui_loading_leaf_3"></div>
            <div class="weui_loading_leaf weui_loading_leaf_4"></div>
            <div class="weui_loading_leaf weui_loading_leaf_5"></div>
            <div class="weui_loading_leaf weui_loading_leaf_6"></div>
            <div class="weui_loading_leaf weui_loading_leaf_7"></div>
            <div class="weui_loading_leaf weui_loading_leaf_8"></div>
            <div class="weui_loading_leaf weui_loading_leaf_9"></div>
            <div class="weui_loading_leaf weui_loading_leaf_10"></div>
            <div class="weui_loading_leaf weui_loading_leaf_11"></div>
        </div>
        <p class="weui_toast_content">数据加载中</p>
    </div>
</div>

<div class="weui_dialog_alert"  id="dialog2" style="display:none">
	    <div class="weui_mask"></div>
	    <div class="weui_dialog">
	        <div class="weui_dialog_hd"><strong class="weui_dialog_title">请填写</strong></div>
	        <div class="weui_dialog_bd">请填写标题及简述</div>
	        <div class="weui_dialog_ft">
	            <a href="#" class="weui_btn_dialog primary">确定</a>
	        </div>
	    </div>
	</div>
<script>$(document).ready(function(c) {
	$('.close').on('click', function(c){
		$('.login-form').fadeOut('slow', function(c){
	  		$('.login-form').remove();
		});
	});	 
	var $dialog = $('#dialog2');
	$dialog.find('.weui_btn_dialog').one('click', function () {
	    $dialog.hide();
	});
	$("#submit").click(function(){
		var openId = $("#openId").val();
		var title = $("#title").val();
		var content = $("#content").val();
		if((title.length < 2 && content.length < 2) || (title=="标题"||content=="简述")){
			$(".weui_dialog_alert").attr("style","display:block");
			return;
		}
		$("#loadingToast").attr("style","display:block");
		$.post("createKf5Final",
	 		{
				openId:openId,
				title:title,
				content:content
			},
		function(name){
				if(name != null && name.length > 0){
					$("#loadingToast").attr("style","display:none");
					window.location.href="BindSuccess.jsp";
				}else{
					$("#loadingToast").attr("style","display:none");
					window.location.href="Register.jsp";
				}
			}	
		)
});
});
	
</script>
</body></html>