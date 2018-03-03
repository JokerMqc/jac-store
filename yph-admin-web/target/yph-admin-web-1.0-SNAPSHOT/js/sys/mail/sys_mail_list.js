/***
 *  系统角色 v 1.0
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
            , layout: ['prev', 'page', 'next', 'limit', 'skip' ,'count']
            , jump: function (obj, first) {
                //首次不执行
                if (!first) {
                    if (!isPaging) {
                        layer.load(0, {shade: false});
                        isPaging = true;
                        page.curr = obj.curr;
                        page.limit = obj.limit;
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
                , {field: 'fromEmail', title: '发送的邮箱', width: 200, align: 'center'}
                , {field: 'fromName', title: '发送人', width: 180, align: 'center'}
                , {field: 'toEmails', title: '接收邮箱', width: 180, align: 'center'}
                , {field: 'subject', title: '主题', width: 170, align: 'center'}
                , {field: 'context', title: '邮件内容', width: 170, align: 'center'}
                , {field: 'type', title: '邮件邮件类型', width: 170, align: 'center'}
                , {field: 'sendStatus', title: '状态', width: 170, align: 'center',templet: '#sendStat'}
                , {field: 'createTime', title: '创建时间', width: 170, align: 'center'}
                , {fixed: 'right', title: '操作', width: 200, align: 'center', toolbar: '#barOption'}
            ]]
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
        if (obj.event === 'edit') {
            openEditWindow(obj.data);
        } else if (obj.event === 'del') {
            layer.confirm('确定要删除所选的?', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                delSysRole(obj.data.id);
            });
        }
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
                batchDelSysRoleByIds(selectIds);
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
        $.post(serverPath + "/sys/email/findMailListByPage.do", {
            pageNum: filter.pageNum,
            pageSize: filter.pageSize,
            roleName: $('#searchName')[0].value,
            createTime: $('#searchCreateTime')[0].value
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



    init();
});