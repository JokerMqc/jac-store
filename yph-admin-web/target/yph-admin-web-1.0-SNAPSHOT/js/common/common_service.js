/**
 *  Js 公共服务 ---> 封装增删改查方法.
 *  create by Hzhan
 *   2018-01-26
 */

/************** 项目路径全局配置  ***********/

// 项目路径
var serverPath = '';

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


/**************************************  table  ***********************************/

/**
 *  初始化分页
 */
var initPgae = function (laypage,page,filter,getTableList) {
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
var initTable = function (table,tableData,colsArr,page) {
    table.render({
        elem: '#tableElem'
        , id: 'tablelId'
        , height: tableHeight
        , data: tableData
        , loading: true
        , limit: page.limit
        , skin: 'row'
        , even: true
        , cols: colsArr
    });
}

















/**
 *  post 请求
 * @param url
 * @param params
 * @param success
 */
function post(url, params, success) {
    $.post(url, params, function (data) {
        if (data.code == '0') {
            success();
        } else {
            // 开启错误窗口
            openFailWindow(layer, serverPath, data);
        }
    });
}

/**
 *   删除 通过Id
 */
function delById(url, id, success) {
    $.post(url, {id: id}, function (data) {
        if (data.code == '0') {
            success();
        } else {
            // 开启错误窗口
            openFailWindow(layer, serverPath, data);
        }
    });
}

/**
 *  保存
 * @param url
 * @param obj
 * @param success
 */
function saveObj(url, obj, success) {
    $.post(url, obj, function (data) {
        if (data.code == '0') {

        } else {
            // 开启错误窗口
            openFailWindow(layer, serverPath, data);
        }
    });
}

/**
 *  获取表格信息
 * @param url
 * @param params
 * @param success
 */
function findTableList(url, params, success) {
    $.post(url, params, function (data) {
        if (data.code == '0') {
            success();
        } else {
            // 开启错误窗口
            openFailWindow(layer, serverPath, data);
        }
    });
}

/**
 *  更新方法
 * @param url
 * @param obj
 * @param success
 */
function updateObj(url, obj, success) {
    $.post(url, obj, function (data) {
        if (data.code == '0') {
            success();
        } else {
            // 开启错误窗口
            openFailWindow(layer, serverPath, data);
        }
    });
}

/**
 *  批量删除  通过Id
 * @param url
 * @param params
 * @param success
 */
function bathDelByids(url, params, success) {
    $.post(url, params, function (data) {
        if (data.code == '0') {
            success();
        } else {
            // 开启错误窗口
            openFailWindow(layer, serverPath, data);
        }
    });
}

/**
 *  获取表格中的复选框中的id
 * @param table
 * @param tableId
 * @returns {Array}
 */
function getIdsByCheckBoxSelect(table, tableId) {
    var selectIds = [];
    var selectObjList = table.checkStatus('idMenu').data;
    if (selectObjList.length > 0) {
        for (var i = 0; i < selectObjList.length; i++) {
            selectIds.push(selectObjList[i].id);
        }
    }
    return selectIds;
}


/**
 *  开启异常窗口
 * @param data
 */
function openFailWindow(layerObj, serverPath, data) {
    if (data.code == '2') {
        layerObj.open({
            type: 1,
            title: false,//不显示标题栏
            closeBtn: false,
            area: '300px;',
            shade: 0.8,
            id: 'LAY_layuipro',//设定一个id，防止重复弹出
            btn: ['wo 要去登录啦 !'],
            btnAlign: 'c',
            moveType: 1,//拖拽模式，0或者1
            content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">' +
            '你知道吗？亲！<br>你超时登录了! 请重新登录。<br><br>我们此后的征途是星辰大海 ^_^</div>',
            yes: function (layero) {
                window.location.href = serverPath + '/index.do';
            }
        });
    } else {
        layerObj.msg(data.msg)
    }
}