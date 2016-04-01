<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>	
<head>
<title>完善信息</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0" >
<link href="css/weui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="css/webdemo.css">
<script src="http://ajax.useso.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script>$(document).ready(function() {
	var $dialog = $('#dialog2');
	$dialog.find('.weui_btn_dialog').one('click', function () {
	    $dialog.hide();
	});
	$("#submit").click(function(){
			var openId = $("#openId").val();
			var phone = $("#phone").val();
			var email = $("#email").val();
			if((phone.length < 2 && email.length < 2) || (phone=="电话"||email=="邮箱")){
				$(".weui_dialog_alert").attr("style","display:block");
				return;
			}
			$("#loadingToast").attr("style","display:block");
			$.post("lead",
		 		{
					openId:openId,
					phone:phone,
					email:email
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
</head>

 <!--SIGN UP-->
	<body>
 <%String openId = (String)request.getAttribute("openId");%>
		<div class="container">
		<div class="upper">
			<div class="upper-left">
				<img src="https://dn-oneapm.qbox.me/oneapmlogo.png" style="width:50px;">
				<!-- 用于白色背景样式 began -->
				<!-- <img src="https://dn-oneapm.qbox.me/oneapmlogo_gray.svg" style="width:50px;"> -->
				<!-- 用于白色背景样式 end -->
			</div>	
			<div class="divider"></div>
			<div class="upper-right">
				<span style="font-size:30px;">意见反馈</span>
			</div>		
		</div>
		<div class="form-part">			
			<form>				
				<div class="input-box">
					<p>邮箱</p>
					<input class="input-text" type="text" name="email" id="email" placeholder="hello@oneapm.com">
				</div>
				<div class="input-box">
					<p>电话</p>
					<input class="input-text" type="tel"  id="phone"  placeholder="010-12345678">
				</div>
				<input type="hidden" value = "<%=openId%>" id="openId">
				<div class="btn-part">
					<input class="btn" name="submit"  value="下一步"  id="submit">
				</div>
			</form>
		</div>
		</div>
	

					

<div class="weui_dialog_alert"  id="dialog2" style="display:none">
	    <div class="weui_mask"></div>
	    <div class="weui_dialog">
	        <div class="weui_dialog_hd"><strong class="weui_dialog_title">请填写</strong></div>
	        <div class="weui_dialog_bd">请填写邮箱电话</div>
	        <div class="weui_dialog_ft">
	            <a href="#" class="weui_btn_dialog primary">确定</a>
	        </div>
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
</body>


</html>