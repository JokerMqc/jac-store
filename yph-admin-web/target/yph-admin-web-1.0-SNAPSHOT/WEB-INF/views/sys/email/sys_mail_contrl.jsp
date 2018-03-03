<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html style="height:100%;width:100%;overflow:hidden;">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>角色列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/font-awesome/css/font-awesome.min.css"
          media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layex/lay-ex.css" media="all">
</head>
<body id="body" style="height:100%;width: 100%;padding:0;margin: 0;">
<input type="text" id="path" hidden="hidden" value="${pageContext.request.contextPath}">
<blockquote class="layui-elem-quote" style="margin-top: 5px;">
    邮件管控台 : 邮件发送配置。
</blockquote>

<fieldset class="layui-elem-field">
    <legend>系统Bug邮件通知设置</legend>
    <div class="layui-field-box">




    </div>
</fieldset>





<fieldset class="layui-elem-field">
    <legend>单邮件发送</legend>
    <div class="layui-field-box">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">邮件地址:</label>
                <div class="layui-input-inline">
                    <input type="text" id="emailAddr" name="emailAddr" lay-verify="email" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <div class="layui-input-inline">
                        <button id="send" class="layui-btn layui-btn-normal layui-anim layui-anim-scaleSpring" data-anim="layui-anim-scaleSpring"
                                lay-submit="" lay-filter="demo1"><i class="layui-icon">&#xe609;</i>发送</button>
                </div>
            </div>
        </div>
    </div>
</fieldset>



<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/sys/mail/sys_mail_contrl.js"></script>
</body>
</html>
