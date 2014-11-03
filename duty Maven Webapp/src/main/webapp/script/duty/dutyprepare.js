
var m_dutyprepare_Org = {}; /*当前组织机构*/
var m_ymd=0; /*当前年月日*/
var m_duty={}; /*备勤记录*/

var m_xid_max=0; //duty的treegrid的id,必须确保

var m_userNode={};//自定义节点信息

$(function () {
	$('#policeConditionwindow').window('close');
	$('#gpsConditionwindow').window('close');
	$('#weaponConditionwindow').window('close');
	$('#vehicleConditionwindow').window('close');
	$('#dutyTypeSelectwindow').window('close');
	$('#dutyTemplateSelectwindow').window('close');
	$('#UserNodeWindows').window('close');
	
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
	        toolbar:"#tb_source_police",
	        columns: [[
	               { title: 'id', field: 'id', align: 'center', width: 0, hidden: true },
	               { title: '姓名', field: 'name', align: 'center', width: 80 },
	               { title: '单位', field: 'orgName', align: 'center', width: 50} ,
	               { title: '类型', field: 'objType', align: 'center', width: 50, hidden: true,formatter:function(value,row,index){
	            	   row.objType=2;
	            	   return row.objType;
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
	            	   row.objType=1;
	            	   return row.objType;
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
	            	   row.objType=4;
	            	   return 4;
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
	            	   row.objType=3;
	            	   return 3;
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
							$(this).treegrid('enableDnd', row?row.xid:null);
						},
			onBeforeDrop:doBeforeDrop,
			onDrop:doDrop
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
            	structureItemTree(duty.items);
            	m_duty=duty;
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
function structureItemTree(items){
	$.each(items,function(i,val){
		structureItem(val,null);
	});
}
function structureItem(item,parent){

	item.getParent=function(){return parent;};
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
			structureItem(val,item);/*获取下级的汇总*/
			item.velicleCount +=val.velicleCount;
			item.policeCount +=val.policeCount;
			item.weaponCount+=val.weaponCount;
			item.gpsCount+=val.gpsCount;
		});
	}
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

/****************逻辑区域结束*********************/

/***************主菜单功能-----开始******************/

function saveDuty(){
	save(false,null);
}

function openSaveDutyTemplateWindow(){
	
}

/*保存到后台*/
function save(isTemplate,name){
	var duty={};
	duty.id=m_duty.id;
	duty.orgId=m_dutyprepare_Org.id;
	duty.name=name;
	duty.isTemplate=isTemplate;
	duty.ymd=m_ymd;
	duty.items=$('#tdDuty').treegrid('getData');
	
	$.ajax({
        url: "duty/save.do",
        type: "POST",
        dataType: "json",
        data:{'duty':JSON.stringify(duty)},
        async:false,
        success: function (req) {
            if (req.isSuccess) {//成功填充数据
            	m_duty.id=req.id;
            	$.messager.alert('提示', "保存成功!", "info");
            }
            else {
            	$.messager.alert('提示', "保存失败!", "info");
            }
        }
    });
	
}

/***************主菜单功能-----结束****************/

function getDispalyName(rowIndex,rowData){
	//
}


/**
 * 将界面数据映射到对象
 */
function viewToObj(){
	
}


function genDutyRow(itemId,name,typeId,innerTypeId,innerTypeName,dutyRow){
	if(dutyRow.id==undefined || dutyRow.id==null)
		dutyRow.id=0;
	dutyRow.xid=genXId(typeId,itemId);
	dutyRow.name=name;
	dutyRow.itemTypeId=typeId;
	dutyRow.itemId=itemId;
	dutyRow.itemInnerTypeId=innerTypeId;
	dutyRow.itemInnerTypeName = innerTypeName;
	dutyRow.displayName=genDisplayName(typeId,innerTypeName,name);
	dutyRow.itemTypeName=genItemTypeName(typeId);
	
}

function doBeforeDrop(tRow,sRow,point){
	
	var pTypeId=null;
	
	if(point=="append"){
		pTypeId=tRow.itemTypeId;
	}else{
		var p=tRow.getParent();
		pTypeId= p==null?0:p.itemTypeId;
	}
	
	var isSuccess=dutyItemRelate.check(pTypeId,sRow.objType);
	
	if(!isSuccess){
		return false;
	}else{
		return true;
	}
}

function doDrop(tRow,sRow,point){

	if(sRow.itemTypeId ==undefined){
		/*从资源拖动过来*/
		/*itemId,name,typeId,innerTypeId,innerTypeName,dutyRow*/
		var name=sRow.objType==2?sRow.name:sRow.number;
		genDutyRow(sRow.id,name,sRow.objType,sRow.typeId,sRow.typeName,sRow);
	}
		/*从新计算并加载数据*/
	var items=$('#tdDuty').treegrid('getData');
	structureItemTree(items);
	$('#tdDuty').treegrid('loadData',items);
	
}

function genXId(itemTypeId,itemId){
	var rs='';
	switch(itemTypeId){
	case  101:
		m_xid_max++;
		rs="101_AI_"+m_xid_max;
		break;
	case 999:
		m_xid_max++;
		rs="999_AI_"+m_xid_max;
		break;
	default:
		rs=itemTypeId +"_"+itemId;
		break;
	};
	return rs;
}

function genItemTypeName(itemTypeId){
	switch(itemTypeId){
	case 1:
		return "车辆";
	case 2:
		return "警员";
	case 3:
		return "武器";
	case 4:
		return "定位设备";
	case 100:
		return  "备勤类型";
	case  101:
		return "班次";
	case 999:
		return "自定义";
	};
}

function genDisplayName(itemTypeId,iteminnerTypeName,name){
	var rs='';
	switch(itemTypeId){
	case 1:
		rs=iteminnerTypeName+":"+name;
		break;
	case 2:
		rs=name;
		break;
	case 3:
		rs=iteminnerTypeName+":"+name;
		break;
	case 4:
		rs=iteminnerTypeName+":"+name;
		break;
	case 100:
		rs=name;
		break;
	case  101:
		rs=name;
		break;
	case 999:
		rs=name;
		break;
	};
	return rs;
}
var  dutyItemRelate={
		root:{
			children:[
			          100 /*勤务类型*/
			          ]
		},
		dutytype:{
			children:[
			          101 /*班次*/
			          ]
		},
		shift:{
			children:[
			          1,
			          2,
			          999
			          ]
		},

		vehicle:{
			 children:[
			           2,
			           4,
			           999
			           ]
		},
		police:{
			 children:[
			           3,
			           4
			           ]
		},
		weapon:{
			children:[
			          ]
		},
		gps:{
			 children:[
			           ]
		},
		usernode:{
			children:[
			          1,
			          2
			          ]
		},
		/**
		 * 检查拖动是否符合规则
		 * @param parenttype
		 * @param childtype
		 * @returns {Boolean}
		 */
		check:function(parenttype,childtype){
			var p=null;
			switch(parenttype){
			case 0:
				p=this.root;
			case 100:
				p=this.dutytype;
				break;
			case 101:
				p=this.shift;
				break;
			case 999:
				p=this.usernode;
				break;
			case 1:
				p=this.vehicle;
				break;
			case 2:
				p=this.police;
				break;
			case 3:
				p=this.weapon;
				break;
			case 4:
				p=this.gps;
				break;
			}
			
			if($.inArray(childtype,p.children)>=0){
				return true;
			}else{
				return false;
			}
		}
};

function addUserNode(){
	var row = $("#tdDuty").datagrid("getSelected");
	if(row==null){
		$.messager.alert('提示', "请选择父节点", "warning");
	}else{
		if(dutyItemRelate.check(row.itemTypeId,999)){
			$('#txtUserNodeName').val('');
			$('#UserNodeWindows').window('open');
			m_userNode.targetRow=row;
			m_userNode.editType="new";
		}else{
			$.messager.alert('提示', "只能在班次，车辆下面自定义节点!", "warning");
		}
	}
}
/**
 * 自定义编组确认
 */
function userNodeConfirm(){
	var name=$('#txtUserNodeName').val();
	
	if(name==''){
		$.messager.alert('提示', "请输入编组名称", "warning");
	}else{
		if(m_userNode.editType=='new'){
			var row={};
			genDutyRow(0,name,999,0,'编组',row);
			$("#tdDuty").treegrid('append', {
				parent: m_userNode.targetRow.xid,
				data: [row]
			});
			$('#tdDuty').treegrid('enableDnd', row.xid);
		}else{
			m_userNode.targetRow.name=name;
			m_userNode.targetRow.displayName=name;
			$("#tdDuty").treegrid('reload', m_userNode.targetRow.xid);
		}
		$('#UserNodeWindows').window('close');
	}
}










