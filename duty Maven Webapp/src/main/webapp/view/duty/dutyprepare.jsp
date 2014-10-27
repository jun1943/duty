<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<!--  
<link href='//cdn.datatables.net/plug-ins/a5734b29083/integration/jqueryui/dataTables.jqueryui.css' type='text/css' />
-->
<script src='<%=basePath%>script/duty/dutytype.js' 	type='text/javascript'></script>

<style type="text/css">
	.prop_val{
		.width:233px
	}
</style>

<title>勤务报备</title>

</head>
	
<body class="easyui-layout" >
	<div id="divResource" data-options="region:'west',split:true" title="资源"
			style="width:30%">
			<div id="tt" class="easyui-tabs" style="width:100%;height:100%;">
				<div title="Tab1" style="padding:20px;display:none;">  
       					 tab1  
    			</div>
			</div>
			
	</div>
	
	<div id="divMember" data-options="region:'center'" title="备勤">
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
	
	


</body>