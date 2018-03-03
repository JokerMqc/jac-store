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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" media="all">

    <%--ztree 插件--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/ztree/assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/ztree/dist/zTreeStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/ztree/dist/font-awesome-zTree.css">

</head>
<body id="body" style="height:100%;width: 100%;padding:0;margin: 0;">
<input type="text" id="path" hidden="hidden" value="${pageContext.request.contextPath}">
<div id="content" style="height: 100%;padding:0;margin: 0;overflow: hidden;">
    <%--搜索--%>
    <blockquote id="searchQuoto" style="margin-top: 5px;" class="layui-elem-quote elem-quote-ex">
        <form class="layui-form" action="">
            <div class="layui-form-item form-item-ex">
                <div class="layui-row">
                    <div class="layui-col-xs3">
                        <label class="layui-form-label form-label-ex-4">代理级别:</label>
                        <div class="layui-input-inline input-line-ex">
                            <select lay-filter="menuTypeFilter" id="menuType" name="type">
                                <option value="" selected="">请选择</option>
                                <option value="0">一级代理</option>
                                <option value="1">二级代理</option>
                                <option value="2">三级代理</option>
                                <option value="3">四级代理</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-col-xs3">
                        <label class="layui-form-label input-line-ex form-label-ex-4">代理上级:</label>
                        <div class="layui-input-inline input-line-ex">
                            <select lay-filter="menuTypeFilter" id="menuType" name="type">
                                <option value="" selected="">请选择</option>
                                <option value="0">代理级别</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-col-xs3">
                        <label class="layui-form-label input-line-ex form-label-ex-4">代理推荐人:</label>
                        <div class="layui-input-inline input-line-ex">
                            <select lay-filter="menuTypeFilter" id="menuType" name="type">
                                <option value="" selected="">请选择</option>
                                <option value="0">代理级别</option>
                                <option value="1">代理上级</option>
                                <option value="2">代理推荐人</option>
                                <option value="3">所属管理员</option>
                                <option value="4">数量</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-col-xs3">
                        <label class="layui-form-label form-label-ex-4">所属管理员:</label>
                        <div class="layui-input-inline input-line-ex">
                            <select lay-filter="menuTypeFilter" id="menuType" name="type">
                                <option value="" selected="">请选择</option>
                                <option value="0">代理级别</option>
                                <option value="1">代理上级</option>
                                <option value="2">代理推荐人</option>
                                <option value="3">所属管理员</option>
                                <option value="4">数量</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="layui-row">
                    <div class="layui-inline ">
                        <label class="layui-form-label form-label-ex-4">角色名称:</label>
                        <div class="layui-input-inline input-line-ex">
                            <input type="text" id="searchName" name="roleName" placeholder="输入角色名称搜索" autocomplete="off"
                                   class="layui-input input-ex">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label form-label-ex-4">创建时间:</label>
                        <div class="layui-input-inline input-line-ex">
                            <input type="text" id="searchCreateTime" name="searchCreateTime" lay-verify="date"
                                   class="layui-input input-ex">
                        </div>
                    </div>
                    <div class="layui-col-xs3">
                        <blockquote id="buttonQuoto" style="margin-top: 5px;" class="elem-quote-ex">
                            <button style="margin-left: 10px;" class="layui-btn layui-btn-sm" onclick="openCreateWindow()"><i class="layui-icon">&#xe608;</i>批量新增
                            </button>
                            <button style="margin-left: 10px; " class="layui-btn layui-btn-danger layui-btn-sm" onclick="batchDel()">
                                <i class="layui-icon">&#xe640;</i> 批量删除
                            </button>
                        </blockquote>
                    </div>
                </div>
            </div>
        </form>
    </blockquote>

    <%--数据表格--%>
    <table class="layui-hide" id="layMenu" lay-data="{id: 'idMenu'}" lay-filter="roleFilter"></table>

    <%--数据分页条--%>
    <div id="pageDiv" style="margin-left: 10px;"></div>

    <%--新建菜单 弹出框--%>
    <div id="createWindow" hidden="hidden" style="overflow: hidden">
        <blockquote class="layui-elem-quote" style="margin-top: 5px;">
            系统角色: 角色具有不同的功能权限等等。
        </blockquote>
        <fieldset class="layui-elem-field" style="width: 830px;margin-left: 8px;">
            <ul id="tree" class="ztree" style="height: 460px;overflow: scroll"></ul>
            <form>
                <div class="layui-form-item form-item-ex" style="text-align: center;">
                    <div class="layui-inline">
                        <div class="layui-input-inline input-line-ex line-button-ex">
                            <a class="layui-btn  layui-btn-sm"  href="javascript:void(0);" onclick="saveRoleMenu()" >保存</a>
                            <button type="reset" class="layui-btn layui-btn-primary  layui-btn-sm" style="margin-left: 70px;">重置</button>
                        </div>
                    </div>
                </div>
            </form>
        </fieldset>
    </div>
</div>

<%--操作模版--%>
<script type="text/html" id="barOption">
    <a class="layui-btn layui-btn-xs" lay-event="edit"><i class="layui-icon">&#xe642;</i>删除</a>
</script>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/ztree/dist/jquery.ztree.all-3.5.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/sys/role/sys_role_menu.js"></script>
</body>
</html>
