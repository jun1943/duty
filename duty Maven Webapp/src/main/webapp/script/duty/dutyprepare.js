
var m_dutyprepare_Org = {};

$(function () {
	var args = getUrlArgs();
	m_dutyprepare_Org.id = args["orgId"];
	m_dutyprepare_Org.code = args["orgCode"];
	m_dutyprepare_Org.path = args["orgPath"];
	
	$('#d1').draggable();
	$('#d2').draggable();
	
	$('#dx1').droppable();
	
	$('#treeDrop').tree(
			{
				dnd:true,
				animate:true,
				cascadeCheck : false,
				onBeforeDrop:onTreeD2BeforeDrop
				//onDrop:onTreeD2BeforeDrop
			});
	
	$('#treeD2').tree(
			{
				dnd:true,
				cascadeCheck : false,
				onBeforeDrop:onTreeD2BeforeDrop
			});
	
	loadOrgs();
	loadTreeD2();
});

function onTreeD2BeforeDrop(target,source,point){
	var t=target;
	var s=source;
	var p=point;
}

function loadOrgs(){
	
	$.ajax({
		url : "org/list.do",
		type : "POST",
		dataType : "json",
		data : {
			orgId:m_dutyprepare_Org.id,
			orgCode :m_dutyprepare_Org.code,
			orgPath: m_dutyprepare_Org.path
		},
		async : false,
		success : function(req) {
			if (req.isSuccess) {
				var nodes=buildOrgTree(req.rows);
				$('#treeDrop').tree("loadData",nodes);
			} else {
				$.messager.alert('提示', req.msg,"warning");
			}
		}
	});
}

function loadTreeD2(){
	var data=[
		      {
		    	  id:'1',
		    	  text:'a1',
		    	  children:[
		    	            {
		    	            	id:'1.1',
		    	            	text:'a1.1'
		    	            },
		    	            {
		    	            	id:'1.2',
		    	            	text:'a1.2'
		    	            },
		    	            ]
		      },
		      {
		    	  id:'2',
		    	  text:'a2',
		      }
		      ];
	
	$('#treeD2').tree("loadData",data);
	
}
