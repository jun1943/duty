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
	<script src='<%=basePath%>script/ajaxupload.3.9.js'
	type='text/javascript'></script>
	<script src='<%=basePath%>script/basedata/uploadsource.js'
	type='text/javascript'></script>
<title>基础数据——车辆管理</title>


</head>

<body class="easyui-layout vehiclelayout"   oncontextmenu=self.event.returnValue=false>
  
	
	<div data-options="region:'center'" class="vehiclecenter"> 
		  
			 <!-- 工具条 -->
				<div id="tbVehicle" class="btn-toolbar vehicletoolbar" >
	  				<div class="btn-group">
	  					<a id="btnAddVehicle" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-tianyi-add"   onclick="btnAddVehicle('add')">新增</a>
	                	<!-- <a id="btnEditVehicle" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-tianyi-edit"  onclick="btnEditVehicle('edit')">修改</a> -->
	                	<a id="btnDelVehicle" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-tianyi-delete"  onclick="btnDelVehicle()">删除</a>
	                	 
	  					<a id="btnSearchVehicle" href="javascript:void(0);" class="easyui-linkbutton vehiclesearchbtn"  iconcls="icon-tianyi-search" >展开查询</a>
	                	<a href="javascript:void(0);" class="easyui-linkbutton policeexportorinport"  iconcls="icon-tianyi-export"  onclick="btnExportAction()">导出数据</a>
	                	
						<a href="javascript:void(0);" class="easyui-linkbutton policeexportorinport"  iconcls="icon-tianyi-export"  onclick="btnInportAction()">导入数据</a>
	                	<!-- a id="btnPrintList" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-print" plain="true" onclick="btnPrintList()">打印</a>
	                	<a id="btnExportList" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-export" plain="true" onclick="btnExportList()">导出</a> -->
	               	</div>
	  			</div>
	  			<!-- 查询框 -->
	  			<div class="MySearch" id="my-search-box" >
		 			<div class="MySearchMain">
				 		<div>
							<form>
								<table>
									<tr>
										<td class="MySearchTDTitle">查询范围选择:</td>
										<td><input id="isSubOrg" class="easyui-combobox vehiclesearchinput" name="dept" 
										data-options="editable:false,valueField:'id',textField:'name',data:[{id: 0,name: '本級机构'},{id: 1,name: '本级及下级机构'}]" /></td>
										<td class="MySearchTDTitle">车牌号码:</td>
										<td><input id="txtsearchnumber" class="easyui-validatebox vehiclesearchinput" type="text" ></td>
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
  			
  			<div id="vehicleInfoinportwindow" class="easyui-window" title="导入数据选项" data-options="modal:true,headerCls:'bbbb'" closed="true" 
			style="width: 450px; height: 200px; padding: 10px;color:white;"
	 	collapsible="false" minimizable="false" maximizable="false" resizable="false" shadow="false" >
	 		<div class="windowdiv">  
	 			<input type="text" class="easyui-validatebox" readonly="readonly" id="txtentityfilename">
	 			<a id="btnfindfile"  href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-tianyi-add"   >选择文件</a> <a href="javascript:void(0);" class="hiperlinka"  onclick="btnDownLoadModel()">[点击下载excel模板]</a>  
	 			<br /><br />
				<label id="staticMessageo"  class="hiperlinka" >上传文件时，请先下载模板文件填写 ；</label><br />
				<label id="staticMessaget"  class="hiperlinka" >文件对象为：97—03版本的excel文件，后缀格式为xls；</label>  
				<br /><br /> 
						&npbs;
				 <a  href="javascript:void(0);" class="easyui-linkbutton"   iconcls="icon-tianyi-save"  
						onclick="btnsaveVehicleData()"> 确定  </a>  
						&npbs; 
					<a  href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-tianyi-delete"  
						onclick="btnCancelVehicleDataAction()"> 取消  </a> 
	 		 </div> 
	 	</div>	
  			
	 	<div id="maskwindow" class="easyui-window" title="导入数据选项" data-options="modal:true,headerCls:'bbbb'" closed="true" 
			style="width: 500px; height: 80px; padding: 10px;color:white;"
	 	collapsible="false" minimizable="false" maximizable="false" resizable="false" shadow="false" >
	 	<h2  class="hiperlinkb" >正在导入数据，请稍后……</h2>
	 	</div>
  <div id="vehicleinfowindow" class="easyui-window" title="新增/编辑车辆信息"
			data-options="iconCls:'icon-tianyi-edit',modal:true,headerCls:'bbbb'" closed="true" 
			style="width: 400px; height: 500px; padding: 10px;color:white;"
	 	collapsible="false" minimizable="false" maximizable="false" resizable="false" shadow="false" 
			 >
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'south',border:false"
					class="vehiclewindowsouth">
					<a id="btnsaveVehicleCon" href="javascript:void(0);" class="easyui-linkbutton vehiclewindowbtnExit"
						 
						onclick="saveVehicleAction()">保存并继续</a> 
					<a href="javascript:void(0);" class="easyui-linkbutton vehiclewindowbtn"
						 
						onclick="saveVehicleActionExit()">　　保　存　　</a> 
				</div>
				<div data-options="region:'center',border:false"
					class="vehiclewindowcenter">
					<table class="vehiclewindowtable">
						<tr>
							<td style="text-align: right;"><label>车辆品牌:　</label></td>
							<td><input type="text" class="easyui-validatebox" id="txtbrand" data-options="validType:['length[0,30]']" ></td>
						</tr>
						<tr>
							<td style="text-align: right;"><input type="hidden" id="vehicleId"><label>车辆类型:　</label></td>
							<td><input id="txttype" class="easyui-combobox" data-options="required:true,editable:false"  /><span style="color:red;font-size:16px;width:15px">*</span></td>
						</tr>
						<tr>
							<td style="text-align: right;"><label>座位数:　</label></td>
							<td><input type="text" class="easyui-validatebox" id="txtsiteqty"  data-options="validType:['length[0,20]']" ></td>
						</tr>
						<tr>
							<td style="text-align: right;"><label>车牌号码:　</label></td>
							<td><input type="text" class="easyui-validatebox" data-options="required:true,validType:['length[0,20]']" id="txtnumber"><span style="color:red;font-size:16px;width:15px">*</span></td>
						</tr>
						<tr>
							<td style="text-align: right;"><label>车辆用途:　</label></td>
							<td><input type="text" class="easyui-validatebox" id="txtpurpose" data-options="validType:['length[0,50]']" ></td>
						</tr>
						<tr>
							<td style="text-align: right;"><label>组呼号:　</label></td>
							<td><input id="txtgroupno" class="easyui-combobox" data-options="editable:false"> </td>
						</tr>
						<tr>
							<td style="text-align: right;"><label>个呼号:　</label></td>
							<td><input type="text" class="easyui-validatebox" id="txtpersonalno" data-options="validType:['length[0,20]']" ></td>
						</tr>
						<tr>
							<td style="text-align: right;"><label>GPS显示名称:　</label></td>
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
