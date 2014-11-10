<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>勤务备勤</title>
    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script src='<%=basePath%>script/duty/dutyframe.js' 	type='text/javascript'></script>
  </head>
  
  <body>
<div id="cc" class="easyui-layout" style="width:100%;height:100%;">
	<div data-options="region:'west',title:'机构',split:true"  style="width:14%;">
		<div>
			<input id="txtOrgName" style="width:100px" type="text"/>
			<a id="btnSearchOrg" href="javascript:void(0);" class="easyui-linkbutton"
                    iconcls="icon-search" onclick="searchOrgAction()" plain="true"></a>
		</div>
    	<ul id="treeDutyFrmOrg"></ul>
    </div>
    <div data-options="region:'center',title:'警务备勤'"  >
    	<iframe id="ifmWorkSpace" name="ifmWorkSpace"  scrolling='yes'  frameborder='0'  style="width:100%;height:100%"></iframe>
    </div>        
    <div data-options="region:'east',title:'功能',split:true"  style="width:10%;">
    	<ul style="list-style-type:none"   	>
				<li><a href="javascript:void(0);" 
						class="easyui-linkbutton " plain="true"
						onclick="onDutyPrepare()">勤务报备</a>
				</li>
				<li><a href="javascript:void(0);" 
						class="easyui-linkbutton " plain="true"
						onclick="onDutyReport()">勤务综合查询</a>
				</li>
				<li><a href="javascript:void(0);" 
						class="easyui-linkbutton " plain="true" 
						onclick="onDutyDataGroup('policegroup')">分组管理</a>
				</li>
				<li><a href="javascript:void(0);" value="view/duty/dutytype.jsp"
						class="easyui-linkbutton" plain="true"
						onclick="onDutyType()">勤务类型管理</a>
				</li>
			</ul>
    </div>    
    
</div>    
    

		
  </body>
</html>
