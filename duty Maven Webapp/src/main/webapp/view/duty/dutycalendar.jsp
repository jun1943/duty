<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<!--  
<link href='//cdn.datatables.net/plug-ins/a5734b29083/integration/jqueryui/dataTables.jqueryui.css' type='text/css' />
-->
<script src='<%=basePath%>script/duty/dutycalendar.js'
	type='text/javascript'></script>
	<script src='<%=basePath%>script/exportExcleUtil.js'
	type='text/javascript'></script>
<link href='<%=basePath%>asset/css/images/dateStyle.css' media='all'
	rel='stylesheet' type='text/css' />
	
	<link href='<%=basePath%>asset/css/images/dutycalendar.css' media='all'
	rel='stylesheet' type='text/css' /> 

<title>勤务管理——工作日历</title> 
</head>
  <body oncontextmenu=self.event.returnValue=false>
    
	<!--日历 s-->
	<div class="dateBox">
		<!--日历主窗体 s-->
    	<div class="dateBoxMain">
    		<div class="dateBoxMainTItle">
        		<div class="dateBoxMainTItleTxt">报备月程表</div>
	            <div class="dateBoxMainTItleBox">
	            	<div class="dateBoxMainTItleBoxMain">
	                	<ul>
	                    	<li><img src="asset/css/images/dateLast.png" onclick="getDateClick('last')" /></li>
	                        <li><span class="lispan" id="sp_years"></span>年</li>
	                        <li><span class="lispan" id="sp_month"></span>月</li>
	                        <li><img  class="liimg" src="asset/css/images/dateNext.png" onclick="getDateClick('next')" /></li>
                    	</ul>
	                </div>
	            </div>	
	            <!-- 清楚本月报备数据以及清空剪切板 -->
	            <div class="dateBoxMainTItleTxt">
	            	<div class="dateBoxMainTItleTxtDIv">
	                    <a href="javascript:void(0);" onclick="clearAlldutyData()"><img src="asset/css/images/canelpast.png" alt="清除当月所有报备数据" title="清除当月所有报备数据" /></a>
	                    <a href="javascript:void(0);" onclick="clearClipbord()"><img src="asset/css/images/past.png" alt="取消剪贴板数据" title="取消剪贴板数据"  /></a>
	                </div>
	            </div>
        	</div>
        	<!-- 日历主界面 -->
        	<div class="dateBoxMainDate" style="overflow:auto">
	        	 <table id="dateTable" class="" width="100%" height="100%" style="font-size:12px" >
	                <thead style="height:15px">
	                    <tr>
	                    <th style="text-align:center;color:red"> 日</th>
	                    <th style="text-align:center;color:black"> 一</th>
	                    <th style="text-align:center;color:black"> 二</th>
	                    <th style="text-align:center;color:black"> 三</th>
	                    <th style="text-align:center;color:black"> 四 </th>
	                    <th style="text-align:center;color:black"> 五</th>
	                    <th style="text-align:center;color:red"> 六</th>
	                    </tr>
	                </thead>
	                <tbody id="dateBody"> 
	                </tbody> 
	            </table> 
        	</div>
    	</div>
	</div>
	 <!-- 报备详细明细窗体内容 -->
 	<div id="dutyDetailsForDaywindow"  title="警务报备情况" class="easyui-window clddtywindow" data-options="iconCls:'icon-tianyi-edit',modal:true" closed="true" 
	 	collapsible="false" minimizable="false" maximizable="false" resizable="false" shadow="false"  > 
		    <div id="tbdutydetailsforday" class="tbdutydetailsfordays"> 
		    <label>名称:</label><input id="txttargetName" type="text" class="easyui-validatebox">
                <a href="javascript:void(0);" class="easyui-linkbutton" 
                    iconcls="icon-tianyi-search" plain="true" onclick="btnSearchAction()">查询</a> 
                <a id="exprotToExcel" name="exprotToExcel" href="javascript:void(0);" class="easyui-linkbutton" 
                    iconcls="icon-tianyi-export" plain="true" onclick="btnExportAction()">导出</a>  
		    </div>
			<div id="tgddutydetailsforday" >
			</div>
	</div>
  </body>
</html>
