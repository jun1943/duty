<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<!--  
<link href='//cdn.datatables.net/plug-ins/a5734b29083/integration/jqueryui/dataTables.jqueryui.css' type='text/css' />
-->	<link rel="stylesheet" type="text/css" href="asset/css/images/dateStyle.css"/>
<script src='<%=basePath%>script/duty/dutyprepare.js'
	type='text/javascript'></script>
 
<title>勤务管理——报备</title>

</head>

<body class="easyui-layout" oncontextmenu=self.event.returnValue=false>
	<div data-options="region:'north'" class="dtypretoptoolbar">
	<!-- 导航工具条 -->
		<div id="tbGroup" class="btn-toolbar">
			<div class="dtyprebtngroup" >
				<a href="javascript:void(0);" class="easyui-linkbutton"
					iconcls="icon-tianyi-add" onclick="selectDutyType()">报备类型选择</a> <a
					href="javascript:void(0);" class="easyui-linkbutton"
					iconcls="icon-tianyi-add" onclick="showDutyTemplate()">选择模板</a> <a
					href="javascript:void(0);" class="easyui-linkbutton"
					iconcls="icon-tianyi-copy" onclick="showCalendar()">报备复制</a> <a
					href="javascript:void(0);" class="easyui-linkbutton"
					iconcls="icon-tianyi-cancel" onclick="clearDuty()">清空报备</a> <a
					href="javascript:void(0);"
					class="easyui-linkbutton icon-camera-retro"
					iconcls="icon-tianyi-save" onclick="saveDuty()">保存</a> <a
					href="javascript:void(0);" class="easyui-linkbutton"
					iconcls="icon-tianyi-save" onclick="saveTemplate()">另存为模板</a> <a
					href="javascript:void(0);" class="easyui-linkbutton"
					iconcls="icon-tianyi-export" onclick="btnExportToExcelAction()">导出</a> <a
					href="javascript:void(0);" class="easyui-linkbutton" style="margin-left:200px"
					iconcls="icon-tianyi-back" onclick="btnBackToCalendarAction()" >返回</a>
			</div>
		</div>
	</div>
	<!-- 内容区域左侧人员、车辆、武器定位设备导航栏 -->
	<div id="divResource" data-options="region:'west',split:false"
		title="资源" class="dtypreresource">
		<div id="tt" class="easyui-tabs" class="dtypreresourcetab" >
			<div title="人员" class="dtypreresourceitem">
				<!-- 警员列表 -->
				<div id="source_police"></div>
				<div class="dtypreresourcediv">
					<a id="btnAddAllPolice" onclick="addSelPolices()" name="btnAddAllPolice"
						href="javascript:void(0);" class="easyui-linkbutton dtypreresourcetb">一键添加</a>
					<!-- <a id="checkAllPolice" style="float:right; margin-right:5px" onclick="uncheckAllResources('source_police')"  name="checkAllPolice"  href="javascript:void(0);" class="easyui-linkbutton"  >取消选择</a>
     					  		<a id="uncheckAllPolice" style="float:right; margin-right:5px" onclick="checkAllResources('source_police')"  name="uncheckAllPolice"  href="javascript:void(0);" class="easyui-linkbutton"  >全选</a> -->
				</div>
				<div id="tb_source_police">
					<a id="showpoliceCondition" name="showpoliceCondition"
						onclick="$('#policeConditionwindow').window('open');"
						href="javascript:void(0);" class="easyui-linkbutton" plain="true">过滤条件</a>

					<input id="txtpname" class="dtypreresourceinput" type="text" /> <a
						id="searchpoliceAction" name="searchpoliceAction"
						href="javascript:void(0);" class="easyui-linkbutton"
						iconcls="icon-search" onclick="searchPoliceAction()" plain="true"></a>
				</div>
			</div>
			<div title="车辆" class="dtypreresourceitem">
				<!-- 车辆列表 -->
				<div id="source_vehicle"></div>
				<div class="dtypreresourcediv">
					<a id="btnCheckAllVehicle" 
						name="btnCheckAllVehicle" href="javascript:void(0);"
						class="easyui-linkbutton dtypreresourcetb" onclick="addSelVehicles()">一键添加</a>
					<!-- <a id="checkAllVehicle" style="float:right; margin-right:5px" onclick="uncheckAllResources('source_vehicle')"  name="checkAllVehicle"  href="javascript:void(0);" class="easyui-linkbutton"  >取消选择</a>
     					  		<a id="uncheckAllVehicle" style="float:right; margin-right:5px" onclick="checkAllResources('source_vehicle')"  name="uncheckAllVehicle"  href="javascript:void(0);" class="easyui-linkbutton"  >全选</a> -->
				</div>
				<div id="tb_source_vehicle">
					<a id="showvehicleCondition" name="showvehicleCondition"
						onclick="$('#vehicleConditionwindow').window('open');"
						href="javascript:void(0);" class="easyui-linkbutton" plain="true">过滤条件</a>
					<input id="txtvnumber" class="dtypreresourceinput" type="text" /> <a
						id="searchvehicleAction" name="searchvehicleAction"
						href="javascript:void(0);" class="easyui-linkbutton"
						iconcls="icon-tianyi-search" onclick="searchVehicleAction()"></a>
				</div>
			</div>
			<div title="定位设备" class="dtypreresourceitem">
				<!-- 定位设备列表 -->
				<div id="source_gpsdevice"></div>
				<div class="dtypreresourcediv">
					<a id="btnCheckAllGps" onclick="addSelgps()" name="btnCheckAllGps"
						href="javascript:void(0);" class="easyui-linkbutton dtypreresourcetb">一键添加</a>
					<!-- <a id="checkAllGps" style="float:right; margin-right:5px" onclick="uncheckAllResources('source_gpsdevice')"  name="checkAllGps"  href="javascript:void(0);" class="easyui-linkbutton"  >取消选择</a>
     					  		<a id="uncheckAllGps" style="float:right; margin-right:5px" onclick="checkAllResources('source_gpsdevice')"  name="uncheckAllGps"  href="javascript:void(0);" class="easyui-linkbutton"  >全选</a>-->
				</div>
				<div id="tb_source_gpsdevice">
					<a id="showgpsCondition" name="showgpsCondition"
						onclick="$('#gpsConditionwindow').window('open');"
						href="javascript:void(0);" class="easyui-linkbutton" plain="true">过滤条件</a>
					<input id="txtgname" class="dtypreresourceinput" type="text" /> <a
						id="searchgpsdeviceAction" name="searchgpsdeviceAction"
						href="javascript:void(0);" class="easyui-linkbutton"
						iconcls="icon-tianyi-search" onclick="searchGpsAction()"></a>
				</div>
			</div>
			<div title="武器" class="dtypreresourceitem" >
				<!-- 武器列表 -->
				<div id="source_weapon"></div>
				<div class="dtypreresourcediv">
					<a id="btnCheckAllWeapon" onclick="addSelWeapons()" name="btnCheckAllWeapon"
						href="javascript:void(0);" class="easyui-linkbutton dtypreresourcetb">一键添加</a>
					<!-- <a id="checkAllWeapon" style="float:right; margin-right:5px" onclick="uncheckAllResources('source_weapon')"  name="checkAllWeapon"  href="javascript:void(0);" class="easyui-linkbutton"  >取消选择</a>
     					  		<a id="uncheckAllWeapon" style="float:right; margin-right:5px" onclick="checkAllResources('source_weapon')"  name="uncheckAllWeapon"  href="javascript:void(0);" class="easyui-linkbutton"  >全选</a>-->
				</div>
				<div id="tb_source_weapon">
					<a id="showweaponCondition" name="showweaponCondition"
						onclick="$('#weaponConditionwindow').window('open');"
						href="javascript:void(0);" class="easyui-linkbutton" plain="true">过滤条件</a>
					<input id="txtwnumber" class="dtypreresourceinput" type="text" /> <a
						id="searchweaponAction" name="searchweaponAction"
						href="javascript:void(0);" class="easyui-linkbutton"
						iconcls="icon-tianyi-search" onclick="searchWeaponAction()"></a>
				</div>
			</div>
		</div>
	</div>
	<!-- 报备区域导航 -->
	<div id="divMember" data-options="region:'center'" title="备勤">
		<div class="easyui-layout" style="height:100%;width :100%">
			<div data-options="region:'north'" class="dtypredutytoolbar">
				<div class="btn-toolbar">
					<div class="dtypredutybtngroup">
						<a href="javascript:void(0);" class="easyui-linkbutton"
							iconcls="icon-tianyi-add" onclick="addShift()">添加班次</a> <a
							href="javascript:void(0);" class="easyui-linkbutton"
							iconcls="icon-tianyi-edit" onclick="setShift()">设置班次</a> <a
							href="javascript:void(0);" class="easyui-linkbutton"
							iconcls="icon-tianyi-add" onclick="addUserNode()">添加编组</a> <a
							href="javascript:void(0);" class="easyui-linkbutton"
							iconcls="icon-tianyi-edit" onclick="setUserNode()">设置编组</a> <a
							href="javascript:void(0);" class="easyui-linkbutton"
							iconcls="icon-tianyi-add" onclick="showTaskWindow()">关联任务</a> <a
							href="javascript:void(0);" class="easyui-linkbutton"
							iconcls="icon-tianyi-delete" onclick="deleteNode()">删除节点</a> 
							<a id="btnSearchExpendbody"
							href="javascript:void(0);" class="easyui-linkbutton" style="margin-left:100px"
							>展开查询</a>
							 
					</div>
				</div>
			</div>
			<div data-options="region:'center'" id="sss">
			
				<div class="MySearch" id="my-search-box">
					<div class="MySearchMain">
						<div>
							<form>
								<table>
									<tr> 
										<td class="MySearchTDTitle">名称:</td>
										<td><input id="txtsearchname" class="easyui-validatebox"
											type="text"></td>
										<td><a id="btnSearchDutyByName" href="javascript:void(0);"
											class="easyui-linkbutton" iconcls="icon-tianyi-search"
											onclick="btnSearchAction()">查询</a></td>

									</tr>
								</table>
							</form>
						</div>
					</div>

				</div>
				<!-- 报备详细列表树 -->
				<div id="tdDuty"></div>
			</div>
		</div>
	</div>
	<!-- 警员过滤筛选条件 -->
	<div id="policeConditionwindow" class="easyui-window dtypreresourceQywindow" title="人员选择过滤条件"
		data-options="iconCls:'icon-tianyi-edit',modal:true" closed="true"
		collapsible="false" minimizable="false" maximizable="false"
		resizable="false" shadow="false" >
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'south',border:false" class="dtypreresourceQywindowbtn">
				<a href="javascript:void(0);" class="easyui-linkbutton"
					style="float:right" onclick="searchPoliceAction()"> 确 定 </a>
			</div>
			<div data-options="region:'west',border:false" class="dtypreresourceQywindowArea">
				<div id="dt_policeType" style="width:90%; height:250px"></div>
			</div>
			<div data-options="region:'center',border:false" class="dtypreresourceQywindowArea">
				<div id="dt_groupType" style="width:90%; height:250px"></div>
			</div>
		</div>
	</div>
		<!-- 定位设备筛选过滤条件 -->
	<div id="gpsConditionwindow" class="easyui-window dtypreresourceQywindow" title="定位设备选择过滤条件"
		data-options="iconCls:'icon-tianyi-edit',modal:true" closed="true"
		collapsible="false" minimizable="false" maximizable="false"
		resizable="false" shadow="false" >
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'south',border:false" class="dtypreresourceQywindowbtn">
				<a href="javascript:void(0);" class="easyui-linkbutton"
					style="float:right" onclick="searchGpsAction()"> 确 定 </a>
			</div>
			<div data-options="region:'west',border:false" class="dtypreresourceQywindowArea">
				<div id="dt_gpsType" style="width:90%; height:250px"></div>
			</div>
			<div data-options="region:'center',border:false" class="dtypreresourceQywindowArea">
				<div id="dt_gpsgroupType" style="width:90%; height:250px"></div>
			</div>
		</div>
	</div>
	<!-- 武器过滤筛选条件 -->
	<div id="weaponConditionwindow" class="easyui-window dtypreresourceQywindow" title="武器选择过滤条件"
		data-options="iconCls:'icon-tianyi-edit',modal:true" closed="true"
		collapsible="false" minimizable="false" maximizable="false"
		resizable="false" shadow="false" >
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'south',border:false" class="dtypreresourceQywindowbtn">
				<a href="javascript:void(0);" class="easyui-linkbutton"
					style="float:right" onclick="searchWeaponAction()"> 确 定 </a>
			</div>
			<div data-options="region:'west',border:false" class="dtypreresourceQywindowArea">
				<div id="dt_weaponType" style="width:90%; height:250px"></div>
			</div>
			<div data-options="region:'center',border:false" class="dtypreresourceQywindowArea">
				<div id="dt_weapongroupType" style="width:90%; height:250px"></div>
			</div>
		</div>
	</div>
	<!-- 车辆过滤筛选条件 -->
	<div id="vehicleConditionwindow" class="easyui-window dtypreresourceQywindow" title="车辆选择过滤条件"
		data-options="iconCls:'icon-tianyi-edit',modal:true" closed="true"
		collapsible="false" minimizable="false" maximizable="false"
		resizable="false" shadow="false" >
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'south',border:false" class="dtypreresourceQywindowbtn">
				<a href="javascript:void(0);" class="easyui-linkbutton"
					style="float:right" onclick="searchVehicleAction()"> 确 定 </a>
			</div>
			<div data-options="region:'west',border:false" class="dtypreresourceQywindowArea">
				<div id="dt_vehicleType"  style="width:90%; height:250px"></div>
			</div>
			<div data-options="region:'center',border:false" class="dtypreresourceQywindowArea">
				<div id="dt_vehiclegroupType"  style="width:90%; height:250px"></div>
			</div>
		</div>
	</div>
	
	<!-- 勤务类型选择框 -->
	<div id="dutyTypeSelectwindow" class="easyui-window" title="报备类型-选择"
	style="width: 354px; height:500px; padding: 10px;"
		data-options="iconCls:'icon-tianyi-edit'" closed="true" collapsible="false"
		minimizable="false" maximizable="false" resizable="false"
		shadow="false" >
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'south',border:false" class="dtypreresourceQywindowbtn">
				<a href="javascript:void(0);" class="easyui-linkbutton"
					style="float:right" onclick="selectDutyTypeAction()"> 确 定 </a>
			</div>
			<div data-options="region:'center',border:false" class="dtypredtypeQywindowcontent" >
				<div id="dtDutyType"></div>
			</div>
		</div>
	</div>
	<!-- 模板选择框 -->
	<div id="dutyTemplateSelectwindow" class="easyui-window"
	style="width: 354px; height:400px; padding: 10px;"
		title="备勤模板-选择" data-options="iconCls:'icon-tianyi-edit'" closed="true"
		collapsible="false" minimizable="false" maximizable="false"
		resizable="false" shadow="false" >
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'south',border:false" class="dtypreresourceQywindowbtn">
				<a href="javascript:void(0);" class="easyui-linkbutton"
					style="float:right" onclick="selectDutyTemplateAction()"> 确 定 </a>
			</div>
			<div data-options="region:'center',border:false" class="dtypredtempQywindowcontent">
				<div id="dtDutyTemplate"></div>
			</div>
		</div>
	</div>
	<!-- 班次设置对话框 -->
	<div style="display:none">
		<div id="div_worksheet">
			<div id="tb_worksheet" class="btn-toolbar">
				<div class="btn-group">
					<input id="starttime" class="easyui-timespinner dtypredutysheetinput" required="required"
						data-options="min:'00:00',showSeconds:false" /> <input
						id="endtime" class="easyui-timespinner" style="width:80px;"
						required="required" data-options="min:'00:01',showSeconds:false" />
					<label>是否第二天</label><input type="checkbox" id="ck_isTomorrow_">
				</div>
				<div>
					<label>数据汇总：</label><label></label>
				</div>
			</div>
			<div id="contentTab" class="easyui-tabs"
				data-options="tools:'#contentTab-tools'" style="width:500px;height:300px;"></div>
			<div id="contentTab-tools">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-add'" onclick="addPanel()"></a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-remove'"
					onclick="removePanel()"></a>
			</div>
		</div>
	</div>
	<!-- 自定义编组对话框 -->
	<div id="userNodeWindows" class="easyui-window"  title="自定义编组"
	style="width: 300px; height:100px; padding: 2px;"
		data-options="iconCls:'icon-tianyi-edit',modal:true" closed="true"
		collapsible="false" minimizable="false" maximizable="false"
		resizable="false" shadow="false" >
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'south',border:false" class="dtypreresourceQywindowbtn">
				<a href="javascript:void(0);" class="easyui-linkbutton"
					style="float:right" onclick="userNodeConfirm()"> 确 定 </a>
				<!-- a href="javascript:void(0);" 
						class="easyui-linkbutton"
						onclick="$('#userNodeWindows').window('close');"
						data-options="iconCls:'icon-remove',plain:true">取消</a> -->
			</div>
			<div data-options="region:'center',border:false" class="dtypredutyusernodeArea">
				<table class="dtypredutyusernodeAreatable">
					<tr>
						<td style="text-align:right"><lable>编组名称:</lable></td>
						<td><input id="txtUserNodeName" type="text"
							class="easyui-validatebox"></input></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<!-- 班次添加对话框 -->
	<div id="shiftWindows" class="easyui-window" title="班次"
	style="width: 380px; height:200px; padding: 2px;"
		data-options="iconCls:'icon-tianyi-edit',modal:true" closed="true"
		collapsible="false" minimizable="false" maximizable="false"
		resizable="false" shadow="false" >
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'south',border:false" class="dtypreresourceQywindowbtn">
				<a href="javascript:void(0);" class="easyui-linkbutton"
					style="float:right" onclick="shiftConfirm()"> 确 定 </a>
				<!-- a href="javascript:void(0);" 
						class="easyui-linkbutton"
						onclick="$('#shiftWindows').window('close');"
						data-options="iconCls:'icon-remove',plain:true">取消</a> -->
			</div>
			<div data-options="region:'center',border:false" class="dtypredutyusernodeArea">
				<table class="dtypredutyusernodeAreatable">
					<tr>
						<td class="dtypredutyshiftWindowstdf"><label>班次名称:</label></td>
						<td class="dtypredutyshiftWindowstds"><input id="txtShiftName" type="text"
							class="easyui-validatebox"></input></td>
						<td class="dtypredutyshiftWindowstdt"></td>
					</tr>
					<tr>
						<td style="text-align:right"><lable>开始时间:</lable></td>
						<td><input id="txtBeginTime" type="text"
							class="easyui-timespinner"></input></td>
						<td></td>
					</tr>
					<tr>
						<td style="text-align:right"><lable>结束时间:</lable></td>
						<td><input id="txtEndTime" type="text"
							class="easyui-timespinner"></input></td>
						<td><lable>
							<input type="checkbox" id="chkDayType"></input>第二天</lable></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<!-- 保存模板对话框 -->
	<div id="templateWindows" class="easyui-window" title="备勤模板-保存"
	style="width: 300px; height:100px; padding: 2px;"
		data-options="iconCls:'icon-tianyi-save',modal:true" closed="true"
		collapsible="false" minimizable="false" maximizable="false"
		resizable="false" shadow="false" >
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'south',border:false" class="dtypreresourceQywindowbtn">
				<a href="javascript:void(0);" class="easyui-linkbutton"
					style="float:right" onclick="templateNameConfirm()"> 确 定 </a>
				<!--  <a href="javascript:void(0);" 
						class="easyui-linkbutton"
						onclick="$('#templateWindows').window('close');"
						data-options="iconCls:'icon-remove',plain:true">取消</a>-->
			</div>
			<div data-options="region:'center',border:false" class="dtypredutyusernodeArea">
				<table class="dtypredutyusernodeAreatable">
					<tr>
						<td style="text-align:right"><lable>模板名称:</lable></td>
						<td><input id="txtTemplateName" type="text"
							class="easyui-validatebox"></input></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<!-- 报备复制日历显示框 -->
	<div id="calendarWindow" class="easyui-window" title="备勤复制"
	style="width: 300px; height:300px; padding: 2px;"
		data-options="iconCls:'icon-tianyi-save',modal:true" closed="true"
		collapsible="false" minimizable="false" maximizable="false"
		resizable="false" shadow="false" >
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'south',border:false" class="dtypreresourceQywindowbtn">
				<!--   <a href="javascript:void(0);" 
						class="easyui-linkbutton"
					  style="float:right"
						onclick="$('#calendarWindow').window('close');"
						data-options="iconCls:'icon-remove'"> 取　消 </a>-->
			</div>
			<div data-options="region:'center',border:false" class="dtypredutyusernodeArea">
				<div id="cc"></div>
			</div>
		</div>
	</div>
	<!-- 关联任务对话框 -->
	 <div id="taskWindow"  class="easyui-window" title="任务关联"  data-options="iconCls:'icon-tianyi-edit',modal:true" closed="true"
	 style="width: 580px; height:350px; padding: 2px;" 
	 	collapsible="false" minimizable="false" maximizable="false" resizable="false" shadow="false" >
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'south',border:false" class="dtypreresourceQywindowbtn"> 
					<a href="javascript:void(0);" class="easyui-linkbutton" 
					  style="float:right" onclick="taskConfirm()"> 确　定 </a>  

			</div>
			<div data-options="region:'center',border:false" class="dtypredutyusernodeArea">
				<div id="dgtaskTarget" style="width:100%; height:100%"></div>
				<div id="tbTaskTarget" class="dtypreresourceQywindowbtn">
					<label id="lblPoliceInfo"></label>
				</div>
			</div>
		</div>
	</div>

</body>
