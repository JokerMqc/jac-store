<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <title>登录页面</title>
    <%@include file="../common/top.jsp"%>
    <script>
        if (window != window.top) top.location.href = self.location.href;
    </script>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/slide.css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/js/jquery.SuperSlide.2.1.1.js"></script>
</head>
<body class="login" style="background:#ffffff;">
    <div class="wrapper header clearfix" style="height:100px;">
        <div class="fl logo"><img src="${pageContext.request.contextPath}/img/logo.png" /></div>
        <div class="fr tip" ><i class="tel-icon"><img height="37" src="${pageContext.request.contextPath}/img/tel.png" /></i><span class="tel">服务热线：020-89859636</span></div>
    </div>
    <div class="bg  slideBox container">
        <div class="hd">
            <ul><li></li><li></li></ul>
        </div>
        <div class="wrapper" style="padding:20px 0">
            <div class="slide">
                <div  id="slideBox" class="slideBox">
                    <div class="bd">
                        <ul class="clearfix">
                            <li class="banner01"><img src="${pageContext.request.contextPath}/img/banner1.png"/></li>
                            <li class="banner02"><img src="${pageContext.request.contextPath}/img/banner2.png" /></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div id="loginbox" class="fl" style="width: 300px;">
                <div class="kit-login-box">
                    <h1 class="col-orange h50 text-center">云品汇掌控系统</h1>
                    <div class="kit-login-main">

                        <form id="loginForm" action="/" class="layui-form" method="post">
                            <div class="layui-form-item">
                                <label class="kit-login-icon">
                                    <i class="layui-icon">&#xe612;</i>
                                </label>
                                <input type="text" name="userName" lay-verify="required" autocomplete="off"
                                       placeholder="这里输入用户名" class="layui-input">
                            </div>
                            <div class="layui-form-item">
                                <label class="kit-login-icon">
                                    <i class="layui-icon">&#xe642;</i>
                                </label>
                                <input type="password" name="password" lay-verify="required" autocomplete="off"
                                       placeholder="这里输入密码" class="layui-input">
                            </div>
                            <div class="layui-form-item">
                                <label class="kit-login-icon">
                                    <i class="layui-icon">&#xe642;</i>
                                </label>
                                <input type="text" id="validCode" name="validCode" autocomplete="off" placeholder="输入验证码"
                                       class="layui-input">
                                <span class="form-code" id="changeCode" style="position:absolute;right:1px; top:1px;">
                                    <img width="100" height="36" src="${pageContext.request.contextPath}/sys/user/captcha.do"
                                             id="refImg" style="cursor:pointer;" title="点击刷新"/>
                                </span>
                            </div>
                            <div class="layui-form-item">
                                <div class="kit-pull-left">
                                    <input type="checkbox" name="rememberMe" value="true" lay-skin="primary" checked
                                           title="记住帐号?">

                                </div>
                                <div class="kit-pull-left"><a onclick="toForgetPassword()" href="javascript:void(0);" style="line-height:38px;">忘记密码?</a></div>
                                <div class="kit-pull-right">
                                    <button class="layui-btn btn-blue" lay-submit lay-filter="login" style="transition: none;">
                                        登录
                                    </button>
                                </div>
                                <div class="kit-clear"></div>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer>
        <div class="text-center fz-16 h50">
            <span id="nameerr"> ©2016-2018  云品汇掌控 -（广州）云品汇网络科技有限公司 粵ICP备17013200号</span>
        </div>
    </footer>
<script>
    layui.use(['layer', 'form'], function () {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form;

        $('#changeCode').on('click', function () {
            $("#validCode")[0].value = '';
            $('#changeCode > img')[0].src = '${pageContext.request.contextPath}/sys/user/captcha.do';
        });

        //清理左侧菜单缓存
        var index = layer.load(2, {
            shade: [0.3, '#333']
        });
        $(window).on('load', function () {
            layer.close(index);
            form.on('submit(login)', function (formData) {
               var validCode= $('#validCode')[0].value;
               if(validCode==''){
                   layer.msg(' 验证码不能为空 ! ');
               } else{
                   $.post('${pageContext.request.contextPath}/sys/user/login.do', $('#loginForm').serialize(),function (data) {
                       if(data.code ==0 ){ // 登录成功
                           window.location.href="main.do";
                       }else{
                           layer.msg(data.msg);
                       }
                   });
               }
                return false;
            });
        }());

    });
    //焦点图
    $(function(){
        jQuery(".slideBox").slide({mainCell:".bd ul",autoPlay:true});
    }) ;

    var toForgetPassword =function () {
        window.location.href='toForgetPassword.do';
    }



</script>
</body>

</html>