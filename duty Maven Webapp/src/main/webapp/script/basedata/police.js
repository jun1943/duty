var m_Police_Org = {};
var m_Police_OrgId;
var m_Police_OrgCode;
var m_Police_OrgPath;
var m_police_Query = {};
$(function() {

	$("#policeinfowindow").window("close");

	var args = getUrlArgs();
	m_Police_OrgId =  args["orgId"];
	m_Police_OrgCode =   args["orgCode"];
	m_Police_OrgPath =   args["orgPath"];
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
	    title:"人员列表",
	   
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
			title : '姓名',
			field : 'name',
			align : 'center',
			width : 100
		}, {
			title : '警员类别',
			field : 'typeName',
			align : 'center',
			width : 100
		}, {
			title : '职务',
			field : 'title',
			align : 'center',
			width : 100
		}, {
			title : '手机号',
			field : 'mobile',
			align : 'center',
			width : 100
		}, {
			title : '公安短号',
			field : 'mobileShort',
			align : 'center',
			width : 150
		}, {
			title : '身份证号码',
			field : 'idcardno',
			align : 'center',
			width : 80
		}, {
			title : '警号',
			field : 'number',
			align : 'center',
			width : 80
		}, {
			title : 'GPS名称',
			field : 'gpsName',
			align : 'center',
			width : 200
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
	
	if($("#isSubOrg").combobox("getValue")!=""&&$("#isSubOrg").combobox("getValue")>0){
		m_police_Query.isSubOrg = $("#isSubOrg").combobox("getValue");
	}else{
		m_police_Query.isSubOrg = 0;
	} 
	m_police_Query.name = $("#txtsearchName").val();
	if($("#sltType").combobox("getValue")!=""&&$("#sltType").combobox("getValue")>0){
		m_police_Query.typeid = $("#sltType").combobox("getValue");
	}else{
		m_police_Query.typeid = 0;
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
	getBaseDataCombobox("police/getGpsId.do?orgId=" + orgId, "txtgpsid");
}
// 查询按钮事件
function btnSearchAction() {
	pack_police_Query();
	$('#dtPolice').datagrid("reload", {
		'police_Query' : JSON.stringify(m_police_Query)
	});
	$("#isSubOrg").combobox("setValue",0);
	$("#txtsearchName").combobox("setValue", 0);
	$("#sltType").combobox("setValue", "");
};
// 新增开始
function btnAddPolice() {
	$("#policeinfowindow").window("open");
	clearForm();
	// $('#myModal').modal('show');
};
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
	if (rows.length > 1) {
		$.messager.alert('操作提示', "只能选择单个操作项!", "warning");
		return;
	}
	var name = rows[0].name;
	var id = rows[0].id;
	$.messager.confirm("系统提示", "确认删除警员    " + name + " 的数据信息吗？", function(r) {
		if (r) {
			deletePolice(id);
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
// 编辑开始
function btnEditPolice() {
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
	$("#txtgpsdes").val(rows[0].gpsName);
	$("#txtgpsid").combobox("setValue", rows[0].gpsId);
	$("#txttype").combobox("setValue", rows[0].typeId);
	$("#txtgroupno").combobox("setValue", rows[0].intercomGroup);
	$("#txtpersonalno").val(rows[0].intercomPerson);
	$("#policeinfowindow").window("open");
	//$('#myModal').modal('show');
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
	$("#txtgpsdes").val("");
	$("#txtgpsid").combobox("setValue", "");
	$("#txttype").combobox("setValue", "");
	$("#txtgroupno").combobox("setValue", "");
	$("#txtpersonalno").val("");
}
// 保存新增或者编辑的数据
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
	if ($("#txtidcardno").val() == "") {
		$.messager.alert("错误提示", "请输入警员身份证号码", "error");
		return;
	}
	police.idcardno = $("#txtidcardno").val();
	police.orgId = m_Police_OrgId;
	if ($("#txtnumber").val() == "") {
		$.messager.alert("错误提示", "请输入警员警号", "error");
		return;
	}
	police.number = $("#txtnumber").val();
	if ($("#txttitle").val() == "") {
		$.messager.alert("错误提示", "请输入警员职务", "error");
		return;
	}
	police.title = $("#txttitle").val();
	if ($("#txtmobile").val() == "") {
		$.messager.alert("错误提示", "请输入警员手机号码", "error");
		return;
	}
	police.mobile = $("#txtmobile").val();
	if ($("#txtmobileshort").val() == "") {
		$.messager.alert("错误提示", "请输入警员公安短号", "error");
		return;
	}
	police.mobileShort = $("#txtmobileshort").val();

	if ($("#txtgroupno").combobox("getValue") > 0
			&& $("#txtgroupno").combobox("getValue") != "") {
		police.intercomGroup = $("#txtgroupno").combobox("getValue");
	} else {
		$.messager.alert("错误提示", "请选择对讲机组呼号", "error");
		return;
	}
	if ($("#txtpersonalno").val() == "") {
		$.messager.alert("错误提示", "请输入警员对讲机个呼号", "error");
		return;
	}
	police.intercomPerson = $("#txtpersonalno").val();
	if ($("#txtgpsid").combobox("getValue") > 0
			&& $("#txtgpsid").combobox("getValue") != "") {
		police.gpsId = $("#txtgpsid").combobox("getValue");
	} else {
		$.messager.alert("错误提示", "请选择GPS_ID", "error");
		return;
	}

	if ($("#txtgpsdes").val() == "") {
		$.messager.alert("错误提示", "请输入警员对讲机个呼号", "error");
		return;
	}
	police.gpsName = $("#txtgpsdes").val();
	$.ajax({
		url : "police/savePolice.do",
		type : "POST",
		dataType : "json",
		async : false,
		data : police,
		success : function(req) {
			$.messager.alert("消息提示", req.Message, "info");
			//$('#myModal').modal('hide');
			$("#policeinfowindow").window("close");
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