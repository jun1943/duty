<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<!--  
<link href='//cdn.datatables.net/plug-ins/a5734b29083/integration/jqueryui/dataTables.jqueryui.css' type='text/css' />
-->
<script src='<%=basePath%>script/basedata/police.js'
	type='text/javascript'></script>
<title>警员管理</title>


</head>

<body class="easyui-layout">
	<div data-options="region:'west',split:true,title:'组织机构导航树'"
		style="width:13%;padding:10px;">
		<div>
			<input type="text">
		</div>
		<ul id="tt" class="easyui-tree">
			<li><span>Folder</span>
				<ul>
					<li><span>Sub Folder 1</span>
						<ul>
							<li><span><a href="#">File 11</a></span></li>
							<li><span>File 12</span></li>
							<li><span>File 13</span></li>
						</ul></li>
					<li><span>File 2</span></li>
					<li><span>File 3</span></li>
				</ul></li>
			<li><span>File21</span></li>
		</ul>
	</div>
	<div data-options="region:'center'" style="padding:10px;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'north',border:false"
				style="padding: 2px; height: 55px;overflow:hidden">
				<div class="container-fluid my-nav-bg">
					<ul class="nav navbar-nav">
						<li style="width:1px;"><a href="#">&nbsp;</a></li>
						<li class="active"><a href="/duty/view/basedata/police.jsp"
							class="my-nav-btn">人员管理 <img
								src="asset/css/images/nav-btn-icon.png" width="22" height="22"></a></li>
						<li><a href="/duty/view/basedata/vehicle.jsp"
							class="my-nav-btn">车辆管理 <img
								src="asset/css/images/nav-btn-icon.png" width="22" height="22">
						</a></li>
						<li><a href="/duty/view/basedata/weapon.jsp"
							class="my-nav-btn">武器管理 <img
								src="asset/css/images/nav-btn-icon.png" width="22" height="22">
						</a></li>
						<li><a href="/duty/view/basedata/gpsdevice.jsp"
							class="my-nav-btn">定位设备 <img
								src="asset/css/images/nav-btn-icon.png" width="22" height="22">
						</a></li>
						<li><a href="/duty/view/basedata/icons.jsp"
							class="my-nav-btn">图标管理 <img
								src="asset/css/images/nav-btn-icon.png" width="22" height="22">
						</a></li>
					</ul>
				</div>
			</div>
			<div data-options="region:'center',border:false">
				<div id="tbPolice" class="btn-toolbar" style="height:40px; margin-top:10px">
					<div class="btn-group">
						<a id="btnAddPolice" href="javascript:void(0);"
							class="easyui-linkbutton" iconcls="icon-add" plain="true"
							onclick="btnAddPolice()">新增</a> <a id="btnEditPolice"
							href="javascript:void(0);" class="easyui-linkbutton"
							iconcls="icon-edit" plain="true" onclick="btnEditPolice()">修改</a>
						<a id="btnDelPolice" href="javascript:void(0);"
							class="easyui-linkbutton" iconcls="icon-cancel" plain="true"
							onclick="btnDelPolice()">删除</a>
					</div>
					<div class="btn-group" style="float:right; margin-right:20px">
						<a id="btnSearchPolice" href="javascript:void(0);"
							class="easyui-linkbutton" iconcls="icon-search" plain="true"
							onclick="btnSearchPolice()">查询</a>
						<!--a id="btnPrintList" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-print" plain="true" onclick="btnPrintList()">打印</a>
                	<a id="btnExportList" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-export" plain="true" onclick="btnExportList()">导出</a-->
					</div>
				</div>
				<div id="my-search-box" class="panel-body" style="border:0px;display:none">
					<form class="form-inline">
						<div class="form-group">
							<div class="form-group">
								<div class="input-group">
									　　查询范围选择 <input id="isSubOrg" class="easyui-combobox" name="dept"
										data-options="valueField:'id',textField:'name',data:[{id: 0,name: '本級机构'},{id: 1,name: '本级及下级机构'}]" />

								</div>
							</div>
						</div>

						<div class="form-group">
							<div class="form-group">
								<div class="input-group">
									姓名 <input id="txtsearchName" type="text"
										class="easyui-validatebox">
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="form-group">
								<div class="input-group">
									人员类别 <input id="sltType" class="easyui-combobox" />

								</div>
							</div>
						</div>
						<a id="btnSearchAction" href="javascript:void(0);"
							class="easyui-linkbutton" iconcls="icon-search" plain="true"
							onclick="btnSearchAction()">查询</a>
						<!-- button type="submit" class="btn btn-info">查询</button -->
					</form>
				</div>
				<div id="dtPolice"></div>
			</div>

		</div>
		<div id="policeinfowindow" class="easyui-window" title="新增/编辑 警员信息"
			data-options="iconCls:'icon-edit'"
			style="width: 400px; height: 500px; padding: 10px;">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'north',border:false"
					style="padding: 2px; height: 30px;">
					<a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'icon-save',plain:true"
						onclick="savePoliceAction()">保存</a> <a href="javascript:void(0);"
						class="easyui-linkbutton"
						onclick="$('#policeinfowindow').window('close');"
						data-options="iconCls:'icon-remove',plain:true">关闭</a>
				</div>
				<div data-options="region:'center',border:false"
					style="text-align: right; margin-left: 5px;
            padding: 5px 0 0;">
					<table style="width:100%;height:99%; font: 12px 微软雅黑;">
						<tr>
							<td><input type="hidden" id="policeId"><label>人员类别:</label></td>
							<td><input id="txttype" class="easyui-combobox" /></td>
						</tr>
						<tr>
							<td><label>姓名:</label></td>
							<td><input id="txtname" type="text"
								class="easyui-validatebox"></td>
						</tr>
						<tr>
							<td><label>身份证号码:</label></td>
							<td><input id="txtidcardno" type="text"
								class="easyui-validatebox"></td>
						</tr>
						<tr>
							<td><label>警号:</label></td>
							<td><input id="txtnumber" type="text"
								class="easyui-validatebox"></td>
						</tr>
						<tr>
							<td><label>职务:</label></td>
							<td><input id="txttitle" type="text"
								class="easyui-validatebox"></td>
						</tr>
						<tr>
							<td><label>手机号码:</label></td>
							<td><input id="txtmobile" type="text"
								class="easyui-validatebox"></td>
						</tr>
						<tr>
							<td><label>公安短号:</label></td>
							<td><input id="txtmobileshort" type="text"
								class="easyui-validatebox"></td>
						</tr>
						<tr>
							<td><label>组呼号:</label></td>
							<td><input id="txtgroupno" class="easyui-combobox" /></td>
						</tr>
						<tr>
							<td><label>个呼号:</label></td>
							<td><input id="txtpersonalno" type="text"
								class="easyui-validatebox"></td>
						</tr>
						<tr>
							<td><label>GPS_ID:</label></td>
							<td><input id="txtgpsid" class="easyui-combobox" /></td>
						</tr>
						<tr>
							<td><label>GPS备注:</label></td>
							<td><input id="txtgpsdes" type="text"
								class="easyui-validatebox"></td>
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
					<h4 class="modal-title" id="myModalLabel">新增警员信息</h4>
				</div>
				<div class="modal-body">

					<!--表单开始 
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label for="txttype" class="col-sm-3 control-label">人员类别:</label>
							<div class="col-sm-4">
								<select id="txttype" class="form-control"> 
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="txtname" class="col-sm-3 control-label">姓名:</label>
							<div class="col-sm-4">
								<input type="hidden" id="policeId" >
								<input id="txtname" type="text" class="form-control"  placeholder="">
							</div>
						</div>
						<div class="form-group">
							<label for="txtidcardno" class="col-sm-3 control-label">身份证号码:</label>
							<div class="col-sm-4">
								<input id="txtidcardno" type="text" class="form-control" placeholder="">
							</div>
						</div>
						<div class="form-group">
							<label for="txtnumber" class="col-sm-3 control-label">警号:</label>
							<div class="col-sm-4">
								<input id="txtnumber" type="text" class="form-control" placeholder="">
							</div>
						</div>
						<div class="form-group">
							<label for="txttitle" class="col-sm-3 control-label">职务:</label>
							<div class="col-sm-4">
								<input id="txttitle" type="text" class="form-control" placeholder="">
							</div>
						</div>
						<div class="form-group">
							<label for="txtmobile" class="col-sm-3 control-label">手机号码:</label>
							<div class="col-sm-4">
								<input id="txtmobile" type="text" class="form-control" placeholder="">
							</div>
						</div>
						<div class="form-group">
							<label for="txtmobileshort" class="col-sm-3 control-label">公安短号:</label>
							<div class="col-sm-4">
								<input id="txtmobileshort" type="text" class="form-control" placeholder="">
							</div>
						</div>
						
						<div class="form-group">
							<label for="txtgroupno" class="col-sm-3 control-label">组呼号:</label>
							<div class="col-sm-4">
								<select id="txtgroupno" class="form-control">  
								</select>
							</div>
						</div> 
						<div class="form-group">
							<label for="txtpersonalno" class="col-sm-3 control-label">个呼号:</label>
							<div class="col-sm-4">
								<input id="txtpersonalno" type="text" class="form-control" placeholder="">
							</div>
						</div>
 
						<div class="form-group">
							<label for="txtgpsid" class="col-sm-3 control-label">GPS_ID:</label>
							<div class="col-sm-4"> 
								<select id="txtgpsid" class="form-control">  
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="txtgpsdes" class="col-sm-3 control-label">GPS备注:</label>
							<div class="col-sm-4">
								<input id="txtgpsdes" type="text" class="form-control" placeholder="">
							</div>
						</div>
					</form>
					<!--表单结束 

				</div>
				<div class="modal-footer">
					<button id="btnsavepolice" onclick="savePoliceAction()"
						type="button" class="btn btn-primary">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>			 -->
</body>
</html>