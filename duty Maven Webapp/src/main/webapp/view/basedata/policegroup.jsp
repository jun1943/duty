<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<!--  
<link href='//cdn.datatables.net/plug-ins/a5734b29083/integration/jqueryui/dataTables.jqueryui.css' type='text/css' />
-->
<script src='<%=basePath%>script/basedata/policegroup.js'
	type='text/javascript'></script>
<title>人员分组</title>

</head>

<body>
	<div class="easyui-layout" style="width:100%;height:100%;">
		<div data-options="region:'west',split:true" title="警员组"
			style="width:40%;">
			<div id="tbGroup" class="btn-toolbar">
  				<div class="btn-group">
  					<a id="btnAddEvidenceBill" href="javascript:void(0);" class="easyui-linkbutton icon-camera-retro"  plain="true" onclick="btnAddGroup()">创建</a>
                	<a id="btnEditEvidenceBill" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-edit" plain="true" onclick="btnEditGroup()">修改</a>
                	<a id="btnDelEvidenceBill" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-cancel" plain="true" onclick="btnDelGroup()">删除</a>
  				</div>
  			</div>
  			<div id="dtGroup" ></div>
		</div>
		<div data-options="region:'center',title:'警员'">
			<div id="tbGroup" class="btn-toolbar">
  				<div class="btn-group">
  					<a id="btnAddEvidenceBill" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-add" plain="true" onclick="btnAddGroupMember()">添加</a>
                	<a id="btnEditEvidenceBill" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-edit" plain="true" onclick="btnDelGroupMember()">删除</a>
                	<a id="btnDelEvidenceBill" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-cancel" plain="true" onclick="btnCleanGroupMember()">清空</a>
  				</div>
  			</div>
			<div id="dtGroupMember"></div>
		</div>
	</div>
	
	<!-- 编辑页面 -->
	<div class="modal fade" id="dlgPoliceGroup" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
            		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                  		&times;
            		</button>
            		<h5 class="modal-title" id="myModalLabel">
            			警员组
            		</h5>
            		
         		</div>
         		<div class="modal-body">
         			<div id="tbGroup" class="btn-toolbar">
  						<div class="btn-group">
  							<a id="btnSavePoliceGroup" href="javascript:void(0);" class="easyui-linkbutton "  plain="true" onclick="savePoliceGroup"()">保存</a>
                			<a id="btnClosePoliceGroupDlg" href="javascript:void(0);" class="easyui-linkbutton "  plain="true" onclick="closePoliceGroupDlg()">退出</a>
  						</div>
  					</div>
  					

					<!-- 
  					<div class="container-fluid">
  						<label for="txtGroupName" class="col-md-4">组名称：</label>
  						<input id="txtGroupName" type="text" class="col-md-8" placeholder="请输入警员组名称">
  						<label for="txtGroupName" class="col-md-4">组名称：</label>
  						<input id="txtGroupName" type="text" class="col-md-8" placeholder="请输入警员组名称">
  					</div>
					 -->
					
					<div class="row"> 
						<label class="col-md-4">名称:</label>
						<input id="txtVehicleNameplate" class="col-md-4 easyui-validatebox" data-options="required:false" />
					</div>
					
					<div class="row"> 
						<div class="span4"></div> 
						<label class="col-md-4">名称:</label>
						<input id="txtVehicleNameplate" class="col-md-4 easyui-validatebox" data-options="required:false" />
					</div>
					
					<table style="padding: 3px">
						<tr>
							<td>
								<label >名称:</label>
							</td>
							<td>
								<input id="txtVehicleNameplate" class="easyui-validatebox" data-options="required:false" />
							</td>
						</tr>
						
						<tr>
							<td>
								<label >名称:</label>
							</td>
							<td>
								<input id="txtVehicleNameplate2" class="easyui-validatebox" data-options="required:false" />
							</td>
						</tr>
					</table>
                    
                    
                  </div>
  					                
         		</div>
			</div>
		</div>
		
	</div>
	
</body>
</html>
