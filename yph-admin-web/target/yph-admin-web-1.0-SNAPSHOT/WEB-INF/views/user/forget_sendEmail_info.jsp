<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>设置新密码</title>
    <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/img/favicon.ico"/>
    <link href="${pageContext.request.contextPath}/js/plugins/layui/css/layui.css" rel="stylesheet"/>
</head>
<body >
<div style="text-align: center;margin-top: 400px;">
    <h2>
        请到邮箱查收邮件 ! 并点击修改密码的连接进行修改密码。
    </h2>
</div>

<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script>
    setTimeout(function () {
        window.location.href="main.do";
    },3000);
</script>
</body>
</html>