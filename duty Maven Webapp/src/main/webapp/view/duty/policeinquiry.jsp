<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<!--  
<link href='//cdn.datatables.net/plug-ins/a5734b29083/integration/jqueryui/dataTables.jqueryui.css' type='text/css' />
-->
<script src='<%=basePath%>script/duty/policeinquiry.js'
	type='text/javascript'></script>

<style type="text/css">
.prop_val { .
	width: 233px
}
</style>

<title>勤务报备</title> 
</head>

<body class="easyui-layout">
	<div id="tab" class="easyui-tabs" style="width:100%;height:800px">
		<div title="全局警力" style="padding:10px;height:100%" fit="true">
			<div id="div_titleInfo">
				<p>
					<label id="tt_orgName" style="color:#ff6600; font-size:18px"></label>
					<label style="margin-left:50px">开始时间:</label>
                    <input id="dteBeginTime" class="easyui-datebox" style="width:120px" />
                    <label> - </label>
                    <label>结束时间:</label>
                    <input id="dteEndTime" class="easyui-datebox" style="width:120px" />
                    <a id="searchpoliceQueryAction" name="searchpoliceQueryAction" href="javascript:void(0);" class="easyui-linkbutton"
                    iconcls="icon-search" onclick="searchQueryAction()" plain="true"></a>
				</p>
			</div>
			<div id="div_totalPoliceInfo">
				<div id="dtPolicetotal">
				</div>
			</div>
			<div id="div_detailsPoliceInfo">
				<div>
				<a href="javascript:void(0);" class="easyui-linkbutton"
                    iconcls="icon-report"  plain="true"></a>
                    <label style="color:#ff6600; font-size:18px">详情</label>
				</div>
				<div id="dtPolicedetails"></div>
			</div>
		</div> 
	</div>
</body>
</html>
