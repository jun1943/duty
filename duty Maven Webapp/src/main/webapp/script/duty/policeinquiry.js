var m_policeQuery_Org = {};
$(function() {
	var args = getUrlArgs();
	m_policeQuery_Org.id = args["orgId"];
	m_policeQuery_Org.code = args["orgCode"];
	m_policeQuery_Org.path = args["orgPath"];
	var orgName = args["orgName"];
	m_policeQuery_Org.name = decodeURI(orgName);
	$("#tt_orgName").html(m_policeQuery_Org.name+"警力:");
	
	 $('#dtPolicetotal').datagrid({ 
		  	pagination : false,
			fitColumns : true,  
	        width:'100%',
	        height:70,
			//singleSelect: true,
		    //title:"警力总数",
	        columns: [[
	               { title: 'id', field: 'id', align: 'left', width: 0, hidden: true },
	               { title: '机构树', field: 'orgTotal', align: 'left', width: 80 },
	               { title: '值班领导', field: 'dutyLeader', align: 'left', width: 100 },
	               { title: '值班主任', field: 'dutyDirector', align: 'left', width: 80},
	               { title: '值班警力', field: 'dutyPolice', align: 'left', width: 80},
	               { title: '车辆', field: 'vehicles', align: 'left', width: 80},
	               { title: '枪支', field: "weapons", align: 'left', width: 80 },
	               { title: '单位值班', field: "unitDuty", align: 'left', width: 80 },
	               { title: '接处警', field: "illuminates", align: 'left', width: 80 },
	               { title: '巡逻警力', field: "patrolPolice", align: 'left', width: 80},
	               { title: '动态卡点警力', field: "pointPolice", align: 'left', width: 80 },
	               { title: '巡区警力', field: "patrolareaPolice", align: 'left', width: 80 }
	              
	        ]]
	    });
	 $('#dtPolicedetails').datagrid({
			pagination : false,
			fitColumns : true,  
	        width:'100%',
	        height:400,
			//singleSelect: true,
		    //title:"警力总数",
	        columns: [[
	               { title: 'id', field: 'id', align: 'left', width: 0, hidden: true },
	               { title: '机构名称', field: 'orgName', align: 'left', width: 80 },
	               { title: '值班领导', field: 'dutyLeader', align: 'left', width: 100 },
	               { title: '值班主任', field: 'dutyDirector', align: 'left', width: 80},
	               { title: '值班警力', field: 'dutyPolice', align: 'left', width: 80},
	               { title: '车辆', field: 'vehicles', align: 'left', width: 80},
	               { title: '枪支', field: "weapons", align: 'left', width: 80 },
	               { title: '单位值班', field: "unitDuty", align: 'left', width: 80 },
	               { title: '接处警', field: "illuminates", align: 'left', width: 80 },
	               { title: '巡逻警力', field: "patrolPolice", align: 'left', width: 80},
	               { title: '动态卡点警力', field: "pointPolice", align: 'left', width: 80 },
	               { title: '巡区警力', field: "patrolareaPolice", align: 'left', width: 80 }
	              
	        ]] 
	    });
	 loadTotalPolice();
	 loadPoliceInfodetail();
});

function loadTotalPolice(){
	 $.ajax({
	        url: "",
	        type: "POST",
	        dataType: "json",
	        data:{},
	        success: function (req) {
		           //获取数据成功，加载datagrid数据
	        }
	    });
};
function loadPoliceInfodetail(){
	 $.ajax({
	        url: "",
	        type: "POST",
	        dataType: "json",
	        data:{},
	        success: function (req) {
	           //获取数据成功，加载datagrid数据
	        }
	    });
};