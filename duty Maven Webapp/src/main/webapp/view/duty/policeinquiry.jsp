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
                    <a id="exportData" name="exportData" href="javascript:void(0);" class="easyui-linkbutton"
                    iconcls="icon-export" onclick="btnExportDataAction()" plain="true" style="float:right; margin-left:20px">导出</a>
                    <a id="searchpoliceQuery" href="javascript:void(0);" class="easyui-linkbutton"
                    iconcls="icon-search" onclick="btnSearchQueryAction()" plain="true" style="float:right">查询</a>
				</p>
 
				<p>
					<table style="width:80%; height:150px; text-align:center">
					<tr>
						<td><label style="color:#ff6600">时间范围:</label></td>	
						<td colspan="13" style=" text-align:left">
							<input id="dteBeginDate" class="easyui-datebox" style="width:120px" />
							<input id="dteBeginTime" class="easyui-timespinner"  style="width:100px;" data-options="min:'00:00',showSeconds:true" />
							——
							<input id="dteEndTime"   class="easyui-timespinner"  style="width:100px;" data-options="min:'00:00',showSeconds:true" /> 
						</td>
					</tr>
					<tr><td><label style="color:#ff6600">任务属性</label></td>
							   <td><input id="ckProattack" type="checkbox" ></td>
							   <td>打击</td>
							   <td><input id="ckProguardagainst" type="checkbox" ></td>
							   <td>防范</td>
							   <td><input id="ckProcounterterrorism" type="checkbox" ></td>
							   <td>反恐</td>
							   <td><input id="ckProstability" type="checkbox" ></td>
							   <td>维稳</td>
							   <td><label style="color:#ff6600">衣着</label></td>
								<td><input id="ckProattire" type="checkbox" ></td>
								<td>着装</td>
								<td><input id="ckProplainclothes" type="checkbox" ></td>
								<td>便衣</td><td></td><td></td><td></td><td></td></tr>
							<tr><td><label style="color:#ff6600">人员类型</label></td>
								<td><input id="ckPropoliceman" type="checkbox" ></td>
								<td>民警</td>
								<td><input id="ckProauxiliarypolice" type="checkbox" ></td>
								<td>辅警</td>
								<td><input id="ckProemployee" type="checkbox" ></td>
								<td>职工</td><td></td><td></td>
								<td><label style="color:#ff6600">武装</label></td>
								<td><input id="ckProarmed" type="checkbox" ></td>
								<td>武装</td>
								<td><input id="ckProunarmed" type="checkbox" ></td>
								<td>非武装</td><td></td><td></td><td></td><td></td></tr>
							<tr><td><label style="color:#ff6600">报备类型</label></td>
								<td colspan="13" style=" text-align:left">
								<input id="cmtdutytype"  class="easyui-combotree" style="width:200px" >
								</td></tr>
					</table>
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
