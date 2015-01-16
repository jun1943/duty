/*
 *定位设备管理
 *
 *包括增、删、改、查
 *
 *
 */


var m_Gpsdevice_OrgId;
var m_Gpsdevice_OrgCode;
var m_Gpsdevice_OrgPath;
var m_Gpsdevice_UserId;
var m_Gpsdevice_Query = {};
var m_Icons_Query = {};
var operationType = "";
$(function() {

	$("#gpsdeviceinfowindow").window("close");

	var args = getUrlArgs();
	m_Gpsdevice_OrgId = args["orgId"];
	m_Gpsdevice_OrgCode = args["orgCode"];
	m_Gpsdevice_OrgPath = args["orgPath"];
	m_Gpsdevice_UserId = args["userId"];

	pack_Gpsdevice_Query();
	m_Icons_Query.name = "";
	m_Icons_Query.typeid = 0;
	$("#orgtree").tree(
			{
				url : "org/list.do?orgCode=" + m_Gpsdevice_OrgCode
						+ "&orgPath=" + m_Gpsdevice_OrgPath,
				loadFilter : function(data) {
					return buildOrgTree(data);
				}
			});

	$('#dtGpsdevice')
			.datagrid(
					{
						url : "gpsdevice/getGpsdeviceList.do",
						queryParams : {
							'gpsdevice_Query' : JSON
									.stringify(m_Gpsdevice_Query)
						},
						pagination : true,
						fitColumns : true,
						pageNumber : 1,
						pageSize : 10,
						title : '定位设备列表',
						onDblClickRow : dblClickRow,
					    onClickRow: clickRow,
						// singleSelect : true,
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
									title : '机构',
									field : 'orgName',
									align : 'center',
									width : 100,
									hidden : true
								},
								{
									title : 'GPS类型',
									field : 'typeName',
									align : 'center',
									width : 100
								},
								{
									title : 'GPS显示名称',
									field : 'gpsName',
									align : 'center',
									width : 100
								},
								{
									title : 'GPS编号',
									field : 'number',
									align : 'center',
									width : 100
								},
								{
									title : 'GPS图标',
									field : 'iconUrl',
									align : 'center',
									width : 150,
									formatter : function(value, rowData, index) {
										var src = value.substring(1,
												value.length);
										return "<img style='width:25px; height:25px' src='"
												+ src + "' />";
									}
								},
								{
									title : '操作项',
									aligh : 'center',
									field : 'operator',
									width : 80,
									formatter : function(value, row, index) {
										return '<a href="javascript:void(0);" class="easyui-linkbutton"'
												+ 'iconcls="icon-tianyi-edit" style="color:blue"  onclick="btnCellClick('
												+ index + ')">修改</a>';
									}
								} ] ]
					});
	$("#btnSearchGpsdevice").bind("click", function() {
		$('#my-search-box').toggle();
	});
	InitData();
});

//取消点击行选中
function clickRow(index, data) {
  $("#dtGpsdevice").datagrid("unselectRow", index);
}
function btnSearchAction() {
	pack_Gpsdevice_Query();
	$('#dtGpsdevice').datagrid("reload", {
		'gpsdevice_Query' : JSON.stringify(m_Gpsdevice_Query)
	});
//	$("#isSubOrg").combobox("setValue", "");
//	$("#txtsearchname").val("");
};
function InitData() {
	getGpsType();
	getGpsIconList();
};
//获取GPS类型列表
function getGpsType() {
	getBaseDataCombobox("gpsdevice/getGpsType.do", "txttype");
};
//获取相关上传图标列表，以下拉列表的形式
function getGpsIconList() {
	$("#txtgpsicon")
			.combogrid(
					{
						url : 'icons/getIconsList.do',
						queryParams : {
							'icons_Query' : JSON.stringify(m_Icons_Query)
						},
						idField : 'id',
						textField : 'name',
						pagination : true,
						pageNumber : 1,
						pageSize : 10,
						fitColumns : true,
						panelWidth : 430,
						singleSelect : true,
						columns : [ [
								{
									field : 'id',
									title : 'id',
									width : 100,
									hidden : true
								},
								{
									field : 'iconUrl',
									title : '缩略图',
									width : 40,
									align : 'center',
									formatter : function(value, rowData, index) {
										var src = value.substring(1,
												value.length);
										return "<img style='width:25px; height:25px' src='"
												+ src + "' />";
									}
								},
								{
									field : 'name',
									title : '图片名称',
									width : 80,
									align : 'left',
									formatter : function(value, rowData, index) {
										if (rowData.typeId == 1) {
											return "警员图标:" + rowData.name;
										} else if (rowData.typeId == 2) {
											return "车辆图标:" + rowData.name;
										} else if (rowData.typeId == 3) {
											return "武器图标:" + rowData.name;
										} else if (rowData.typeId == 4) {
											return "定位设备图标:" + rowData.name;
										}
									}
								} ] ],
						onSelect : function(index, data) {
							$("#txtIconUrl").val(data.iconUrl);
							$("#sltImage").attr(
									"src",
									data.iconUrl.substring(1,
											data.iconUrl.length));
						}
					});
}
function btnAddGpsdevice(optType) {
	operationType = optType;
	clearForm();
	//$('#myModal').modal('show');
	$("#gpsdeviceinfowindow").window("open");
	$("#btnsaveDeviceCon").show();
};


function btnCellClick(index) {
	operationType = "edit";
	var row = $("#dtGpsdevice").datagrid('getData').rows[index];
	editGpsdeviceModel(row);
}
function dblClickRow(index,rowData){
	operationType = "edit";
	editGpsdeviceModel(rowData);
}
function editGpsdeviceModel(rows){
	clearForm();
	$("#gpsdeviceId").val(rows.id);
	$("#txttype").combobox("setValue", rows.typeId);
	$("#txtgpsname").val(rows.gpsName);
	$("#txtgpsicon").combogrid("setValue", rows.gpsName);
	$("#txtgpsnumber").val(rows.number);
	$("#txtIconUrl").val(rows.iconUrl);
	$("#sltImage").attr("src",
			rows.iconUrl.substring(1, rows.iconUrl.length));
	// $('#myModal').modal('show');
	$("#gpsdeviceinfowindow").window("open");
	$("#btnsaveDeviceCon").hide();
}
function btnEditGpsdevice(optType) {
	operationType = optType;
	var hasRows = $('#dtGpsdevice').datagrid('getRows');
	if (hasRows.length == 0) {
		$.messager.alert('操作提示', "没有可操作数据", "warning");
		return;
	}
	var rows = $("#dtGpsdevice").datagrid("getChecked");
	if (!rows || rows.length == 0) {
		$.messager.alert('操作提示', "请选择操作项!", "warning");
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('操作提示', "只能选择单个操作项!", "warning");
		return;
	}
	 
	editGpsdeviceModel(rows[0]);
	// $('#myModal').modal('show');
};
function clearForm() {
	$("#gpsdeviceId").val(0);
	$("#txttype").combobox("setValue", "");
	$("#txtgpsname").val("");
	$("#txtgpsnumber").val("");
	$("#txtIconUrl").val("");
	$("#txtgpsicon").combogrid("setValue", "");
	$("#sltImage").attr("src", "");
}
function pack_Gpsdevice_Query() {
	m_Gpsdevice_Query.orgId = m_Gpsdevice_OrgId;
	m_Gpsdevice_Query.orgCode = m_Gpsdevice_OrgCode;
	m_Gpsdevice_Query.orgPath = m_Gpsdevice_OrgPath;
	if ($("#isSubOrg").combobox("getValue") != ""
			&& $("#isSubOrg").combobox("getValue") > 0) {
		m_Gpsdevice_Query.isSubOrg = $("#isSubOrg").combobox("getValue");
	} else {
		m_Gpsdevice_Query.isSubOrg = 0;
	}
	m_Gpsdevice_Query.gpsname = $("#txtsearchname").val();
};

function btnDelGpsdevice() {
	var hasRows = $('#dtGpsdevice').datagrid('getRows');
	if (hasRows.length == 0) {
		$.messager.alert('操作提示', "没有可操作数据", "warning");
		return;
	}
	var rows = $("#dtGpsdevice").datagrid("getChecked");
	if (!rows || rows.length == 0) {
		$.messager.alert('操作提示', "请选择操作项!", "warning");
		return;
	}
	var ids = "";
	if (rows.length == 1) {
		ids = rows[0].id;
	} else {
		for ( var i = 0; i < rows.length; i++) {
			ids += rows[i].id + ",";
		}
		if (ids.length > 2) {
			ids = ids.substring(0, ids.length - 1);
		}
	}
	// if (rows.length > 1) {
	// $.messager.alert('操作提示', "只能选择单个操作项!", "warning");
	// return;
	// }
	// var gpsName = rows[0].gpsName;
	// var id = rows[0].id;
	$.messager.confirm("系统提示", "确认删除GPS设备数据信息吗？", function(r) {
		if (r) {
			deleteGpsdevice(ids);
		}
	});
};
//删除事件
function deleteGpsdevice(id) {
	$.ajax({
		url : "gpsdevice/deleteGpsdevice.do",
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
};
var isComplete = true;
function saveGpsdeviceAction() {
	saveGpsModel();
};
//保存方法模块
function saveGpsModel() {
	var gpsdevice = {};
	gpsdevice.id = $("#gpsdeviceId").val();

	if ($("#txttype").combobox("getValue") > 0
			&& $("#txttype").combobox("getValue") != "") {
		gpsdevice.typeId = $("#txttype").combobox("getValue");
	} else {
		$.messager.alert("错误提示", "请选择GPS类型", "error");
		isComplete = false;
		return;
	}
	if ($.trim($("#txtgpsname").val()) == "") {
		$.messager.alert("错误提示", "请输入GPS设备名称", "error");
		isComplete = false;
		return;
	}
	if ($.trim($("#txtgpsname").val()).length>20) {
		$.messager.alert("错误提示", "GPS设备名称长度过长，限制长度1--20！", "error");
		isComplete = false;
		return;
	}
	gpsdevice.gpsName = $.trim($("#txtgpsname").val());
	if ($.trim($("#txtgpsnumber").val()) == "") {
		$.messager.alert("错误提示", "请输入GPS设别编号", "error");
		isComplete = false;
		return;
	}
	if ($.trim($("#txtgpsnumber").val()).length>20) {
		$.messager.alert("错误提示", "GPS设备编号长度过长，限制长度1--20！", "error");
		isComplete = false;
		return;
	}
	var gpsnumber = $.trim($("#txtgpsnumber").val());
	if (operationType == "add") {
		isExistGpsdevice(gpsnumber);
		if (!isExist) {
			$.messager.alert("错误提示", "编号为   "+gpsnumber+" 的定位设备已存在，请检查！", "error");
			$("#txtgpsnumber").focus();
			isComplete = false;
			return;
		}
	}  
	gpsdevice.number = gpsnumber;
	gpsdevice.orgId = m_Gpsdevice_OrgId;
	if ($("#txtIconUrl").val() == "") {
		$.messager.alert("错误提示", "请选择GPS图标", "error");
		isComplete = false;
		return;
	}
	gpsdevice.iconUrl = $("#txtIconUrl").val();

	$.ajax({
		url : "gpsdevice/saveGpsdevice.do",
		type : "POST",
		dataType : "json",
		async : false,
		data : gpsdevice,
		success : function(req) {
			// $.messager.alert("消息提示", req.Message, "info");
			isComplete = true;
			clearForm();
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
function saveGpsdeviceActionExit() {
	saveGpsModel();
	if (isComplete) {
		$("#gpsdeviceinfowindow").window("close");
	}
}


//判断警员是否存在
function isExistGpsdevice(param) {
	isExist = false;
	$.ajax({
		url : "gpsdevice/isExistGpsDevice.do",
		type : "POST",
		dataType : "json",
		async : false,
		data : {
			"param" : param
		},
		success : function(req) {
			if (req.isSuccess && req.Message == "UnExits") {
				isExist = true;
			}
		}
	});
}
/**
 * 导出数据
 */
function btnExportAction(){
	pack_Gpsdevice_Query();
	$.ajax({
		url : "gpsdevice/exportDataToExcle.do",
		type : "POST",
		dataType : "json",
		async : false,
		timeout : 60000,
		data : {
			'gpsdevice_Query' : JSON.stringify(m_Gpsdevice_Query)
		},
		success : function(req) {
			if (req.isSuccess) {
				var urlStr = req.Data.substring(1, req.Data.length);
				if (/msie/.test(navigator.userAgent.toLowerCase())) {
					urlStr = "../../" + urlStr;
				}
				window.location.href = urlStr;
			}
		},
		failer : function(a, b) {
			$.messager.alert("消息提示", a, "info");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.messager.alert("消息提示", errorThrown, "error");
		}
	});
}