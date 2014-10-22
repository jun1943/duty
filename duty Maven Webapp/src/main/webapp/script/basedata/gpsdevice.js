var m_Gpsdevice_OrgId;
var m_Gpsdevice_OrgCode;
var m_Gpsdevice_OrgPath;
var m_Gpsdevice_Query = {};

$(function() {

	var args = getUrlArgs();
	m_Gpsdevice_OrgId = 2; // args["orgId"];
	m_Gpsdevice_OrgCode = '510106992500';// args["orgCode"];
	m_Gpsdevice_OrgPath = '/510106000000';// args["orgPath"];
	pack_Gpsdevice_Query();

	$("#orgtree").tree({
	// url: '/TreeData/GetFunTree'
	});

	$('#dtGpsdevice').datagrid({
		url : "gpsdevice/getGpsdeviceList.do",
		queryParams : {
			'gpsdevice_Query' : JSON.stringify(m_Gpsdevice_Query)
		},
		pagination : true,
		fitColumns : true,
		pageNumber : 1,
		pageSize : 10,
		// title:"s",
		// singleSelect: true,
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			title : 'Id',
			field : 'id',
			align : 'center',
			width : 10,
			hidden : true
		}, {
			title : '机构',
			field : 'orgName',
			align : 'center',
			width : 100
		}, {
			title : 'GPS类型',
			field : 'typeName',
			align : 'center',
			width : 100
		}, {
			title : 'GPS显示名称',
			field : 'gpsName',
			align : 'center',
			width : 100
		}, {
			title : 'GPS设备编号',
			field : 'number',
			align : 'center',
			width : 100
		}, {
			title : 'GPS图标',
			field : 'gpsIcon',
			align : 'center',
			width : 150
		} ] ]
	});
	$("#btnSearchGpsdevice").bind("click", function() {
		$('#my-search-box').toggle();
	});
	InitData();
});

function btnSearchAction() {
	pack_Gpsdevice_Query();
	$('#dtGpsdevice').datagrid("reload", {
		'gpsdevice_Query' : JSON.stringify(m_Gpsdevice_Query)
	});
	$("#isSubOrg").val(0);
	$("#txtsearchname").val("");
};
function InitData() {

};
function btnAddGpsdevice() {
	clearForm();
	$('#myModal').modal('show');
};
function btnEditGpsdevice() {
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
	clearForm();
	$("#gpsdeviceId").val(rows[0].id);
	$("#txttype").val(rows[0].typeId);
	$("#txtgpsname").val(rows[0].gpsName);
	$("#txtgpsnumber").val(rows[0].number);
	$('#myModal').modal('show');
};
function clearForm() {
	$("#gpsdeviceId").val(0);
	$("#txttype").val(0);
	$("#txtgpsname").val("");
	$("#txtgpsnumber").val("");
}
function pack_Gpsdevice_Query() {
	m_Gpsdevice_Query.orgId = m_Gpsdevice_OrgId;
	m_Gpsdevice_Query.orgCode = m_Gpsdevice_OrgCode;
	m_Gpsdevice_Query.orgPath = m_Gpsdevice_OrgPath;
	m_Gpsdevice_Query.isSubOrg = $("#isSubOrg").val();
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
	if (rows.length > 1) {
		$.messager.alert('操作提示', "只能选择单个操作项!", "warning");
		return;
	}
	var gpsName = rows[0].gpsName;
	var id = rows[0].id;
	$.messager.confirm("系统提示", "确认删除名称号为    " + gpsName + " 的数据信息吗？", function(
			r) {
		if (r) {
			deleteGpsdevice(id);
		}
	});
};
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
function saveGpsdeviceAction() {
	var gpsdevice = {};
	gpsdevice.id = $("#gpsdeviceId").val();

	if ($("#txttype").val() > 0) {
		gpsdevice.typeId = $("#txttype").val();
	} else {
		$.messager.alert("错误提示", "请选择GPS类型", "error");
		return;
	}
	if ($("#txtgpsname").val() == "") {
		$.messager.alert("错误提示", "请输入GPS设备名称", "error");
		return;
	}
	gpsdevice.gpsName = $("#txtgpsname").val();
	if ($("#txtgpsnumber").val() == "") {
		$.messager.alert("错误提示", "请输入GPS设别编号", "error");
		return;
	}
	gpsdevice.number = $("#txtgpsnumber").val();
	gpsdevice.orgId = m_Gpsdevice_OrgId;
	$.ajax({
		url : "gpsdevice/saveGpsdevice.do",
		type : "POST",
		dataType : "json",
		async : false,
		data : gpsdevice,
		success : function(req) {
			$.messager.alert("消息提示", req.Message, "info");
			$('#myModal').modal('hide');
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