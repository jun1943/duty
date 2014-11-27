/*
 * 勤务管理主页面
 */

var m_basedataFrame_Org={};
var m_basedataFrame_User={};
var m_basedataFrame_func_prop={};

var m_org_node = {};
$(function () {
	
	var args = getUrlArgs();
	m_basedataFrame_User.userName =window.atob(args["user"]);
	m_basedataFrame_User.pwd = args["pwd"]; 
	m_basedataFrame_User.css = args["css"];
	m_basedataFrame_User.config = args["config"];
	
	
	
	
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
function searchOrgAction(){
	var name = $('#txtOrgName').val();
	var a = findOrgs(name);
	$('#orgtree').tree("loadData", a);
}

function findOrgs(name) {
	var a = [];
	if (m_org_node != null) {
		$.each(m_org_node, function(index, value) {
			var o = findOrgTree(value, name);
			if (o != null) {
				a.push(o);
			}
		});
	}
	return a;
}


function findOrgTree(org, name, array) {
	var ls = [];
	if (org.children != null) {
		$.each(org.children2, function(index, value) {
			var o = findOrgTree(value, name);
			if (o != null) {
				ls.push(o);
			}
		});
	}

	org.children = ls;

	if (name = "" || org.name.indexOf(name) >= 0 || ls.length > 0) {
		return org;
	} else {
		return null;
	}
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
				m_org_node = nodes;
				$('#orgtree').tree("loadData",nodes);
			} else {
				$.messager.alert('提示', req.msg,"warning");
			}
		}
	});
}