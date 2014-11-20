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
<title>图标管理</title>


</head> 
  <body class="easyui-layout" style="padding:2px"> 
    
	<div data-options="region:'center'"style="padding:2px;"> 
 
		 
			 
			<div id="tbIcons" class="btn-toolbar" style="height:30px; margin-top:3px">
  				<div class="btn-group">
  					<a id="btnAddIcons" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-tianyi-add" 	onclick="btnAddIcons('add')">新增</a>
                	<a id="btnEditIcons" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-tianyi-edit"   onclick="btnEditIcons('edit')">修改</a>
                	<a id="btnDelIcons" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-tianyi-delete"  onclick="btnDelIcons()">删除</a> 
  			 
  					<a id="btnSearchIcons" href="javascript:void(0);" class="easyui-linkbutton"  style="float:right; margin-right:20px"   onclick="btnSearchIcons()">展开查询</a>
               	</div>
  			</div>
  			<div class="MySearch" id="my-search-box" style=" padding:3px;display:none; ">
		 			<div class="MySearchMain">
				 		<div>
							<form>
								<table>
									<tr> 
										<td class="MySearchTDTitle">名称:</td>
										<td><input id="txtsearchName" class="easyui-validatebox" type="text" ></td>
										<td class="MySearchTDTitle">图片类别 :</td>
										<td><input id="sltType" class="easyui-combobox" name="dept"
										data-options="valueField:'id',textField:'name',data:[{id: 0,name: '全部'},{id: 1,name: '警员'},{id: 2,name: '车辆'},{id: 3,name: '武器'},{id: 4,name: '定位设备'}]" /></td>
										<td> <a id="btnSearchAction" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-tianyi-search"  onclick="btnSearchAction()">查询</a></td>
									 
									</tr> 
								</table>
							</form>
						</div>	
					</div>		
				
				</div> 
  			 
  			<div id="dtIcons" >
  			</div> 
	</div> 
	 
	
	 <div id="iconsinfowindow" class="easyui-window" title="新增/编辑武器信息"
			data-options="iconCls:'icon-edit',modal:true" closed="true" 
	 	collapsible="false" minimizable="false" maximizable="false" resizable="false" shadow="false" 
			style="width: 450px; height: 240px; padding: 10px;">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'south',border:false"
					style="padding: 2px; height: 30px;"> 
					 <a href="javascript:void(0);" class="easyui-linkbutton"
						 style="float:right"
						onclick="saveIconsAction()">保存并继续</a>  
				</div>
				<div data-options="region:'center',border:false"
					style="text-align: right; margin-left: 5px;
            padding: 5px 0 0;"> 
						<table style="width:100%;height:99%; font: 12px 微软雅黑;"> 
							<tr>
								<td style="text-align: right;"><input type="hidden" id="iconsId"><label style="width:45px">图片类型:　</label></td>
								<td> <input id="txttype" class="easyui-combobox" name="dept"
											data-options="valueField:'id',textField:'name',data:[{id: 0,name: '请选择图片类型'},{id: 1,name: '警员'},{id: 2,name: '车辆'},{id: 3,name: '武器'},{id: 4,name: '定位设备'}]" />
	                         	</td>
	                         	<td style="text-align: right;" rowspan="3">
		                         	<img id="sltImage" style="width:110px; height:110px" src="">
                  				</td>
							</tr>
							<tr>
								<td style="text-align: right;"><label style="width:45px">图标标题:　</label></td>
								<td><input type="text" class="easyui-validatebox" id="txtname"></td>
							</tr> 
							<tr>
								<td style="text-align: right;"><label style="width:45px">图标选择:　</label>
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
