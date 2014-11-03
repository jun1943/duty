
var m_dutyprepare_Org = {}; /*当前组织机构*/
var m_ymd=0; /*当前年月日*/
var m_duty={}; /*备勤记录*/

var m_xid_max=0; //duty的treegrid的id,必须确保

$(function () {
	$('#policeConditionwindow').window('close');
	$('#gpsConditionwindow').window('close');
	$('#weaponConditionwindow').window('close');
	$('#vehicleConditionwindow').window('close');
	$('#dutyTypeSelectwindow').window('close');
	$('#dutyTemplateSelectwindow').window('close');
	
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
	        treeField: 'name',
	        //toolbar:"#tb_source_police",
	        columns: [[
	               { title: 'id', field: 'id', align: 'center', width: 0, hidden: true },
	               { title: '姓名', field: 'name', align: 'center', width: 80 },
	               { title: '警号', field: 'number', align: 'center', width: 80},
	               { title: '单位', field: 'orgName', align: 'center', width: 50} ,
	               { title: '类型', field: 'objType', align: 'center', width: 50, hidden: true,formatter:function(value,row,index){
	            	   return "警员";
	               } } 
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
	               { title: '车辆品牌', field: 'brand', align: 'center', width: 50} ,
	               { title: '类型', field: 'objType', align: 'center', width: 50, hidden: true,formatter:function(value,row,index){
	            	   return "车辆";
	               } } 
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
	               { title: 'GPS设备编号', field: 'number', align: 'center', width: 50} ,
	               { title: '类型', field: 'objType', align: 'center', width: 50, hidden: true,formatter:function(value,row,index){
	            	   return "定位设备";
	               } } 
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
	               { title: '规格标准', field: 'standard', align: 'center', width: 50} ,
	               { title: '类型', field: 'objType', align: 'center', width: 50, hidden: true,formatter:function(value,row,index){
	            	   return "武器";
	               } } 
	        ]],
			onLoadSuccess: function(row){
				$(this).treegrid('enableDnd', row?row.id:null);
			}
	    });
	   
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
	   
	   $('#tdDuty').treegrid({
		   fitColumns:true,
		   dnd:true,
		   resizable:true,
		   idField:'xid',
		   treeField:'displayName',
		   toolbar:'#tdDutyToolbar',
		   showFooter:true,
		   columns:[[
		             { title: 'xid', field: 'xid', width: 0, hidden: true },
		             { title: '名称', field: 'displayName', width: 200 },
		             { title: '类型', field: 'itemInnerTypeName', align: 'center', width: 70},
		             { title: '时间区间', field: 'beginTime', align: 'center', width: 100,formatter:fmtShiftPeriod },
		             { title: '车辆', field: 'velicleCount', align: 'right', width: 50,formatter:fmtDigit} ,
		             { title: '警员', field: 'policeCount', align: 'right', width: 50,formatter:fmtDigit} ,
		             { title: '武器', field: 'weaponCount', align: 'right', width: 50,formatter:fmtDigit} ,
		             { title: '定位', field: 'gpsCount', align: 'right', width: 50,formatter:fmtDigit} 
		             ]],
		    onLoadSuccess: function(row){
							$(this).treegrid('enableDnd', row?row.id:null);
						},
			onBeforeDrop:function(targetRow, sourceRow, point){
				/*drop前的业务逻辑验证*/
							
						}
	   });

	   initResourceQueryTG();
	   loadDutyType();
	   loadDuty();
});

function fmtDisplayTypeName(value,row,index){
	switch(row.itemTypeId){
	case 101:
		return "班次";
		break;
	case 999:
		return "自定义";
		break;
	default:
		return row.itemTypeName;
	}
}

function fmtDigit(value,row,index){
	if(value==0)
		return "";
	else
		return value;
}

function fmtShiftPeriod(value,row,index){
	var result="";
	if(row.beginTime !=null && row.endTime){
		var b=new Date(row.beginTime);
		var e=new Date(row.endTime);

		var b1=new Date(b.getFullYear(),b.getMonth(),b.getDate());
		var e1=new Date(e.getFullYear(),e.getMonth(),e.getDate());

		result =b.getHours()+":" +b.getMinutes() + "至";
		
		var diff=b1.dateDiff("d", e1);

		switch(diff){
		case 0:
			result += e.getHours()+":"+e.getMinutes();
			break;
		case 1:
			result +="明日"+ e.getHours()+":"+e.getMinutes();
			break;
		default:
			result ="起止时间错误!";
		}
		
	}
	return result;
}

function initResourceQueryTG(){
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
} 

function loadDutyType() {
    $.ajax({
        url: "dutyType/list.do",
        type: "POST",
        dataType: "json",
        async:false,
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
/**
 * 从后台获取当前duty数据
 */
function loadDuty(){
	$.ajax({
        url: "duty/loadDutyByOrgIdAndYMD.do",
        type: "POST",
        dataType: "json",
        data:{'orgId':m_dutyprepare_Org.id,'ymd':m_ymd},
        async:false,
        success: function (req) {
            if (req.isSuccess) {//成功填充数据
            	var duty=req.obj;
            	gatherDutyTree(duty);
                $('#tdDuty').treegrid('loadData', duty.items);
            }
            else {
                alert("获取数据失败");
            }
        }
    });
}

function initDuty(){
	m_duty.id=0;
	m_duty.orgId=m_dutyprepare_Org.id;
	m_duty.ymd=m_ymd;
	m_duty.items=[];
}

/**
 * 汇总各级节点的数据
 * @param duty
 */
function gatherDutyTree(duty){
	$.each(duty.items,function(i,val){
		gatherItem(val);
	});
}
function gatherItem(item){
	if(item.xid ==undefined){
		item.xid='x_'+100;
	}
	/*初始化数量等于0*/
	item.velicleCount =0;
	item.policeCount =0;
	item.weaponCount=0;
	item.gpsCount=0;
	
	if(item.xid==undefined || item.xid==null || item.xid==''){
		if(item.itemId==null || item.itemId  ==0 || item.itemId==''){
			/*班次，自定义的itemId等于null*/
			item.xid=item.itemTypeId + "_" + item.id;
		}else{
			item.xid=item.itemTypeId + "_" + item.itemId;
		}
	}
	switch(item.itemTypeId){
	case 1:
		item.velicleCount=1;
		break;
	case 2:
		item.policeCount=1;
		item.xid=2 +"_" +item.itemId;
		break;
	case 3:
		item.weaponCount=1;

		break;
	case 4:
		item.gpsCount=1;
		break;
	}
	if(item.children!=undefined && item.children !=null && item.children.length>0){
		$.each(item.children,function(i,val){
			gatherItem(val);/*获取下级的汇总*/
			item.velicleCount +=val.velicleCount;
			item.policeCount +=val.policeCount;
			item.weaponCount+=val.weaponCount;
			item.gpsCount+=val.gpsCount;
		});
	}
}

function getMaxXId(){
	
}

/**
 * 格式化开始时间-结束时间
 */
function fmtPeriod(value, rowData, index){
	
}


function SearchVehicleAction(){
	var number = $("#txtvnumber").val();
	var row = $('#dt_vehicleType').datagrid("getChecked");
	var typeId =row.length>0?row[0].id:0;

	$('#source_vehicle').treegrid("reload",{"orgId":m_dutyprepare_Org.id,"number":number,"typeId":typeId});
	$("#txtvnumber").val("");
	$('#dt_vehicleType').datagrid("unselectAll");
	$('#vehicleConditionwindow').window('close');
}
function SearchGpsAction(){ 
	var name = $("#txtgname").val();
	var row = $('#dt_gpsType').datagrid("getChecked");
	var typeId =row.length>0?row[0].id:0;


	$('#source_gpsdevice').treegrid("reload",{"orgId":m_dutyprepare_Org.id,"gpsname":name,"typeId":typeId});
	$("#txtgname").val("");
	$('#dt_gpsType').datagrid("unselectAll");
	$('#gpsConditionwindow').window('close');
}
function SearchWeaponAction(){
	var number = $("#txtwnumber").val();
	var row = $('#dt_weaponType').datagrid("getChecked");
	var typeId =row.length>0?row[0].id:0;

	$('#source_weapon').treegrid("reload",{"orgId":m_dutyprepare_Org.id,"number":number,"typeId":typeId});
	$("#txtwnumber").val("");
	$('#dt_weaponType').datagrid("unselectAll");
	$('#weaponConditionwindow').window('close');
}
function SearchPoliceAction(){
	var name = $("#txtpname").val();
	var typerow = $('#dt_policeType').datagrid("getChecked");
	var grouprow = $('#dt_groupType').datagrid("getChecked");
	var typeId =typerow.length>0?typerow[0].id:0;
	var groupId=grouprow.length>0?grouprow[0].id:0;

	$('#source_police').treegrid("reload",{"orgId":m_dutyprepare_Org.id,"name":name,"typeId":typeId,"groupId":groupId});
	$("#txtpname").val("");
	$('#dt_policeType').datagrid("unselectAll");
	$('#dt_groupType').datagrid("unselectAll");
	$('#policeConditionwindow').window('close');
};

/**************勤务报备模块业务逻辑*****************/
//勤务报备类型选择，根据选择类型，加载区域标签
function selectDutyTemplate(){ 
	 $('#dtDutyTemplate').datagrid({ 
		 url : "duty/loadTemplateByOrgId.do",
			queryParams : {
				'orgId' : m_dutyprepare_Org.id
			},
			pagination : false,
			fitColumns : true, 
			title:'勤务报备模板',
	        width:'90%',
	        height:'90%',
			singleSelect: true,
			columns : [ [ { field : 'ck', checkbox : true },
			              { title : '模板名称', field : 'name', align : 'center', width : 100  }, 
			              { title : 'Id', field : 'id', align : 'center', width : 10, hidden : true }
		              ] ]
		 
	 });
	$('#dutyTemplateSelectwindow').window('open');
};
function selectDutyType(){ 
	
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
			var titlename = rows[i].name;
			var accId = rows[i].id;
			if($.inArray(accId, ids)==-1){
				$('#dutyTypeAccordion').accordion('add',{
					title:titlename,
					//content: document.getElementById("div_worksheet"),
					content: initdatagridcontent(accId),
					tools:[ 
					{
						iconCls:'icon-remove',
						handler:reMoveAccdordion
					}]
				});

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
function initdatagridcontent(id){
	var html = '<div id="tb_worksheet_'+id+'" class="btn-toolbar"><div class="btn-group">'
			 + ' <input id="starttime_'+id+'" class="easyui-timespinner"  style="width:80px;" required="required" data-options="min:\'00:00\',showSeconds:false" />     '
			 + ' <input id="endtime_'+id+'" class="easyui-timespinner"  style="width:80px;" required="required" data-options="min:\'00:01\',showSeconds:false" />'
			 + ' <label>是否第二天</label><input type="checkbox" id="ck_isTomorrow_'+id+'"> '
			 + ' </div></div> '    
			 + ' <div><label>数据汇总：</label><label></label></div> '
			 + ' <div id="contentTab_'+id+'" class="easyui-tabs" data-options="tools:\'#contentTab-tools_'+id+'\'" style="width:500px;height:300px"></div> '    
			 + ' <div id="contentTab-tools_'+id+'"> '    
		     + ' <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:\'icon-add\'" onclick="addPanel('+id+')"></a> ' 
		     + ' <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:\'icon-remove\'" onclick="removePanel('+id+')"></a> '
		     + ' </div></div> '    ; 
	return html;
};
 var index = 0;
function addPanel(id){ 
	index++;
	$('#contentTab_'+id).tabs('add',{
		title: '班次'+index,
		content: '<div id="dt_shedule_'+id+'_'+index+'" style="padding:10px"> </div>',
		closable: false
	});
	$("#dt_shedule_"+id+"_"+index).treegrid({ 
		 //url:"police/getPoliceSource.do?orgId="+m_dutyprepare_Org.id+"&name=",
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
function removePanel(id){
	
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













