/**
 *  系统菜单 --js
 *      create by Hzhan 2018-01-05
 *  @type {*|jQuery}
 */

// 开启创建菜单页面
var openCreateMenuWindow = null;

var batchDelMenus = null;

var formSearch = null;

layui.use(['laydate', 'laypage', 'layer', 'table', 'form', 'element'], function () {

    var laydate = layui.laydate //日期
        , laypage = layui.laypage //分页
        , form = layui.form //分页
        ,layer = layui.layer //弹层
        , table = layui.table //表格
        , element = layui.element; //元素操作


    // 项目路径
    var serverPath = $('#path').val();


    // 设置表格宽高
    var contentWidth = $('#content')[0].offsetWidth;
    var contentHeight = $('#content')[0].offsetHeight;
    var tableWidth = contentWidth - 265;
    var tableHeight = contentHeight - 180;
    $('#tree')[0].style.height = contentHeight + 'px';
    $('#buttonQuoto')[0].style.width = tableWidth + 'px';

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

    /**
     *  初始化日期
     */
    laydate.render({
        elem: '#searchCreateTime'
    });

    /**
     *  初始化表格
     * @param tableData
     */
    var initMenuTable = function (tableData) {
        table.render({
            elem: '#layMenu'
            , id: 'idMenu'
            , height: tableHeight
            , width: tableWidth
            , data: tableData
            , loading: true
            , limit: page.limit
            , skin: 'row'
            , even: true
            , cols: [[ //表头
                {field: 'left', title: '', type: 'checkbox', width: 80, align: 'center'}
                , {field: '', title: '序号', type: 'numbers', width: 80, align: 'center'}
                , {field: 'name', title: '菜单名称', width: 200, align: 'center'}
                , {field: 'type', title: '菜单类型', width: 180, toolbar: '#menuBar', align: 'center'}
                , {field: 'icon', title: '图标', width: 130, align: 'center'}
                , {field: 'sort', title: '排序', width: 180, align: 'center'}
                , {field: 'parentId', title: '上级id', width: 180, align: 'center'}
                , {field: 'url', title: '链接地址', width: 180, align: 'center'}
                , {field: 'createTime', title: '创建时间', width: 170, align: 'center'}
                , {fixed: 'right', title: '操作', width: 200, align: 'center', toolbar: '#barOption'}
            ]]
        });
    }


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
                        page.limit = obj.limit;
                        filter.pageNum = obj.curr;
                        filter.pageSize = obj.limit;
                        getMenuListPage();
                    } else {
                        layer.msg('亲! 不要点击这么快啦~~~~ ');
                    }
                }
            }
        });
    }


    /**
     *  开启新增菜单页面
     */
    openCreateMenuWindow = function () {
        if (selectNode.name === '' || selectNode.name == null) {
            layer.msg('请选择左侧的菜单节点作为新增的菜单的菜单参考!');
        } else {
            $('#parentName')[0].value = selectNode.name;
            $('#parentId')[0].value = selectNode.parentId;
            $('#type')[0].value = selectNode.type + 1;
            layer.open({
                type: 1,
                title: ['新建菜单', 'font-size:18px;'],
                offset: 'auto',
                anim: 2,
                fixed: false,
                offset: '100px',
                scrollbar: false,
                resize: false,
                area: ['850px', '500px'],
                content: $('#createMenuWindow'),
                end: function () {
                    $('#createForm')[0].reset();
                }
            });
        }
    }

    /**
     *  监听 新增菜单的保存按钮操作
     */
    form.on('submit(saveMenu)', function (data) {
        saveMenu(data.field);
        return false;
    });

    //监听工具条
    table.on('tool(menuFilter)', function (obj) {
        if (obj.event === 'edit') {
            // 编辑
            openEditWindow(obj.data);
        } else if (obj.event === 'del') {
            layer.confirm('确定要删除该菜单', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                delMenuById(obj.data.id);
            });
        }
    });


    /**
     *  批量按钮删除
     */
    batchDelMenus = function () {
        layer.confirm('确定要删除所选的？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            var selectIds = [];
            var selectObjList = table.checkStatus('idMenu').data;
            if (selectObjList.length > 0) {
                for (var i = 0; i < selectObjList.length; i++) {
                    selectIds.push(selectObjList[i].id);
                }
                console.log(selectIds);
                batchDelMenusByIds(selectIds);
            } else {
                layer.msg('请选择要删除的菜单选项喔!');
            }
        }, function () {
        });
    }

    /**
     *  搜索功能
     */
    formSearch = function () {
        filter.name = $('#searchName')[0].value;
        filter.createTime = $('#searchCreateTime')[0].value;
        getMenuListPage();
    }

    /**
     *   开启编辑窗口
     * @param data  选中的行的数据
     */
    var openEditWindow = function (data) {

        // 隐藏
        $('#eidtDiv').css('display','none');


        // 数据回显
        $('#id')[0].value = data.id;
        $('#sort')[0].value = data.sort;
        $('#url')[0].value = data.url;
        $('#parentId')[0].value = data.parentId;
        $('#icon')[0].value = data.icon;
        $('#name')[0].value = data.name;

        layer.open({
            type: 1,
            title: ['编辑菜单', 'font-size:18px;'],
            offset: 'auto',
            anim: 2,
            fixed: false,
            offset: '100px',
            scrollbar: false,
            resize: false,
            area: ['850px', '500px'],
            content: $('#createMenuWindow'),
            end: function () {
                // 将回显的数据清空
                $('#createForm')[0].reset();
                // 显示
                $('#eidtDiv').css('display','');
            }
        });
    }


    /*****************************************  END   【  以上是lay组件初始化   】 *****************************************/

    /*****************************************  START 【 以下是获取数据 操作数据 】 *****************************************/

    /**
     *  初始化获取数据
     */
    var init = function () {

        initMenuTable(null);

        initPgae();

        getAllZtreeList();

        getMenuListPage();

    }

    /**
     *  初始化树
     */
    var initTree = function (data) {
        // ztree 设置
        var setting = {
            // 异步请求  --> 可以按需请求
            // async: {
            //     enable: true,
            //     url: serverPath + '/sys/menu/findListByZtree.do',
            //     autoParam: ["id"],
            //     dataFilter: datasFilter,
            //     type: 'post'
            // }
            callback: {
                onClick: zTreeOnClick
            }
        };
        var treeObj = $.fn.zTree.init($("#tree"), setting, data);
        treeObj.expandAll(true);
    }

    // 节点对象
    var selectNode = {
        parentId: null,
        name: null,
        type: null,
        id: null
    };

    /**
     *  树节点单击事件
     * @param event
     * @param treeId
     * @param treeNode
     */
    function zTreeOnClick(event, treeId, treeNode) {

        selectNode.parentId = treeNode.parentId;
        selectNode.name = treeNode.name;
        selectNode.type = treeNode.type;
        selectNode.id = treeNode.id;
    };

    /**
     *   树表返回数据 进行拼装渲染
     * @param treeId
     * @param parentNode
     * @param childNodes
     * @returns {null}
     */
    function datasFilter(treeId, parentNode, childNodes) {
        if (childNodes.code == '0') {
            if (childNodes.data.length < 1) {
                layer.msg('没有下级的菜单啦~~~');
                return null;
            }
            return childNodes.data;
        } else {
            layer.msg('获取下级菜单失败!');
        }
    }


    /**
     *  获取菜单数据
     */
    var getMenuListPage = function () {
        // 获取数据
        $.post(serverPath + "/sys/menu/findMenuListByPage.do", {
            pageNum: filter.pageNum,
            pageSize: filter.pageSize,
            name: $('#searchName')[0].value,
            createTime: $('#searchCreateTime')[0].value
        }, function (data) {
            if (data.code == '0') {
                // 处理 --
                initMenuTable(data.data.list);
                page.count = data.data.total;
                initPgae();
                isPaging = false;
                layer.closeAll();
            } else {
                openFailWindow(layer, serverPath, data);
            }
        });


    }

    /**
     *   保存系统菜单
     *      通过Id 判断是更新还是新增
     * @param data
     */
    var saveMenu = function (data) {
        var selectType = $("#selectType")[0].checked;

        if(selectNode.type=='2' && !selectType && data.id==''){
            layer.msg(' 只能支持三级菜单喔! ');
        }else{
            if(data.id==''){ // 新增
                if (selectType) {
                    // on : 同级    off : 上级
                    data.parentId = selectNode.parentId;
                    data.type = selectNode.type;
                }else if(!selectType){
                    data.parentId = selectNode.id;
                    data.type =selectNode.type+1;
                }
            }else{
            }


            var url = '';
            if (data.id != null && data.id != '') {
                url = serverPath + '/sys/menu/updateSysMenu.do';
            } else {
                url = serverPath + '/sys/menu/saveSysMenu.do'
            }
            $.post(url, data, function (data) {
                if (data.code == '0') {
                    layer.closeAll();
                    layer.msg(data.msg);
                    getMenuListPage();
                    getAllZtreeList();
                } else {
                    layer.msg(data.msg);
                }
            });
        }
    }

    /**
     *   通过Id 删除菜单
     * @param menuId
     */
    var delMenuById = function (menuId) {
        $.post(serverPath + '/sys/menu/delSysMenuById.do', {
            id: menuId
        }, function (data) {
            if (data.code == '0') {
                layer.closeAll();
                layer.msg(data.msg);
                getAllZtreeList();
                getMenuListPage();
            } else {
                layer.msg(data.msg);
            }
        });
    }

    /**
     *  批量删除 通过Id
     * @param ids
     */
    var batchDelMenusByIds = function (ids) {
        $.ajax({
            type: "POST",
            url: serverPath + "/sys/menu/batchDelSysMenuByIds.do",
            dataType: "json",
            traditional: true,
            data: {
                ids: ids
            },
            async: true,
            success: function (data) {
                if (data.code == '0') {
                    layer.closeAll();
                    layer.msg('删除成功!');
                    getMenuListPage();
                    getAllZtreeList();
                }
            },
            error: function (e) {
                layer.msg('删除失败!');
            }
        });
    }


    /**
     *  获取所有的菜单树形
     */
    var getAllZtreeList = function () {
        $.ajax({
            type: "POST",
            url: serverPath + '/sys/menu/findAllListByZtree.do',
            dataType: "json",
            async: true,
            success: function (data) {
                initTree(data.data);
            },
            error: function (e) {
                layer.msg('获取失败!');
            }
        });
    }


    init();
});

