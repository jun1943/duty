var m_Icons_OrgId;
var m_Icons_OrgCode;
var m_Icons_OrgPath;
var m_Icons_Query = {};
$(function() {


	$("#iconsinfowindow").window("close");

	
	var args = getUrlArgs();
	m_Icons_OrgId = 2; // args["orgId"];
	m_Icons_OrgCode = '510106992500';// args["orgCode"];
	m_Icons_OrgPath = '/510106000000';// args["orgPath"];
	pack_Icons_Query();

	$("#orgtree").tree({
	// url: '/TreeData/GetFunTree'
	});

	$('#dtIcons').datagrid({
		url : "icons/getIconsList.do",
		queryParams : {
			'icons_Query' : JSON.stringify(m_Icons_Query)
		},
		pagination : true,
		fitColumns : true,
		pageNumber : 1,
		pageSize : 10,
		title:'图标列表',
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
			title : '图标类型',
			field : 'typeName',
			align : 'center',
			width : 100
		}, {
			title : '图标名称',
			field : 'name',
			align : 'center',
			width : 100
		}, {
			title : '缩略图',
			field : 'icon',
			align : 'center',
			width : 100
		} ] ]
	});
	$("#btnSearchIcons").bind("click", function() {
		$('#my-search-box').toggle();
	}); 
});
//打包查询条件
function pack_Icons_Query() {
	m_Icons_Query.orgId = m_Icons_OrgId;
	m_Icons_Query.orgCode = m_Icons_OrgCode;
	m_Icons_Query.orgPath = m_Icons_OrgPath;
	m_Icons_Query.isSubOrg = $("#isSubOrg").combobox("getValue");
	m_Icons_Query.name = $("#txtsearchName").val();
	m_Icons_Query.typeid = $("#sltType").combobox("getValue");
};

//查询按钮事件
function btnSearchAction() {
	pack_Icons_Query();
	$('#dtIcons').datagrid("reload", {
		'icons_Query' : JSON.stringify(m_Icons_Query)
	});
	$("#isSubOrg").combobox("setValue",0);
	$("#txtsearchName").val("");
	$("#sltType").combobox("setValue",0);
};
//新增开始
function btnAddIcons() {
	clearForm();
	//$('#myModal').modal('show');
	$("#iconsinfowindow").window("open");
};//清空form表单
function clearForm() {
	$("#iconsId").val(0);
	$("#txttype").combobox("setValue",0);
	$("#txtname").val("");
	$("#txticons").val(""); 
};
function selectIconsAction(){
	document.getElementById("iconfile").click();            //彈出打開文件對話框
	var str= document.getElementById("iconfile").value;    //獲取文件路徑信息。
	$("#txticons").val(str);
	$("#iconPath").attr("src",str);
	//document.getElementById("btnfindIcon").value ="正在上傳....";
}