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
<script src='<%=basePath%>script/basedata/gpsdevice.js'
	type='text/javascript'></script>
<title>基础数据——定位设备管理</title>


</head>

<body class="easyui-layout gpslayout"   oncontextmenu=self.event.returnValue=false> 
	 
	
	<div data-options="region:'center'" class="gpscenter" > 
	 		<!-- 工具条 -->
			<div id="tbGpsdevice" class="btn-toolbar gpstoolbar" >
  				<div class="btn-group">
  					<a id="btnAddGpsdevice" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-tianyi-add"  onclick="btnAddGpsdevice('add')">创建</a>
                	<a id="btnEditGpsdevice" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-tianyi-edit"   onclick="btnEditGpsdevice('edit')">修改</a>
                	<a id="btnDelGpsdevice" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-tianyi-delete"  onclick="btnDelGpsdevice()">删除</a>
                
  					<a id="btnSearchGpsdevice" href="javascript:void(0);" class="easyui-linkbutton gpssearchbtn"   >展开查询</a>
                	<a href="javascript:void(0);" class="easyui-linkbutton policeexportorinport"  iconcls="icon-tianyi-export"  onclick="btnExportAction()">导出数据</a>
                	<!-- a id="btnPrintList" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-print" plain="true" onclick="btnPrintList()">打印</a>
                	<a id="btnExportList" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-export" plain="true" onclick="btnExportList()">导出</a> -->
               	</div>
  			</div>
  			<!-- 查询条件 -->
  			<div class="MySearch" id="my-search-box"  >
		 			<div class="MySearchMain">
				 		<div>
							<form>
								<table>
									<tr>
										<td class="MySearchTDTitle">查询范围选择:</td>
										<td><input id="isSubOrg" class="easyui-combobox gpssearchinput" name="dept"  
										data-options="editable:false,valueField:'id',textField:'name',data:[{id: 0,name: '本級机构'},{id: 1,name: '本级及下级机构'}]" /></td>
										<td class="MySearchTDTitle">GPS名称:</td>
										<td><input id="txtsearchname" class="easyui-validatebox gpssearchinput" type="text"></td>
										<td><a id="btnSearchAction" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-tianyi-search"   onclick="btnSearchAction()">查询</a></td>
									 
									</tr> 
								</table>
							</form>
						</div>	
					</div>		
				
				</div> 
				<!-- 定位设备列表 -->
  			<div id="dtGpsdevice" >
  			</div> 
 
	</div>   
	<!-- 编辑窗体内容 -->
	 <div id="gpsdeviceinfowindow" class="easyui-window" title="新增/编辑定位设备信息"
			data-options="iconCls:'icon-tianyi-edit',modal:true" closed="true" 
			style="width: 400px; height: 240px; padding: 10px;"
	 	collapsible="false" minimizable="false" maximizable="false" resizable="false" shadow="false">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'south',border:false" class="gpswindowsouth"> 
					<a id="btnsaveDeviceCon"  href="javascript:void(0);" class="easyui-linkbutton gpswindowbtnExit"
						 
						onclick="saveGpsdeviceAction()">保存并继续</a> 
					<a id="btnsaveDeviceExit" href="javascript:void(0);" class="easyui-linkbutton gpswindowbtn"
					 
						onclick="saveGpsdeviceActionExit()">　　保　存　　</a> 
				</div>
				<div data-options="region:'center',border:false" class="gpswindowcenter">
					<table class="gpswindowtable">  
						<tr>
							<td style="text-align: right;"><input type="hidden" id="gpsdeviceId"><label>GPS类型:　</label></td>
							<td><input id="txttype"   class="easyui-combobox gpswindowinput"  data-options="editable:false,required:true"/><span style="color:red">*</span></td>
							<td rowspan="4"><img id="sltImage" class="gpswindowimage" src=""></td>
						</tr>
						<tr>
							<td style="text-align: right;"><label>GPS名称:　</label></td>
							<td><input type="text" class="easyui-validatebox gpswindowinput" id="txtgpsname" data-options="required:true,validType:['length[1,20]']"><span style="color:red">*</span></td>
							<td></td>
						</tr>
						<tr>
							<td style="text-align: right;"><label>GPS编号:　</label></td>
							<td><input type="text" class="easyui-validatebox gpswindowinput" id="txtgpsnumber" data-options="required:true,validType:['length[1,20]']"><span style="color:red">*</span></td>
							<td></td>
						</tr> 
						<tr>
							<td style="text-align: right;"><label>GPS图标:　</label></td>
							
							<td><input type="text" class="easyui-validatebox gpswindowinput" id="txtgpsicon" data-options="editable:false,required:true"><span style="color:red">*</span><input type="hidden" id="txtIconUrl"></td>
							
							<td></td>
						</tr> 
					</table>
				</div>
			</div>
		</div>
	 
</body>
</html>