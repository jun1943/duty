/*
 * 勤务管理主页面
 */

var m_dutyFrame_Org={};

var m_dutyFrame_func_prop={};

var m_org_map={};

var m_org_node={};

$(function () {
	
	var args = getUrlArgs();
	m_dutyFrame_Org.id = args["orgId"];
	m_dutyFrame_Org.code = args["orgCode"];
	m_dutyFrame_Org.path = args["orgPath"];
	
	$('#treeDutyFrmOrg').tree(
			{
				checkbox:false,
				onDblClick:onOrgTreeDblClick
			});
	
	loadFrmOrgs();
});

function onOrgTreeDblClick(node){
	m_dutyFrame_func_prop.orgId=node.id;
	m_dutyFrame_func_prop.orgCode=node.code;
	m_dutyFrame_func_prop.orgPath=node.path;
	var name = node.text;
	m_dutyFrame_func_prop.orgName = name;
	m_dutyFrame_func_prop.url = "view/duty/dutycalendar.jsp";
	pageSwitch();
}
/*
 * 页面切换
 */
function pageSwitch(node,url){
	var src=m_dutyFrame_func_prop.url
		+"?orgId="+m_dutyFrame_func_prop.orgId
		+"&orgCode="+m_dutyFrame_func_prop.orgCode
		+"&orgPath="+m_dutyFrame_func_prop.orgPath
		+"&orgName="+m_dutyFrame_func_prop.orgName;

	var esrc=encodeURI(src);
	$("#ifmWorkSpace").attr("src",esrc);
}


function onDutyPrepare(){
	m_dutyFrame_func_prop.url="view/duty/dutycalendar.jsp";
	setCheckBox(m_dutyFrame_func_prop.ctl,1);
	m_dutyFrame_func_prop.ctl=1;
	pageSwitch();
}

function onDutyReport(){
	m_dutyFrame_func_prop.url="view/duty/policeinquiry.jsp";
	setCheckBox(m_dutyFrame_func_prop.ctl,2);
	pageSwitch();
}

function onDutyDataGroup(name){
	m_dutyFrame_func_prop.url="view/duty/"+name+".jsp";
	setCheckBox(m_dutyFrame_func_prop.ctl,3);
	pageSwitch();
}

function onDutyType(){
	m_dutyFrame_func_prop.url="view/duty/dutytype.jsp";
	setCheckBox(m_dutyFrame_func_prop.ctl,4);
	pageSwitch();
}

function setCheckBox(ctlA,ctlB){
	if(ctlA!=2 && ctlB==2){
		$('#treeDutyFrmOrg').tree({'checkbox':true});
		$('#treeDutyFrmOrg').tree('loadData',m_org_node);
	}else if(ctlA==2 && ctlB!=2){
		$('#treeDutyFrmOrg').tree({'checkbox':false});
		$('#treeDutyFrmOrg').tree('loadData',m_org_node);
	}
	m_dutyFrame_func_prop.ctl=ctlB;
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
				m_org_node=nodes;
				$('#treeDutyFrmOrg').tree("loadData",nodes);
			} else {
				$.messager.alert('提示', req.msg,"warning");
			}
		}
	});
}

function searchOrgAction(){
	var name=$('#txtOrgName').val();
	var a=findOrgs(name);
	$('#treeDutyFrmOrg').tree("loadData",a);
}

function findOrgs(name){
	var a=[];
	if(m_org_node!=null){
		$.each(m_org_node,function(index,value){
			var o=findOrgTree(value,name);
			if(o!=null){
				a.push(o);
			}
		});
	}
	return a;
}

function findOrgTree(org,name,array){
	var ls=[];
	if(org.children!=null){
		$.each(org.children2,function(index,value){
			var o=findOrgTree(value,name);
			if(o!=null){
				ls.push(o);
			}
		});
	}
	
	org.children=ls;
	
	if(name="" || org.name.indexOf(name)>=0 || ls.length>0){
		return org;
	}else{
		return null;
	}
}

function onClickData(date){
	m_dutyFrame_func_prop.url="view/duty/dutyprepare.jsp";
	var src=m_dutyFrame_func_prop.url
	+"?orgId="+m_dutyFrame_func_prop.orgId
	+"&orgCode="+m_dutyFrame_func_prop.orgCode
	+"&orgPath="+m_dutyFrame_func_prop.orgPath
	+"&orgName="+m_dutyFrame_func_prop.orgName
	+"&ymd="+date;

	$("#ifmWorkSpace").attr("src",src);
};
