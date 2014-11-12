var m_Gpsdevice_OrgId;
var m_Gpsdevice_OrgCode;
var m_Gpsdevice_OrgPath;
var m_Gpsdevice_Query = {};
var m_Icons_Query = {};
$(function() {
	

	$("#gpsdeviceinfowindow").window("close");

	var args = getUrlArgs();
	m_Gpsdevice_OrgId =   args["orgId"];
	m_Gpsdevice_OrgCode =   args["orgCode"];
	m_Gpsdevice_OrgPath =  args["orgPath"];
	pack_Gpsdevice_Query();
	m_Icons_Query.name = "";
	m_Icons_Query.typeid =0;
	$("#orgtree").tree({
		url:  "org/list.do?orgCode=" + m_Gpsdevice_OrgCode + "&orgPath=" + m_Gpsdevice_OrgPath,
		loadFilter : function(data) {
			return buildOrgTree(data);
		}
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
		title:'定位设备列表',
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
			field : 'iconUrl',
			align : 'center',
			width : 150,
			formatter:function(value,rowData,index){
				var src = value.substring(1,value.length);
				return "<img style='width:25px; height:25px' src='"+src+"' />";
			}
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
	$("#isSubOrg").combobox("setValue","");
	$("#txtsearchname").val("");
};
function InitData() {
	getGpsType();
	getGpsIconList();
};
function getGpsType(){
	getBaseDataCombobox("gpsdevice/getGpsType.do","txttype"); 
};

function getGpsIconList(){
	$("#txtgpsicon").combogrid({
		url: 'icons/getIconsList.do', 
		queryParams : {
			'icons_Query' : JSON.stringify(m_Icons_Query)
		},
        idField: 'id',
        textField: 'name',
        pagination: true,  
		pageNumber : 1,
		pageSize : 10,
        fitColumns: true,
        panelWidth: 430,
        singleSelect: true,
        columns: [[
            { field: 'id', title: 'id', width: 100, hidden: true },
            { field: 'iconUrl', title: '缩略图', width: 40, align: 'center',
    			formatter:function(value,rowData,index){
    				var src = value.substring(1,value.length);
    				return "<img style='width:25px; height:25px' src='"+src+"' />";
    			} },
            { field: 'name', title: '图片名称', width: 80, align: 'left',
        			formatter:function(value,rowData,index){
        				if(rowData.typeId==1){
        					return "警员图标:"+rowData.name;
        				}else if(rowData.typeId==2){
        					return "车辆图标:"+rowData.name;
        				}else if(rowData.typeId==3){
        					return "武器图标:"+rowData.name;
        				}else if(rowData.typeId==4){
        					return "定位设备图标:"+rowData.name;
        				}
        			} }
        ]],
        onSelect: function (index, data) {
        	$("#txtIconUrl").val(data.iconUrl);
        }
	});
}
function btnAddGpsdevice() {
	clearForm();
	//$('#myModal').modal('show');
	$("#gpsdeviceinfowindow").window("open");
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
	$("#txttype").combobox("setValue",rows[0].typeId);
	$("#txtgpsname").val(rows[0].gpsName);
	$("#txtgpsnumber").val(rows[0].number);
	//$('#myModal').modal('show');
	$("#gpsdeviceinfowindow").window("open");
};
function clearForm() {
	$("#gpsdeviceId").val(0);
	$("#txttype").combobox("setValue","");
	$("#txtgpsname").val("");
	$("#txtgpsnumber").val("");
	$("#txtIconUrl").val("");
}
function pack_Gpsdevice_Query() {
	m_Gpsdevice_Query.orgId = m_Gpsdevice_OrgId;
	m_Gpsdevice_Query.orgCode = m_Gpsdevice_OrgCode;
	m_Gpsdevice_Query.orgPath = m_Gpsdevice_OrgPath;
	if($("#isSubOrg").combobox("getValue")!=""&&$("#isSubOrg").combobox("getValue")>0){
		m_Gpsdevice_Query.isSubOrg = $("#isSubOrg").combobox("getValue");
	}else{
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

	if ($("#txttype").combobox("getValue") > 0&&$("#txttype").combobox("getValue")!="") {
		gpsdevice.typeId = $("#txttype").combobox("getValue");
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
	if ($("#txtIconUrl").val() == "") {
		$.messager.alert("错误提示", "请选择GPS图标", "error");
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
			$.messager.alert("消息提示", req.Message, "info");
			$("#gpsdeviceinfowindow").window("close");
			//$('#myModal').modal('hide');
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