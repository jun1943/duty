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
			<div id="tbPoliceInquiry" class="btn-toolbar" style="height:40px; margin-top:10px"> 
				<div class="btn-group" style="float:right; margin-right:20px">
					 <a id="exportData" name="exportData" href="javascript:void(0);" class="easyui-linkbutton"
                   iconcls="icon-export" onclick="btnExportDataAction()" plain="true" style="float:right; margin-left:20px">导出</a>
                   <a id="searchpoliceQuery" href="javascript:void(0);" class="easyui-linkbutton"
                   iconcls="icon-search"  plain="true" style="float:right">查询</a>
				</div>
		 	</div>
		 	<div id="my-search-box" class="panel-body" style="border:0px;display:none">
					<form class="form-inline">
						<div class="form-group">
							<div class="form-group">
								<div class="input-group">
									时间范围 
									<input id="dteBeginDate" class="easyui-datebox" style="width:200px" />
									<input id="dteBeginTime" class="easyui-timespinner"  style="width:80px;" data-options="min:'00:00',showSeconds:true" />
									——
									<input id="dteEndTime"   class="easyui-timespinner"  style="width:80px;" data-options="min:'00:00',showSeconds:true" />

								</div>
							</div>
						</div>
						<br />
						<div class="form-group">
							<div class="form-group">
								<div class="input-group">
									任务属性 <input id="dutyProperty" class="easyui-combobox" name="dutyProperty"  style="width:200px" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="form-group">
								<div class="input-group">
									衣着
									<input id="ckProattire" type="checkbox" >
									着装
									<input id="ckProplainclothes" type="checkbox" >
									便衣
								</div>
							</div>
						</div>
						<br />
						<div class="form-group">
							<div class="form-group">
								<div class="input-group">
									人员类别 <input id="cmpoliceType" class="easyui-combobox" name="policeType"  style="width:200px" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="form-group">
								<div class="input-group">
									武装
									<input id="ckProarmed" type="checkbox" >
									武装
									<input id="ckProunarmed" type="checkbox" >
									非武装
								</div>
							</div>
						</div>
						
						<br />
						<div class="form-group">
							<div class="form-group">
								<div class="input-group">
									勤务类型 <input id="cmtdutytype"  class="easyui-combotree" style="width:200px" >
								</div>
							</div>
						</div>
                   <a href="javascript:void(0);" class="easyui-linkbutton"
                   iconcls="icon-search" onclick="btnSearchQueryAction()" plain="true">查询</a> 
					</form>
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
