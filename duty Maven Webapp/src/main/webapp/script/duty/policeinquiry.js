var m_policeQuery_Org = {};
$(function() {
	var args = getUrlArgs();
	m_policeQuery_Org.id = args["orgId"];
	m_policeQuery_Org.code = args["orgCode"];
	m_policeQuery_Org.path = args["orgPath"];
	var orgName = args["orgName"];
	m_policeQuery_Org.name = decodeURI(orgName); 
	
	$("#btnsearchQueryBox").bind("click", function() {
		$('#my-search-box').toggle();
	});
	
	$("#dutyProperty").combobox({
        valueField: 'id',
        textField: 'name',
        panelHeight: "auto",
        multiple:true
    });
	
	$("#cmbpoliceType").combobox({
        valueField: 'id',
        textField: 'name',
        panelHeight: "auto",
        multiple:true
    });
	
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
	 $('#dtPolicedetails').treegrid({
			fitColumns : true,  
	        width:'100%',
	        height:300,
	        idField : 'id',
			treeField : 'orgShortName',
			//singleSelect: true,
		    //title:"警力总数",
	        columns: [[ 
					{ title: '机构树', field: 'orgShortName', align: 'left', width: 120 },
					{ title: '值班领导', field: 'shiftLeaderCount', align: 'left', width: 80 },
					{ title: '警力', field: 'policeCount', align: 'center', width: 80},
					{ title: '车辆', field: 'vehicleCount', align: 'center', width: 80},
					{ title: '枪支', field: "weaponCount", align: 'center', width: 80 },
					{ title: '巡逻警力', field: "patrolpoliceCount", align: 'center', width: 80},
					{ title: '卡点警力', field: "checkpointpoliceCount", align: 'center', width: 80 },
					{ title: '巡区警力', field: "patrolareaPoliceCount", align: 'center', width: 80 } 
	        ]] 
	    });

		$("#cmbdutytype").combotree({  
	        valueField: 'id',
	        textField: 'name',
	        multiple:"true"
		});
 
	 loadTotalPolice();
	 loadTotalPolicedetail();
	 loaddutyTypeComboTree();
	 getBaseDataCombobox("police/getPoliceType.do", "cmbpoliceType");
	 getBaseDataCombobox("duty/getdutyProperty.do", "dutyProperty");
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
function loadTotalPolicedetail(){
	var beginTime ="";
	var endTime ="";
	 $.ajax({
	        url: "dutyCalendar/getTotalPolicedetail.do?orgId=" + m_policeQuery_Org.id + "&orgCode=" + m_policeQuery_Org.code+"&orgPath=" + m_policeQuery_Org.path+"&beginTime="+beginTime+"&endTime="+endTime,
	        type: "POST",
	        dataType: "json", 
	        success: function (req) {
	        	if(req.isSuccess){
	        		$('#dtPolicedetails').datagrid("loadData",req.rows);
	        	}else
        		{
	        		alert("获取数据失败");
        		}
	        }
	    });
}; 
function loaddutyTypeComboTree(){
	$.ajax({
		url : "dutyType/list.do",
		type : "POST",
		dataType : "json",
		data:{isUsed:true},
		//async : false,
		success : function(req) {
			if (req.isSuccess) {// 成功填充数据
				var ss = buildDutyTypeTree(req.rows);
				$('#cmbdutytype').combotree('loadData', ss);
			} else {
				alert("获取数据失败");
			}
		}
	});
};

function btnExportDataAction(){
	 alert("导出数据");
};
function btnSearchQueryAction(){
	packCriteria();
};



/**
 * 打包查询
 */
function packCriteria(){
	var criteria={};
	var dateStr=$("#dteBeginDate").datebox("getValue");
	criteria.beginTime = new Date(dateStr);
	criteria.endTime=new Date(dateStr);
	
	var h1=$('#spnBeginTime').timespinner('getHours');
	var m1=$('#spnBeginTime').timespinner('getMinutes');
	
	var h2=$('#spnEndTime').timespinner('getHours');
	var m2=$('#spnEndTime').timespinner('getMinutes');
	
	criteria.beginTime.setHours(h1, m1, 0, 0);
	criteria.endTime.setHours(h2, m2, 0, 0);
	
	var orgs=window.parent.$('#treeDutyFrmOrg').tree("getChecked");

	var orgSel=OrgSelect.createNew(orgs);
	
	criteria.ids=orgSel.getIds();
	
	criteria.taskProperyIds=[];
	var ps=$("#dutyProperty").combobox('getValues');
	$.each(ps,function(i,v){
		criteria.taskProperyIds.push(v);
	});
	
	criteria.attireTypeIds=[];
	if($("#ckAttireType1").prop('checked')){
		criteria.attireTypeIds.push(0);
	}
	if($("#ckAttireType2").prop('checked')){
		criteria.attireTypeIds.push(1);
	}
	
	criteria.policeTypeIds=[];
	var pt=$("#cmbpoliceType").combobox('getValues');
	$.each(pt,function(i,v){
		criteria.policeTypeIds.push(v);
	});
	
	criteria.dutyTypeIds=[];
	var dt=$("#cmbdutytype").combogrid('getValues');
	$.each(dt,function(i,v){
		criteria.dutyTypeIds.push(v);
	});
}


var OrgSelect={
		createNew:function(orgs){
			var _orgSelect={};
			var _minLevel=99999;
			
			var _orgs=orgs;
			var _result=[];
			var _map={};
			
			_orgSelect.getIds=function(){
				_orgSelect.getMinLevel();
				_orgSelect.addOrgsToArray();
				
				return _orgSelect.getWholeOrgIds();
			};
			
			_orgSelect.getWholeOrgIds=function(){
				var ids=[];
				$.each(_result,function(i,v){
					ids.push(v.id);
				});
				return ids;
			};
			
			_orgSelect.getWholeOrgs=function(){
				addOrgsToArray(_orgs);
				return _result;
			};
			
			_orgSelect.getMinLevel=function(){
				$.each(_orgs,function(i,v){
					if(v.level<_minLevel){
						_minLevel=v.level;
					}
				});
				return _minLevel;
			};
			
			_orgSelect.addOrgsToArray=function(){
				$.each(_orgs,function(i,v){
					_orgSelect.addArray(v);
				});
			};
			
			_orgSelect.addArray=function(org){
				if(org.level >= _minLevel){
					if(_map[org.id]==undefined){
						_result.push(org);
						_map[org.id]=org.id;
					}
					
					if(org.level > _minLevel && org.parentObj!=null){
						_orgSelect.addArray(org.parentObj);
					}
				}
			};

			
			return _orgSelect;
		}
};
