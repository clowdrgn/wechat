<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="css/weui.css" rel="stylesheet" type="text/css">
<script src="js/jquery.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=yes">
<title>操作成功</title>
</head>
<body>
<%-- <%String name = (String)request.getAttribute("name");%> --%>
<hr>
<div class="weui_msg">
    <div class="weui_icon_area"><i class="weui_icon_success weui_icon_msg"></i></div>
    <div class="weui_text_area">
        <h2 class="weui_msg_title">操作成功</h2>
        <p class="weui_msg_desc">您好，感谢您的支持，我们即将推出更多微信服务</p>
    </div>
    <div class="weui_opr_area">
        <p class="weui_btn_area">
            <a href="" id="queding" class="weui_btn weui_btn_primary" onclick="WeixinJSBridge.call('closeWindow');">确定</a>
        </p>
    </div>
    <div class="weui_extra_area">
        <a href="">查看详情</a>
    </div>
    <script>
    </script>
</div>
</body></html>