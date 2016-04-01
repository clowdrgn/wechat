<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>请您填写工单信息</title>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<meta name="keywords" content="Flat Dark Web Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />
<link href="css/weui.css" rel="stylesheet" type="text/css">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0" >
<link rel="stylesheet" type="text/css" href="css/webdemo.css">
<script src="http://ajax.useso.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
</head>
 <%String openId = (String)request.getAttribute("openId");%>
<body>
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
				<form action="#" method="get" id="myform">				
					<div class="input-box">
						<p>问题</p>
							<input class="input-text" type="text" id="title" >
					</div>
					<div class="input-box">
						<p>类型</p>
						<div id="divselect">
							<span>请选择类型</span>
							<ul>
								<li><a href="javascript:;" selectId=1>Application Insight</a></li>
								<li><a href="javascript:;" selectId=2>Browser Insight</a></li>
								<li><a href="javascript:;" selectId=3>Mobile Insight</a></li>
								<li><a href="javascript:;" selectId=4>OneAPM Servers</a></li>
								<li><a href="javascript:;" selectId=5>其他</a></li>
								<li><a href="javascript:;" selectId=6>企业级</a></li>
								<li><a href="javascript:;" selectId=7>Cloud Insight</a></li>
								<li><a href="javascript:;" selectId=8>OneAlert</a></li>
								<li><a href="javascript:;" selectId=9 >Cloud Test</a></li>
							</ul>
						</div>
					</div>			
					<input type="hidden" value = "<%=openId%>" id="openId">		
					<input name="hideSelect" type="hidden" value="" id="inputselect"/>
					<div class="input-box">
						<p>描述</p>
						<div class="description-part">
						<textarea form="myform"  rows="8"  cols="40"  id="content" ></textarea>
						</div>
					</div>					
					<div class="btn-part">
						<input class="btn" name="submit" value=" 提交" id="submit">
					</div>				
				</form>
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
	        <div class="weui_dialog_bd">请填写标题简述及产品类型</div>
	        <div class="weui_dialog_ft">
	            <a href="#" class="weui_btn_dialog primary">确定</a>
	        </div>
	    </div>
	</div>
<script>$(document).ready(function(c) {
	var $dialog = $('#dialog2');
	$dialog.find('.weui_btn_dialog').one('click', function () {
	    $dialog.hide();
	});
	$("#submit").click(function(){
		var openId = $("#openId").val();
		var title = $("#title").val();
		var content = $("#content").val();
		var leixing = $("#divselect span").text();
		if((title.length < 2 && content.length < 2) || (title=="标题"||content=="简述")){
			$(".weui_dialog_alert").attr("style","display:block");
			return;
		}
		if(leixing.length < 2){
			$(".weui_dialog_alert").attr("style","display:block");
			return;
		}
		$("#loadingToast").attr("style","display:block");
		$.post("createKf5Final",
	 		{
				openId:openId,
				title:title,
				content:content,
				leixing:leixing
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
jQuery.divselect = function(divselectId,inputselectId){
	var inputselect = $(inputselectId);
	$(divselectId+" span").click(function(){
		var myul = $(divselectId+' ul');
		if(myul.css("display")=="none"){
			$(divselectId).addClass("open");
			myul.slideDown();
		}else{					
			myul.slideUp();
			$(divselectId).removeClass("open");
		}
	});
	$(divselectId+" ul li a").click(function(){
		var mytext = $(this).text();
		console.log(mytext);
		$(divselectId+" span").html(mytext);
		var myvalue = $(this).attr("selectId");
		inputselect.val(myvalue);
		$(divselectId+" ul").hide();
	});
};
$(function(){
	$.divselect("#divselect","#inputselect");
});
</script>
</body></html>