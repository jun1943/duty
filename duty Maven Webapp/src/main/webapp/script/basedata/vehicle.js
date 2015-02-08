/*
 * 车辆管理；
 * 
 * 包括车辆的增删改查
 */

var m_Vehicle_OrgId;
var m_Vehicle_OrgCode;
var m_Vehicle_OrgPath;
var m_Vehicle_Query = {};
var operationType = "";
$(function() {

	$("#vehicleinfowindow").window("close");
	$("#vehicleInfoinportwindow").window("close");

	var args = getUrlArgs();
	m_Vehicle_OrgId = args["orgId"];
	m_Vehicle_OrgCode = args["orgCode"];
	m_Vehicle_OrgPath = args["orgPath"];
	m_Vehicle_UserId = args["userId"];
	pack_Vehicle_Query();

	$("#orgtree").tree(
			{
				url : "org/list.do?orgCode=" + m_Vehicle_OrgCode + "&orgPath="
						+ m_Vehicle_OrgPath,
				loadFilter : function(data) {
					return buildOrgTree(data);
				}
			});

	$('#dtVehicle')
			.datagrid(
					{
						url : "vehicle/getVehicleList.do",
						queryParams : {
							'vehicle_Query' : JSON.stringify(m_Vehicle_Query)
						},
						pagination : true,
						fitColumns : true,
						pageNumber : 1,
						pageSize : 10,
						width : '100%',
						title : '车辆列表',
						onDblClickRow : dblClickRow,
						onClickRow : clickRow,
						// singleSelect: true,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									title : 'Id',
									field : 'Id',
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
									title : '车辆类型',
									field : 'typeName',
									align : 'center',
									width : 100
								},
								{
									title : '车牌号码',
									field : 'number',
									align : 'center',
									width : 100,
									sortable : true
								},
								{
									title : '车辆用途',
									field : 'purpose',
									align : 'center',
									width : 100
								},
								{
									title : '车辆品牌',
									field : 'brand',
									align : 'center',
									width : 150
								},
								{
									title : '座位数',
									field : 'siteQty',
									align : 'center',
									width : 150
								},
								{
									title : 'GPS设备ID',
									field : 'gpsId',
									align : 'center',
									width : 80,
									hidden : true
								},
								{
									title : 'GPS显示名称',
									field : 'gps_name',
									align : 'center',
									width : 150,
									formatter : function(value, row, index) {
										return row.gpsName;
									},
									sortable : true
								},
								{
									title : '组呼号',
									field : 'intercomGroup',
									align : 'center',
									width : 80
								},
								{
									title : '个呼号',
									field : 'intercomPerson',
									align : 'center',
									width : 100
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
	$("#btnSearchVehicle").bind("click", function() {
		$('#my-search-box').toggle();
	});
	InitData();
});
// 取消点击行选中
function clickRow(index, data) {
	$("#dtVehicle").datagrid("unselectRow", index);
}
// 查询条件筛选
function btnSearchAction() {
	pack_Vehicle_Query();
	$('#dtVehicle').datagrid("reload", {
		'vehicle_Query' : JSON.stringify(m_Vehicle_Query)
	});
	// $("#isSubOrg").combobox("setValue", "");
	// $("#txtsearchnumber").val("");
};
function InitData() {
	getVehicleType();
	getGroupNumber();
	getGpsID(m_Vehicle_OrgId);
};
// 获取车辆属性、以下拉列表的形式呈现；
function getVehicleType() {
	getBaseDataCombobox("vehicle/getVehicleType.do", "txttype");
};
function getGroupNumber() {
	getBaseDataCombobox("vehicle/getintercomGroup.do", "txtgroupno");
};
function getGpsID(orgId) {
	getBaseDataCombobox("police/getGpsId.do?orgId=" + orgId, "txtgpsid");
}
function btnAddVehicle(optType) {
	operationType = optType;
	clearForm();
	$("#vehicleinfowindow").window("open");
	$('#btnsaveVehicleCon').show();
};

function btnCellClick(index) {
	operationType = "edit";
	var row = $("#dtVehicle").datagrid('getData').rows[index];
	editVehicleModel(row);
}
function dblClickRow(index, rowData) {
	operationType = "edit";
	editVehicleModel(rowData);
}
function editVehicleModel(rows) {
	clearForm();
	$("#vehicleId").val(rows.id);
	$("#txtbrand").val(rows.brand);
	$("#txttype").combobox("setValue", rows.vehicleTypeId);
	$("#txtsiteqty").val(rows.siteQty);
	$("#txtnumber").val(rows.number);
	$("#txtpurpose").val(rows.purpose);
	$("#txtgroupno").combobox("setValue",
			rows.intercomGroup == 0 ? "" : rows.intercomGroup);
	$("#txtpersonalno").val("");
	$("#txtgpsid").combobox("setValue", rows.gpsId);
	$("#vehicleinfowindow").window("open");
	$('#btnsaveVehicleCon').hide();
}
function btnEditVehicle(optType) {
	operationType = optType;
	var hasRows = $('#dtVehicle').datagrid('getRows');
	if (hasRows.length == 0) {
		$.messager.alert('操作提示', "没有可操作数据", "warning");
		return;
	}
	var rows = $("#dtVehicle").datagrid("getChecked");
	if (!rows || rows.length == 0) {
		$.messager.alert('操作提示', "请选择操作项!", "warning");
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('操作提示', "只能选择单个操作项!", "warning");
		return;
	}
	editVehicleModel(rows[0]);
};
function clearForm() {
	$("#vehicleId").val(0);
	$("#txtbrand").val("");
	$("#txttype").combobox("setValue", "");
	$("#txtsiteqty").val("");
	$("#txtnumber").val("");
	$("#txtpurpose").val("");
	$("#txtgroupno").combobox("setValue", "");
	$("#txtpersonalno").val("");
	$("#txtgpsid").combobox("setValue", "");
	// $("#txtgpsname").val("");
}
function pack_Vehicle_Query() {
	m_Vehicle_Query.orgId = m_Vehicle_OrgId;
	m_Vehicle_Query.orgCode = m_Vehicle_OrgCode;
	m_Vehicle_Query.orgPath = m_Vehicle_OrgPath;
	if ($("#isSubOrg").combobox("getValue") != ""
			&& $("#isSubOrg").combobox("getValue") > 0) {
		m_Vehicle_Query.isSubOrg = $("#isSubOrg").combobox("getValue");
	} else {
		m_Vehicle_Query.isSubOrg = 0;
	}
	m_Vehicle_Query.number = $("#txtsearchnumber").val();
};
// 删除模块
function btnDelVehicle() {
	var hasRows = $('#dtVehicle').datagrid('getRows');
	if (hasRows.length == 0) {
		$.messager.alert('操作提示', "没有可操作数据", "warning");
		return;
	}
	var rows = $("#dtVehicle").datagrid("getChecked");
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

	// var number = rows[0].number;
	// var id = rows[0].id;
	$.messager.confirm("系统提示", "确认删除车辆数据信息吗？", function(r) {
		if (r) {
			deleteVehicle(ids);
		}
	});
};

// 删除事件
function deleteVehicle(id) {
	$.ajax({
		url : "vehicle/deleteVehicle.do",
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
function saveVehicleAction() {
	saveVehicleModel();
};
// 保存模块事件
var isExist = false;
function saveVehicleModel() {
	var vehicle = {};

	vehicle.id = $("#vehicleId").val();

	if ($("#txttype").combobox("getValue") > 0
			&& $("#txttype").combobox("getValue") != "") {
		vehicle.vehicleTypeId = $("#txttype").combobox("getValue");
	} else {
		$.messager.alert("错误提示", "请选择车辆类型", "error");
		isComplete = false;
		return;
	}
	if ($.trim($("#txtbrand").val()).length > 0
			&& $.trim($("#txtbrand").val()).length > 30) {
		$.messager.alert("错误提示", "车辆品牌内容过长，限制长度为0--30！", "error");
		isComplete = false;
		return;
	}
	vehicle.brand = $.trim($("#txtbrand").val());
	if ($.trim($("#txtsiteqty").val()).length > 0
			&& $.trim($("#txtsiteqty").val()).length > 20) {
		$.messager.alert("错误提示", "车辆座位信息内容过长，限制长度为0--20！", "error");
		isComplete = false;
		return;
	}
	vehicle.siteQty = $.trim($("#txtsiteqty").val());
	vehicle.orgId = m_Vehicle_OrgId;
	var carnumber = $.trim($("#txtnumber").val());
	if (carnumber == "") {
		$.messager.alert("错误提示", "请输入车牌号码", "error");
		isComplete = false;
		return;
	}
	if (carnumber.length > 20) {
		$.messager.alert("错误提示", "车牌号码长度过长，限制长度为20！", "error");
		isComplete = false;
		return;
	}
	if (operationType == "add") {
		isExistVehicle(carnumber);
		if (!isExist) {
			$.messager.alert("错误提示", "车牌号为  " + carnumber + " 的车辆已存在，请检查！",
					"error");
			$("#txtnumber").focus();
			isComplete = false;
			return;
		}
	}
	vehicle.number = carnumber;
	// if ($("#txtpurpose").val() == "") {
	// $.messager.alert("错误提示", "请输入车辆用途", "error");
	// return;
	// }
	if ($.trim($("#txtpurpose").val()).length > 0
			&& $.trim($("#txtpurpose").val()).length > 50) {
		$.messager.alert("错误提示", "车辆用途内容过长，限制长度为0--50！", "error");
		isComplete = false;
		return;
	}
	vehicle.purpose = $.trim($("#txtpurpose").val());

	// vehicle.intercomGroup = $("#txtgroupno").combobox("getValue");
	if ($("#txtgroupno").combobox("getValue") > 0
			&& $("#txtgroupno").combobox("getValue") != "") {
		vehicle.intercomGroup = $("#txtgroupno").combobox("getValue");
	}
	if ($("#txtgpsid").combobox("getValue") > 0
			&& $("#txtgpsid").combobox("getValue") != "") {
		vehicle.gpsId = $("#txtgpsid").combobox("getValue");
		vehicle.gpsName = $("#txtgpsid").combobox("getText");
	}
	if ($.trim($("#txtpersonalno").val()).length > 0
			&& $.trim($("#txtpersonalno").val()).length > 20) {
		$.messager.alert("错误提示", "车辆个呼号内容过长，限制长度为0--20！", "error");
		isComplete = false;
		return;
	}
	vehicle.intercomPerson = $("#txtpersonalno").val();
	// vehicle.gpsName = $("#txtgpsname").val();
	$.ajax({
		url : "vehicle/saveVehicle.do",
		type : "POST",
		dataType : "json",
		async : false,
		data : vehicle,
		success : function(req) {
			isComplete = true;
			clearForm();
			btnSearchAction();
		},
		failer : function(a, b) {
			$.messager.alert("消息提示", "保存失败，数据传输错误", "warning");
		},
		error : function(a) {
			$.messager.alert("消息提示", "保存失败，数据移交到后台过程失败", "error");
		}
	});
}
function saveVehicleActionExit() {
	saveVehicleModel();
	if (isComplete) {
		$("#vehicleinfowindow").window("close");
	}
}

// 判断警员是否存在
function isExistVehicle(param) {
	isExist = false;
	$.ajax({
		url : "vehicle/isExistVehicle.do",
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
function btnExportAction() {
	pack_Vehicle_Query();
	$.ajax({
		url : "vehicle/exportDataToExcle.do",
		type : "POST",
		dataType : "json",
		async : false,
		timeout : 60000,
		data : {
			'vehicle_Query' : JSON.stringify(m_Vehicle_Query)
		},
		success : function(req) {
			if (req.isSuccess) {
				var urlStr = req.Data.substring(1, req.Data.length);
				if (/msie/.test(navigator.userAgent.toLowerCase())) {
					if (b_version.indexOf("MSIE 8.0", 0) > -1
							|| b_version.indexOf("MSIE 9.0", 0) > -1) {
						urlStr = "../../" + urlStr;
					}
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
// 导入事件
function btnInportAction() {
	InitEntityUploadFun();
	$("#vehicleInfoinportwindow").window("open");
}
function btnCancelVehicleDataAction() {
	/**
	 * do sth
	 */
	$("#vehicleInfoinportwindow").window("close");
}

function btnsaveVehicleData() {
	var urlStr = $("#txtentityfilename").val();
	if ($.trim(urlStr) == "") {
		$.messager.alert("操作提示", "获取文件失败，请选择需要导入的文件", "warning");
		return;
	}
	$.ajax({
		url : "excelUpload/exportDataToDatabase.do",
		type : "POST",
		dataType : "json",
		async : false,
		data : {
			'orgid' : m_Vehicle_OrgId,
			'fileName' : urlStr,
			'sourcetype' : 'CarInfo'
		},
		success : function(req) {
			if (req.isSuccess) {
				$("#vehicleInfoinportwindow").window("close");
				$.messager.alert("提示信息", req.Message, "info");
				btnSearchAction();
			} else {
				$.messager.alert("提示信息", req.Message, "info");
			}
		},
		failer : function(a, b) {
			$.messager.alert("消息提示", "导入数据失败", "info");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.messager.alert("错误提示", "导入数据失败", "error");
		}
	});
}
function btnDownLoadModel() {
	var urlStr = "resource/ExelModel/CarInfo.xls";
	if (/msie/.test(navigator.userAgent.toLowerCase())) {
		if (b_version.indexOf("MSIE 8.0", 0) > -1
				|| b_version.indexOf("MSIE 9.0", 0) > -1) {
			urlStr = "../../" + urlStr;
		}
	}
	window.location.href = urlStr;
}