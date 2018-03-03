<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <title>找回密码</title>
    <%@include file="../common/top.jsp"%>
</head>
<style>
    .forget {
        width: 400px;
        height: 350px;
        background-color: white;
        border-radius: 5px;
        margin: 150px auto;
        -webkit-box-shadow: 0px 0 50px #999;
        box-shadow: 0px 0 50px #999;
    }

</style>
<body style="background-color: #eee;">
    <input type="text" id="path" hidden="hidden" value="${pageContext.request.contextPath}">
    <div>
        <ul class="layui-nav h50"></ul>
    </div>
    <div class="forget">
        <form style="margin: 20px" class="layui-form layui-form-pane" action="${pageContext.request.contextPath}/sendForgetPassWordMail.do" method="post">

            <div class="layui-form-item">
                <fieldset class="layui-elem-field layui-field-title h50">
                    <legend>找回密码</legend>
                </fieldset>
            </div>

            <div class="layui-form-item" style="margin-top: 50px;">
                <label class="layui-form-label">邮箱地址</label>
                <div class="layui-input-block">
                    <input type="text" id="email" name="email" autocomplete="off" placeholder="输入绑定的邮箱地址" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item" style="margin-top: 30px;">
                <label class="layui-form-label" style="padding: 0px;border: 0px;"><img id="changeCode" width="100" height="36" src="${pageContext.request.contextPath}/sys/user/captcha.do"
                                                      id="refImg" style="cursor:pointer;" title="点击刷新"/></label>
                <div class="layui-input-block">
                    <input type="text" id="validCode" name="validCode" autocomplete="off" placeholder="输入验证码"
                           class="layui-input">
                    <%--<span class="form-code" id="changeCode" style="position:absolute;right:1px; top:1px;"></span>--%>
                    <%--<input type="text" name="title" autocomplete="off" placeholder="输入绑定的邮箱地址" class="layui-input">--%>
                </div>
            </div>

            <div class="layui-form-item" style="text-align: center;margin-top: 40px;">
                <%--<a style="width: 300px;" href="javascript:void(0);" type="button" class="layui-btn" onclick="submitForget()">立即提交</a>--%>
                <button style="width: 140px;"  type="submit" class="layui-btn" >立即提交</button>
            </div>

        </form>
    </div>
    <footer>
        <div class="text-center fz-16 h50">
            <span id="nameerr"> ©2016-2017  云品汇掌控 -（广州）云品汇网络科技有限公司 粵ICP备17013200号</span>
        </div>
    </footer>
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/layui/layui.js"></script>
    <script>

        $('#changeCode').on('click', function () {
            $("#validCode")[0].value = '';
            $('#changeCode')[0].src = '${pageContext.request.contextPath}/sys/user/captcha.do';
        });

        /**
         *  提交找回密码
         */
        <%--var submitForget =function () {--%>
            <%--$.post("${pageContext.request.contextPath}/sendForgetPassWordMail.do", {--%>
                <%--email:$('#email')[0].value,--%>
                <%--validCode:$('#validCode')[0].value--%>
            <%--}, function (data) {--%>
                <%--if (data.code == '0') {--%>
                    <%--// layer.msg(data.msg);--%>
                <%--} else {--%>
                    <%--// layer.msg('查询错误 : ' + data.msg);--%>
                <%--}--%>
            <%--});--%>
        <%--}--%>

    </script>
    </body>
</html>