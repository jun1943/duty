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
						{ title: '机构树', field: 'orgName', align: 'center', width: 80 },
						{ title: '值班领导', field: 'shiftLeaderCount', align: 'center', width: 80 },
						{ title: '值班主任', field: 'chiefLeaderCount', align: 'center', width: 80},
						{ title: '值班警力', field: 'dutypoliceCount', align: 'center', width: 80},
						{ title: '车辆', field: 'vehicleCount', align: 'center', width: 80},
						{ title: '枪支', field: "weaponCount", align: 'center', width: 80 },
						{ title: '单位值班', field: "unitdutyCount", align: 'center', width: 80 },
						{ title: '接处警', field: "illuminatesCount", align: 'center', width: 80 },
						{ title: '巡逻警力', field: "patrolpoliceCount", align: 'center', width: 80},
						{ title: '动态卡点警力', field: "checkpointpoliceCount", align: 'center', width: 80 },
						{ title: '巡区警力', field: "patrolareaPoliceCount", align: 'center', width: 80 } 
	              
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
					{ title: '机构树', field: 'orgName', align: 'center', width: 80 },
					{ title: '值班领导', field: 'shiftLeaderCount', align: 'center', width: 80 },
					{ title: '值班主任', field: 'chiefLeaderCount', align: 'center', width: 80},
					{ title: '值班警力', field: 'dutypoliceCount', align: 'center', width: 80},
					{ title: '车辆', field: 'vehicleCount', align: 'center', width: 80},
					{ title: '枪支', field: "weaponCount", align: 'center', width: 80 },
					{ title: '单位值班', field: "unitdutyCount", align: 'center', width: 80 },
					{ title: '接处警', field: "illuminatesCount", align: 'center', width: 80 },
					{ title: '巡逻警力', field: "patrolpoliceCount", align: 'center', width: 80},
					{ title: '动态卡点警力', field: "checkpointpoliceCount", align: 'center', width: 80 },
					{ title: '巡区警力', field: "patrolareaPoliceCount", align: 'center', width: 80 } 
	        ]] 
	    });
	 loadTotalPolice();
	 loadPoliceInfodetail();
});

function loadTotalPolice(){
	var beginTime ="";
	var endTime ="";
	
	 $.ajax({
	        url: "dutyCalendar/getTotalPolice.do?orgId=" + m_policeQuery_Org.id + "&orgCode=" + m_policeQuery_Org.code+"&orgPath=" + m_policeQuery_Org.path+"&beginTime="+beginTime+"&endTime="+endTime,
	        type: "POST",
	        dataType: "json", 
	        success: function (req) {
	        	if(req.isSuccess){
	        		$('#dtPolicetotal').datagrid("loadData",req.rows);
	        	}else
	        		{
	        		alert("获取数据失败");
	        		}
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