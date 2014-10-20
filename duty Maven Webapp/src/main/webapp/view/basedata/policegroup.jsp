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

<body >
	

	<div class="easyui-layout" style="width:100%;height:100%;">
        <div data-options="region:'west',split:true" title="West" style="width:40%;"></div>
        <div data-options="region:'center',title:'Main Title',iconCls:'icon-ok'">
            <table class="easyui-datagrid"
                    data-options="url:'datagrid_data1.json',method:'get',border:false,singleSelect:true,fit:true,fitColumns:true">
                <thead>
                    <tr>
                        <th data-options="field:'itemid'" width="80">Item ID</th>
                        <th data-options="field:'productid'" width="100">Product ID</th>
                        <th data-options="field:'listprice',align:'right'" width="80">List Price</th>
                        <th data-options="field:'unitcost',align:'right'" width="80">Unit Cost</th>
                        <th data-options="field:'attr1'" width="150">Attribute</th>
                        <th data-options="field:'status',align:'center'" width="60">Status</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
	
</body>
</html>
