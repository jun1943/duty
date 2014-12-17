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
<script src='<%=basePath%>script/basedata/icons.js'
	type='text/javascript'></script>
	<script src='<%=basePath%>script/ajaxupload.3.9.js'
	type='text/javascript'></script>
	<script src='<%=basePath%>script/basedata/uploadIcon.js'
	type='text/javascript'></script>
<title>基础数据——图标管理</title>


</head> 
  <body class="easyui-layout iconslayout"   oncontextmenu=self.event.returnValue=false> 
    
	<div data-options="region:'center'" class="iconscenter"> 
 
		 
			 <!-- 工具条 -->
			<div id="tbIcons" class="btn-toolbar iconstoolbar" >
  				<div class="btn-group">
  					<a id="btnAddIcons" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-tianyi-add" 	onclick="btnAddIcons('add')">新增</a>
                	<a id="btnEditIcons" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-tianyi-edit"   onclick="btnEditIcons('edit')">修改</a>
                	<a id="btnDelIcons" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-tianyi-delete"  onclick="btnDelIcons()">删除</a> 
  			 
  					<a id="btnSearchIcons" href="javascript:void(0);" class="easyui-linkbutton iconssearchbtn"    onclick="btnSearchIcons()">展开查询</a>
               	</div>
  			</div>
  			<!-- 查询条件 -->
  			<div class="MySearch" id="my-search-box" >
		 			<div class="MySearchMain">
				 		<div>
							<form>
								<table>
									<tr> 
										<td class="MySearchTDTitle">名称:</td>
										<td><input id="txtsearchName" class="easyui-validatebox iconssearchinput" type="text" ></td>
										<td class="MySearchTDTitle">图片类别 :</td>
										<td><input id="sltType" class="easyui-combobox iconssearchinput" name="dept" 
										data-options="editable:false,valueField:'id',textField:'name',data:[{id: 0,name: '全部'},{id: 1,name: '警员'},{id: 2,name: '车辆'},{id: 3,name: '武器'},{id: 4,name: '定位设备'}]" /></td>
										<td> <a id="btnSearchAction" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-tianyi-search"  onclick="btnSearchAction()">查询</a></td>
									 
									</tr> 
								</table>
							</form>
						</div>	
					</div>		
				
				</div> 
  			 <!-- 图标列表 -->
  			<div id="dtIcons" >
  			</div> 
	</div> 
	 
	<!-- 编辑窗体内容 -->
	 <div id="iconsinfowindow" class="easyui-window iconswindow" title="新增/编辑图标信息"
			data-options="iconCls:'icon-tianyi-edit',modal:true" closed="true" 
	 	collapsible="false" minimizable="false" maximizable="false" resizable="false" shadow="false" >
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'south',border:false" class="iconswindowsouth" > 
					 <a id="btnsaveIconsCon" href="javascript:void(0);" class="easyui-linkbutton iconswindowbtnExit"
						 
						onclick="saveIconsAction()">保存并继续</a>  
					 <a href="javascript:void(0);" class="easyui-linkbutton iconswindowbtn"
						 
						onclick="saveIconsActionExit()">　　保　存　　</a> 
				</div>
				<div data-options="region:'center',border:false" class="iconswindowcenter"> 
						<table class="iconswindowtable"> 
							<tr>
								<td class="iconswindowtabletd"><input type="hidden" id="iconsId"><label class="iconswindowlabel">图片类型:　</label></td>
								<td> <input id="txttype" class="easyui-combobox" name="dept"
											data-options="editable:false,required:true,valueField:'id',textField:'name',data:[{id: 0,name: '请选择图片类型'},{id: 1,name: '警员'},{id: 2,name: '车辆'},{id: 3,name: '武器'},{id: 4,name: '定位设备'}]" /><span style="color:red">*</span>
	                         	</td>  
	                         	<td class="iconswindowtabletd" rowspan="3">
		                         	<img id="sltImage" class="iconswindowimage" src="">
                  				</td>
							</tr>
							<tr>
								<td class="iconswindowtabletd"><label class="iconswindowlabel">图标标题:　</label></td>
								<td><input type="text" class="easyui-validatebox" id="txtname"></td>
							</tr> 
							<tr>
								<td class="iconswindowtabletd"><label class="iconswindowlabel">图标选择:　</label>
								</td>
								<td><input type="text" class="easyui-validatebox" id="txtfilename"><a id="btnfindIcon"  href="javascript:void(0);" class="easyui-linkbutton"  plain="true" >选择图片</a>
								</td>
							</tr>
						</table>
					  
				</div>
			</div>
		</div>
	
	 
  </body>
</html>
