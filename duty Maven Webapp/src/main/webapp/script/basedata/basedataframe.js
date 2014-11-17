/*
 * 勤务管理主页面
 */

var m_basedataFrame_Org={};

var m_basedataFrame_func_prop={};

$(function () {
	
	var args = getUrlArgs();
	m_basedataFrame_Org.id = args["orgId"];
	m_basedataFrame_Org.code = args["orgCode"];
	m_basedataFrame_Org.path = args["orgPath"];
	 
	$('#orgtree').tree(
			{
				onDblClick:onOrgTreeDblClick,
				cascadeCheck : false
			});
	 
	loadFrmOrgs(); 
	InitPage();
	
//	$("div[doc='dateBoxMenu']").each(function() { //开始遍历
//
//		$(this).mouseover(function() {
//			$(this).attr("class", "dateBoxMenuOn");
//
//		});
//
//		$(this).mouseleave(function() {
//			$(this).attr("class", "");
//		});
//	});
});

function InitPage(){
	var src="view/basedata/police.jsp"
	+"?orgId="+m_basedataFrame_Org.id
	+"&orgCode="+m_basedataFrame_Org.code
	+"&orgPath="+m_basedataFrame_Org.path;

	$("#ifmWorkSpace").attr("src",src); 
	$("#policemanage").attr("class", "dateBoxMenuOn");
}
function onOrgTreeDblClick(node){
	m_basedataFrame_func_prop.url="view/basedata/police.jsp";
	m_basedataFrame_func_prop.orgId=node.id;
	m_basedataFrame_func_prop.orgCode=node.code;
	m_basedataFrame_func_prop.orgPath=node.path;
	
	pageSwitch();
	$("#policemanage").attr("class", "dateBoxMenuOn");
	$("#vehiclemanage").attr("class", "");
	$("#weaponmanage").attr("class", "");
	$("#gpsdevicemanage").attr("class", "");
	$("#iconsmanage").attr("class", "");
}
/*
 * 页面切换
 */
function pageSwitch(node,url){
	var src=m_basedataFrame_func_prop.url
		+"?orgId="+m_basedataFrame_func_prop.orgId
		+"&orgCode="+m_basedataFrame_func_prop.orgCode
		+"&orgPath="+m_basedataFrame_func_prop.orgPath;
	
	$("#ifmWorkSpace").attr("src",src); 
}


function onPoliceManage(){ 
	m_basedataFrame_func_prop.url="view/basedata/police.jsp";
	pageSwitch(); 
	$("#policemanage").attr("class", "dateBoxMenuOn");
	$("#vehiclemanage").attr("class", "");
	$("#weaponmanage").attr("class", "");
	$("#gpsdevicemanage").attr("class", "");
	$("#iconsmanage").attr("class", "");
}

function onVehicleManage(){
	m_basedataFrame_func_prop.url="view/basedata/vehicle.jsp";
	pageSwitch(); 
	$("#vehiclemanage").attr("class", "dateBoxMenuOn");
	$("#policemanage").attr("class", "");
	$("#weaponmanage").attr("class", "");
	$("#gpsdevicemanage").attr("class", "");
	$("#iconsmanage").attr("class", "");
}

function onWeaponManage(){
	m_basedataFrame_func_prop.url="view/basedata/weapon.jsp";
	pageSwitch(); 
	$("#weaponmanage").attr("class", "dateBoxMenuOn");
	$("#policemanage").attr("class", "");
	$("#vehiclemanage").attr("class", "");
	$("#gpsdevicemanage").attr("class", "");
	$("#iconsmanage").attr("class", "");
}

function onGpsdeviceManage(){
	m_basedataFrame_func_prop.url="view/basedata/gpsdevice.jsp";
	pageSwitch(); 
	$("#gpsdevicemanage").attr("class", "dateBoxMenuOn");
	$("#policemanage").attr("class", "");
	$("#vehiclemanage").attr("class", "");
	$("#weaponmanage").attr("class", "");
	$("#iconsmanage").attr("class", "");
}
function onIconsManage(){
	m_basedataFrame_func_prop.url="view/basedata/icons.jsp";
	pageSwitch(); 
	$("#iconsmanage").attr("class", "dateBoxMenuOn");
	$("#policemanage").attr("class", "");
	$("#vehiclemanage").attr("class", "");
	$("#weaponmanage").attr("class", "");
	$("#gpsdevicemanage").attr("class", "");
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
			orgId:m_basedataFrame_Org.id,
			orgCode :m_basedataFrame_Org.code,
			orgPath: m_basedataFrame_Org.path
		},
		async : false,
		success : function(req) {
			if (req.isSuccess) {
				var nodes=buildOrgTree(req.rows);
				$('#orgtree').tree("loadData",nodes);
			} else {
				$.messager.alert('提示', req.msg,"warning");
			}
		}
	});
}