var m_policeQuery_Org = {};
var m_report_sum={};

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
	
	 $('#dtReportSum').treegrid({ 
		  	pagination : false,
			fitColumns : true,  
	        width:'100%',
	        height:70,
	        idField : 'orgCount1',
	        treeField : 'orgCount2',
			//singleSelect: true,
		    //title:"警力总数",
	        columns: [[ 
	                   	{ title: '机构', field: 'orgCount2', align: 'left', width: 120,formatter:fmtOrgCount },
	                   	{ title: '值班领导', field: 'leaderCount', align: 'right', width: 80 },
	                   	{ title: '警力', field: 'policeCount', align: 'right', width: 80},
	                   	{ title: '车辆', field: 'vehicleCount', align: 'right', width: 80},
	                   	{ title: '枪支', field: "weaponCount", align: 'right', width: 80 },
	                   	{ title: '社区', field: "communityCount", align: 'right', width: 80},
	                   	{ title: '巡区', field: "patrolAreaCount", align: 'right', width: 80 },
	                   	{ title: '卡点', field: "bayonetCount", align: 'right', width: 80 } 
	              
	        ]]
	    });
	 $('#dtReport').treegrid({
			fitColumns : true,  
	        width:'100%',
	        height:300,
	        idField : 'id',
			treeField : 'orgShortName',
			//singleSelect: true,
		    //title:"警力总数",
	        columns: [[ 
					{ title: '机构树', field: 'orgShortName', align: 'left', width: 120},
					{ title: '值班领导', field: 'leaderNames', align: 'left', width: 80 },
					{ title: '警力', field: 'policeCount', align: 'right', width: 80},
					{ title: '车辆', field: 'vehicleCount', align: 'right', width: 80},
					{ title: '枪支', field: "weaponCount", align: 'right', width: 80 },
					{ title: '社区', field: "communityCount", align: 'right', width: 80},
					{ title: '巡区', field: "patrolAreaCount", align: 'right', width: 80 },
					{ title: '卡点', field: "bayonetCount", align: 'right', width: 80 } 
	        ]] 
	    });

		$("#cmbdutytype").combotree({  
	        valueField: 'id',
	        textField: 'name',
	        multiple:"true"
		});
 
	var cd=new Date();
	var dateStr=cd.toSimpleString();
		
	$('#dteBeginDate').datebox('setValue',dateStr);
	$('#spnBeginTime').timespinner('setValue','00:00');
	$('#spnEndTime').timespinner('setValue','23:59');
	
	$('#ckArmamentType1').attr('checked','checked');
	$('#ckArmamentType2').attr('checked','checked');
	$('#ckAttireType1').attr('checked','checked');
	$('#ckAttireType2').attr('checked','checked');
	
	 loaddutyTypeComboTree();
	 loadBaseDataForCombox("police/getPoliceType.do", $('#cmbpoliceType'));
	 loadBaseDataForCombox("duty/getdutyProperty.do", $('#dutyProperty'));
//	 getBaseDataCombobox("police/getPoliceType.do", "cmbpoliceType");
//	 getBaseDataCombobox("duty/getdutyProperty.do", "dutyProperty");
	 
	 var propDatas=$('#dutyProperty').combobox('getData');
	 
	 initCriteria();
});

function fmtOrgCount(value, row, index){
	return row.orgCount2 + "/"+row.orgCount1;
}

function loaddutyTypeComboTree(){
	$.ajax({
		url : "dutyType/list.do",
		type : "POST",
		dataType : "json",
		data:{isUsed:true},
		async : false,
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
	var propDatas=$('#dutyProperty').combobox('getData');
	loadReport();
	
};

function loadBaseDataForCombox(url,cmb){
	$.ajax({
		url : url,
		type : "POST",
		dataType : "json",
		async : false,
		success : function(req) {
			cmb.combobox('loadData', req);
		}
	});
}

function initCriteria(){
	
	var propId=[];
	var propDatas=$('#dutyProperty').combobox('getData');
	$.each(propDatas,function(i,v){
		propId.push(v.id);
	});
	$('#dutyProperty').combobox('setValues',propId);
	
	var policeTypeIds=[];
	var policeTypeDatas=$('#cmbpoliceType').combobox('getData');
	$.each(policeTypeDatas,function(i2,v2){
		policeTypeIds.push(v2.id);
	});
	$('#cmbpoliceType').combobox('setValues',policeTypeIds);
	
	
	$('#ckAttireType1').prop('checked',true);
	$('#ckAttireType2').prop('checked',true);
	
	$('#ckArmamentType1').prop('checked',true);
	$('#ckArmamentType2').prop('checked',true);
	
	$('#cmbdutytype').combogrid('selectAll');
	
}

function loadReport(){
var criteria=packCriteria();
	
	var s=JSON.stringify(criteria);
	
	$.ajax({
		url : "dutyReport/loadDutyReport.do",
		type : "POST",
		dataType : "json",
		data:{criteria:s},
		//async : false,
		success : function(req) {
			if (req.isSuccess) {// 成功填充数据
				var data=req.rows;
				var ss=buildReportTree(data);
				$('#dtReport').treegrid('loadData',ss);
				var sum=[];
				sum.push(m_report_sum);
				$('#dtReportSum').treegrid('loadData', sum);
			} else {
				alert("获取数据失败");
			}
		}
	});
}


function buildReportTree(data){
	var ss = [];
	
	if(data == null || data.length==0){
		return ss;
	}
    var count = data.length;    
    /**
     * 如果已经排过序的话，第一个肯定在根节点上
     * 以这个parentid作为后续rog是否在根节点上的依据。
     */
    var rootParent=data[0].parentId;
    m_report_sum={};
    m_report_sum.orgCount1=data.length;
    m_report_sum.orgCount2=0;
    m_report_sum.leaderCount =0;
    m_report_sum.policeCount  =0;
    m_report_sum.vehicleCount  =0;
    m_report_sum.weaponCount =0;
    m_report_sum.gpsCount   =0;
    m_report_sum.communityCount  =0;
    m_report_sum.patrolAreaCount  =0;
    m_report_sum.bayonetCount =0;
    
    for (var i = 0; i < count; i++) {
    	var node = data[i];
    	node.text = node.orgShortName;
        node.children=[];
        
        if(node.policeCount>0){
        	m_report_sum.orgCount2 ++;
        }
        m_report_sum.leaderCount +=node.leaderCount;
        m_report_sum.policeCount +=node.policeCount;
        m_report_sum.vehicleCount +=node.vehicleCount;
        m_report_sum.weaponCount +=node.weaponCount;
        m_report_sum.gpsCount  +=node.gpsCount;
        m_report_sum.communityCount +=node.communityCount;
        m_report_sum.patrolAreaCount +=node.patrolAreaCount;
        m_report_sum.bayonetCount +=node.bayonetCount;
        
        if(node.parentId==rootParent){
        	node.parentObj=null;
        	node.level=1;
        	ss.push(node);
        }
        
        for (var j = i; j < count; j++) {
        	var tmp = data[j];
        	if (tmp.parentId == node.id){
        		tmp.parentObj=node;
        		tmp.level=node.level+1;
        		node.children.push(tmp);
        	}
        }
        
    }
    
    return ss;
}

/**
 * 打包查询
 */
function packCriteria(){
	var criteria={};
	var dateStr=$("#dteBeginDate").datebox("getValue");
	criteria.beginTime2 = new Date(dateStr);
	criteria.endTime2=new Date(dateStr);
	
	var h1=$('#spnBeginTime').timespinner('getHours');
	var m1=$('#spnBeginTime').timespinner('getMinutes');
	
	var h2=$('#spnEndTime').timespinner('getHours');
	var m2=$('#spnEndTime').timespinner('getMinutes');
	
	criteria.ymd=criteria.beginTime2.toYMD();
	criteria.beginTime2.setHours(h1, m1, 0, 0);
	criteria.endTime2.setHours(h2, m2, 0, 0);
	
	criteria.beginTime=criteria.beginTime2.toSimpleString();
	criteria.endTime=criteria.endTime2.toSimpleString();
	criteria.beginTime2=undefined;
	criteria.endTime2=undefined;
	
	var orgs=window.parent.$('#treeDutyFrmOrg').tree("getChecked");

	var orgSel=OrgSelect.createNew(orgs);
	
	criteria.orgIds=orgSel.getIds();
	
	criteria.taskPropertyIds=[];
	var ps=$("#dutyProperty").combobox('getValues');
	$.each(ps,function(i,v){
		criteria.taskPropertyIds.push(v);
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
	
	criteria.armamentTypeIds=[];
	if($("#ckArmamentType1").prop('checked')){
		criteria.armamentTypeIds.push(0);
	}
	if($("#ckArmamentType2").prop('checked')){
		criteria.armamentTypeIds.push(1);
	}
	
	criteria.dutyTypeIds=[];
	var dt=$("#cmbdutytype").combogrid('getValues');
	$.each(dt,function(i,v){
		criteria.dutyTypeIds.push(v);
	});
	
	return criteria;
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
