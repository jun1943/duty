
var m_dutyprepare_Org = {};

$(function () {
	$('#policeConditionwindow').window('close');
	$('#gpsConditionwindow').window('close');
	$('#weaponConditionwindow').window('close');
	$('#vehicleConditionwindow').window('close');
	var args = getUrlArgs();
	m_dutyprepare_Org.id = args["orgId"];
	m_dutyprepare_Org.code = args["orgCode"];
	m_dutyprepare_Org.path = args["orgPath"];
	
	$('#d1').draggable();
	$('#d2').draggable();
	
	$('#dx1').droppable();
	
//	$('#treeDrop').tree({
//		dnd:true,
//		animate:true,
//		cascadeCheck : false,
//		onBeforeDrop:onTreeD2BeforeDrop
//		//onDrop:onTreeD2BeforeDrop
//	});
	   $('#source_police').treegrid({ 
		    url:"police/getPoliceSource.do?orgId="+m_dutyprepare_Org.id+"&name=",
		    dnd:true,
	        fitColumns: true, 
	        resizable: true,
	        idField: 'id',
	        treeField: 'id',  
	        toolbar:"#tb_source_police",
	        columns: [[
	               { title: 'id', field: 'id', align: 'center', width: 0, hidden: true },
	               { title: '姓名', field: 'name', align: 'center', width: 80 },
	               { title: '警号', field: 'number', align: 'center', width: 80},
	               { title: '单位', field: 'orgName', align: 'center', width: 50} 
	        ]],
			onLoadSuccess: function(row){
				$(this).treegrid('enableDnd', row?row.id:null);
			}
	    });

	   $('#source_vehicle').treegrid({ 
		    url:"vehicle/getVehicleSource.do?orgId="+m_dutyprepare_Org.id+"&number=",
	        fitColumns: true, 
		    dnd:true,
	        resizable: true,
	        idField: 'id',
	        treeField: 'id',  
	        toolbar:"#tb_source_vehicle",
	        columns: [[
	               { title: 'id', field: 'id', align: 'center', width: 0, hidden: true },
	               { title: '车辆类型', field: 'typeName', align: 'center', width: 80 },
	               { title: '车牌号码', field: 'number', align: 'center', width: 80},
	               { title: '车辆品牌', field: 'brand', align: 'center', width: 50} 
	        ]],
			onLoadSuccess: function(row){
				$(this).treegrid('enableDnd', row?row.id:null);
			}
	    });
	   $('#source_gpsdevice').treegrid({ 
		    url:"gpsdevice/getGpsdeviceSource.do?orgId="+m_dutyprepare_Org.id+"&gpsname=",
	        fitColumns: true, 
		    dnd:true,
	        resizable: true,
	        idField: 'id',
	        treeField: 'id',  
	        toolbar:"#tb_source_gpsdevice",
	        columns: [[
	               { title: 'id', field: 'id', align: 'center', width: 0, hidden: true },
	               { title: 'GPS类型', field: 'typeName', align: 'center', width: 80 },
	               { title: 'GPS显示名称', field: 'gpsName', align: 'center', width: 80},
	               { title: 'GPS设备编号', field: 'number', align: 'center', width: 50} 
	        ]],
			onLoadSuccess: function(row){
				$(this).treegrid('enableDnd', row?row.id:null);
			}
	    });

	   $('#source_weapon').treegrid({ 
		    url:"weapon/getweaponSource.do?orgId="+m_dutyprepare_Org.id+"&number=",
	        fitColumns: true, 
		    dnd:true,
	        resizable: true,
	        idField: 'id',
	        treeField: 'id',  
	        toolbar:"#tb_source_weapon",
	        columns: [[
	               { title: 'id', field: 'id', align: 'center', width: 0, hidden: true },
	               { title: '武器类型', field: 'typeName', align: 'center', width: 80 },
	               { title: '武器编号', field: 'number', align: 'center', width: 80},
	               { title: '规格标准', field: 'standard', align: 'center', width: 50} 
	        ]],
			onLoadSuccess: function(row){
				$(this).treegrid('enableDnd', row?row.id:null);
			}
	    });
	$('#treeD2').tree({
		dnd:true,
		cascadeCheck : false,
		onBeforeDrop:onTreeD2BeforeDrop
	});
	
	loadOrgs();
	loadTreeD2();
	InitGrid();
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
				//$('#treeDrop').tree("loadData",nodes);
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




function InitGrid(){
	$('#dt_policeType').datagrid({
		url : "police/getPoliceTypeList.do",  
		fitColumns : true,
		pagination: false, 
		title:"人员类别",
		columns : [ [ {field : 'ck',checkbox : true}, 
		              {title : 'Id',field : 'id',align : 'center',width : 10,hidden : true}, 
		              {title : '类型',field : 'name',align : 'center',width : 150} 
	              ] ]
	});
	
//	$('#dt_dutyType').treegrid({  
//        fitColumns: true, 
//        idField: 'id',
//        treeField: 'name', 
//        title:"勤务类型",
//        columns: [[ 
//                    {field : 'ck',checkbox : true}, 
//               { title: 'id', field: 'id', align: 'left', width: 0, hidden: true },
//               { title: '名称', field: 'name', align: 'left', width: 200 } 
//        ]]
//    });
	$('#dt_groupType').datagrid({
		url : 'policeGroup/getPoliceGrouplist.do?orgId='+m_dutyprepare_Org.id, 
		fitColumns : true,
		pagination: false, 
		title:"人员分组",
		columns : [ [ {field : 'ck',checkbox : true}, 
		              {	title : 'Id',field : 'id',align : 'left',width : 10,hidden : true}, 
		              {	title : '组名称',field : 'name',align : 'left',width : 150	}
	              ] ]
	});
	$('#dt_gpsType').datagrid({
		url : 'gpsdevice/getGpsTypelist.do', 
		fitColumns : true,
		pagination: false, 
		title:"定位设备类型",
		columns : [ [ {field : 'ck',checkbox : true}, 
		              {	title : 'Id',field : 'id',align : 'left',width : 10,hidden : true}, 
		              {	title : '设备类型',field : 'name',align : 'left',width : 150	}
	              ] ]
	});
	$('#dt_weaponType').datagrid({
		url : 'weapon/getWeaponTypelist.do', 
		fitColumns : true,
		pagination: false, 
		title:"武器类型",
		columns : [ [ {field : 'ck',checkbox : true}, 
		              {	title : 'Id',field : 'id',align : 'left',width : 10,hidden : true}, 
		              {	title : '武器类型',field : 'name',align : 'left',width : 150	}
	              ] ]
	});
	$('#dt_vehicleType').datagrid({
		url : 'vehicle/getvehicleTypelist.do', 
		fitColumns : true,
		pagination: false, 
		title:"车辆类型",
		columns : [ [ {field : 'ck',checkbox : true}, 
		              {	title : 'Id',field : 'id',align : 'left',width : 10,hidden : true}, 
		              {	title : '车辆类型',field : 'name',align : 'left',width : 150	}
	              ] ]
	});
	loadDutyType();
} 
function loadDutyType() {
    $.ajax({
        url: "dutyType/list.do",
        type: "POST",
        dataType: "json",
        //async:false,
        success: function (req) {
            if (req.isSuccess) {//成功填充数据
            	var ss = buildDutyTypeTree(req.rows);
                $('#dt_dutyType').treegrid('loadData', ss);
            }
            else {
                alert("获取数据失败");
            }
        }
    });
};
function SearchVehicleAction(){
	var number = $("#txtvnumber").val();
	var row = $('#dt_vehicleType').datagrid("getChecked");
	var typeId;
	if(row.length>0){
		typeId= row[0].id;
	}
	$('#source_vehicle').treegrid("reload",{"orgId":m_dutyprepare_Org.id,"number":number,"typeId":typeId});
	$("#txtvnumber").val("");
	$('#dt_vehicleType').datagrid("unselectAll");
	$('#vehicleConditionwindow').window('close');
}
function SearchGpsAction(){ 
	var name = $("#txtgname").val();
	var row = $('#dt_gpsType').datagrid("getChecked");
	var typeId;
	if(row.length>0){
		typeId= row[0].id;
	}
	$('#source_gpsdevice').treegrid("reload",{"orgId":m_dutyprepare_Org.id,"gpsname":name,"typeId":typeId});
	$("#txtgname").val("");
	$('#dt_gpsType').datagrid("unselectAll");
	$('#gpsConditionwindow').window('close');
}
function SearchWeaponAction(){
	var number = $("#txtwnumber").val();
	var row = $('#dt_weaponType').datagrid("getChecked");
	var typeId;
	if(row.length>0){
		typeId= row[0].id;
	}
	$('#source_weapon').treegrid("reload",{"orgId":m_dutyprepare_Org.id,"number":number,"typeId":typeId});
	$("#txtwnumber").val("");
	$('#dt_weaponType').datagrid("unselectAll");
	$('#weaponConditionwindow').window('close');
}
function SearchPoliceAction(){
	var name = $("#txtpname").val();
	var typerow = $('#dt_policeType').datagrid("getChecked");
	var grouprow = $('#dt_groupType').datagrid("getChecked");
	var typeId;
	var groupId;
	if(typerow.length>0){
		typeId= typerow[0].id;
	}
	if(grouprow.length>0){
		groupId = grouprow[0].id;
	}
	$('#source_police').treegrid("reload",{"orgId":m_dutyprepare_Org.id,"name":name,"typeId":typeId,"groupId":groupId});
	$("#txtpname").val("");
	$('#dt_policeType').datagrid("unselectAll");
	$('#dt_groupType').datagrid("unselectAll");
	$('#policeConditionwindow').window('close');
};


























