var m_Vehicle_OrgId;
var m_Vehicle_OrgCode;
var m_Vehicle_OrgPath;
var m_Vehicle_Query = {};

$(function() {


	$("#vehicleinfowindow").window("close");
	
	var args = getUrlArgs();
	m_Vehicle_OrgId = 15; // args["orgId"];
	m_Vehicle_OrgCode = '510106993600';// args["orgCode"];
	m_Vehicle_OrgPath = '/510106000000';// args["orgPath"];
	pack_Vehicle_Query();

	$("#orgtree").tree({
		url:  "org/list.do?orgCode=" + m_Vehicle_OrgCode + "&orgPath=" + m_Vehicle_OrgPath,
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
		title:'车辆列表',
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
			width : 100
		}, {
			title : '车辆类型',
			field : 'typeName',
			align : 'center',
			width : 100
		}, {
			title : '车牌号码',
			field : 'number',
			align : 'center',
			width : 100
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
			field : 'gpsName',
			align : 'center',
			width : 80
		}, {
			title : '组呼号',
			field : 'intercomGroup',
			align : 'center',
			width : 200
		} ] ]
	});
	$("#btnSearchVehicle").bind("click", function() {
		$('#my-search-box').toggle();
	});
	InitData();
});

function btnSearchAction() {
	pack_Vehicle_Query();
	$('#dtVehicle').datagrid("reload", {
		'vehicle_Query' : JSON.stringify(m_Vehicle_Query)
	});
	$("#isSubOrg").combobox("setValue",0);
	$("#txtsearchnumber").val("");
};
function InitData() { 
	getVehicleType();
	getGroupNumber();
	getGpsID(m_Vehicle_OrgId); 
};

function getVehicleType(){
	getBaseDataCombobox("vehicle/getVehicleType.do","txttype"); 
};
function getGroupNumber(){
	getBaseDataCombobox( "vehicle/getintercomGroup.do","txtgroupno");   
};
function getGpsID(orgId){
	getBaseDataCombobox( "police/getGpsId.do?orgId="+orgId,"txtgpsid");   
}
function btnAddVehicle() {
	clearForm();
	$("#vehicleinfowindow").window("open");
	//$('#myModal').modal('show');
};
function btnEditVehicle() {
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
	clearForm();
	$("#vehicleId").val(rows[0].id);
	$("#txtbrand").val(rows[0].brand);
	$("#txttype").combobox("setValue",rows[0].vehicleTypeId);
	$("#txtsiteqty").val(rows[0].siteQty);
	$("#txtnumber").val(rows[0].number);
	$("#txtpurpose").val(rows[0].purpose);
	$("#txtgroupno").combobox("setValue",rows[0].intercomGroup);
	$("#txtpersonalno").val(rows[0].intercomGroup);
	$("#txtgpsid").combobox("setValue",rows[0].gpsId);
	$("#txtgpsname").val(rows[0].gpsName);
	$("#vehicleinfowindow").window("open");
	//$('#myModal').modal('show');
};
function clearForm() {
	$("#vehicleId").val(0);
	$("#txtbrand").val("");
	$("#txttype").combobox("setValue","");
	$("#txtsiteqty").val("");
	$("#txtnumber").val("");
	$("#txtpurpose").val("");
	$("#txtgroupno").combobox("setValue","");
	$("#txtpersonalno").val("");
	$("#txtgpsid").combobox("setValue","");
	$("#txtgpsname").val("");
}
function pack_Vehicle_Query() {
	m_Vehicle_Query.orgId = m_Vehicle_OrgId;
	m_Vehicle_Query.orgCode = m_Vehicle_OrgCode;
	m_Vehicle_Query.orgPath = m_Vehicle_OrgPath;
	if($("#isSubOrg").combobox("getValue")!=""&&$("#isSubOrg").combobox("getValue")>0){
		m_Vehicle_Query.isSubOrg = $("#isSubOrg").combobox("getValue");
	}else{
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
	if (rows.length > 1) {
		$.messager.alert('操作提示', "只能选择单个操作项!", "warning");
		return;
	}
	var number = rows[0].number;
	var id = rows[0].id;
	$.messager.confirm("系统提示", "确认删除车牌号为    " + number + " 的数据信息吗？",
			function(r) {
				if (r) {
					deleteVehicle(id);
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
function saveVehicleAction() {
	var vehicle = {};

	vehicle.id = $("#vehicleId").val();

	if ($("#txttype").combobox("getValue") > 0&&$("#txttype").combobox("getValue")!="") {
		vehicle.vehicleTypeId = $("#txttype").combobox("getValue");
	} else {
		$.messager.alert("错误提示", "请选择车辆类型", "error");
		return;
	}
	if ($("#txtbrand").val() == "") {
		$.messager.alert("错误提示", "请输入车辆品牌", "error");
		return;
	}
	vehicle.brand = $("#txtbrand").val();
	if ($("#txtsiteqty").val() == "") {
		$.messager.alert("错误提示", "请输入车辆座位数", "error");
		return;
	}
	vehicle.siteQty = $("#txtsiteqty").val();
	vehicle.orgId = m_Vehicle_OrgId;
	if ($("#txtnumber").val() == "") {
		$.messager.alert("错误提示", "请输入车牌号码", "error");
		return;
	}
	vehicle.number = $("#txtnumber").val();
	if ($("#txtpurpose").val() == "") {
		$.messager.alert("错误提示", "请输入车辆用途", "error");
		return;
	}
	vehicle.purpose = $("#txtpurpose").val();
	 
	if ($("#txtgroupno").combobox("getValue") > 0&&$("#txtgroupno").combobox("getValue")!="") {
		vehicle.intercomGroup = $("#txtgroupno").combobox("getValue");
	} else {
		$.messager.alert("错误提示", "请输入组呼号", "error");
		return;
	}
	
	if ($("#txtgpsid").combobox("getValue") > 0&&$("#txtgpsid").combobox("getValue")!="") {
		vehicle.gpsId = $("#txtgpsid").combobox("getValue");
	} else {
		$.messager.alert("错误提示", "请选择GPS_ID", "error");
		return;
	}
	if ($("#txtgpsname").val() == "") {
		$.messager.alert("错误提示", "请输入GPS名称", "error");
		return;
	}
	vehicle.gpsName = $("#txtgpsname").val();
	$.ajax({
		url : "vehicle/saveVehicle.do",
		type : "POST",
		dataType : "json",
		async : false,
		data : vehicle,
		success : function(req) {
			$.messager.alert("消息提示", req.Message, "info");
			$("#vehicleinfowindow").window("close");
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