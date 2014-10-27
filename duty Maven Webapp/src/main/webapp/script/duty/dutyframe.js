/*
 * 勤务管理主页面
 */

var m_dutyFrame_Org={};

var m_dutyFrame_func_prop={};

$(function () {
	
	var args = getUrlArgs();
	m_dutyFrame_Org.id = args["orgId"];
	m_dutyFrame_Org.code = args["orgCode"];
	m_dutyFrame_Org.path = args["orgPath"];
	
	$('#treeDutyFrmOrg').tree(
			{
				onDblClick:onOrgTreeDblClick,
				cascadeCheck : false
			});
	
	loadFrmOrgs();
});

function onOrgTreeDblClick(node){
	m_dutyFrame_func_prop.orgId=node.id;
	m_dutyFrame_func_prop.orgCode=node.code;
	m_dutyFrame_func_prop.orgPath=node.path;
	
	pageSwitch();
}
/*
 * 页面切换
 */
function pageSwitch(node,url){
	var src=m_dutyFrame_func_prop.url
		+"?orgId="+m_dutyFrame_func_prop.orgId
		+"&orgCode="+m_dutyFrame_func_prop.orgCode
		+"&orgPath="+m_dutyFrame_func_prop.orgPath;
	
	$("#ifmWorkSpace").attr("src",src);
	//$("#ifmWorkSpace").attr("src",src);
	//$("#ifmWorkSpace").reload();
}


function onDutyPrepare(){
	m_dutyFrame_func_prop.url="view/duty/dutyprepare.jsp";
	pageSwitch();
}

function onPoliceGroup(){
	m_dutyFrame_func_prop.url="view/duty/policegroup.jsp";
	pageSwitch();
}

function onDutyType(){
	m_dutyFrame_func_prop.url="view/duty/dutytype.jsp";
	pageSwitch();
}

/*
 * 读取组织机构树
 */
function loadFrmOrgs(){
	
	$.ajax({
		url : "org/list.do",
		type : "POST",
		dataType : "json",
		data : {
			orgId:m_dutyFrame_Org.id,
			orgCode :m_dutyFrame_Org.code,
			orgPath: m_dutyFrame_Org.path
		},
		async : false,
		success : function(req) {
			if (req.isSuccess) {
				var nodes=buildOrgTree(req.rows);
				$('#treeDutyFrmOrg').tree("loadData",nodes);
			} else {
				$.messager.alert('提示', req.msg,"warning");
			}
		}
	});
}