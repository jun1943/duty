<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<!--  
<link href='//cdn.datatables.net/plug-ins/a5734b29083/integration/jqueryui/dataTables.jqueryui.css' type='text/css' />
-->
<script src='<%=basePath%>script/duty/dutyprepare.js' 	type='text/javascript'></script>

<style type="text/css">
	.prop_val{
		.width:233px
	}
</style>

<title>勤务报备</title>

</head>
	
<body class="easyui-layout" >
	<div id="divResource" data-options="region:'west',split:true" title="资源"
			style="width:30%;height:100%">
			<div id="tt" class="easyui-tabs" style="width:100%;height:500px;">	
 				<div title="人员" style="padding:10px;height:100%">  
       					  <div id="source_police"></div> 
       					  <div id="tb_source_police">
       					  <a id="showpoliceCondition" name="showpoliceCondition" onclick="$('#policeConditionwindow').window('open');" href="javascript:void(0);" class="easyui-linkbutton"  plain="true">过滤条件</a>
       					   
                <input id="txtpname" style="width:100px" type="text"/>
                <a id="searchpoliceAction" name="searchpoliceAction" href="javascript:void(0);" class="easyui-linkbutton"
                    iconcls="icon-search" onclick="SearchPoliceAction()" plain="true"></a>
       					  </div>
    			</div>
				<div title="车辆" style="padding:10px;height:100%">  
       					 <div id="source_vehicle"></div> 
       					  <div id="tb_source_vehicle">
       					  <a id="showvehicleCondition" name="showvehicleCondition" onclick="$('#vehicleConditionwindow').window('open');" href="javascript:void(0);" class="easyui-linkbutton"  plain="true">过滤条件</a>
       					  	 <input id="txtvnumber" style="width:100px" type="text"/>
                <a id="searchvehicleAction" name="searchvehicleAction" href="javascript:void(0);" class="easyui-linkbutton"
                    iconcls="icon-search" onclick="SearchVehicleAction()" plain="true"></a>
       					  </div>
    			</div>		
 				<div title="定位设备" style="padding:10px;height:100%">  
       					  <div id="source_gpsdevice"></div> 
       					  <div id="tb_source_gpsdevice">
       					  	<a id="showgpsCondition" name="showgpsCondition" onclick="$('#gpsConditionwindow').window('open');" href="javascript:void(0);" class="easyui-linkbutton"  plain="true">过滤条件</a>
       					  	 <input id="txtgname" style="width:100px" type="text"/>
                <a id="searchgpsdeviceAction" name="searchgpsdeviceAction" href="javascript:void(0);" class="easyui-linkbutton"
                    iconcls="icon-search" onclick="SearchGpsAction()" plain="true"></a>
       					  </div>
    			</div>	
 				<div title="武器" style="padding:10px;height:100%">  
       					  <div id="source_weapon"></div> 
       					  <div id="tb_source_weapon">
       					  	<a id="showweaponCondition" name="showweaponCondition" onclick="$('#weaponConditionwindow').window('open');" href="javascript:void(0);" class="easyui-linkbutton"  plain="true">过滤条件</a>
       					  	 <input id="txtwnumber" style="width:100px" type="text"/>
                <a id="searchweaponAction" name="searchweaponAction" href="javascript:void(0);" class="easyui-linkbutton"
                    iconcls="icon-search" onclick="SearchWeaponAction()" plain="true"></a>
       					  </div>
    			</div>
			</div>
			
	</div>
	
	<div id="divMember" data-options="region:'center'" title="备勤">
		<div class="easyui-layout" style="height:100%;width :100%">
			<div data-options="region:'north'"  style="height:30px">
				<div id="tbGroup" class="btn-toolbar">
					<div class="btn-group">
						<a id="btnAddPoliceGroup" href="javascript:void(0);"	class="easyui-linkbutton icon-camera-retro" 
						plain="true"	onclick="addPoliceGroup()">保存</a> 
						<a id="btnEditPoliceGroup"	href="javascript:void(0);" class="easyui-linkbutton"	iconcls="icon-edit" 
						plain="true" onclick="editPoliceGroup()">保存为模板</a>
						<a id="btnEditPoliceGroup"	href="javascript:void(0);" class="easyui-linkbutton"	iconcls="icon-edit" 
						plain="true" onclick="editPoliceGroup()">报备类型选择</a>
						<a id="btnDelPoliceGroup" href="javascript:void(0);"	class="easyui-linkbutton" iconcls="icon-cancel" 
						plain="true"	onclick="delPoliceGroup()">选择模板</a>
						<a id="btnDelPoliceGroup" href="javascript:void(0);"	class="easyui-linkbutton" iconcls="icon-cancel" 
						plain="true"	onclick="delPoliceGroup()">报备复制</a>
						<a id="btnDelPoliceGroup" href="javascript:void(0);"	class="easyui-linkbutton" iconcls="icon-cancel" 
						plain="true"	onclick="delPoliceGroup()">清空报备</a>
						<a id="btnDelPoliceGroup" href="javascript:void(0);"	class="easyui-linkbutton" iconcls="icon-cancel" 
						plain="true"	onclick="delPoliceGroup()">导出</a>
					</div>
				</div>
			</div>99
			<div data-options="region:'center'"  >
				<div id="aa" class="easyui-accordion" style="width:100%;height:90%;">    
    				<div title="Title1" data-options="iconCls:'icon-save'" style="overflow:auto;padding:5px;">
    					       
        				<h3 style="color:#0099FF;">Accordion for jQuery</h3>    
        				<p>Accordion is a part of easyui framework for jQuery.     
        					It lets you define your accordion component on web page more easily.
        				</p>    
    				</div>    
    				<div title="Title2" data-options="iconCls:'icon-reload'" style="padding:5px;">    
        				
    				</div>    
    				<div title="Title3">    
        				<ul id="treeD2"></ul> 
    				</div>    
				</div>
			</div>
		</div>
	</div>

	<div id="policeConditionwindow" class="easyui-window" title="人员选择过滤条件"
			data-options="iconCls:'icon-edit'"
			style="width: 354px; height: 300px; padding: 10px;">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'south',border:false"
					style="padding: 2px; height: 30px;">
					 <a href="javascript:void(0);" style="float:right"
						class="easyui-linkbutton"
						onclick="$('#policeConditionwindow').window('close');"
						data-options="iconCls:'icon-remove',plain:true">取消</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" style="float:right"
						data-options="iconCls:'icon-save',plain:true"
						onclick="SearchPoliceAction()">确定</a>  
				</div>				
				<div data-options="region:'west',border:false"
					style="padding: 2px; width:150px; height:200px">
					 <div id="dt_policeType" style="width:80%; height:200px"></div>
				</div>			
				<!-- <div data-options="region:'center',border:false"
					style="padding: 2px; width:133px; height:400px">
					<div id="dt_dutyType" style="width:80%; height:200px"></div>
				</div>	 -->	
				<div data-options="region:'center',border:false"
					style="padding: 2px;  width:150px; height:200px">
					 <div id="dt_groupType" style="width:80%; height:200px"></div>
				</div>
			</div>
	</div>
	
	<div id="gpsConditionwindow" class="easyui-window" title="定位设备选择过滤条件"
			data-options="iconCls:'icon-edit'"
			style="width: 354px; height: 300px; padding: 10px;">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'south',border:false"
					style="padding: 2px; height: 30px;">
					 <a href="javascript:void(0);" style="float:right"
						class="easyui-linkbutton"
						onclick="$('#gpsConditionwindow').window('close');"
						data-options="iconCls:'icon-remove',plain:true">取消</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" style="float:right"
						data-options="iconCls:'icon-save',plain:true"
						onclick="SearchGpsAction()">确定</a>  
				</div>	 
				<div data-options="region:'center',border:false"
					style="padding: 2px;  width:300px; height:200px">
					 <div id="dt_gpsType" style="width:90%; height:200px"></div>
				</div>
			</div>
	</div>
	<div id="weaponConditionwindow" class="easyui-window" title="武器选择过滤条件"
			data-options="iconCls:'icon-edit'"
			style="width: 354px; height: 300px; padding: 10px;">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'south',border:false"
					style="padding: 2px; height: 30px;">
					 <a href="javascript:void(0);" style="float:right"
						class="easyui-linkbutton"
						onclick="$('#weaponConditionwindow').window('close');"
						data-options="iconCls:'icon-remove',plain:true">取消</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" style="float:right"
						data-options="iconCls:'icon-save',plain:true"
						onclick="SearchWeaponAction()">确定</a>  
				</div>	 
				<div data-options="region:'center',border:false"
					style="padding: 2px;  width:300px; height:200px">
					 <div id="dt_weaponType" style="width:90%; height:200px"></div>
				</div>
			</div>
	</div>
		<div id="vehicleConditionwindow" class="easyui-window" title="车辆选择过滤条件"
			data-options="iconCls:'icon-edit'"
			style="width: 354px; height: 300px; padding: 10px;">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'south',border:false"
					style="padding: 2px; height: 30px;">
					 <a href="javascript:void(0);" style="float:right"
						class="easyui-linkbutton"
						onclick="$('#vehicleConditionwindow').window('close');"
						data-options="iconCls:'icon-remove',plain:true">取消</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" style="float:right"
						data-options="iconCls:'icon-save',plain:true"
						onclick="SearchVehicleAction()">确定</a>  
				</div>	 
				<div data-options="region:'center',border:false"
					style="padding: 2px;  width:300px; height:200px">
					 <div id="dt_vehicleType" style="width:90%; height:200px"></div>
				</div>
			</div>
	</div>
</body>