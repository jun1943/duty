

/*
 * 警员组
 * 
 */

var m_policeGroup_Org={};

var m_policeGroup_Query={};

$(function () {
	
	var args = getUrlArgs();
	m_policeGroup_Org.code = args["orgCode"];
	m_policeGroup_Org.path = args["orgPath"];
	
	pack_policeGroup_Query();
	
	$('#dtGroup').datagrid({
		url:'policeGroup/list.do',
		queryParams: { 'policeGroup_Query': JSON.stringify(m_policeGroup_Query)},
		pagination: true,
        pageNumber: 1,
        pageSize: 10,
        singleSelect: true,
		columns: [[
	               { title: 'Id', field: 'Id', align: 'left', width: 10, hidden: true },
	               { title: '组名称', field: 'name', align: 'left', width: '40%' },
	               { title: '共享类型', field: 'shareTypeDesc', align: 'left', width: '60%' }
	        ]]
	});
	
	$('#dtGroupMember').datagrid({
		pagination: true,
        pageNumber: 1,
        pageSize: 10,
		columns: [[
	               { title: 'Id', field: 'Id', align: 'left', width: 10, hidden: true },
	               { title: '组名称', field: 'name', align: 'left', width: 100 },
	               { title: '共享类型', field: 'shareTypeDesc', align: 'left', width: 100 }
	               
	        ]]
	});
	
	
	$('#dt1').datagrid({
		pagination: true,
        pageNumber: 1,
        pageSize: 10,
		columns: [[
	               { title: 'Id', field: 'Id', align: 'left', width: 10, hidden: true },
	               { title: '组名称', field: 'name', align: 'left', width: 100 },
	               { title: '共享类型', field: 'shareTypeDesc', align: 'left', width: 100 }
	               
	        ]]
	});
	
	$('#treeOrg').tree({
		url:"org/list.do?orgCode="+m_policeGroup_Org.code +"&orgPath="+m_policeGroup_Org.path,
		loadFilter:function(data){
			return buildOrgTree(data);
		}
	});
	
	
	pack_policeGroup_Query();
	
});


function pack_policeGroup_Query(){
	m_policeGroup_Query.orgCode=m_policeGroup_Org.code;
	m_policeGroup_Query.orgPath=m_policeGroup_Org.path;
}


function btnAddGroup(){
	$('#dlgPoliceGroup').modal({
	      keyboard: true});
}
