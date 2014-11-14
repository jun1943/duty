var m_Icons_OrgId;
var m_Icons_OrgCode;
var m_Icons_OrgPath;
var m_Icons_Query = {};
$(function() {

	$("#iconsinfowindow").window("close");

	var args = getUrlArgs();
	m_Icons_OrgId = args["orgId"];
	m_Icons_OrgCode = args["orgCode"];
	m_Icons_OrgPath = args["orgPath"];
	pack_Icons_Query();

	// $("#orgtree").tree({
	// url: "org/list.do?orgCode=" + m_Icons_OrgCode + "&orgPath=" +
	// m_Icons_OrgPath,
	// loadFilter : function(data) {
	// return buildOrgTree(data);
	// }
	// });

	$('#dtIcons')
			.datagrid(
					{
						url : "icons/getIconsList.do",
						queryParams : {
							'icons_Query' : JSON.stringify(m_Icons_Query)
						},
						pagination : true,
						fitColumns : true,
						pageNumber : 1,
						pageSize : 10,
						title : '图标列表',
					    onDblClickRow:btnEditIcons,
					    singleSelect: true,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									title : 'Id',
									field : 'id',
									align : 'center',
									width : 10,
									hidden : true
								},
								{
									title : '图标类型',
									field : 'typeName',
									align : 'center',
									width : 100,
									formatter : function(value, rowData, index) {
										if (rowData.typeId == 1) {
											return "警员";
										} else if (rowData.typeId == 2) {
											return "车辆";
										} else if (rowData.typeId == 3) {
											return "武器";
										} else if (rowData.typeId == 4) {
											return "定位设备";
										}
									}
								},
								{
									title : '图标名称',
									field : 'name',
									align : 'center',
									width : 100
								},
								{
									title : '缩略图',
									field : 'iconUrl',
									align : 'center',
									width : 100,
									formatter : function(value, rowData, index) {
										var src = value.substring(1,
												value.length);
										return "<img style='width:25px; height:25px' src='"
												+ src + "' />";
									}
								} ] ]
					});
	$("#btnSearchIcons").bind("click", function() {
		$('#my-search-box').toggle();
	});
	InitUploadFun();
});
// 打包查询条件
function pack_Icons_Query() {
	m_Icons_Query.name = $("#txtsearchName").val();
	if ($("#sltType").combobox("getValue") != ""
			&& $("#sltType").combobox("getValue") > 0) {
		m_Icons_Query.typeid = $("#sltType").combobox("getValue");
	} else {
		m_Icons_Query.typeid = 0;
	}
};

// 查询按钮事件
function btnSearchAction() {
	pack_Icons_Query();
	$('#dtIcons').datagrid("reload", {
		'icons_Query' : JSON.stringify(m_Icons_Query)
	});
	$("#isSubOrg").combobox("setValue", 0);
	$("#txtsearchName").val("");
	$("#sltType").combobox("setValue", 0);
};
// 新增开始
function btnAddIcons() {
	clearForm();
	// $('#myModal').modal('show');
	$("#iconsinfowindow").window("open");
};
function btnEditIcons() {
	var hasRows = $('#dtIcons').datagrid('getRows');
	if (hasRows.length == 0) {
		$.messager.alert('操作提示', "没有可操作数据", "warning");
		return;
	}
	var rows = $("#dtIcons").datagrid("getChecked");
	if (!rows || rows.length == 0) {
		$.messager.alert('操作提示', "请选择操作项!", "warning");
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('操作提示', "只能选择单个操作项!", "warning");
		return;
	}
	clearForm();

	$("#iconsId").val(rows[0].id);
	$("#txttype").combobox("setValue", rows[0].typeId);
	$("#txtname").val(rows[0].name); 
	$("#txtfilename").val(rows[0].iconUrl.substring(1, rows[0].iconUrl.length));
	$("#sltImage").attr("src",
			rows[0].iconUrl.substring(1, rows[0].iconUrl.length));
	$("#iconsinfowindow").window("open");
};
function btnDelIcons() {
	var hasRows = $('#dtIcons').datagrid('getRows');
	if (hasRows.length == 0) {
		$.messager.alert('操作提示', "没有可操作数据", "warning");
		return;
	}
	var rows = $("#dtIcons").datagrid("getChecked");
	if (!rows || rows.length == 0) {
		$.messager.alert('操作提示', "请选择操作项!", "warning");
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('操作提示', "只能选择单个操作项!", "warning");
		return;
	}
	var iconId = rows[0].id;
	var name = rows[0].name;
	$.messager.confirm("系统提示", "确认删除名为    " + name + " 的数据信息吗？", function(r) {
		if (r) {
			deleteIcons(iconId);
		}
	});
}
function deleteIcons(id) {
	$.ajax({
		url : "icons/deleteIcons.do",
		type : "POST",
		dataType : "json",
		async : false,
		data : {
			"id" : id
		},
		success : function(req) {
			$.messager.alert("消息提示", req.Message, "info");
			btnSearchAction();
		},
		failer : function(a, b) {
			$.messager.alert("消息提示", a, "info");
		},
		error : function(a) {
			$.messager.alert("消息提示", a, "error");
		}
	});
}
// 清空form表单
function clearForm() {
	$("#iconsId").val(0);
	$("#txttype").combobox("setValue", 0);
	$("#txtname").val("");
	$("#txtfilename").val("");
	$("#sltImage").attr("src", "");
};
function saveIconsAction() {
	$("#iconsId").val(0);
	$("#txttype").combobox("setValue", 0);
	$("#txtname").val("");
	$("#txtfilename").val("");
}