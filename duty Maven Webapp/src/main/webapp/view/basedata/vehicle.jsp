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
 
	<div data-options="region:'west',split:true,title:'组织机构导航树'" style="width:13%;padding:10px;">
		<div>
			<input type="text">
		</div>
		
		<ul id="orgtree" class="easyui-tree"> 
		</ul>
	</div>  
	
	<div data-options="region:'center'"style="padding:10px;"> 
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'north',border:false"
				style="padding: 2px; height: 55px;overflow:hidden">
				<div class="container-fluid my-nav-bg">
					<ul class="nav navbar-nav">
						<li style="width:1px;"><a href="#">&nbsp;</a></li>
						<li><a href="/duty/view/basedata/police.jsp"
							class="my-nav-btn">人员管理 <img src="asset/css/images/nav-btn-icon.png"
								width="22" height="22"></a></li>
						<li class="active"><a href="/duty/view/basedata/vehicle.jsp" class="my-nav-btn">车辆管理
								<img src="asset/css/images/nav-btn-icon.png" width="22" height="22">
						</a></li>
						<li><a href="/duty/view/basedata/weapon.jsp" class="my-nav-btn">武器管理
								<img src="asset/css/images/nav-btn-icon.png" width="22" height="22">
						</a></li>
						<li><a href="/duty/view/basedata/gpsdevice.jsp" class="my-nav-btn">定位设备
								<img src="asset/css/images/nav-btn-icon.png" width="22" height="22">
						</a></li>
						<li><a href="/duty/view/basedata/icons.jsp" class="my-nav-btn">图标管理
								<img src="asset/css/images/nav-btn-icon.png" width="22" height="22">
						</a></li>
					</ul>
				</div>
			</div>
			<div data-options="region:'center',border:false">
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
  		</div>   
	</div>   
  
  <div id="vehicleinfowindow" class="easyui-window" title="新增/编辑车辆信息"
			data-options="iconCls:'icon-edit'"
			style="width: 400px; height: 500px; padding: 10px;">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'north',border:false"
					style="padding: 2px; height: 30px;">
					<a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'icon-save',plain:true"
						onclick="saveVehicleAction()">保存</a> <a href="javascript:void(0);"
						class="easyui-linkbutton"
						onclick="$('#vehicleinfowindow').window('close');"
						data-options="iconCls:'icon-remove',plain:true">关闭</a>
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
							<td><input type="text" class="easyui-validatebox" id="txtnumber"></td>
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
  
	<!--新增开始 
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">新增车辆信息</h4>
				</div>
				<div class="modal-body">

					<!--表单开始 
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label for="txtbrand" class="col-sm-2 control-label">车辆品牌</label>
							<div class="col-sm-4">
								<input type="hidden" id="vehicleId"> 
								<input type="text" class="form-control" id="txtbrand"
									placeholder="">
							</div>
						</div> 
						<div class="form-group">
							<label for="txttype" class="col-sm-2 control-label">车辆类型:</label>
							<div class="col-sm-4">
								<select id="txttype" class="form-control"> 
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="txtsiteqty" class="col-sm-2 control-label">座位数:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="txtsiteqty"
									placeholder="">
							</div>
						</div>

						<div class="form-group">
							<label for="txtnumber" class="col-sm-2 control-label">车牌号码:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="txtnumber"
									placeholder="">
							</div>
						</div>

						<div class="form-group">
							<label for="txtpurpose" class="col-sm-2 control-label">车辆用途:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="txtpurpose"
									placeholder="">
							</div>
						</div>
 
						<div class="form-group">
							<label for="txtgroupno" class="col-sm-2 control-label">组呼号:</label>
							<div class="col-sm-4">
								<select id="txtgroupno" class="form-control"> 
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="txtpersonalno" class="col-sm-2 control-label">个呼号:</label>
							<div class="col-sm-4">
							<input type="text" class="form-control" id="txtpersonalno"
									placeholder="">
								 
							</div>
						</div>
						<div class="form-group">
							<label for="txtgpsid" class="col-sm-2 control-label">GPS_ID:</label>
							<div class="col-sm-4">
								<select id="txtgpsid" class="form-control"> 
								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label for="txtgpsname" class="col-sm-2 control-label">GPS名称:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="txtgpsname"
									placeholder="">
							</div>
						</div>
					</form>
					<!--表单结束

				</div>
				<div class="modal-footer">
					<button id="btnsaveweapon" onclick="saveVehicleAction()"
						type="button" class="btn btn-primary">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>-->
</body>
</html>
