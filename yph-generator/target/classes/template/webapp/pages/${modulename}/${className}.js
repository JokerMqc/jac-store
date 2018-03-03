var query_action = contextRootPath + "/${modulename}/${classNameLowerCase}/query";
var queryResultTable = "${className}Table";
var page_toolBar = [ {
	text : '修改',
	align : 'right',
	iconCls : 'icon-edit',
	handler : function() {
		prepareUpdate();
	}
}, {
	text : '删除',
	align : 'right',
	iconCls : 'icon-remove',
	handler : function() {
		executeDelete();
	}
} ];
var page_contentColumnHeaders = [ [
		{
			field : 'checkBoxNo',
			checkbox : true
		},
		<#list table.columns as column>
			<#if column.pk>
		{
			field : '${column.columnNameFirstLower}',
			title : '${column.columnAlias}',
			align : 'center',
			sortable : true,
			formatter : function(value, row, index) {
				return '<a style="cursor:pointer;text-decoration: underline;" onclick="view(\''
						+ value + '\')">' + value + '</a>';
			}
		},
			</#if>
        </#list>	
		<#list table.columns as column>
		<#if !column.pk>
		{
			field : '${column.columnNameFirstLower}',
			title : '${column.columnAlias}',
			align : 'center',
			sortable : true
		}
			<#if column_has_next>,</#if>	
		</#if>
		</#list>
		] ];


/* 查询 */
function executeQuery() {
	var data = $("#fm").serialize();
	var send_url = query_action + "?" + data;
	$('#'+queryResultTable).datagrid({
		title : "查询结果",
		url : send_url,
		nowrap : true,
		striped : true,
		remoteSort : false,
		pageNumber : 1,
		pagination : true,
		columns : page_contentColumnHeaders,
		toolbar : page_toolBar
	});
}
/* 修改 */
function prepareUpdate() {
	var rows = $('#' + queryResultTable).datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}
	//请手工修改主键信息
	var url = contextRootPath+"/${modulename}/${classNameLowerCase}/prepareUpdate?id=" + rows[0]["id"];
	var handle = window.showModalDialog(url,window,
	"dialogHide:yes;help:no;status:no;scroll:yes;dogWidth:650px;dialogHeight:400px;resizable:yes");
	if(handle==null || handle=='undefine'){return;}
	if(handle==GLOBAL_SUCCESS){
		$('#'+queryResultTable).datagrid('reload');
	}
}
/* 新增 */
function prepareAdd() {
	var url = contextRootPath+'/${modulename}/${classNameLowerCase}/prepareAdd';
	var handle = window.showModalDialog(url,window,
	"dialogHide:yes;help:no;status:no;scroll:yes;dialogWidth:650px;dialogHeight:400px;resizable:yes");
	if(handle==null || handle=='undefine'){return;}
	if(handle==GLOBAL_SUCCESS){
		$('#'+queryResultTable).datagrid('reload');
	}
}
/* 查看 */
function view(params) {
	var url = contextRootPath+"/${modulename}/${classNameLowerCase}/view?id=" + params;
	window.showModalDialog(url,window,
	"dialogHide:yes;help:no;status:no;scroll:yes;dialogWidth:650px;dialogHeight:300px;resizable:yes");
}

/* 删除 */
function executeDelete() {
	var rows = $('#' + queryResultTable).datagrid('getSelections');
	if (rows == null) {
		return;
	}
	var num = rows.length;
	if (num != 1) {
		$.messager.alert('提示消息', "请选择一行", 'info');
		return;
	}
	$.messager.confirm('提示', '是否确认删除?', function(r){
		if (r){
			var url = contextRootPath+"/${modulename}/${classNameLowerCase}/delete?id=" + rows[0]["id"];
			 $.ajax({
					   type: "POST",
					   url: url,
					   success: function(result){
							  $.messager.alert('提示信息','记录删除成功！	','info');
							  $('#'+queryResultTable).datagrid('reload');
					   },
					   error:function(result){
						   alert("出错啦，请联系管理员");
					   }
			});
		}
	});
}

function closeWinAndReLoad() {
	try{
		window.opener.reLoadResult();
	}catch(e){}
	window.close();
}
/* 保存或更新 */
function executeSave(){
	if(!$('#fm').form('validate')){
	   	return false; 
	}else{
	    var url = null;
	    var data = $("#fm").serialize();
		if($('#operateType').val()=='update'){
			url = contextRootPath+"/${modulename}/${classNameLowerCase}/update?"+data;
		}
		else if($('#operateType').val()=='add'){
			url = contextRootPath+"/${modulename}/${classNameLowerCase}/add?"+data;
		}
		$.ajax({
			type: "POST",
			url: url,
			success: function(result){
				$.messager.alert('提示信息','操作成功！','info');
				$('#'+queryResultTable).datagrid('reload');
			},
			error:function(result){
				alert("出错啦，请联系管理员");
			}
		});
	}	    
}