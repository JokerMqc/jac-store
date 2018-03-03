<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>后台管理系统</title>
    <link href="${pageContext.request.contextPath}/img/favicon.ico" rel="shortcut icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/font-awesome/css/font-awesome.min.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/themes/default.css" media="all" id="skin" kit-skin />
</head>
<style>
    .menu-left-show{
        display: block;
    }
    .menu-left-hide{
        display: none;
    }
</style>
<body class="kit-theme">
    <div class="layui-layout layui-layout-admin kit-layout-admin">
        <%@include file="base/top.jsp"%>
        <%@include file="base/left.jsp"%>
        <div class="layui-body" id="container"></div>
        <%@include file="base/footer.jsp"%>
    </div>
    <script src="${pageContext.request.contextPath}/js/plugins/layui/layui.js"></script>
    <script>
        var message;
        var changeLeftMenu;
        layui.config({
            base: 'js/'
        }).use(['app', 'message'], function () {
            var app = layui.app,
                $ = layui.jquery,
                layer = layui.layer;
            //将message设置为全局以便子页面调用
            message = layui.message;
            //主入口
            app.set({
                type: 'iframe'
            }).init();



            /**
             *  改变隐藏左侧菜单
             * @param obj
             */
            changeLeftMenu =function (obj) {
                var id =  'menu_'+obj.id;
                $('.menu-left-show').addClass('menu-left-hide');
                $('.menu-left-show').removeClass(' menu-left-show');
                $('#'+id).removeClass('menu-left-hide');
                $('#'+id).addClass('menu-left-show');
            };

            /**
             *  改变皮肤颜色
             * @param obj
             */
            $('dl.skin > dd').on('click', function() {
                var $that = $(this);
                var skin = $that.children('a').data('skin');
                switchSkin(skin);
            });
            var setSkin = function(value) {
                    layui.data('kit_skin', {
                        key: 'skin',
                        value: value
                    });
                },
                getSkinName = function() {
                    return layui.data('kit_skin').skin;
                },
                switchSkin = function(value) {
                    var _target = $('link[kit-skin]')[0];
                    _target.href = _target.href.substring(0, _target.href.lastIndexOf('/') + 1) + value + _target.href.substring(_target.href.lastIndexOf('.'));
                    setSkin(value);
                },
                initSkin = function() {
                    var skin = getSkinName();
                    switchSkin(skin === undefined ? 'default' : skin);
                }();
        });
    </script>
</body>
</html>
