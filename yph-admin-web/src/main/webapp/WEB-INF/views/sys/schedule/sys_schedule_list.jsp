<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html style="height:100%;width:100%;overflow:hidden;">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>角色列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layex/lay-ex.css" media="all">
</head>
<body id="body" style="height:100%;width: 100%;padding:0;margin: 0;">
<input type="text" id="path" hidden="hidden" value="${pageContext.request.contextPath}">
<div id="content" style="height: 100%;padding:0;margin: 0;overflow: hidden;">
    <%--搜索--%>
    <blockquote id="searchQuoto" style="margin-top: 5px;" class="layui-elem-quote elem-quote-ex">
        <form class="layui-form" action="">
            <div class="layui-form-item form-item-ex">
                <div class="layui-inline ">
                    <label class="layui-form-label form-label-ex-4">任务名称:</label>
                    <div class="layui-input-inline input-line-ex">
                        <input type="text" id="searchName" name="jobName" placeholder="输入任务名称搜索" autocomplete="off"
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
                <div class="layui-inline">
                    <div class="layui-input-inline input-line-ex line-button-ex">
                        <a class="layui-btn  layui-btn-sm" href="javascript:void(0);" onclick="formSearch()">查询</a>
                        <button type="reset" class="layui-btn layui-btn-primary  layui-btn-sm">重置</button>
                    </div>
                </div>
            </div>
        </form>
    </blockquote>

    <%--按钮条--%>
    <blockquote id="buttonQuoto" style="margin-top: 5px;" class="layui-elem-quote elem-quote-ex">
        <button style="margin-left: 10px;" class="layui-btn layui-btn-sm" onclick="openCreateWindow()"><i class="layui-icon">&#xe608;</i>新增任务
        </button>
        <button style="margin-left: 10px; " class="layui-btn layui-btn-danger layui-btn-sm" onclick="batchDel()">
            <i class="layui-icon">&#xe640;</i> 批量删除
        </button>
    </blockquote>

    <%--数据表格--%>
    <table class="layui-hide" id="layMenu" lay-data="{id: 'idMenu'}" lay-filter="roleFilter"></table>

    <%--数据分页条--%>
    <div id="pageDiv" style="margin-left: 10px;"></div>

    <%--新建 弹出框--%>
    <div id="createWindow" hidden="hidden" style="overflow: hidden">
        <blockquote class="layui-elem-quote" style="margin-top: 5px;">
           定时任务: 可以定时操作任务,支持暂停 停止  删除 恢复立即运行.
        </blockquote>
        <fieldset class="layui-elem-field" style="width: 830px;margin-left: 8px;">
            <legend style="margin: 0px;padding: 0px;">填写任务信息</legend>
            <div class="layui-field-box">
                <form id="createForm" class="layui-form" action="">
                    <div class="layui-form-item form-item-ex">
                        <div class="layui-row">
                            <div class="layui-col-xs6">
                                <label class="layui-form-label form-label-ex-4">任务名称:</label>
                                <div class="layui-input-inline form-input-line-ex">
                                    <input type="text" id="jobName" name="jobName" lay-verify="required" required autocomplete="off" class="layui-input">
                                </div>
                            </div>

                            <div class="layui-col-xs6">
                                <label class="layui-form-label form-label-ex-4">bean名称:</label>
                                <div class="layui-input-inline form-input-line-ex">
                                    <input type="text" id="beanName" name="beanName" lay-verify="required" required autocomplete="off" class="layui-input">
                                </div>
                            </div>

                        </div>
                    </div>

                    <div class="layui-form-item form-item-ex">
                        <div class="layui-row">

                            <div class="layui-col-xs6">
                                <label class="layui-form-label form-label-ex-4">方法名称:</label>
                                <div class="layui-input-inline form-input-line-ex">
                                    <input type="text" id="methodName" name="methodName" lay-verify="required" required autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-col-xs6">
                                <label class="layui-form-label form-label-ex-4">方法参数:</label>
                                <div class="layui-input-inline form-input-line-ex">
                                    <input type="text" id="params" name="params"  autocomplete="off" class="layui-input">
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="layui-form-item form-item-ex">
                        <div class="layui-row">

                            <div class="layui-col-xs6">
                                <label class="layui-form-label form-label-ex-4">cron表达式:</label>
                                <div class="layui-input-inline form-input-line-ex">
                                    <input type="text" id="cronExpression" lay-verify="required" name="cronExpression" required autocomplete="off" class="layui-input">
                                </div>
                            </div>

                            <div class="layui-col-xs6">
                                <label class="layui-form-label form-label-ex-4">备注:</label>
                             <div class="layui-input-inline form-input-line-ex">
                                    <input type="text" id="remark" name="remark"  autocomplete="off" class="layui-input">
                                </div>
                            </div>

                        </div>
                    </div>

                    <hr class="layui-bg-gray">
                    <div class="layui-form-item form-item-ex" style="text-align: center;">
                        <div class="layui-inline">
                            <div class="layui-input-inline input-line-ex line-button-ex">
                                <button class="layui-btn  layui-btn-sm" lay-submit="" lay-filter="save">保存</button>
                                <button type="reset" class="layui-btn layui-btn-primary  layui-btn-sm"
                                        style="margin-left: 70px;">重置
                                </button>
                            </div>
                        </div>
                    </div>
                    <input type="text" id="jobId" name ="id" value="" hidden="hidden">
                    <input type="text" id="status" name ="status" value="" hidden="hidden">
                </form>
            </div>
        </fieldset>
    </div>

    </div>

<%--状态模版--%>
<script type="text/html" id="statusOption">
    {{# if(d.status=='0'){   }}
    <span class="layui-badge layui-bg-blue">执行中..</span>
    {{# } else if(d.status=='1'){  }}
    <span class="layui-badge layui-bg-cyan">已暂停..</span>
    {{# } }}
</script>


<%--操作模版--%>
<script type="text/html" id="barOption">
    {{# if(d.status=='0'){  }}
         <a class="layui-btn   layui-btn-warm layui-btn-xs" lay-event="pauseTask"><i class="layui-icon">&#xe623;</i>暂停</a>
    {{# } else if(d.status=='1'){  }}
         <a class="layui-btn  layui-btn-normal layui-btn-xs" lay-event="resumeTask"><i class="layui-icon">&#xe623;</i>恢复</a>
    {{# } }}
    <a class="layui-btn layui-btn-xs" lay-event="edit"><i class="layui-icon">&#xe642;</i>编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delTask"><i class="layui-icon">&#xe640;</i>删除</a>
</script>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/common/common_service.js"></script>
<script src="${pageContext.request.contextPath}/js/sys/schedule/sys_schedule_list.js"></script>
</body>
</html>
