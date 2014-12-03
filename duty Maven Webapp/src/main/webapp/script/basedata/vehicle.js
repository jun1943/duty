var m_Vehicle_OrgId;
var m_Vehicle_OrgCode;
var m_Vehicle_OrgPath;
var m_Vehicle_Query = {};
var operationType = "";
$(function() {

	$("#vehicleinfowindow").window("close");

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

	$('#dtVehicle').datagrid({
		url : "vehicle/getVehicleList.do",
		queryParams : {
			'vehicle_Query' : JSON.stringify(m_Vehicle_Query)
		},
		pagination : true,
		fitColumns : true,
		pageNumber : 1,
		pageSize : 10,
		title : '车辆列表',
		onDblClickRow : dblClickRow,
	    onClickRow: clickRow,
		// singleSelect: true,
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			title : 'Id',
			field : 'Id',
			align : 'center',
			width : 10,
			hidden : true
		}, {
			title : '机构',
			field : 'orgName',
			align : 'center',
			width : 100,
			hidden : true
		}, {
			title : '车辆类型',
			field : 'typeName',
			align : 'center',
			width : 100
		}, {
			title : '车牌号码',
			field : 'number',
			align : 'center',
			width : 100,
			sortable : true
		}, {
			title : '车辆用途',
			field : 'purpose',
			align : 'center',
			width : 100
		}, {
			title : '车辆品牌',
			field : 'brand',
			align : 'center',
			width : 150
		}, {
			title : '座位数',
			field : 'siteQty',
			align : 'center',
			width : 150
		}, {
			title : 'GPS设备ID',
			field : 'gpsId',
			align : 'center',
			width : 80
		}, {
			title : 'GPS名称',
			field : 'gps_name',
			align : 'center',
			width : 80,
			formatter : function(value, row, index) {
				return row.gpsName;
			},
			sortable : true
		}, {
			title : '组呼号',
			field : 'intercomGroup',
			align : 'center',
			width : 200,formatter:function(value,row,index){
				if(value==0){
					return "";
				}else
					{return value;}
			}
		}, {
			title : '个呼号',
			field : 'personalno',
			align : 'center',
			width : 200
		} ] ]
	});
	$("#btnSearchVehicle").bind("click", function() {
		$('#my-search-box').toggle();
	});
	InitData();
});
//取消点击行选中
function clickRow(index, data) {
    $("#dtVehicle").datagrid("unselectRow", index);
}
function btnSearchAction() {
	pack_Vehicle_Query();
	$('#dtVehicle').datagrid("reload", {
		'vehicle_Query' : JSON.stringify(m_Vehicle_Query)
	});
	$("#isSubOrg").combobox("setValue", 0);
	$("#txtsearchnumber").val("");
};
function InitData() {
	getVehicleType();
	getGroupNumber();
	getGpsID(m_Vehicle_OrgId);
};

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



function dblClickRow(index,rowData){
	editVehicleModel(rowData);
}
function editVehicleModel(rows){
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
	$("#txtgpsid")
			.combobox("setValue", rows.gpsId == 0 ? "" : rows.gpsId);
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
			// $.messager.alert("消息提示", req.Message, "info");
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
	vehicle.brand = $("#txtbrand").val();
	vehicle.siteQty = $("#txtsiteqty").val();
	vehicle.orgId = m_Vehicle_OrgId;
	if ($("#txtnumber").val() == "") {
		$.messager.alert("错误提示", "请输入车牌号码", "error");
		isComplete = false;
		return;
	}
	vehicle.number = $("#txtnumber").val();
	// if ($("#txtpurpose").val() == "") {
	// $.messager.alert("错误提示", "请输入车辆用途", "error");
	// return;
	// }
	vehicle.purpose = $("#txtpurpose").val();

	// vehicle.intercomGroup = $("#txtgroupno").combobox("getValue");
	if ($("#txtgroupno").combobox("getValue") > 0
			&& $("#txtgroupno").combobox("getValue") != "") {
		vehicle.intercomGroup = $("#txtgroupno").combobox("getValue");
	} else {
		vehicle.intercomGroup = 0;
		// $.messager.alert("错误提示", "请选择GPS_ID", "error");
		// return;
	}
	if ($("#txtgpsid").combobox("getValue") > 0
			&& $("#txtgpsid").combobox("getValue") != "") {
		vehicle.gpsId = $("#txtgpsid").combobox("getValue");
		vehicle.gpsName = $("#txtgpsid").combobox("getText");
	} else {
		vehicle.gpsId = 0;
		vehicle.gpsName = "";
		// $.messager.alert("错误提示", "请选择GPS_ID", "error");
		// return;
	}
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
			$.messager.alert("消息提示", a, "info");
		},
		error : function(a) {
			$.messager.alert("消息提示", a, "error");
		}
	});
}
function saveVehicleActionExit() {
	saveVehicleModel();
	if (isComplete) {
		$("#vehicleinfowindow").window("close");
	}
}