/*
 * 基础数据管理初始-主页面
 * 
 * 包括页面跳转、
 * 组织机构数的加载、
 * 以及权限的控制
 */

var m_basedataFrame_Org = {};
var m_basedataFrame_User = {};
var m_basedataFrame_func_prop = {};

var m_org_node = {};
$(function() {

	var args = getUrlArgs();
	m_basedataFrame_User.userName = args["user"];
	m_basedataFrame_User.pwd = args["pwd"];
	m_basedataFrame_User.css = args["css"];
	m_basedataFrame_User.config = args["config"];

	if (m_basedataFrame_User.userName == ''
			|| m_basedataFrame_User.userName == null
			|| m_basedataFrame_User.userName == undefined
			|| m_basedataFrame_User.pwd == ''
			|| m_basedataFrame_User.pwd == null
			|| m_basedataFrame_User.pwd == undefined) {
		$.messager.alert("传入数据信息为空，请检测是否登录！");
		return;
	} else {
		batchGetUserAuthorization(m_basedataFrame_User.userName,
				m_basedataFrame_User.pwd);
	}

	$('#orgtree').tree({
		onClick : onOrgTreeDblClick,
		cascadeCheck : false
	});

	loadFrmOrgs();
	InitPage();

	// $("div[doc='dateBoxMenu']").each(function() { //开始遍历
	//
	// $(this).mouseover(function() {
	// $(this).attr("class", "dateBoxMenuOn");
	//
	// });
	//
	// $(this).mouseleave(function() {
	// $(this).attr("class", "");
	// });
	// });
	$('#txtOrgName').keydown(function(e){
		if(e.keyCode==13){
			searchOrgAction();
		}
	});
});

//验证用户有效性，加载相关组织机构信息
function batchGetUserAuthorization(userName, pwd) {
	$.ajax({
		url : "police/batchGetUserAuthorization.do",
		type : "POST",
		dataType : "json",
		async : false,
		data : {
			"userName" : userName,
			"password" : pwd
		},
		success : function(req) {
			if (req.isSuccess) {
				m_basedataFrame_User.id= req.obj.id;
				m_basedataFrame_Org.id = req.obj.orgId;// args["orgId"];
				m_basedataFrame_Org.code = req.obj.orgCode;// args["orgCode"];
				m_basedataFrame_Org.path = req.obj.orgPath;// args["orgPath"];
			} else {
				$.messager.alert("用户信息验证失败");
			}
		},
		failer : function(a, b) {
			$.messager.alert("消息提示", a, "info");
		},
		error : function(a) {
			$.messager.alert("消息提示", a, "error");
		}
	});
};

function InitPage() {
	var src = "view/basedata/police.jsp" + "?orgId=" + m_basedataFrame_Org.id
			+ "&orgCode=" + m_basedataFrame_Org.code + "&orgPath="
			+ m_basedataFrame_Org.path+"&userId="+m_basedataFrame_User.id;

	$("#ifmWorkSpace").attr("src", src);
	$("#policemanage").attr("class", "dateBoxMenuOn");
}

//树点击事件
function onOrgTreeDblClick(node) {
	m_basedataFrame_func_prop.url = "view/basedata/police.jsp";
	m_basedataFrame_func_prop.orgId = node.id;
	m_basedataFrame_func_prop.orgCode = node.code;
	m_basedataFrame_func_prop.orgPath = node.path;

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
function pageSwitch(node, url) {
	var src = m_basedataFrame_func_prop.url + "?orgId="
			+ m_basedataFrame_func_prop.orgId + "&orgCode="
			+ m_basedataFrame_func_prop.orgCode + "&orgPath="
			+ m_basedataFrame_func_prop.orgPath+"&userId="+m_basedataFrame_User.id;

	$("#ifmWorkSpace").attr("src", src);
}
function searchOrgAction() {
	var name = $.trim($('#txtOrgName').val());
//	if(name==""){
//		return;
//	}
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
function onPoliceManage() {
	m_basedataFrame_func_prop.url = "view/basedata/police.jsp";
	pageSwitch();
	$("#policemanage").attr("class", "dateBoxMenuOn");
	$("#vehiclemanage").attr("class", "");
	$("#weaponmanage").attr("class", "");
	$("#gpsdevicemanage").attr("class", "");
	$("#iconsmanage").attr("class", "");
}

function onVehicleManage() {
	m_basedataFrame_func_prop.url = "view/basedata/vehicle.jsp";
	pageSwitch();
	$("#vehiclemanage").attr("class", "dateBoxMenuOn");
	$("#policemanage").attr("class", "");
	$("#weaponmanage").attr("class", "");
	$("#gpsdevicemanage").attr("class", "");
	$("#iconsmanage").attr("class", "");
}

function onWeaponManage() {
	m_basedataFrame_func_prop.url = "view/basedata/weapon.jsp";
	pageSwitch();
	$("#weaponmanage").attr("class", "dateBoxMenuOn");
	$("#policemanage").attr("class", "");
	$("#vehiclemanage").attr("class", "");
	$("#gpsdevicemanage").attr("class", "");
	$("#iconsmanage").attr("class", "");
}

function onGpsdeviceManage() {
	m_basedataFrame_func_prop.url = "view/basedata/gpsdevice.jsp";
	pageSwitch();
	$("#gpsdevicemanage").attr("class", "dateBoxMenuOn");
	$("#policemanage").attr("class", "");
	$("#vehiclemanage").attr("class", "");
	$("#weaponmanage").attr("class", "");
	$("#iconsmanage").attr("class", "");
}
function onIconsManage() {
	m_basedataFrame_func_prop.url = "view/basedata/icons.jsp";
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
function loadFrmOrgs() {

	$.ajax({
		url : "org/list.do",
		type : "POST",
		dataType : "json",
		data : {
			orgId : m_basedataFrame_Org.id,
			orgCode : m_basedataFrame_Org.code,
			orgPath : m_basedataFrame_Org.path
		},
		async : false,
		success : function(req) {
			if (req.isSuccess) {
				var nodes = buildOrgTree(req.rows);
				m_org_node = nodes;
				$('#orgtree').tree("loadData", nodes);
				var nodess = $('#orgtree').tree('find', m_basedataFrame_Org.id);
				$('#orgtree').tree('select', nodess.target);
				onOrgTreeDblClick(nodess);
 
			} else {
				$.messager.alert('提示', req.msg, "warning");
			}
		}
	});
}