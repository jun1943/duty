<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<!--  
<link href='//cdn.datatables.net/plug-ins/a5734b29083/integration/jqueryui/dataTables.jqueryui.css' type='text/css' />
-->
<script src='<%=basePath%>script/basedata/vehicle.js'
	type='text/javascript'></script>
<title>车辆管理</title>


</head>

<body class="easyui-layout">
  
	
	<div data-options="region:'center'"style="padding:10px;"> 
		 
		 
				 
			 
				<div id="tbVehicle" class="btn-toolbar" style="height:40px; margin-top:10px">
	  				<div class="btn-group">
	  					<a id="btnAddVehicle" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-add"  plain="true" onclick="btnAddVehicle()">创建</a>
	                	<a id="btnEditVehicle" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-edit" plain="true" onclick="btnEditVehicle()">修改</a>
	                	<a id="btnDelVehicle" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-cancel" plain="true" onclick="btnDelVehicle()">删除</a>
	                	
	  				</div>  				
	  				<div class="btn-group" style="float:right">
	  					<a id="btnSearchVehicle" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-search"  plain="true" onclick="btnSearchVehicle()">查询</a>
	                	<!-- a id="btnPrintList" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-print" plain="true" onclick="btnPrintList()">打印</a>
	                	<a id="btnExportList" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-export" plain="true" onclick="btnExportList()">导出</a> -->
	               	</div>
	  			</div>
	  			<div id="my-search-box" class="panel-body" style="border:0px;display:none">
	  			 <form class="form-inline"> 
	                  <div class="form-group">
	                      <div class="form-group">
	                        <div class="input-group">
	                           		　　查询范围选择 
	                         <input id="isSubOrg" class="easyui-combobox" name="dept"
										data-options="valueField:'id',textField:'name',data:[{id: 0,name: '本級机构'},{id: 1,name: '本级及下级机构'}]" />

	                        </div>
	                      </div>
	                  </div>
	                  
	                  <div class="form-group">
	                      <div class="form-group">
	                        <div class="input-group">
	                          		车牌号码:
	                           <input id="txtsearchnumber" class="easyui-validatebox" type="text" >
	                        </div>
	                      </div>
	                  </div> 
	                  
	                 <a id="btnSearchAction" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-search"  plain="true" onclick="btnSearchAction()">查询</a>
					<!-- button type="submit" class="btn btn-info">查询</button -->
					</form>
				</div>	
	  			<div id="dtVehicle" >
  				</div> 
  			</div>  
  <div id="vehicleinfowindow" class="easyui-window" title="新增/编辑车辆信息"
			data-options="iconCls:'icon-edit',modal:true" closed="true" 
	 	collapsible="false" minimizable="false" maximizable="false" resizable="false" shadow="false" 
			style="width: 400px; height: 500px; padding: 10px;">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'south',border:false"
					style="padding: 2px; height: 30px;">
					<a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'icon-save',plain:true" style="float:right"
						onclick="saveVehicleAction()">保存</a> 
				</div>
				<div data-options="region:'center',border:false"
					style="text-align: right; margin-left: 5px;
            padding: 5px 0 0;">
					<table style="width:100%;height:99%; font: 12px 微软雅黑;">
						<tr>
							<td><label>车辆品牌</label></td>
							<td><input type="text" class="easyui-validatebox" id="txtbrand" ></td>
						</tr>
						<tr>
							<td><input type="hidden" id="vehicleId"><label>车辆类型:</label></td>
							<td><input id="txttype" class="easyui-combobox" /></td>
						</tr>
						<tr>
							<td><label>座位数:</label></td>
							<td><input type="text" class="easyui-validatebox" id="txtsiteqty"></td>
						</tr>
						<tr>
							<td><label>车牌号码:</label></td>
							<td><input type="text" class="easyui-validatebox" data-options="required:true" id="txtnumber"></td>
						</tr>
						<tr>
							<td><label>车辆用途:</label></td>
							<td><input type="text" class="easyui-validatebox" id="txtpurpose"></td>
						</tr>
						<tr>
							<td><label>组呼号:</label></td>
							<td><input id="txtgroupno" class="easyui-combobox" > </td>
						</tr>
						<tr>
							<td><label>个呼号:</label></td>
							<td><input type="text" class="easyui-validatebox" id="txtpersonalno"></td>
						</tr>
						<tr>
							<td><label>GPS_ID:</label></td>
							<td><input id="txtgpsid" class="easyui-combobox" ></td>
						</tr>
						<tr>
							<td><label>GPS名称:</label></td>
							<td><input type="text" class="easyui-validatebox" id="txtgpsname"></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
   
</body>
</html>
