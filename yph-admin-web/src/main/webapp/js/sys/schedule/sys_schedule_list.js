/***
 *  任务列表 v 1.0
 *
 * @type {null}
 */

var openCreateWindow = null;

var batchDel = null;

var formSearch = null;

layui.use(['laydate', 'laypage', 'layer', 'table', 'form', 'element'], function () {

    var laydate = layui.laydate //日期
        , laypage = layui.laypage //分页
        , form = layui.form //分页
    layer = layui.layer //弹层
        , table = layui.table //表格
        , element = layui.element; //元素操作


    // 项目路径
    var serverPath = $('#path').val();

    // 设置表格宽高
    var bodyWidth = $('#body')[0].offsetWidth;
    var contentHeight = $('#content')[0].offsetHeight;
    $('#content')[0].style.width = bodyWidth + 'px';
    var tableHeight = contentHeight - 176;


    /**
     *  搜索对象
     * @type {{pageNum: number, pageSize: number, name: null}}
     */
    var filter = {
        createTime: null,
        pageNum: 1,
        pageSize: 20,
        name: null
    };


    var isPaging = false;
    /**
     *  分页对象
     * @type {{count: null, limit: null, limits: null, curr: null}}
     */
    var page = {
        count: null,
        limit: 20,
        limits: [20, 30, 40, 50],
        curr: 1,
        groups: 5
    }

    /**
     *  初始化分页
     */
    var initPgae = function () {
        laypage.render({
            elem: 'pageDiv'
            , count: page.count
            , limit: page.limit
            , limits: page.limits
            , curr: page.curr
            , groups: page.groups
            , layout: ['prev', 'page', 'next', 'limit', 'skip', 'count']
            , jump: function (obj, first) {
                //首次不执行
                if (!first) {
                    if (!isPaging) {
                        layer.load(0, {shade: false});
                        isPaging = true;
                        page.curr = obj.curr;
                        page.limi
                        t = obj.limit;
                        filter.pageNum = obj.curr;
                        filter.pageSize = obj.limit;
                        getTableList();
                    } else {
                        layer.msg('亲! 不要点击这么快啦~~~~ ');
                    }
                }

            }
        });
    }


    /**
     *  初始化表格
     * @param tableData
     */
    var initTable = function (tableData) {
        table.render({
            elem: '#layMenu'
            , id: 'idRole'
            , height: tableHeight
            // , width: tableWidth
            , data: tableData
            , loading: true
            , limit: page.limit
            , skin: 'row'
            , even: true
            , cols: [[ //表头
                {field: 'left', title: '', type: 'checkbox', width: 80, align: 'center'}
                , {field: '', title: '序号', type: 'numbers', width: 80, align: 'center'}
                , {field: 'jobName', title: '任务名称', width: 200, align: 'center'}
                , {field: 'beanName', title: 'bean名称', width: 200, align: 'center'}
                , {field: 'methodName', title: '方法名', width: 180, align: 'center'}
                , {field: 'params', title: '参数', width: 180, align: 'center'}
                , {field: 'cronExpression', title: 'cron表达式', width: 180, align: 'center'}
                , {field: 'remark', title: '备注', width: 180, align: 'center'}
                , {field: 'status', title: '状态', width: 180, align: 'center', toolbar: '#statusOption'}
                , {field: 'createTime', title: '创建时间', width: 170, align: 'center'}
                , {fixed: 'right', title: '操作', width: 200, align: 'center', toolbar: '#barOption'}
            ]]
        });
    }


    /**
     *  开启创建窗口
     */
    openCreateWindow = function () {
        layer.open({
            type: 1,
            title: ['新建角色', 'font-size:18px;'],
            offset: 'auto',
            anim: 2,
            fixed: false,
            offset: '100px',
            scrollbar: false,
            resize: false,
            area: ['850px', '380px'],
            content: $('#createWindow'),
            end: function () {
                // 表单重置
                $('#createForm')[0].reset();
            }
        });
    }

    /**
     *  初始化日期
     */
    laydate.render({
        elem: '#searchCreateTime'
    });


    //监听工具条
    table.on('tool(roleFilter)', function (obj) {
        if (obj.event === 'delTask') {
            // 删除
            layer.confirm('确定要删除所选的?', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                delTask(obj);
            });
        }
    });

    /**
     *  开启编辑窗口
     * @param data
     */
    var openEditWindow = function (data) {
        // 数据回显
        $('#jobName')[0].value = data.jobName;
        $('#beanName')[0].value = data.beanName;
        $('#methodName')[0].value = data.methodName;
        $('#params')[0].value = data.params;
        $('#cronExpression')[0].value = data.cronExpression;
        $('#remark')[0].value = data.remark;
        $('#jobId')[0].value = data.id;
        $('#status')[0].value = data.status;

        layer.open({
            type: 1,
            title: ['修改角色', 'font-size:18px;'],
            offset: 'auto',
            anim: 2,
            fixed: false,
            offset: '100px',
            scrollbar: false,
            resize: false,
            area: ['850px', '380px'],
            content: $('#createWindow'),
            end: function () {
                // 表单重置
                $('#createForm')[0].reset();
            }
        });
    }

    /**
     *  监听 新增菜单的保存按钮操作
     */
    form.on('submit(save)', function (data) {
        // 保存
        saveScheduleJob(data.field);

        return false;
    });


    /**
     *  批量删除
     */
    batchDel = function () {
        layer.confirm('确定要删除所选的？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            var selectIds = [];
            var selectObjList = table.checkStatus('idRole').data;
            if (selectObjList.length > 0) {
                for (var i = 0; i < selectObjList.length; i++) {
                    selectIds.push(selectObjList[i].id);
                }
                batchDelByIds(selectIds);
            } else {
                layer.msg('请选择要删除的喔!');
            }
        }, function () {
        });
    }

    /**
     *  搜索功能
     */
    formSearch = function () {
        getTableList();
    }

    /*****************************************  END   【  以上是lay组件初始化   】 *****************************************/

    /*****************************************  START 【 以下是获取数据 操作数据 】 *****************************************/

    /**
     *  页面初始化
     */
    var init = function () {

        initPgae();

        initTable();

        getTableList();

    }


    /**
     *  获取表格数据
     */
    var getTableList = function () {
        $.post(serverPath + "/sys/schedule/findScheduleJobByPage.do", {
            pageNum: filter.pageNum,
            pageSize: filter.pageSize,
            jobName:$('#searchName')[0].value
        }, function (data) {
            if (data.code == '0') {
                // 处理 --
                initTable(data.data.list);
                page.count = data.data.total;
                initPgae();
                isPaging = false;
                layer.closeAll();
            } else {
                layer.msg('查询错误 : ' + data.msg);
            }
        });
    }


    /***************************  定时任务 ***************************/

    /**
     *  保存新建任务
     */
    var saveScheduleJob = function (fromData) {
        var url = serverPath;
        if (fromData.id != '') {
            // 更新
            url = url + '/sys/schedule/updateScheduleJob.do';
        } else {
            url = url + '/sys/schedule/saveScheduleJob.do';
        }

        $.post(url, fromData, function (data) {
            if (data.code == '0') {
                getTableList();
                layer.closeAll();
                layer.msg(data.msg);
            } else {
                // 开启错误窗口
                openFailWindow(layer, serverPath, data);
            }
        });
    }


    /**
     *  开启任务
     * @param obj
     */
    var startTask = function (obj) {
        $.post(serverPath + '/sys/schedule/runScheduleJobById.do', {
            id: obj.data.id
        }, function (data) {
            if (data.code == '0') {
                getTableList();
                layer.closeAll();
                layer.msg('开启任务成功!');
            } else {
                // 开启错误窗口
                openFailWindow(layer, serverPath, data);
            }
        });
    }

    /**
     *  暂停任务
     * @param obj
     */
    var pauseTask = function (obj) {
        $.post(serverPath + '/sys/schedule/pauseScheduleJobById.do', {
            id: obj.data.id
        }, function (data) {
            if (data.code == '0') {
                getTableList();
                layer.closeAll();
                layer.msg('已成功暂停了任务了哈!');
            } else {
                // 开启错误窗口
                openFailWindow(layer, serverPath, data);
            }
        });

    }

    /**
     *  恢复任务
     * @param obj
     */
    var resumeTask = function (obj) {
        $.post(serverPath + '/sys/schedule/resumeScheduleJobById.do', {
            id: obj.data.id
        }, function (data) {
            if (data.code == '0') {
                getTableList();
                layer.closeAll();
                layer.msg('成功恢复任务了哈!');
            } else {
                // 开启错误窗口
                openFailWindow(layer, serverPath, data);
            }
        });
    }

    /**
     *  删除任务
     * @param obj
     */
    var delTask = function (obj) {
        $.post(serverPath + '/sys/schedule/delScheduleJobById.do', {
            id: obj.data.id
        }, function (data) {
            if (data.code == '0') {
                getTableList();
                layer.closeAll();
                layer.msg('删除任务成功 !');
            } else {
                // 开启错误窗口
                openFailWindow(layer, serverPath, data);
            }
        });
    }


    /**
     *   批量删除
     * @param ids
     */
    var batchDelByIds = function (ids) {
        $.ajax({
            type: "POST",
            url: serverPath + "/sys/schedule/batchDelScheduleJob.do",
            dataType: "json",
            traditional: true,
            data: {
                jobIds: ids
            },
            async: true,
            success: function (data) {
                if (data.code == '0') {
                    layer.closeAll();
                    layer.msg('删除成功!');
                    getTableList();
                }
            },
            error: function (e) {
                layer.msg('删除失败!');
            }
        });
    }


    init();
});