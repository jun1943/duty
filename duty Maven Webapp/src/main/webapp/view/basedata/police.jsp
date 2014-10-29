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
	 
	<div data-options="region:'center'" style="padding:10px;">
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
							onclick="btnSearchAction()"></a>
						<!-- button type="submit" class="btn btn-info">查询</button -->
					</form>
				</div>
				<div id="dtPolice"></div>
			 
			 
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
	 
</body>
</html>