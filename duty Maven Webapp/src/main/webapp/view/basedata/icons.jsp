<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<!--  
<link href='//cdn.datatables.net/plug-ins/a5734b29083/integration/jqueryui/dataTables.jqueryui.css' type='text/css' />
-->
<script src='<%=basePath%>script/basedata/icons.js'
	type='text/javascript'></script>
	<script src='<%=basePath%>script/ajaxupload.3.9.js'
	type='text/javascript'></script>
	<script src='<%=basePath%>script/basedata/uploadIcon.js'
	type='text/javascript'></script>
<title>图标管理</title>


</head> 
  <body class="easyui-layout"> 
    
	<div data-options="region:'center'"style="padding:10px;"> 
 
		 
			 
			<div id="tbIcons" class="btn-toolbar" style="height:40px; margin-top:10px">
  				<div class="btn-group">
  					<a id="btnAddIcons" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-add"  plain="true" onclick="btnAddIcons()">新增</a>
                	<a id="btnEditIcons" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-edit" plain="true" onclick="btnEditIcons()">修改</a>
                	<a id="btnDelIcons" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-cancel" plain="true" onclick="btnDelIcons()">删除</a> 
  				</div>  				
  				<div class="btn-group" style="float:right; margin-right:20px">
  					<a id="btnSearchIcons" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-search"  plain="true" onclick="btnSearchIcons()">查询</a>
               	</div>
  			</div>
  			<div id="my-search-box" class="panel-body" style="border:0px;display:none">
  			 <form class="form-inline">  
                  
                  <div class="form-group">
                      <div class="form-group">
                        <div class="input-group">
                         	名称	
                           <input id="txtsearchName" class="easyui-validatebox" type="text" >
                        </div>
                      </div>
                  </div> 
                  <div class="form-group">
                      <div class="form-group">
                        <div class="input-group">
                         	图片类别	
                         	  <input id="sltType" class="easyui-combobox" name="dept"
										data-options="valueField:'id',textField:'name',data:[{id: 0,name: '全部'},{id: 1,name: '警员'},{id: 2,name: '车辆'},{id: 3,name: '武器'},{id: 4,name: '定位设备'}]" />
                         	
                          
                        </div>
                      </div>
                  </div> 
                 <a id="btnSearchAction" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-search"  plain="true" onclick="btnSearchAction()">查询</a>
				<!-- button type="submit" class="btn btn-info">查询</button -->
				</form>
			</div>	
  			<div id="dtIcons" >
  			</div> 
	</div> 
	 
	
	 <div id="iconsinfowindow" class="easyui-window" title="新增/编辑武器信息"
			data-options="iconCls:'icon-edit',modal:true" closed="true" 
	 	collapsible="false" minimizable="false" maximizable="false" resizable="false" shadow="false" 
			style="width: 400px; height: 240px; padding: 10px;">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'north',border:false"
					style="padding: 2px; height: 30px;">
					<!-- <a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'icon-save',plain:true"
						onclick="saveIconsAction()">保存</a> --> <a href="javascript:void(0);"
						class="easyui-linkbutton"
						onclick="$('#iconsinfowindow').window('close');"
						data-options="iconCls:'icon-remove',plain:true">关闭</a>
				</div>
				<div data-options="region:'center',border:false"
					style="text-align: right; margin-left: 5px;
            padding: 5px 0 0;"> 
						<table style="width:100%;height:99%; font: 12px 微软雅黑;"> 
							<tr>
								<td><input type="hidden" id="iconsId"><label>图片类型:</label></td>
								<td> <input id="txttype" class="easyui-combobox" name="dept"
											data-options="valueField:'id',textField:'name',data:[{id: 0,name: '请选择图片类型'},{id: 1,name: '警员'},{id: 2,name: '车辆'},{id: 3,name: '武器'},{id: 4,name: '定位设备'}]" />
	                         	</td>
							</tr>
							<tr>
								<td><label>图标名称:</label></td>
								<td><input type="text" class="easyui-validatebox" id="txtname"><a id="btnfindIcon"  href="javascript:void(0);" class="easyui-linkbutton"  plain="true" >选择图片</a></td>
							</tr> 
						</table>
					  
				</div>
			</div>
		</div>
	
	 
  </body>
</html>
