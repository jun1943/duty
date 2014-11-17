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
</head>

<body class="easyui-layout">
	<div data-options="region:'center',title:'资源'"
		style="padding:10px;width:30%">
		<div id="tbPolice" 
			style="height:40px; margin-top:10px">
			<div >
				<a id="btnAddPolice" href="javascript:void(0);"
					class="easyui-linkbutton" iconcls="icon-add" 
					onclick="btnAddPolice('add')">新增</a> <a id="btnEditPolice"
					href="javascript:void(0);" class="easyui-linkbutton"
					iconcls="icon-edit" plain="true">修改</a> <a id="btnUnLockPolice"
					href="javascript:void(0);" class="easyui-linkbutton"
					iconcls="icon-edit"  >启用</a> <a id="btnLockPolice"
					href="javascript:void(0);" class="easyui-linkbutton"
					iconcls="icon-edit" plain="true">停用</a> <a id="btnDelPolice"
					href="javascript:void(0);" class="easyui-linkbutton"
					iconcls="icon-cancel" plain="true" onclick="btnDelPolice()">删除</a>
			</div>
		</div>
	</div>


</body>
</html>