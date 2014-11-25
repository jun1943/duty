var m_Police_Org = {};
var m_Police_OrgId;
var m_Police_OrgCode;
var m_Police_OrgPath;
var m_police_Query = {};
var operationType = "";
$(function() {

	$("#policeinfowindow").window("close");
	// $('.panel-header').css({ "background-color": "#FFF000"
	// }).css({"filter":"progid:DXImageTransform.Microsoft.gradient(startColorstr=#FFF000,endColorstr=#FFFFFF,GradientType=0)"});
	var args = getUrlArgs();
	m_Police_OrgId = args["orgId"];
	m_Police_OrgCode = args["orgCode"];
	m_Police_OrgPath = args["orgPath"];
	m_Police_Org.id = m_Police_OrgId;
	m_Police_Org.code = m_Police_OrgCode;
	m_Police_Org.path = m_Police_OrgPath;
	pack_police_Query();

	$('#dtPolice').datagrid({
		url : "police/getPoliceList.do",
		queryParams : {
			'police_Query' : JSON.stringify(m_police_Query)
		},
		pagination : true,
		fitColumns : true,
		pageNumber : 1,
		pageSize : 10,
		title : "人员列表",
		onDblClickRow : btnEditPolice,
		//singleSelect : true,
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			title : 'Id',
			field : 'id',
			align : 'left',
			width : 10,
			hidden : true
		}, {
			title : '状态',
			field : 'isUsed',
			align : 'center',
			width : 50,
			formatter : fmtIsUsed,
			sortable:true
		}, {
			title : '职务',
			field : 'title',
			align : 'left',
			width : 100
		}, {
			title : '姓名',
			field : 'name',
			align : 'left',
			width : 100,
			sortable:true
		}, {
			title : '警号',
			field : 'number',
			align : 'left',
			width : 80
		}, {
			title : 'GPS名称',
			field : 'gpsName',
			align : 'left',
			width : 200,
			sortable:true
		}, {
			title : '手机号',
			field : 'mobile',
			align : 'left',
			width : 100
		}, {
			title : '公安短号',
			field : 'mobileShort',
			align : 'left',
			width : 150
		}, {
			title : '身份证号码',
			field : 'idcardno',
			align : 'left',
			width : 80
		}, {
			title : '机构',
			field : 'orgName',
			align : 'left',
			width : 100
		}, {
			title : '警员类别',
			field : 'typeName',
			align : 'left',
			width : 100,
			sortable:true
		} ] ]
	});
	$("#btnSearchPolice").bind("click", function() {
		$('#my-search-box').toggle();
	});
	InitData();
});
// 打包查询条件
function pack_police_Query() {
	m_police_Query.orgId = m_Police_OrgId;
	m_police_Query.orgCode = m_Police_OrgCode;
	m_police_Query.orgPath = m_Police_OrgPath;

	if ($("#isSubOrg").combobox("getValue") != ""
			&& $("#isSubOrg").combobox("getValue") > 0) {
		m_police_Query.isSubOrg = $("#isSubOrg").combobox("getValue");
	} else {
		m_police_Query.isSubOrg = 0;
	}
	m_police_Query.name = $("#txtsearchName").val();
	if ($("#sltType").combobox("getValue") != ""
			&& $("#sltType").combobox("getValue") > 0) {
		m_police_Query.typeid = $("#sltType").combobox("getValue");
	} else {
		m_police_Query.typeid = 0;
	}
}
function fmtIsUsed(value, row, index) {
	if (row.isused) {
		return "<img alt='停用' style='width:18px; height:18px' src='asset/css/easyui/icons/tianyi_lock.png'>";
	} else {
		return "<img alt='启用' style='width:18px; height:18px' src='asset/css/easyui/icons/tianyi_unlock.png'>";
	}
}
// 初始化下拉列表数据
function InitData() {
	getPoliceType();
	getGroupNumber();
	getGpsID(m_Police_OrgId);
};

function getPoliceType() {
	getBaseDataCombobox("police/getPoliceType.do", "txttype");
	getBaseDataCombobox("police/getPoliceType.do", "sltType");
};
function getGroupNumber() {
	getBaseDataCombobox("police/getintercomGroup.do", "txtgroupno");
};
function getGpsID(orgId) {
	$("#txtgpsid").combobox({
		valueField : 'id',
		textField : 'name',
		panelWidth : 250,
		async : false,
		url : "police/getGpsId.do?orgId=" + orgId
	});
	// getBaseDataCombobox("police/getGpsId.do?orgId=" + orgId, "txtgpsid");
}
// 查询按钮事件
function btnSearchAction() {
	pack_police_Query();
	$('#dtPolice').datagrid("reload", {
		'police_Query' : JSON.stringify(m_police_Query)
	});
	$("#isSubOrg").combobox("setValue", 0);
	$("#txtsearchName").combobox("setValue", 0);
	$("#sltType").combobox("setValue", "");
};
// 新增开始
function btnAddPolice(optType) {
	operationType = optType;
	$("#policeinfowindow").window("open");
	clearForm();
	// $('#myModal').modal('show');
};

function btnUnLockPolice() {
	changePoliceState(0);
};
function btnLockPolice() {
	changePoliceState(1);
};
function changePoliceState(pType) {
	var hasRows = $('#dtPolice').datagrid('getRows');
	if (hasRows.length == 0) {
		$.messager.alert('操作提示', "没有可操作数据", "warning");
		return;
	}
	var rows = $("#dtPolice").datagrid("getChecked");
	if (!rows || rows.length == 0) {
		$.messager.alert('操作提示', "请选择操作项!", "warning");
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('操作提示', "只能选择单个操作项!", "warning");
		return;
	}
	var pId = rows[0].id;
	$.ajax({
		url : "police/changePoliceState.do",
		type : "POST",
		dataType : "json",
		async : false,
		data : {
			"id" : pId,
			"isUsed" : pType
		},
		success : function(req) {
			//$.messager.alert("消息提示", req.Message, "info");
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
// 删除按钮事件
function btnDelPolice() {
	var hasRows = $('#dtPolice').datagrid('getRows');
	if (hasRows.length == 0) {
		$.messager.alert('操作提示', "没有可操作数据", "warning");
		return;
	}
	var rows = $("#dtPolice").datagrid("getChecked");
	if (!rows || rows.length == 0) {
		$.messager.alert('操作提示', "请选择操作项!", "warning");
		return;
	}
//	if (rows.length > 1) {
//		$.messager.alert('操作提示', "只能选择单个操作项!", "warning");
//		return;
//	}
	var ids = "";
	if(rows.length == 1){
		ids = rows[0].id;
	}else{
		for(var i = 0; i< rows.length;i++){
			ids += rows[i].id+",";
		}
		if(ids.length>2){
			ids = ids.substring(0, ids.length-1);
		}
	}
//	var name = rows[0].name;
//	var id = rows[0].id;
	$.messager.confirm("系统提示", "确认删除警员数据信息吗？", function(r) {
		if (r) {
			deletePolice(ids);
		}
	});
};
// 删除开始
function deletePolice(id) {
	$.ajax({
		url : "police/deletePolice.do",
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
}
// 编辑开始
function btnEditPolice(optType) {
	operationType = optType;
	var hasRows = $('#dtPolice').datagrid('getRows');
	if (hasRows.length == 0) {
		$.messager.alert('操作提示', "没有可操作数据", "warning");
		return;
	}
	var rows = $("#dtPolice").datagrid("getChecked");
	if (!rows || rows.length == 0) {
		$.messager.alert('操作提示', "请选择操作项!", "warning");
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('操作提示', "只能选择单个操作项!", "warning");
		return;
	}
	clearForm();
	$("#policeId").val(rows[0].id);
	$("#txtname").val(rows[0].name);
	$("#txttitle").val(rows[0].title);
	$("#txtmobile").val(rows[0].mobile);
	$("#txtmobileshort").val(rows[0].mobileShort);
	$("#txtidcardno").val(rows[0].idcardno);
	$("#txtnumber").val(rows[0].number);
	// $("#txtgpsdes").val(rows[0].gpsName);
	$("#txtgpsid").combobox("setValue", rows[0].gpsId);
	$("#txttype").combobox("setValue", rows[0].typeId);
	$("#txtgroupno").combobox("setValue", rows[0].intercomGroup);
	$("#txtpersonalno").val(rows[0].intercomPerson);
	$("#policeinfowindow").window("open");
	// $('#myModal').modal('show');
}
// 清空form表单
function clearForm() {
	$("#policeId").val(0);
	$("#txtname").val("");
	$("#txttitle").val("");
	$("#txtmobile").val("");
	$("#txtmobileshort").val("");
	$("#txtidcardno").val("");
	$("#txtnumber").val("");
	// $("#txtgpsdes").val("");
	$("#txtgpsid").combobox("setValue", "");
	$("#txttype").combobox("setValue", "");
	$("#txtgroupno").combobox("setValue", "");
	$("#txtpersonalno").val("");
}
// 保存新增或者编辑的数据
var isExist = false;
function savePoliceAction() {
	var police = {};

	police.id = $("#policeId").val();
	if ($("#txttype").combobox("getValue") > 0
			&& $("#txttype").combobox("getValue") != "") {
		police.typeId = $("#txttype").combobox("getValue");
	} else {
		$.messager.alert("错误提示", "请选择人员类别", "error");
		return;
	}
	if ($("#txtname").val() == "") {
		$.messager.alert("错误提示", "请输入警员名称", "error");
		return;
	}
	police.name = $("#txtname").val();
	police.idcardno = $("#txtidcardno").val();
	var idcardno = $("#txtidcardno").val();

	// 对身份证以及警号进行验证，ajax同步
	if (operationType == "add") {
		isExistPolice(idcardno, "idCard");
		if (!isExist) {
			$.messager.alert("错误提示", "身份证号码重复，请检查", "error"); 
			$("#txtidcardno").focus();
			return;
		}
	}
	police.orgId = m_Police_OrgId;
	police.number = $("#txtnumber").val();
	var number = $("#txtnumber").val();
	if (operationType == "add") {
		isExistPolice(number, "number");
		if (!isExist) {
			$.messager.alert("错误提示", "该警号重复，请检查", "error");
			isExist = false;
			$("#txtnumber").focus();
			return;
		}
	}
	police.title = $("#txttitle").val();
	police.mobile = $("#txtmobile").val();
	police.mobileShort = $("#txtmobileshort").val();

	//police.intercomGroup = $("#txtgroupno").combobox("getValue");
	if ($("#txtgroupno").combobox("getValue") > 0
			&& $("#txtgroupno").combobox("getValue") != "") {
		police.intercomGroup = $("#txtgroupno").combobox("getValue");
	} else {
		police.intercomGroup =0;
//		$.messager.alert("错误提示", "请选择GPS_ID", "error");
//		return;
	}
	police.intercomPerson = $("#txtpersonalno").val();
	if ($("#txtgpsid").combobox("getValue") > 0
			&& $("#txtgpsid").combobox("getValue") != "") {
		police.gpsId = $("#txtgpsid").combobox("getValue");
		police.gpsName = $("#txtgpsid").combobox("getText");
	} else {
		police.gpsId =0;
		police.gpsName ="";
//		$.messager.alert("错误提示", "请选择GPS_ID", "error");
//		return;
	}
	// police.gpsName = $("#txtgpsdes").val();
	$.ajax({
		url : "police/savePolice.do",
		type : "POST",
		dataType : "json",
		async : false,
		data : police,
		success : function(req) {
			// $.messager.alert("消息提示", req.Message, "info");
			if (operationType == "add") {
				clearForm();
			}else
			if (operationType == "edit") {
				operationType = "";
				$("#policeinfowindow").window("close");
			}
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
function isExistPolice(param, pType) {
	isExist = false;
	$.ajax({
		url : "police/isExistPolice.do",
		type : "POST",
		dataType : "json",
		async : false,
		data : {
			"param" : param,
			"paramType" : pType
		},
		success : function(req) {
			if (req.isSuccess && req.Message == "UnExits") {
				isExist = true;
			}
		}
	});
}