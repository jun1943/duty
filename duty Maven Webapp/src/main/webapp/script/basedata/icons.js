var m_Icons_OrgId;
var m_Icons_OrgCode;
var m_Icons_OrgPath;
var m_Icons_Query = {};
$(function() {


	$("#iconsinfowindow").window("close");

	
	var args = getUrlArgs();
	m_Icons_OrgId = 15; // args["orgId"];
	m_Icons_OrgCode = '510106993600';// args["orgCode"];
	m_Icons_OrgPath = '/510106000000';// args["orgPath"];
	pack_Icons_Query();

	$("#orgtree").tree({
		url:  "org/list.do?orgCode=" + m_Icons_OrgCode + "&orgPath=" + m_Icons_OrgPath,
		loadFilter : function(data) {
			return buildOrgTree(data);
		}
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
	if($("#isSubOrg").combobox("getValue")!=""&&$("#isSubOrg").combobox("getValue")>0){
		m_Icons_Query.isSubOrg = $("#isSubOrg").combobox("getValue");
	}else{
		m_Icons_Query.isSubOrg = 0;
	}  
	m_Icons_Query.name = $("#txtsearchName").val();
	if($("#sltType").combobox("getValue")!=""&&$("#sltType").combobox("getValue")>0){
		m_Icons_Query.typeid = $("#sltType").combobox("getValue");
	}else{
		m_Icons_Query.typeid = 0;
	}
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
	//var str= document.getElementById("iconfile").value;    //獲取文件路徑信息。
	var str = getPath(document.getElementById("iconfile"));
	$("#txticons").val(str);
	var arry = str.split('\\');
	$("#txtname").val(arry[arry.length-1]);
	$("#img").attr("src",str);
	//document.getElementById("btnfindIcon").value ="正在上傳....";
};

function getPath(obj) {
	 if (obj) {
	  if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
	   obj.select();
	   return document.selection.createRange().text;
	  } else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
	   if (obj.files) {
	    return obj.files.item(0).getAsDataURL();
	   }
	   return obj.value;
	  }
	  return obj.value;
	 }
	}
function saveIconsAction(){
	var icons = {};
	icons.id=$("#iconsId").val();
	//icons.name = $("#txtname").val();
	if ($("#txttype").combobox("getValue") > 0
			&& $("#txttype").combobox("getValue") != "") {
		icons.typeId = $("#txttype").combobox("getValue");
	} else {
		$.messager.alert("错误提示", "请选择人员类别", "error");
		return;
	}
	icons.name = $("#txticons").val();
	
	$.ajax({
		url : "icons/saveIcons.do",
		type : "POST",
		dataType : "json",
		async : false,
		data : icons,
		success : function(req) {
			$.messager.alert("消息提示", req.Message, "info"); 
		},
		failer : function(a, b) {
			$.messager.alert("消息提示", a, "info");
		},
		error : function(a) {
			$.messager.alert("消息提示", a, "error");
		}
	});
}