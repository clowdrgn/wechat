<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="css/weui.css" rel="stylesheet" type="text/css">
<script src="js/jquery.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=yes">
<title>请您完善信息</title>
</head>
<body>
<%
String openId = (String)request.getAttribute("openId");%>
<div class="weui_cells_title">请您完善信息以更好的使用我们的服务</div>
        <div class="weui_cells weui_cells_form">
            <div class="weui_cell">
                <div class="weui_cell_hd"><label class="weui_label">邮箱</label></div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input class="weui_input" type="email" pattern="^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$" id="email" placeholder="请输入邮箱"/>
                </div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_hd"><label class="weui_label">电话</label></div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input class="weui_input"  id="phone" type="number" pattern="[0-9]*" placeholder="请输入电话号码"/>
                </div>
            </div>
        </div>
        <input type="hidden" value = "<%=openId%>" id="openId">
        <div class="weui_cells_tips">感谢您的支持</div>
        <div class="weui_btn_area">
            <a class="weui_btn weui_btn_primary" href="javascript:submit();" id="showTooltips">确定</a>
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
	        <div class="weui_dialog_bd">请填写邮箱电话</div>
	        <div class="weui_dialog_ft">
	            <a href="#" class="weui_btn_dialog primary">确定</a>
	        </div>
	    </div>
	</div>
<script>
var $dialog = $('#dialog2');
$dialog.find('.weui_btn_dialog').one('click', function () {
    $dialog.hide();
});
	function submit(){
		var openId = $("#openId").val();
		var phone = $("#phone").val();
		var email = $("#email").val();
		if(phone.length < 2 && email.length < 2){
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
	}
	
</script>
</body></html>