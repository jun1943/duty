
/*
*请勿类型管理页面；
*
*增加、珊瑚、修改、启用锁定操作；
*/

$(function () {
	
	
	//加载勤务类型相关属性，以下拉列表的形式呈现；
	
	$("#cmbProperty").combobox({
        valueField: 'id',
        textField: 'name',
        panelHeight: "auto",
        multiple:true
    });
	
	$("#cmbTaskType").combobox({
        valueField: 'id',
        textField: 'text',
        panelHeight: "auto",
        data:[
              {id:1,text:'社区'},
              {id:2,text:'巡区'},
              {id:3,text:'卡点'}
              ]
    });
	
    $('#dtDutyType').treegrid({
        fitColumns: true,
        rownumbers: true,
        resizable: true,
        idField: 'id',
        treeField: 'name',
        toolbar: '#tbDutyType',
        singleSelect: true,
        onDblClickRow:selDutyRow,
        columns: [[
               { title: 'id', field: 'id', align: 'left', width: 0, hidden: true },
               { title: '名称', field: 'name', align: 'left', width: 200 },
               { title: '人数上限', field: 'maxPolice', align: 'left', width: 50,formatter: getMaxPolice },
               { title: '统计显示方式', field: 'isShowname', align: 'left', width: 50, formatter: getDutyTypeDisplayType },
               { title: '属性', field: 'properties', align: 'left', width: 150,formatter: getDutyTypeProperties },
               { title: '关联任务', field: 'assoTaskType', align: 'left', width: 60,formatter: getDutyTypeTaskType},
               { title: '着装', field: "attireType", align: 'left', width: 60, formatter: getDutyTypeAttireType },
               { title: '武器', field: "armamentType", align: 'left', width: 60, formatter: getDutyTypeArmamentType },
               { title: '停用', field: "isUsed", align: 'left', width: 30, formatter: getDutyTypeUsed },
               { title: 'parentId', field: "parentId", align: 'left', width: 5, hidden: true}
              
        ]]
    });
       
    loadDutyType();
    loadDutyProperties();
});
//设置人数上限；
function changeUnMax(){
	if($('#chkUnMax').prop("checked")){
		$('#txtMaxPolice').val("");
		$('#txtMaxPolice').attr("disabled", "disabled");
	}else{
		$('#txtMaxPolice').val("");
		$('#txtMaxPolice').removeAttr("disabled");
	}
		
}
//设置显示内容，名称还是刷量
function getDutyTypeDisplayType(value, rowData, index){
	if(rowData!=null){
		if(rowData.isShowname)
			return "名称";
		else
			return "数量";
	}
}

function getDutyTypeProperties(value, rowData, index){
	var a="";
	if(rowData!=null && rowData.properties !=null){
		
		var ps=rowData.properties;
		var count=ps.length;
		for(var i=0;i<count;i++){
			var p=ps[i];
			if(a=="")
				a =p.name;
			else
				a =a+";"+p.name;
		}
	}
	return a;
}

function getDutyTypeTaskType(value, rowData, index){
	if(rowData!=null){
		switch(rowData.assoTaskType){
		case 1:
			return "社区";
		case 2:
			return "巡区";
		case 3:
			return "卡点";
		default:
			return "";
		
		}
	}
}

function getDutyTypeArmamentType(value, rowData, index){
	if(rowData!=null){
			if(rowData.armamentType==0)
				return "非武装";
			else
				return "武装";
	}
}

function getDutyTypeAttireType(value, rowData, index){
	if(rowData!=null){
		switch(rowData.attireType){
		case 0:
			return "制服";
		case 1:
			return "便衣";	
		default:
			return "";
		}
	}
}

function getMaxPolice(value, rowData, index){
	if(rowData!=null){
		if(rowData.maxPolice==null || rowData.maxPolice==0){
			return "不限";
		}else{
			return rowData.maxPolice;
		}
	}
}

function getDutyTypeUsed(value, rowData, index){
	if(rowData!=null){
		if(rowData.isUsed)
			return "启用";
		else
			return "停用";
	}
}
//加载勤务类型属性；
function loadDutyProperties(){
	$.ajax({
        url: "dutyType/loadProperties.do",
        type: "POST",
        dataType: "json",
        //async:false,
        success: function (req) {
            if (req.isSuccess) {//成功填充数据
            	//var a = JSON.parse(req.rows);
            	$('#cmbProperty').combobox("loadData", req.rows);
            }
            else {
                alert("获取数据失败");
            }
        }
    });
}


//获取勤务类型列表
function loadDutyType() {
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
}



/**
 * 增加新的根节点
 */
function  addRootDutyType(){
	$('#txtDutyTypeId').val(0);
	$('#txtDutyTypeParentId').val(0);
	$('#txtDutyTypeParentName').val('');
	$('#txtDutyTypeParentFullPath').val('');
	$('#txtDutyTypeName').val('');
	$('#txtMaxPolice').val('');
	$('#chkUnMax').attr("checked", true);
	$('#radioDisplayType1').attr('checked', true);
	$('#cmbProperty').combobox('clear');
	$('#cmbTaskType').combobox('clear');
	$('#radioAttireType1').attr('checked', true);
	$('#radioArmamentType1').attr('checked', true);
	$('#txtDutyTypeIsUsed').val(true); //新增的节点默认使用状态
	$('#txtDutyTypeIsLeaf').val(true);//新增的节点永远多是最末级
	$("#winDutyType").window("open");
}
/**
 * 添加子节点
 */
function addChildDutyType(){
	var row = $("#dtDutyType").datagrid("getSelected");
	if(row !=null){
		if(!row.isUsed){
			$.messager.alert('提示', '已停用的父节点不能再添加子节点!');
		}else{
			$('#txtDutyTypeId').val(0);
			$('#txtDutyTypeParentId').val(row.id);
			$('#txtDutyTypeParentName').val(row.name);
			$('#txtDutyTypeParentFullPath').val(row.fullpath);
			$('#txtDutyTypeName').val('');
			$('#txtMaxPolice').val('');
			$('#chkUnMax').attr("checked", true);
			$('#radioDisplayType1').attr('checked', true);
			$('#cmbProperty').combobox('clear');
			$('#cmbTaskType').combobox('clear');
			$('#radioAttireType1').attr('checked', true);
			$('#radioArmamentType1').attr('checked', true);
			$('#txtDutyTypeIsUsed').val(true); //新增的节点默认使用状态
			$('#txtDutyTypeIsLeaf').val(true);//新增的节点永远多是最末级
			
			$("#winDutyType").window("open");
		}
	}else{
		$.messager.alert('提示', '请先选择父节点!');
	}
}
/**
 * 编辑勤务类型
 */
function editDutyType(){
	var row = $("#dtDutyType").datagrid("getSelected");
	if(row !=null){
		
		if(!row.isLeaf)/*如果不是子节点就不能再修改名称！*/
			$('#txtDutyTypeName').prop('disabled',true);
		else
			$('#txtDutyTypeName').prop('disabled',false);
		
		$('#txtDutyTypeId').val(row.id);
		$('#txtDutyTypeParentId').val(row.parentId);
		$('#txtDutyTypeParentName').val(row.parentName);
		$('#txtDutyTypeParentFullPath').val(row.parentFullPath);
		$('#txtDutyTypeName').val(row.name);

		if(row.maxPolice==null || row.maxPolice==0){
			$('#chkUnMax').attr("checked", true);
			$('#txtMaxPolice').val('');
		}else{
			$('#chkUnMax').attr("checked", false);
			$('#txtMaxPolice').val(row.maxPolice);
		}
		
		if(row.isShowname){
			$('#radioDisplayType2').attr('checked', true);
		}else{
			$('#radioDisplayType1').attr("checked", true);
		}
		
		var count=row.properties.length;
		var pa=[];
		for(var i=0;i<count;i++){
			var p=row.properties[i];
			pa.push(p.id);
		}
		
		$('#cmbProperty').combobox('setValues',pa);
		if(row.assoTaskType>0){
			$('#cmbTaskType').combobox('setValue',row.assoTaskType);
		}
		$("input[name='attireType'][value="+row.attireType+"]").attr("checked",true);  //根据Value值设置Radio为选中状态
		$("input[name='armamentType'][value="+row.armamentType+"]").attr("checked",true);  //根据Value值设置Radio为选中状态

		$('#txtDutyTypeIsUsed').val(row.isUsed); 
		$('#txtDutyTypeIsLeaf').val(row.isLeaf);
		
		$("#winDutyType").window("open");
	}
}
//删除勤务类型，只能删除最末级节点
function delDutyType(){
	var row = $("#dtDutyType").datagrid("getSelected");
	if(row !=null){
		if(!row.isLeaf){
			$.messager.alert('提示', '只能删除最末级节点!');
		}else{
			$.messager.confirm('操作提示',  "确定要删除["+row.name + "]吗?", function (r) {
	            if (r) 
	            	$.ajax({
	                    url: "dutyType/deleteDutyType.do",
	                    type: "POST",
	                    dataType: "json",
	                    data:{
	                    	id:row.id
	                    },
	                    async:false,
	                    success: function (req) {
	                        if (req.isSuccess) {//成功填充数据
	                        	loadDutyType();
	                        }
	                        else {
	                        	$.messager.alert('错误',req.msg);
	                        }
	                    }
	                });
	        });
		}
	}else{
		$.messager.alert('提示', '请先选择勤务类型!');
	}
}

/**
 * 启用勤务类型
 */
function useDutyType(){
	var row = $("#dtDutyType").datagrid("getSelected");
	if(row !=null){
		if(row.isUsed){
			$.messager.alert('提示', '该勤务类型已经启用!');
			return;
		}
		updateUsedState(row.id,!row.isUsed);

	}else{
		$.messager.alert('提示', '请先选择勤务类型!');
	}
}


/**
 * 停用勤务类型
 */
function unUseDutyType(){
	var row = $("#dtDutyType").datagrid("getSelected");
	if(row !=null){
		if(!row.isUsed){
			$.messager.alert('提示', '该勤务类型已经停用!');
			return;
		}

		if(!row.isLeaf){
			$.messager.confirm('操作提示',  "停用["+row.name + "]及下属所有勤务类型?", function (r) {
	            if (r) 
	            	updateUsedState(row.id,!row.isUsed);
	        });
		}else{
			updateUsedState(row.id,!row.isUsed);
		}
	}else{
		$.messager.alert('提示', '请先选择勤务类型!');
	}
}


function  updateUsedState(id,isUsed){
	$.ajax({
        url: "dutyType/changeDutyTypeUseState.do",
        type: "POST",
        dataType: "json",
        data:{
        	id:id,isUsed:isUsed
        },
        //async:false,
        success: function (req) {
            if (req.isSuccess) {//成功填充数据
            	loadDutyType();
            }
            else {
                alert("获取数据失败");
            }
        }
    });
}

//曹村勤务类型模块事件

function saveDutyType(){
	var dt={};
	dt.properties=[];
	
	dt.id=$('#txtDutyTypeId').val();
	dt.parentId=$('#txtDutyTypeParentId').val();
	dt.name=$('#txtDutyTypeName').val();
	
	if($('chkUnMax').attr("checked"))
		dt.maxPolice=0;
	else
		dt.maxPolice =$('#txtMaxPolice').val();
	
	dt.isShowname = $('input:radio[name="displayType"]:checked').val();
	var ps=$('#cmbProperty').combobox('getValues');
	var count=ps.length;
	for(var i=0;i<count;i++){
		var p={};
		p.id=ps[i];
		dt.properties.push(p);
	}
	dt.assoTaskType=$('#cmbTaskType').combobox('getValue');
	dt.attireType=$('input:radio[name="attireType"]:checked').val();
	dt.armamentType=$('input:radio[name="armamentType"]:checked').val();
	dt.isLeaf=$('#txtDutyTypeIsLeaf').val();
	dt.isUsed=$('#txtDutyTypeIsUsed').val();
	
	$.ajax({
        url: "dutyType/saveDutyType.do",
        type: "POST",
        dataType: "json",
		data : {
			'dutyType' : JSON.stringify(dt)
		},
        async:false,
        success: function (req) {
            if (req.isSuccess) {//成功填充数据
            	loadDutyType();
            	$('#txtDutyTypeId').val(req.id);//回写保存后的id
            	$.messager.alert('提示', '保存成功!');
            }
            else {
            	$.messager.alert('提示', '保存失败!'+req.msg);
            }
        }
    });
}

function selDutyRow(){
	editDutyType();
}

