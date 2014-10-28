<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<!--  
<link href='//cdn.datatables.net/plug-ins/a5734b29083/integration/jqueryui/dataTables.jqueryui.css' type='text/css' />
-->
<script src='<%=basePath%>script/duty/dutyprepare.js' 	type='text/javascript'></script>

<style type="text/css">
	.prop_val{
		.width:233px
	}
</style>

<title>勤务报备</title>

</head>
	
<body class="easyui-layout" >
	<div id="divResource" data-options="region:'west',split:true" title="资源"
			style="width:20%;height:100%">
			<div id="tt" class="easyui-tabs" style="width:100%;height:500px;">
				<div title="Tab1" style="padding:10px;height:100%">  
       					 <ul id="treeDrop"></ul>
    			</div>
			</div>
			
	</div>
	
	<div id="divMember" data-options="region:'center'" title="备勤">
		<div class="easyui-layout" style="height:100%;width :100%">
			<div data-options="region:'north'"  style="height:30px">
				<div id="tbGroup" class="btn-toolbar">
					<div class="btn-group">
						<a id="btnAddPoliceGroup" href="javascript:void(0);"	class="easyui-linkbutton icon-camera-retro" 
						plain="true"	onclick="addPoliceGroup()">保存</a> 
						<a id="btnEditPoliceGroup"	href="javascript:void(0);" class="easyui-linkbutton"	iconcls="icon-edit" 
						plain="true" onclick="editPoliceGroup()">保存为模板</a>
						<a id="btnEditPoliceGroup"	href="javascript:void(0);" class="easyui-linkbutton"	iconcls="icon-edit" 
						plain="true" onclick="editPoliceGroup()">报备类型选择</a>
						<a id="btnDelPoliceGroup" href="javascript:void(0);"	class="easyui-linkbutton" iconcls="icon-cancel" 
						plain="true"	onclick="delPoliceGroup()">选择模板</a>
						<a id="btnDelPoliceGroup" href="javascript:void(0);"	class="easyui-linkbutton" iconcls="icon-cancel" 
						plain="true"	onclick="delPoliceGroup()">报备复制</a>
						<a id="btnDelPoliceGroup" href="javascript:void(0);"	class="easyui-linkbutton" iconcls="icon-cancel" 
						plain="true"	onclick="delPoliceGroup()">清空报备</a>
						<a id="btnDelPoliceGroup" href="javascript:void(0);"	class="easyui-linkbutton" iconcls="icon-cancel" 
						plain="true"	onclick="delPoliceGroup()">导出</a>
					</div>
				</div>
			</div>99
			<div data-options="region:'center'"  >
				<div id="aa" class="easyui-accordion" style="width:100%;height:90%;">    
    				<div title="Title1" data-options="iconCls:'icon-save'" style="overflow:auto;padding:5px;">
    					       
        				<h3 style="color:#0099FF;">Accordion for jQuery</h3>    
        				<p>Accordion is a part of easyui framework for jQuery.     
        					It lets you define your accordion component on web page more easily.
        				</p>    
    				</div>    
    				<div title="Title2" data-options="iconCls:'icon-reload'" style="padding:5px;">    
        				
    				</div>    
    				<div title="Title3">    
        				<ul id="treeD2"></ul> 
    				</div>    
				</div>
			</div>
		</div>
	</div>

</body>