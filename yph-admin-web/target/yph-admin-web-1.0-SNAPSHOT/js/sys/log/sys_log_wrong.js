/***
 *  系统角色 v 1.0
 *
 * @type {null}
 */
layui.use(['laydate', 'laypage', 'layer', 'table', 'form', 'element'], function () {

    var laydate = layui.laydate //日期
        , laypage = layui.laypage //分页
        , form = layui.form //分页
        ,layer = layui.layer //弹层
        , table = layui.table //表格
        , element = layui.element; //元素操作

    var tableData=[];

    var colsArr=[];

    /**
     *  初始化日期
     */
    laydate.render({
        elem: '#searchCreateTime'
    });



    //监听工具条
    table.on('tool(roleFilter)', function (obj) {
        if (obj.event === 'edit') {


        } else if (obj.event === 'del') {
            layer.confirm('确定要删除所选的?', {
                btn: ['确定', '取消'] //按钮
            }, function () {


            });
        }
    });


    /**
     *  监听 新增菜单的保存按钮操作
     */
    form.on('submit(saveSysRole)', function (data) {
        saveSysRole(data.field);
        return false;
    });


    /*****************************************  END   【  以上是lay组件初始化   】 *****************************************/


    /*****************************************  START 【 以下是获取数据 操作数据 】 *****************************************/

    /**
     *  页面初始化
     */
    var init = function () {

        initPgae(laypage,page,filter,getTableList);

        initTable(table,tableData,colsArr,page);

    }


    /**
     *  获取表格数据
     */
    var getTableList = function () {
        $.post(serverPath + "/sys/role/findSysRoleListByPage.do", {
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