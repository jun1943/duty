<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<!--  
<link href='//cdn.datatables.net/plug-ins/a5734b29083/integration/jqueryui/dataTables.jqueryui.css' type='text/css' />
-->
	<link rel="stylesheet" type="text/css" href="asset/css/images/dateStyle.css"/>
<script src='<%=basePath%>script/basedata/vehicle.js'
	type='text/javascript'></script>
<title>基础数据——车辆管理</title>


</head>

<body class="easyui-layout" style="padding:2px" oncontextmenu=self.event.returnValue=false>
  
	
	<div data-options="region:'center'"style="padding:2px;"> 
		 
		 
				 
			 <!-- 工具条 -->
				<div id="tbVehicle" class="btn-toolbar" style="height:30px; margin-top:3px">
	  				<div class="btn-group">
	  					<a id="btnAddVehicle" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-tianyi-add"   onclick="btnAddVehicle('add')">创建</a>
	                	<a id="btnEditVehicle" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-tianyi-edit"  onclick="btnEditVehicle('edit')">修改</a>
	                	<a id="btnDelVehicle" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-tianyi-delete"  onclick="btnDelVehicle()">删除</a>
	                	 
	  					<a id="btnSearchVehicle" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-tianyi-search" style="float:right;margin-right:20px"  onclick="btnSearchVehicle()">展开查询</a>
	                	<!-- a id="btnPrintList" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-print" plain="true" onclick="btnPrintList()">打印</a>
	                	<a id="btnExportList" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-export" plain="true" onclick="btnExportList()">导出</a> -->
	               	</div>
	  			</div>
	  			<!-- 查询框 -->
	  			<div class="MySearch" id="my-search-box" style=" padding:3px;display:none; ">
		 			<div class="MySearchMain">
				 		<div>
							<form>
								<table>
									<tr>
										<td class="MySearchTDTitle">查询范围选择:</td>
										<td><input id="isSubOrg" class="easyui-combobox" name="dept" style="width:104px"
										data-options="editable:false,valueField:'id',textField:'name',data:[{id: 0,name: '本級机构'},{id: 1,name: '本级及下级机构'}]" /></td>
										<td class="MySearchTDTitle">车牌号码:</td>
										<td><input id="txtsearchnumber" class="easyui-validatebox" type="text" ></td>
										<td><a id="btnSearchAction" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-tianyi-search"   onclick="btnSearchAction()">查询</a></td>
									 
									</tr> 
								</table>
							</form>
						</div>	
					</div>		
				
				</div>
	  			 <!-- 车辆列表 -->
	  			<div id="dtVehicle" >
  				</div> 
  			</div>  
  			<!-- 编辑窗体内容 -->
  <div id="vehicleinfowindow" class="easyui-window" title="新增/编辑车辆信息"
			data-options="iconCls:'icon-edit',modal:true" closed="true" 
	 	collapsible="false" minimizable="false" maximizable="false" resizable="false" shadow="false" 
			style="width: 400px; height: 500px; padding: 10px;">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'south',border:false"
					style="padding: 2px; height: 30px;">
					<a id="btnsaveVehicleCon" href="javascript:void(0);" class="easyui-linkbutton"
						 style="float:right"
						onclick="saveVehicleAction()">保存并继续</a> 
					<a href="javascript:void(0);" class="easyui-linkbutton"
						 style="float:right;margin-right:15px"
						onclick="saveVehicleActionExit()">　　保　存　　</a> 
				</div>
				<div data-options="region:'center',border:false"
					style="text-align: right; margin-left: 5px;
            padding: 5px 0 0;">
					<table style="width:100%;height:99%; font: 12px 微软雅黑;">
						<tr>
							<td style="text-align: right;"><label>车辆品牌:　</label></td>
							<td><input type="text" class="easyui-validatebox" id="txtbrand" ></td>
						</tr>
						<tr>
							<td style="text-align: right;"><input type="hidden" id="vehicleId"><label>车辆类型:　</label></td>
							<td><input id="txttype" class="easyui-combobox" data-options="required:true,editable:false"  /><span style="color:red">*</span></td>
						</tr>
						<tr>
							<td style="text-align: right;"><label>座位数:　</label></td>
							<td><input type="text" class="easyui-validatebox" id="txtsiteqty"></td>
						</tr>
						<tr>
							<td style="text-align: right;"><label>车牌号码:　</label></td>
							<td><input type="text" class="easyui-validatebox" data-options="required:true" id="txtnumber"><span style="color:red">*</span></td>
						</tr>
						<tr>
							<td style="text-align: right;"><label>车辆用途:　</label></td>
							<td><input type="text" class="easyui-validatebox" id="txtpurpose"></td>
						</tr>
						<tr>
							<td style="text-align: right;"><label>组呼号:　</label></td>
							<td><input id="txtgroupno" class="easyui-combobox" data-options="editable:false"> </td>
						</tr>
						<tr>
							<td style="text-align: right;"><label>个呼号:　</label></td>
							<td><input type="text" class="easyui-validatebox" id="txtpersonalno"></td>
						</tr>
						<tr>
							<td style="text-align: right;"><label>GPS设备:　</label></td>
							<td><input id="txtgpsid" class="easyui-combobox" ></td>
						</tr>
						<!-- <tr>
							<td style="text-align: right;"><label>GPS名称:　</label></td>
							<td><input type="text" class="easyui-validatebox" id="txtgpsname"></td>
						</tr> -->
					</table>
				</div>
			</div>
		</div>
   
</body>
</html>
