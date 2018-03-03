<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!--头部-->
<div class="layui-header">
    <div class="layui-logo"><span style="font-size: 21px;">后台管理系统</span></div>
    <ul class="layui-nav layui-layout-left kit-nav">
        <c:choose>
            <c:when test="${isMenuSplit}">
                <c:forEach items="${menuList}" var="nav">
                    <li class="layui-nav-item" id="${nav.id}" onclick="changeLeftMenu(this)"><a
                            href="javascript:void(0);"><i class="${nav.icon}" style="font-size:16px;margin-right:10px;" aria-hidden="true"></i>${nav.name}</a></li>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <c:forEach items="${showList}" var="nav">
                    <li class="layui-nav-item" id="${nav.id}" onclick="changeLeftMenu(this)"><a
                            href="javascript:void(0);"><i class="fz-16 ${nav.icon}" style="font-size:14px;margin-right:10px;"  aria-hidden="true"></i>${nav.name}</a></li>
                </c:forEach>
                <li class="layui-nav-item layui-this" onclick="changeLeftMenu(this)">
                    <a href="javascript:;">${sysMenuVo.name}</a>
                    <dl class="layui-nav-child">
                        <c:forEach items="${hidenList}" var="nav">
                            <dd id="${nav.id}" onclick="changeLeftMenu(this)"><a
                                    href="javascript:void(0);">${nav.name}</a></dd>
                        </c:forEach>
                    </dl>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>

    <ul class="layui-nav layui-layout-right kit-nav">
        <li class="layui-nav-item">
            <a href="javascript:;">
                <i class="layui-icon">&#xe63f;</i> 皮肤</a>
            </a>
            <dl class="layui-nav-child skin">
                <dd><a href="javascript:;" data-skin="default" style="color:#393D49;"><i class="layui-icon">&#xe658;</i> 默认</a></dd>
                <dd><a href="javascript:;" data-skin="orange" style="color:#ff6700;"><i class="layui-icon">&#xe658;</i> 橘子橙</a></dd>
                <dd><a href="javascript:;" data-skin="green" style="color:#00a65a;"><i class="layui-icon">&#xe658;</i> 原谅绿</a></dd>
                <dd><a href="javascript:;" data-skin="pink" style="color:#FA6086;"><i class="layui-icon">&#xe658;</i> 少女粉</a></dd>
                <dd><a href="javascript:;" data-skin="blue.1" style="color:#00c0ef;"><i class="layui-icon">&#xe658;</i> 天空蓝</a></dd>
                <dd><a href="javascript:;" data-skin="red" style="color:#dd4b39;"><i class="layui-icon">&#xe658;</i> 枫叶红</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item">
            <a href="javascript:;">
                <img src="http://m.zhengjinfan.cn/images/0.jpg" class="layui-nav-img"> ${user.userName}
            </a>
            <dl class="layui-nav-child">
                <dd><a href="javascript:;">基本资料</a></dd>
                <dd><a href="javascript:;">安全设置</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item"><a href="javascript:;" onclick="loginOut()"><i class="fa fa-sign-out"
                                                                                  aria-hidden="true"></i> 注销</a>
        </li>
    </ul>
</div>

<script type="text/javascript">
    var loginOut = function () {
        layer.confirm('确定要退出系统吗？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            layui.jquery.post('${pageContext.request.contextPath}/sys/user/loginOut.do', function (data) {
                if (data.code == 0) {
                    layer.msg('退出成功!即将跳转到登录页面。');
                    setTimeout("toLogin()", 3000);
                }
            });
        }, function () {
            layer.closeAll();
        });
    }


    var toLogin = function () {
        window.location.href = "toLogin.do";
    }

</script>