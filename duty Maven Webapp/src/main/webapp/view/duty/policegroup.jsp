<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<!--  
<link href='//cdn.datatables.net/plug-ins/a5734b29083/integration/jqueryui/dataTables.jqueryui.css' type='text/css' />
-->	<link rel="stylesheet" type="text/css" href="asset/css/images/dateStyle.css"/>
<script src='<%=basePath%>script/duty/policegroup.js'
	type='text/javascript'></script>
<title>人员分组</title>

</head>

<body class="easyui-layout grouplayout" oncontextmenu=self.event.returnValue=false>
	<div  class="easyui-layout grouplayout" >
		 <div data-options="region:'north'" class="grouplayoutnorth">
		 	<div class="dateBoxMenu">
					<ul>
						<li><div id="policemanage" doc="dateBoxMenu" class="dateBoxMenuOn">
								<a href="javascript:void(0)" onclick="onPoliceManGroup('policegroup')">警员分组
								</a>
							</div></li>
						<li><div id="vehiclemanage" doc="dateBoxMenu">
								<a href="javascript:void(0)" onclick="onVehicleGroup('vehiclegroup')">车辆分组</a>
							</div></li>
						<li><div id="weaponmanage"  doc="dateBoxMenu">
								<a href="javascript:void(0)" onclick="onWeaponGroup('weapongroup')">武器分组</a>
							</div></li>
						<li><div id="gpsdevicemanage" doc="dateBoxMenu">
								<a href="javascript:void(0)" onclick="onGpsDeviceGroup('gpsgroup')">定位设备分组</a>
							</div></li> 
					</ul>
				</div>
		  
		 </div>
		 <div data-options="region:'center'"  class="grouplayoutcenter">
		 	<div  class="easyui-layout grouplayout">
		 		
			<div id="divPG" data-options="region:'west'" title="警员组" class="groupwestArea">
				<div id="tbGroup" class="btn-toolbar grouptoolbar">
	
					<div class="btn-group" >
						<a id="btnAddPoliceGroup" href="javascript:void(0);" 
							class="easyui-linkbutton icon-camera-retro groupfstbtn"  iconcls="icon-tianyi-add" 
							onclick="addPoliceGroup()">创建</a> <a id="btnEditPoliceGroup"
							href="javascript:void(0);" class="easyui-linkbutton"
							iconcls="icon-tianyi-edit"  onclick="editPoliceGroup()">修改</a>
						<a id="btnDelPoliceGroup" href="javascript:void(0);"
							class="easyui-linkbutton" iconcls="icon-tianyi-delete"  
							onclick="delPoliceGroup()">删除</a>
					</div>
				</div>
				<div id="dtPoliceGroup"></div>
			</div>
			<div id="dtGroup"></div>
			<div data-options="region:'center',title:'警员'" class="groupgridcenter">
				<div id="tbGroupMember" class="btn-toolbar grouptoolbar" >
					<div class="btn-group"  >
						<a id="btnAddPoliceGroupMember" href="javascript:void(0);" 
							class="easyui-linkbutton groupfstbtn" iconcls="icon-tianyi-add"  
							onclick="addPoliceGroupMember()">添加</a> <a
							id="btnEditPoliceGroupMember" href="javascript:void(0);"
							class="easyui-linkbutton" iconcls="icon-tianyi-edit" 
							onclick="delPoliceGroupMemeber()">删除</a> <a
							id="btnCleanPoliceGroupMember" href="javascript:void(0);"
							class="easyui-linkbutton" iconcls="icon-tianyi-cancel"  
							onclick="cleanPGMember()">清空</a>
					</div>
				</div>
				<div id="dtGroupMember"></div>
			</div>
			 		
			 	</div>
			 </div>
	</div>

	<div id="winPG" class="easyui-window groupwindow" title="警员分组管理"  
        data-options="iconCls:'icon-tianyi-save',modal:true" closed="true" 
        collapsible="false" minimizable="false" maximizable="false" resizable="false" shadow="false">
		
			<input type="hidden" id="txtPoliceGroupId"></input>
			<table class="groupwindowtable">
				<tr>
					<td style="text-align:right"><lable>组名称:</lable></td>
					<td><input id="txtPoliceGroupName" type="text"
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
						<div class="groupwindowdiv">
							<div id="divOrg">
								<ul id="treeOrg" class="easyui-tree" style="overflow:auto"></ul>
							</div>

						</div>
					</td>
				</tr>
			</table>
				<div id="tbGroup" class="btn-toolbar groupwindowtoolbar" >
				<div class="btn-group groupwindowtoolbar">
					<a id="btnSavePoliceGroup " href="javascript:void(0);"
						class="easyui-linkbutton groupwindowbtn" 
						onclick="savePoliceGroup()"> 保　存 </a>  
				</div>
			</div>
		</div>

	<div id="winPGMember" class="easyui-window groupmemberwindow" title="组成员选择" 
        data-options="iconCls:'icon-save',modal:true" closed="true" 
        collapsible="false" minimizable="false" maximizable="false" resizable="false" shadow="false">    
   	 		 
			<input id="txtPoliceGroupId"  type="hidden"></input>
			<table>
				<tr>
					<td class="groupmemberwindowtdf">
						
						<div class="groupmemberwindowdiv" >
							<ul id="treeOrgWithPolice" class="easyui-tree"
								style="overflow:auto"></ul>
						</div>
					</td>
					<td class="groupmemberwindowtds">
						<button onclick="selectMember()">&gt&gt</button>
						<button onclick="unselectMember()">&lt&lt</button>
					</td>

					<td class="groupmemberwindowtdt">
						<div id="dtSelGroupMember" fit="true"></div>
					</td>
				</tr>
			</table>   
			   	 		<div id="tbGroup" class="btn-toolbar groupwindowtoolbar">
				<div class="btn-group groupwindowtoolbar" >
					<a id="btnSavePoliceGroup" href="javascript:void(0);"
						class="easyui-linkbutton groupwindowbtn" 
						onclick="appendMember()"> 保　存 </a> 
				</div>
			</div>
	</div> 


</body>
</html>
