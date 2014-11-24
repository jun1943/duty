
/*
 * 警员组
 * 
 */
var m_policeGroup_Org = {};

var m_policeGroup_Query = {};

var m_member_Query ={groupId:-1};

$(document).ready(function() {
	var args = getUrlArgs();
	m_policeGroup_Org.id = args["orgId"];
	m_policeGroup_Org.code = args["orgCode"];
	m_policeGroup_Org.path = args["orgPath"];

	pack_policeGroup_Query();
	$('#dtPoliceGroup').datagrid({
		url : 'policeGroup/list.do',
		queryParams : {
			'policeGroup_Query' : JSON.stringify(m_policeGroup_Query)
		},
		pagination : true,
		singleSelect : true,
		idField : 'id',
		resizable : true,
		fitColumns : true,
		width : 'auto',
		onSelect:onSelectGroup,
		columns : [ [ 
		              {	title : 'Id',field : 'id',align : 'left',width : 10,hidden : true}, 
		              {	title : '组名称',field : 'name',align : 'left',width : 150	}, 
		              {	title : '共享类型',	field : 'shareTypeDesc',	align : 'left',width : 200} 
		              ] ]
	});

	$('#dtGroupMember').datagrid({
		url:'policeGroup/loadMemberByGroupId.do',
		queryParams : {
			'member_Query' : JSON.stringify(m_member_Query)
		},
		pagination : true,
		singleSelect : true,
		idField : 'id',
		resizable : true,
		fitColumns : true,
		width : 'auto',
		columns : [ [ 
		              {title : 'id',field : 'id',align : 'left',width : 0,hidden : true	}, 
		              {	title : '所属单位',field : 'orgShortName',align : 'left',width : 110	}, 
		              {	title : '姓名',	field : 'name',	align : 'left',width : 100	},
		              {	title : '警号',	field : 'number',	align : 'left',width : 100	},
		              {	title : '职务',	field : 'title',	align : 'left',width : 100	},
		] ]
	});

	$('#treeOrg').tree(
			{
//				url : "org/list.do?orgCode=" + m_policeGroup_Org.code
//						+ "&orgPath=" + m_policeGroup_Org.path,
				checkbox : true,
				cascadeCheck : false
//				async : false
//				loadFilter : function(data) {
//					return buildOrgTree(data);
//				}
			});

	$('#treeOrgWithPolice').tree(
			{
				url : "org/listWithPolice.do?rootId="+m_policeGroup_Org.id,
				checkbox : false,
				cascadeCheck : false
			});
	
	$('#dtSelGroupMember').datagrid({
		idField : 'id',
		singleSelect : true,
		resizable : true,
		fitColumns : true,
		columns : [ [
		            {title : 'id',field : 'id',align : 'left',width : 10,hidden : true}, 
		            {title : '姓名',field : 'name',align : 'left',	width : 100},
		            {title : '警号',field : 'code',align : 'left',width : 100}
		] ]
	});
	loadOrgs();
	//forceSelTisOrg();
});

function onPoliceManGroup(name){
	parent.onDutyDataGroup(name);
}
function onVehicleGroup(name){
	parent.onDutyDataGroup(name);
}
function onWeaponGroup(name){
	parent.onDutyDataGroup(name);
}
function onGpsDeviceGroup(name){
	parent.onDutyDataGroup(name);
}


function loadOrgs(){
	
	$.ajax({
		url : "org/list.do",
		type : "POST",
		dataType : "json",
		data : {
			orgId:m_policeGroup_Org.id,
			orgCode :m_policeGroup_Org.code,
			orgPath: m_policeGroup_Org.path
		},
		async : false,
		success : function(req) {
			if (req.isSuccess) {
				var nodes=buildOrgTree(req.rows);
				$('#treeOrg').tree("loadData",nodes);
//				var node =$('#treeOrg').tree('find',m_policeGroup_Org.id);
//				$('#treeOrg').tree('check',node.target);
//				node.target.attr("disabled", "disabled");
			} else {
				$.messager.alert('提示', req.msg,"warning");
			}
		}
	});
}

function pack_policeGroup_Query() {
	m_policeGroup_Query.orgId = m_policeGroup_Org.id;
	m_policeGroup_Query.orgCode = m_policeGroup_Org.code;
	m_policeGroup_Query.orgPath = m_policeGroup_Org.path;
}

function showPoliceGroupDlg() {
	$('#winPG').window('open'); 
}

function addPoliceGroup() {

	var pg={};
	pg.shareOrgs=[];
	pg.id=0;
	pg.shareType=0;
	var po={};
	po.orgId=m_policeGroup_Org.id;
	pg.shareOrgs.push(po);
	

	displayPoliceGroup(pg);
	showPoliceGroupDlg();
}

function editPoliceGroup() {
	var row = $("#dtPoliceGroup").datagrid("getSelected");
	if (row !== null) {
		var id = row.id;
		loadPoliceGroup(id, displayPoliceGroup);

		showPoliceGroupDlg();
	}
}

function savePoliceGroup() {
	var pg = {};
	pg.shareOrgIds = [];

	pg.orgId = m_policeGroup_Org.id;
	pg.id = $('#txtPoliceGroupId').val();
	pg.name = $('#txtPoliceGroupName').val();
	pg.shareType = $('input:radio[name="shareType"]:checked').val();

	/**
	 * 强制选择根节点！
	 */
	var node =$('#treeOrg').tree('find',m_policeGroup_Org.id);
	$('#treeOrg').tree('check',node.target);
	
	var nodes = $('#treeOrg').tree('getChecked');
	var count = nodes.length;

	for ( var i = 0; i < count; i++) {
		var n = nodes[i];
		pg.shareOrgIds.push(n.id);
	}
	
	$.ajax({
		url : "policeGroup/savePoliceGroup.do",
		type : "POST",
		dataType : "json",
		data : {
			'policeGroup' : JSON.stringify(pg)
		},
		async : false,
		success : function(req) {
			if (req.isSuccess) {
				$('#dtPoliceGroup').datagrid('reload');
				$('#txtPoliceGroupId').val(req.id);//回写保存后的id
				$.messager.alert('提示', '保存成功!');
			} else {
				$.messager.alert('提示', req.msg, "warning");
			}
		}
	});

}
/**
 * 删除警员组
 */
function delPoliceGroup() {
	var row = $("#dtPoliceGroup").datagrid("getSelected");
	if (row !== null) {
		$.messager.confirm('操作提示', "确定删除[ " + row.name + " ]?",
				function(r) {
					if (r) {
						$.ajax({
									url : "policeGroup/deletePoliceGroup.do",
									type : "POST",
									dataType : "json",
									data : {
										"policeGroupId" :row.id
									},
									async : false,
									success : function(req) {
										if (req.isSuccess) {
											$.messager.alert('提示', '删除成功!');
											$('#dtPoliceGroup').datagrid('reload');
										} else {
											$.messager.alert('提示', req.msg,"warning");
										}
									}
								});
					}
				});
	}
}

function closeWinPG() {
	$('#winPG').window('close'); 
}

function cleanShareOrgs() {
	var nodes = $('#treeOrg').tree('getChecked');

	var count = nodes.length;

	for ( var i = 0; i < count; i++) {
		var n = nodes[i];
		if(i==0)
			$('#treeOrg').tree('check', n.target);//根节点强制选择
		else
			$('#treeOrg').tree('uncheck', n.target);
	}
}

function changeShareType() {

	var val = $('input:radio[name="shareType"]:checked').val();

	if (val == 0) {
		$('#divOrg').css('visibility', 'hidden');
		cleanShareOrgs();
	} else {
		$('#divOrg').css('visibility', 'visible');

	}
}

function loadPoliceGroup(id, callback) {
	$.ajax({
		url : "policeGroup/loadPoliceGroup.do",
		type : "POST",
		dataType : "json",
		data : {
			'policeGroupId' : id
		},
		async : false,
		success : function(req) {
			callback(req);
		}
	});
}

/**
 * 显示警员组信息
 * 
 * @param pg
 */
function displayPoliceGroup(pg) {
	$('#txtPoliceGroupId').val(pg.id);
	$('#txtPoliceGroupName').val(pg.name);

	if (pg.shareType == 0) {
		$('#radioShare1').prop('checked', true);
		
		$('#divOrg').css('visibility', 'hidden');
	} else {
		$('#radioShare2').prop('checked', true);
		$('#divOrg').css('visibility', 'visible');
	}
	cleanShareOrgs();
	var count = pg.shareOrgs.length;
	for ( var i = 0; i < count; i++) {
		var pgo = pg.shareOrgs[i];
		var node = $('#treeOrg').tree('find', pgo.orgId);
		$('#treeOrg').tree('check', node.target);
	}
}

function onSelectGroup(rowIndex,rowData){
	m_member_Query.groupId=rowData.id;
	$('#dtGroupMember').datagrid('reload',{ 'member_Query': JSON.stringify(m_member_Query) });
	//var x=$('#dtGroupMember').datagrid('queryParams');
}

/**
 * 添加成员 -------------------------------------------------------------------------------------
 */
function addPoliceGroupMember(){
	var row=$('#dtPoliceGroup').datagrid("getSelected");
	if(row!=null){
		$('#txtPoliceGroupId').val(row.id);
		showGroupMemberDlg();
		$('#dtSelGroupMember').datagrid('loadData',{total:0,rows:[]});
	}else{
		$.messager.alert('提示', '请先选择组!');
	}
}

function delPoliceGroupMemeber(){
	var row=$('#dtGroupMember').datagrid("getSelected");
	if(row!=null){
		$.ajax({
			url : "policeGroup/delMemberById.do",
			type : "POST",
			dataType : "json",
			data : {
				'memberId' : row.id
			},
			async : false,
			success : function(req) {
				if (req.isSuccess) {
					$('#dtGroupMember').datagrid('reload');
				} 
			}
		});
	}else{
		$.messager.alert('提示', '请先选择警员!!');
	}
}

function cleanPGMember(){
	
	var row=$('#dtPoliceGroup').datagrid("getSelected");
	
	if(row!=null){
		$.messager.confirm('操作提示', "确定要清空[ " + row.name + " ]下面所有的成员?",
				function(r) {
					if (r) {
						$.ajax({
									url : "policeGroup/cleanMemberByGroupId.do",
									type : "POST",
									dataType : "json",
									data : {
										"policeGroupId" :row.id
									},
									async : false,
									success : function(req) {
										if (req.isSuccess) {
											$('#dtGroupMember').datagrid('reload');
										} 
									}
								});
					}
				});
	}else{
		$.messager.alert('提示', '请先选择警员组!!');
	}
}


function appendMember(){
	var members=[];
	var groupid=$('#txtPoliceGroupId').val();

	
	var rows=$('#dtSelGroupMember').datagrid('getRows');
	var count=rows.length;
	
	for(var i=0;i<count;i++){
		var row=rows[i];
		var member ={};
		member.id=0;
		member.groupId=groupid;
		member.policeId=row.id;
		members.push(member);
	}
	
	$.ajax({
		url : "policeGroup/appendMember.do",
		type : "POST",
		dataType : "json",
		data : {
			'members' : JSON.stringify(members)
		},
		async : false,
		success : function(req) {
				if (req.isSuccess) {
						$.messager.alert('提示', '保存成功!');
							$('#dtGroupMember').datagrid('reload');
							$('#winPGMember').window("close");
						} else {
							$.messager.alert('提示', req.msg,"warning");
						}
		}
	});
}

function closeWinPGMember(){
	$('#winPGMember').window("close");
}

function selectMember(){
	var node=$('#treeOrgWithPolice').tree('getSelected');
	if(node !=null && node.dataType==2){
		
		var datas=$('#dtSelGroupMember').datagrid('getData');
		
		var count=datas.rows.length;
		
		var exists=false;
		
		for(var i=0;i<count;i++){
			var row=datas.rows[i];
			if(row.id==node.rid){
				exists=true;
				break;
			}
		}
		
		if(!exists){
			$('#dtSelGroupMember').datagrid('appendRow',{
				id:node.rid,
				name: node.name,
				code: node.code
			});
		}
	}
}

function unselectMember(){
	var row=$('#dtSelGroupMember').datagrid('getSelected');
	
	if(row !=null){
		var index=$('#dtSelGroupMember').datagrid('getRowIndex',row);
		$('#dtSelGroupMember').datagrid('deleteRow',index);
	}
}

function showGroupMemberDlg(){
	
	$('#winPGMember').window('open'); 
}

function displayGroupMember(member){
	
}

