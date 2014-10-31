
var m_dutyprepare_Org = {};
var m_ymd=0;
var m_dutyDesc={};

$(function () {
	$('#policeConditionwindow').window('close');
	$('#gpsConditionwindow').window('close');
	$('#weaponConditionwindow').window('close');
	$('#vehicleConditionwindow').window('close');
	$('#dutyTypeSelectwindow').window('close');
	
	var args = getUrlArgs();
	m_dutyprepare_Org.id = args["orgId"];
	m_dutyprepare_Org.code = args["orgCode"];
	m_dutyprepare_Org.path = args["orgPath"];
	m_ymd =args["ymd"];
	
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
	InitGrid();
});

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

/**************勤务报备模块业务逻辑*****************/
//勤务报备类型选择，根据选择类型，加载区域标签
function selectDutyType(){ 
	 $('#dtDutyType').treegrid({ 
	        fitColumns: true,
	        rownumbers: false,
	        resizable: true,
	        idField: 'id',
	        treeField: 'name',  
	        width:'99%',
	        height:'100%',
	        singleSelect:false,
	        onlyLeafCheck:true,
	        onClickRow:isLeafSelected,
	        columns: [[
                   { field : 'ck', checkbox : true },
	               { title: 'id', field: 'id', align: 'left', width: 0, hidden: true },
	               { title: '名称', field: 'name', align: 'left', width: 200},
	               { title: 'parentId', field: "parentId", align: 'left', width: 5, hidden: true}
	              
	        ]]
	    });
	InitDutyTypeTreeGrid();
	$('#dutyTypeSelectwindow').window('open');
}; 
function isLeafSelected(row){
	if(!row.isLeaf){
		$('#dtDutyType').treegrid("unselect",row.id);
	} 
};
function InitDutyTypeTreeGrid(){ 
	 $.ajax({
	        url: "dutyType/list.do",
	        type: "POST",
	        dataType: "json",
	        //async:false,
	        success: function (req) {
	            if (req.isSuccess) {//成功填充数据
	            	var ss = buildDutyTypeTree(req.rows);
	                $('#dtDutyType').treegrid('loadData', ss);
	            }
	            else {
	                alert("获取数据失败");
	            }
	        }
	    }); 
};
//根据选择的勤务类型，加载according标签
function selectDutyTypeAction(){
	var rows = $('#dtDutyType').treegrid('getSelections');
	if(rows.length>0){ 
		var ids = [];
		for(var i =0; i<rows.length; i++){ 
			//var titlename = rows[i].name;
			var accId = rows[i].id;
			if($.inArray(accId, ids)==-1){
				
				var duty={};
				addDuty(duty,rows[i]);
				addDutyPanel(duty);
				
				ids.push(accId); 
			}
		}
		$('#dtDutyType').treegrid('unselectAll');
		$('#dutyTypeSelectwindow').window('close');
		index = 0;
	}
	
};
function reMoveAccdordion(){
	var pp = $('#dutyTypeAccordion').accordion('getSelected');
	if (pp){
		var index = $('#dutyTypeAccordion').accordion('getPanelIndex',pp);
		$('#dutyTypeAccordion').accordion('remove',index);
	}

}; 
function initdatagridcontent(duty){
	var html = '<div id="tb_worksheet_'+duty.cid+'" class="btn-toolbar"><div class="btn-group">'
			 + ' <input id="starttime_'+duty.cid+'" class="easyui-timespinner"  style="width:80px;" required="required" data-options="min:\'00:00\',showSeconds:false" />     '
			 + ' <input id="endtime_'+duty.cid+'" class="easyui-timespinner"  style="width:80px;" required="required" data-options="min:\'00:01\',showSeconds:false" />'
			 + ' <label>是否第二天</label><input type="checkbox" id="ck_isTomorrow_'+duty.cid+'"> '
			 + ' </div></div> '    
			 + ' <div><label>数据汇总：</label><label></label></div> '
			 + ' <div id="contentTab_'+duty.cid+'" class="easyui-tabs" data-options="tools:\'#contentTab-tools_'+duty.cid+'\'" style="width:500px;height:300px"></div> '    
			 + ' <div id="contentTab-tools_'+duty.cid+'"> '    
		     + ' <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:\'icon-add\'" onclick="addDutyShiftPanel('+duty.cid+')"></a> ' 
		     + ' <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:\'icon-remove\'" onclick="removeDutyShiftPanel('+duty.cid+')"></a> '
		     + ' </div></div> '    ; 
	return html;
};
 var index = 0;
function addPanel(id){ 
	index++;
	$('#contentTab_'+id).tabs('add',{
		title: '班次'+index,
		content: '<div id="dt_shedule_'+id+'_'+index+'" style="padding:10px"> </div>',
		closable: true
	});
	$("#dt_shedule_"+id+"_"+index).treegrid({ 
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

} 
function removeDutyShiftPanel(id){
	
	var tab = $('#contentTab_'+id).tabs('getSelected');
	if (tab){
		var tabindex = $('#contentTab_'+id).tabs('getTabIndex', tab);
		$('#contentTab_'+id).tabs('close', tabindex);
		index--;
	}
}

/****************逻辑区域结束*********************/



function loadDutyDesc(id){
	$.ajax({
        url: "duty/loadDutyDesc.do",
        type: "POST",
        dataType: "json",
        data:{'id':id},
        async:false,
        success: function (req) {
            if (req.isSuccess) {//成功填充数据
            	m_dutyDesc = req.obj;
            }
            else {
                alert("获取数据失败");
            }
        }
    }); 
}
/*
 * 初始化一个duty
 */
function addDuty(duty,dutyType){
	duty.id=0;
	duty.name=dutyType.name;
	duty.dutyDescId=m_dutyDesc.id;
	duty.typeId=dutyType.id;
}

function getNewShift(duty){
	var shift={};
	shift.dutyId=duty.id;
	shift.id=0;
	
	return shift;
}
/**
 * 将duty追加到panel,
 * @param duty
 */
function addDutyPanel(duty){
	duty.cid=idgen.getMaxDutyId();
	
	$('#dutyTypeAccordion').accordion('add',{
		title:duty.name,
		content: initdatagridcontent(duty),
		tools:[ 
		{
			iconCls:'icon-remove',
			handler:reMoveAccdordion
		}]
	});
	
	if(duty.children != undefined && duty.children !=null ){
		$.each(duty.children,function(index,value){
			addDutyShiftPanel(value);
		});
	}
}
/**
 * 将shift追加到panel,
 * @param shift
 */
function addDutyShiftPanel(cid){
	var index=0;
	shift.cid=idgen.getMaxDutyShiftId();
	
	$('#contentTab_'+shift.cid).tabs('add',{
		title: '班次'+index,
		content: '<div id="dt_shedule_'+shift.cid+'"  style="padding:10px"> </div>',
		closable: true
	});
	$("#dt_shedule_"+shift.cid).treegrid({ 
		 	url:"police/getPoliceSource.do?orgId="+m_dutyprepare_Org.id+"&name=",
		    dnd:true,
	        fitColumns: true, 
	        resizable: true,
	        idField: 'id',
	        treeField: 'id',  
	        //toolbar:"#tb_source_police",
	        columns: [[
	               { title: 'id', field: 'id', align: 'center', width: 0, hidden: true },
	               { title: '名称', field: 'displayName', align: 'center', width: 160 },
	               { title: '描述', field: 'display', align: 'center', width: 80},
	               { title: 'itemType', field: 'itemType', align: 'center', hidden: true },
	        ]],
			onLoadSuccess: function(row){
				$(this).treegrid('enableDnd', row?row.id:null);
			}
	    });
}

function getDispalyName(rowIndex,rowData){
	//
}


/**
 * 将界面数据映射到对象
 */
function viewToObj(){
	
}


var idgen={
		did:0,
		dsid:0,
		
		getMaxDutyId:function(){
			this.did++;
			return "d_"+this.did;
		},
		
		getMaxDutyShiftId:function(){
			this.dsid++;
			return "ds_"+this.dsid;
		}
};

var DutyMgr={
		cid:0,
		getNextCId:function(){
			cid++;
			return cid;
		},
		
		
		createNew:function(dutyDesc){
			var dutymgr={};
			
			dutymgr.dutyDesc=dutyDesc;
			if(dutymgr.dutyDesc.duties==undefined || dutymgr.dutyDesc.duties==null){
				dutymgr.dutyDesc.duties=[];
			}
			dutymgr.createDuty=function(type){
				var duty={};
				duty.id=0;
				duty.typeId=type.id;
				duty.name=type.name;
				duty.shifts=[];
				
				duty.dutyDescId=dutymgr.dutyDesc.id;
				dutymgr.dutyDesc.duties.push(duty);
				
				return duty;
			};
			
			dutymgr.createShift=function(dutycid){
				var duty=dutymgr.getDutyByCId(dutycid);
				var shift={};
				shift.id=0;
				shift.dutyId=duty.id;
				shift.items=[];
			};
			
			dutymgr.viewDuty=function(duty){
				if(duty.cid==undefined || duty.cid==null || duty.cid ==0)
					duty.cid=
				duty.cid=idgen.getMaxDutyId();
				
				$('#dutyTypeAccordion').accordion('add',{
					title:duty.name,
					content: initdatagridcontent(duty),
					tools:[ 
					{
						iconCls:'icon-remove',
						handler:reMoveAccdordion
					}]
				});
				
				if(duty.children != undefined && duty.children !=null ){
					$.each(duty.children,function(index,value){
						addDutyShiftPanel(value);
					});
				}
			};
			
			dutymgr.viewShift=function(shift){
				
			};
			
			dutymgr.createAndViewDuty=function(type){
				var duty=dutymgr.createDuty(type);
				dutymgr.viewDuty(duty);
			};
			
			dutymgr.createAndViewShift=function(dutycid){
				var shift=dutymgr.createShift(dutycid);
				dutymgr.viewShift(shift);
			};
			
			/**
			 * 通过cid查找duty
			 */
			dutymgr.getDutyByCId=function(cid){
				$.each(dutymgr.dutyDesc.duties,function(index,value){
					if(value.cid==cid){
						return value;
					}
				});
			};
			
			/**
			 * 通过cid查找shift
			 */
			dutymgr.getDutyByCId=function(cid){
				$.each(dutymgr.dutyDesc.duties,function(i,val){
					$.each(val.shifts,function(i2,val){
						if(val.cid==cid){
							return val;
						}
					});
				});
			};
			
			return dutymgr;
			
		}
		
};













