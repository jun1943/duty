<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<!--  
<link href='//cdn.datatables.net/plug-ins/a5734b29083/integration/jqueryui/dataTables.jqueryui.css' type='text/css' />
-->
<script src='<%=basePath%>script/duty/vehiclegroup.js'
	type='text/javascript'></script>
<title>车辆分组</title>

</head>
  
<body class="easyui-layout"  style ="width:100%; height:100%">
	<div  class="easyui-layout"   style ="width:100%; height:100%">
		 <div data-options="region:'north'" style ="width:100%; height:40px">
		 	<div class="btn-toolbar">
					<div class="btn-group">
						<a 	href="javascript:void(0);" class="easyui-linkbutton"	iconcls="icon-edit" 
						plain="true" onclick="onPoliceManGroup('policegroup')">警员分组</a> 
						<a  href="javascript:void(0);"	class="easyui-linkbutton" iconcls="icon-cancel" 
						plain="true"	onclick="onVehicleGroup('vehiclegroup')">车辆分组</a>
						<a  href="javascript:void(0);"	class="easyui-linkbutton" iconcls="icon-cancel" 
						plain="true"	onclick="onWeaponGroup('weapongroup')">武器分组</a>
						<a  href="javascript:void(0);"	class="easyui-linkbutton" iconcls="icon-cancel" 
						plain="true"	onclick="onGpsDeviceGroup('gpsgroup')">定位设备分组</a> 
					</div>
			</div>
		 </div>
		 <div data-options="region:'center'" style="width:100%; height:80%">
		 	<div  class="easyui-layout" style="width:100%; height:100%">
		 		
		<div id="divPG" data-options="region:'west'" title="车辆分组"
			style="width:40%">
			<div id="tbGroup" class="btn-toolbar">

				<div class="btn-group">
					<a id="btnAddVehicleGroup" href="javascript:void(0);"
						class="easyui-linkbutton icon-camera-retro" plain="true"
						onclick="addVehicleGroup()">创建</a> <a id="btnEditVehicleGroup"
						href="javascript:void(0);" class="easyui-linkbutton"
						iconcls="icon-edit" plain="true" onclick="editVehicleGroup()">修改</a>
					<a id="btnDelVehicleGroup" href="javascript:void(0);"
						class="easyui-linkbutton" iconcls="icon-cancel" plain="true"
						onclick="delVehicleGroup()">删除</a>
				</div>
			</div>
			<div id="dtVehicleGroup"></div>
		</div>
		<div id="dtGroup"></div>
		<div data-options="region:'center',title:'车辆'">
			<div id="tbGroupMember" class="btn-toolbar">
				<div class="btn-group">
					<a id="btnAddVehicleGroupMember" href="javascript:void(0);"
						class="easyui-linkbutton" iconcls="icon-add" plain="true"
						onclick="addVehicleGroupMember()">添加</a> <a
						id="btnEditVehicleGroupMember" href="javascript:void(0);"
						class="easyui-linkbutton" iconcls="icon-edit" plain="true"
						onclick="delVehicleGroupMemeber()">删除</a> <a
						id="btnCleanVehicleGroupMember" href="javascript:void(0);"
						class="easyui-linkbutton" iconcls="icon-cancel" plain="true"
						onclick="cleanPGMember()">清空</a>
				</div>
			</div>
			<div id="dtGroupMember"></div>
		</div>
	</div>
			 </div>
	</div>
	<div id="winPG" class="easyui-window" title="My Window" style="height:330px;width:320px"    
        data-options="iconCls:'icon-save',modal:true" closed="true" 
        collapsible="false" minimizable="false" maximizable="false" resizable="false" shadow="false">
			<div id="tbGroup" class="btn-toolbar">
				<div class="btn-group">
					<a id="btnSaveVehicleGroup" href="javascript:void(0);"
						class="easyui-linkbutton " plain="true"
						onclick="saveVehicleGroup()">保存</a> <a id="btnCloseVehicleGroupDlg"
						href="javascript:void(0);" class="easyui-linkbutton " plain="true"
						onclick="closeWinPG()">退出</a>
				</div>
			</div>
			<input type="hidden" id="txtVehicleGroupId"></input>
			<table style="width:100%">
				<tr>
					<td style="text-align:right"><lable>组名称:</lable></td>
					<td><input id="txtVehicleGroupName" type="text"
						class="easyui-validatebox"></input></td>
				</tr>
				<tr>
					<td style="text-align:right"><lable>共享类型:</lable></td>
					<td><label><input id="radioShare1" name="shareType"
							type="radio" value="0" onclick="changeShareType()"></input>不共享</label> 
							<label><input
							id="radioShare2" name="shareType" type="radio" value="1"
							onclick="changeShareType()"></input>共享到下级</label></td>
				</tr>

				<tr>
					<td colspan="2">
						<div style="border:1px solid #000;overflow:auto;height:200px">
							<div id="divOrg">
								<ul id="treeOrg" class="easyui-tree" style="overflow:auto"></ul>
							</div>

						</div>
					</td>
				</tr>
			</table>
		</div>

	<div id="winPGMember" class="easyui-window" title="组成员选择" style="width:450px;height:400px"    
        data-options="iconCls:'icon-save',modal:true" closed="true" 
        collapsible="false" minimizable="false" maximizable="false" resizable="false" shadow="false">    
   	 		<div id="tbGroup" class="btn-toolbar">
				<div class="btn-group">
					<a id="btnSaveVehicleGroup" href="javascript:void(0);"
						class="easyui-linkbutton " plain="true"
						onclick="appendMember()">保存</a> <a id="btnCloseVehicleGroupDlg"
						href="javascript:void(0);" class="easyui-linkbutton " plain="true"
						onclick="closeWinPGMember()">退出</a>
				</div>
			</div>
			<input id="txtVehicleGroupId"  type="hidden"></input>
			<table>
				<tr>
					<td style="width:40%">
						
						<div style="border:1px solid #000;overflow:auto;height:300px">
							<ul id="treeOrgWithVehicle" class="easyui-tree"
								style="overflow:auto"></ul>
						</div>
					</td>
					<td style="width:10%">
						<button onclick="selectMember()">&gt&gt</button>
						<button onclick="unselectMember()">&lt&lt</button>
					</td>

					<td style="width:50%">
						<div id="dtSelGroupMember" fit="true"></div>
					</td>
				</tr>
			</table>   
	</div> 


</body>
</html>