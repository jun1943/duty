<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<!--  
<link href='//cdn.datatables.net/plug-ins/a5734b29083/integration/jqueryui/dataTables.jqueryui.css' type='text/css' />
-->
<script src='<%=basePath%>script/basedata/policegroup.js'
	type='text/javascript'></script>
<title>人员分组</title>


</head>

<body>
	<div class="easyui-layout" style="width:100%;height:100%;">
		<div data-options="region:'west',split:true" title="组"
			style="width:40%;">
			<div id="tbGroup" class="btn-toolbar">
  				<div class="btn-group">
  					<a id="btnAddEvidenceBill" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-add"  plain="true" onclick="btnAddGroup()">创建</a>
                	<a id="btnEditEvidenceBill" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-edit" plain="true" onclick="btnEditGroup()">修改</a>
                	<a id="btnDelEvidenceBill" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-cancel" plain="true" onclick="btnDelGroup()">删除</a>
  				</div>
  			</div>
  			<div id="dtGroup" ></div>
  			
		</div>
		<div data-options="region:'center',title:'警员'">
			<div id="tbGroup" class="btn-toolbar">
  				<div class="btn-group">
  					<a id="btnAddEvidenceBill" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-add" plain="true" onclick="btnAddGroupMember()">添加</a>
                	<a id="btnEditEvidenceBill" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-edit" plain="true" onclick="btnDelGroupMember()">删除</a>
                	<a id="btnDelEvidenceBill" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-cancel" plain="true" onclick="btnCleanGroupMember()">清空</a>
  				</div>
  			</div>
			<div id="dtGroupMember"></div>
		</div>
	</div>
</body>
</html>
