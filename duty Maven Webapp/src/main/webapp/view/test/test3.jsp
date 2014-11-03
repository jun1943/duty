<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<!--  
<link href='//cdn.datatables.net/plug-ins/a5734b29083/integration/jqueryui/dataTables.jqueryui.css' type='text/css' />
-->  
<title>人员分组</title>

<script type="text/javascript">
	 $(function () {
		 $('#source_police').treegrid({ 
			    url:"police/getPoliceSource.do?orgId=15&name=",
			    dnd:true,
		        fitColumns: true, 
		        resizable: true,
		        idField: 'id',
		        treeField: 'name',   
		        columns: [[
		               { title: 'id', field: 'id', align: 'center', width: 0, hidden: true },
		               { title: '姓名', field: 'name', align: 'center', width: 80 },
		               { title: '警号', field: 'number', align: 'center', width: 80},
		               { title: '单位', field: 'orgName', align: 'center', width: 50} ,
		               { title: '类型', field: 'objType', align: 'center', width: 50, hidden: true,formatter:function(value,row,index){
		            	   return "警员";
		               } } 
		        ]],
				onLoadSuccess: function(row, param){
					//enableDnd(this);
					$(this).treegrid('enableDnd', row?row.id:null);
				},
			onBeforeDrag: function(row){
					var i =1;
					i++;
				},	// return false to deny drag
			onStartDrag: function(row){
					var i =1;
					i++;
				},
			onStopDrag: function(row){
					var i =1;
					i++;
				},
			onDragEnter: function(targetRow, sourceRow){
					var i =1;
					i++;
				},	// return false to deny drop
			onDragOver: function(targetRow, sourceRow){
					var i =1;
					i++;
				},	// return false to deny drop
			onDragLeave: function(targetRow, sourceRow){
					var i =1;
					i++;
				},
			onBeforeDrop: function(targetRow, sourceRow, point){
					var i =1;
					i++;
				},
			onDrop: function(targetRow, sourceRow, point){
					var i =1;
					i++;
				}
		    });
		    
	   $('#source_vehicle').treegrid({ 
		    url:"vehicle/getVehicleSource.do?orgId=15&number=",
	        fitColumns: true, 
		    dnd:true,
	        resizable: true,
	        idField: 'id',
	        treeField: 'name',   
	        columns: [[
	               { title: 'id', field: 'id', align: 'center', width: 0, hidden: true },
	               { title: '车辆类型', field: 'typeName', align: 'center', width: 80 },
	               { title: '车牌号码', field: 'number', align: 'center', width: 80},
	               { title: '车辆品牌', field: 'brand', align: 'center', width: 50} ,
	               { title: '类型', field: 'objType', align: 'center', width: 50, hidden: true,formatter:function(value,row,index){
	            	   return "车辆";
	               } } 
	        ]],
			onLoadSuccess: function(row, param){
				$(this).treegrid('enableDnd', row?row.id:null);
			},
			onBeforeDrag: function(row){
					var i =1;
					i++;
				},	// return false to deny drag
			onStartDrag: function(row){
					var i =1;
					i++;
				},
			onStopDrag: function(row){
					var i =1;
					i++;
				},
			onDragEnter: function(targetRow, sourceRow){
					var i =1;
					i++;
				},	// return false to deny drop
			onDragOver: function(targetRow, sourceRow){
					var i =1;
					i++;
				},	// return false to deny drop
			onDragLeave: function(targetRow, sourceRow){
					var i =1;
					i++;
				},
			onBeforeDrop: function(targetRow, sourceRow, point){
					var i =1;
					i++;
				},
			onDrop: function(targetRow, sourceRow, point){
					var i =1;
					i++;
				}
	    });
	    $('#dtDutyType').treegrid({

	        fitColumns: true,
	        rownumbers: true,
	        resizable: true,
			    dnd:true,
	        idField: 'id',
	        treeField: 'name', 
	        title:'勤务类型',
	        singleSelect: true,
	        columns: [[
	               { title: 'id', field: 'id', align: 'left', width: 0, hidden: true },
	               { title: '名称', field: 'name', align: 'left', width: 200 },
	               { title: '编号', field: 'number', align: 'left', width: 200 },
	               { title: '机构', field: 'orgName', align: 'left', width: 200 },
	               { title: '类型', field: 'objType', align: 'left', width: 200 }  
	        ]],
			onLoadSuccess: function(row, param){
				$(this).treegrid('enableDnd', row?row.id:null);
			},
			onBeforeDrag: function(row){
					var i =1;
					i++;
				},	// return false to deny drag
			onStartDrag: function(row){
					var i =1;
					i++;
				},
			onStopDrag: function(row){
					var i =1;
					i++;
				},
			onDragEnter: function(targetRow, sourceRow){
					var i =1;
					i++;
				},	// return false to deny drop
			onDragOver: function(targetRow, sourceRow){
					var i =1;
					i++;
				},	// return false to deny drop
			onDragLeave: function(targetRow, sourceRow){
					var i =1;
					i++;
				},
			onBeforeDrop: function(targetRow, sourceRow, point){
					var i =1;
					i++;
				},
			onDrop: function(targetRow, sourceRow, point){
					var i =1;
					i++;
				}
	    });
	    loadDutyType();
    }); 
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
		switch(rowData.assoTaskType){
		case 1:
			return "制服";
		case 2:
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
</script>
</head>

<body class="easyui-layout">
	<div data-options="region:'west',title:'资源'" style="padding:10px;width:30%">
		<div id="tt" class="easyui-tabs" >	
			<div title="人员" style="padding:10px ">  
				<div id="source_police"></div> 
   			</div>
			<div title="车辆" style="padding:10px ">  
    			<div id="source_vehicle"></div> 
   			</div>		
		</div>
	</div>
	<div data-options="region:'center',title:'树节点'" style="padding:10px;">
		<div id="dtDutyType"></div>	 
	</div>
	  
</body>
</html>