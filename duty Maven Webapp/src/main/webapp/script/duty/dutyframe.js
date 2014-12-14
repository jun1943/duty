/*
 * 勤务管理跳转首页-主页面
 * 
 * 默认加载传入的组织机构信息，当前月份下的报备详细信息
 * 
 * 
 */

var m_dutyFrame_Org = {};
var m_dutyFrame_User = {};
var m_dutyFrame_func_prop = {};
var year = null;
var month = null; 
var m_org_map = {};

var m_org_node = {};

$(function() {

	var args = getUrlArgs();

	var nowsdate = new Date();
	year = nowsdate.getFullYear();
	month = nowsdate.getMonth() + 1;

	// m_dutyFrame_User.userName = window.atob(args["user"]);
	m_dutyFrame_User.userName = args["user"];
	m_dutyFrame_User.pwd = args["pwd"];
	m_dutyFrame_User.css = args["css"];
	m_dutyFrame_User.config = args["config"];

	if (m_dutyFrame_User.userName == '' || m_dutyFrame_User.userName == null
			|| m_dutyFrame_User.userName == undefined
			|| m_dutyFrame_User.pwd == '' || m_dutyFrame_User.pwd == null
			|| m_dutyFrame_User.pwd == undefined) {
		$.messager.alert("传入数据信息为空，请检测是否登录！");
		return;
	} else {
		batchGetUserAuthorization(m_dutyFrame_User.userName,
				m_dutyFrame_User.pwd);
	}
	// m_dutyFrame_Org.id = args["orgId"];
	// m_dutyFrame_Org.code = args["orgCode"];
	// m_dutyFrame_Org.path = args["orgPath"];

	$('#treeDutyFrmOrg').tree({
		checkbox : false,
		cascadeCheck : true,
		onClick : onOrgTreeDblClick
	});
	loadFrmOrgs();
	
	$('#txtOrgName').keydown(function(e){
		if(e.keyCode==13){
			searchOrgAction();
		}
	});
});

//获取地址栏参数，判断用户的有效性，获取相关组织机构信息；
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
				m_dutyFrame_User.id = req.obj.id;
				m_dutyFrame_Org.id = req.obj.orgId;

				m_dutyFrame_Org.code = req.obj.orgCode;
				m_dutyFrame_Org.path = req.obj.orgPath;
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
//树节点点击事件；
function onOrgTreeDblClick(node) { 
		m_dutyFrame_func_prop.orgId = node.id;
		m_dutyFrame_func_prop.orgCode = node.code;
		m_dutyFrame_func_prop.orgPath = node.path;
		m_dutyFrame_func_prop.userId = m_dutyFrame_User.id;
		var name = node.text;
		m_dutyFrame_func_prop.orgName = name;

		m_dutyFrame_func_prop.url = "view/duty/dutycalendar.jsp";
		pageSwitch();
		$("#divDutyPrepare").attr("class", "MenuMouseMove");
		$("#divDutyType").attr("class", "");
		$("#divDutyDataGroup").attr("class", "");
		$("#divDutyReport").attr("class", ""); 
}
/*
 * 页面切换
 */
function pageSwitch(node, url) {
	var src = m_dutyFrame_func_prop.url + "?orgId="
			+ m_dutyFrame_func_prop.orgId + "&orgCode="
			+ m_dutyFrame_func_prop.orgCode + "&orgPath="
			+ m_dutyFrame_func_prop.orgPath + "&orgName="
			+ m_dutyFrame_func_prop.orgName + "&userId=" + m_dutyFrame_User.id
			+ "&year=" + year + "&month=" + month;

	// var esrc = encodeURI(src);
	$("#ifmWorkSpace").attr("src", src);
}
//跳转到日历页面
function onDutycalendar(years, months) {
	m_dutyFrame_func_prop.url = "view/duty/dutycalendar.jsp";
	var src = m_dutyFrame_func_prop.url + "?orgId="
			+ m_dutyFrame_func_prop.orgId + "&orgCode="
			+ m_dutyFrame_func_prop.orgCode + "&orgPath="
			+ m_dutyFrame_func_prop.orgPath + "&orgName="
			+ m_dutyFrame_func_prop.orgName + "&userId=" + m_dutyFrame_User.id
			+ "&year=" + years + "&month=" + months;
	$("#ifmWorkSpace").attr("src", src);
}
//跳转到点击日历，跳转到详细报备页面
function onDutyPrepare() { 
		m_dutyFrame_func_prop.url = "view/duty/dutycalendar.jsp";
		setCheckBox(m_dutyFrame_func_prop.ctl, 1);
		m_dutyFrame_func_prop.ctl = 1;
		pageSwitch();
		$("#divDutyPrepare").attr("class", "MenuMouseMove");
		$("#divDutyType").attr("class", "");
		$("#divDutyDataGroup").attr("class", "");
		$("#divDutyReport").attr("class", ""); 
}
//跳转到报备查询页面
function onDutyReport() { 
		m_dutyFrame_func_prop.url = "view/duty/policeinquiry.jsp";
		setCheckBox(m_dutyFrame_func_prop.ctl, 2);
		pageSwitch();
		$("#divDutyPrepare").attr("class", "");
		$("#divDutyType").attr("class", "");
		$("#divDutyDataGroup").attr("class", "");
		$("#divDutyReport").attr("class", "MenuMouseMove"); 
}
//跳转到分组页面
function onDutyDataGroup(name) { 
		m_dutyFrame_func_prop.url = "view/duty/" + name + ".jsp";
		setCheckBox(m_dutyFrame_func_prop.ctl, 3);
		pageSwitch();
		$("#divDutyPrepare").attr("class", "");
		$("#divDutyType").attr("class", "");
		$("#divDutyDataGroup").attr("class", "MenuMouseMove");
		$("#divDutyReport").attr("class", ""); 

}
//跳转到勤务类型管理页面
function onDutyType() { 
		m_dutyFrame_func_prop.url = "view/duty/dutytype.jsp";
		setCheckBox(m_dutyFrame_func_prop.ctl, 4);
		pageSwitch();
		$("#divDutyPrepare").attr("class", "");
		$("#divDutyType").attr("class", "MenuMouseMove");
		$("#divDutyDataGroup").attr("class", "");
		$("#divDutyReport").attr("class", ""); 
}
//跳转到详细统计也难的时候，将左侧的树，增加显示复选框
function setCheckBox(ctlA, ctlB) {
	if (ctlA != 2 && ctlB == 2) {
		$('#treeDutyFrmOrg').tree({
			'checkbox' : true
		});
		$('#treeDutyFrmOrg').tree('loadData', m_org_node);
	} else if (ctlA == 2 && ctlB != 2) {
		$('#treeDutyFrmOrg').tree({
			'checkbox' : false
		});
		$('#treeDutyFrmOrg').tree('loadData', m_org_node);
	}
	m_dutyFrame_func_prop.ctl = ctlB;
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
			orgId : m_dutyFrame_Org.id,
			orgCode : m_dutyFrame_Org.code,
			orgPath : m_dutyFrame_Org.path
		},
		async : false,
		success : function(req) {
			if (req.isSuccess) {
				var nodes = buildOrgTree(req.rows);
				m_org_node = nodes;
				$('#treeDutyFrmOrg').tree("loadData", nodes);
				var nodess = $('#treeDutyFrmOrg').tree('find',
						m_dutyFrame_Org.id);
				$('#treeDutyFrmOrg').tree('select', nodess.target);
				onOrgTreeDblClick(nodess);
			} else {
				$.messager.alert('提示', req.msg, "warning");
			}
		}
	});
}

function searchOrgAction() {
	var name = $.trim($('#txtOrgName').val());
	if(name ==""){
		return;
	}
	var a = findOrgs(name);
	$('#treeDutyFrmOrg').tree("loadData", a);
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
//跳转静茹详细报备页面；
function onClickData(date) {
	m_dutyFrame_func_prop.url = "view/duty/dutyprepare.jsp";
	var src = m_dutyFrame_func_prop.url + "?orgId="
			+ m_dutyFrame_func_prop.orgId + "&orgCode="
			+ m_dutyFrame_func_prop.orgCode + "&orgPath="
			+ m_dutyFrame_func_prop.orgPath + "&orgName="
			+ m_dutyFrame_func_prop.orgName + "&ymd=" + date;

	$("#ifmWorkSpace").attr("src", src);
};

