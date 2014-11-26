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
<script src='<%=basePath%>script/basedata/police.js'
	type='text/javascript'></script>
<title>警员管理</title>


</head>

<body class="easyui-layout" style="padding:2px" oncontextmenu=self.event.returnValue=false>
	 
	<div data-options="region:'center'" style="padding:2px">
				<div id="tbPolice" class="btn-toolbar" style="height:30px; margin-top:3px">
					<div class="btn-group">
						<a id="btnAddPolice" href="javascript:void(0);"
							class="easyui-linkbutton" iconcls="icon-tianyi-add" 
							onclick="btnAddPolice('add')">新增</a> <a id="btnEditPolice"
							href="javascript:void(0);" class="easyui-linkbutton"
							iconcls="icon-tianyi-edit"  onclick="btnEditPolice('edit')">修改</a>
							<a id="btnUnLockPolice"
							href="javascript:void(0);" class="easyui-linkbutton"
							iconcls="icon-tianyi-unlock"  onclick="btnUnLockPolice()">启用</a>
							<a id="btnLockPolice"
							href="javascript:void(0);" class="easyui-linkbutton"
							iconcls="icon-tianyi-lock"  onclick="btnLockPolice()">停用</a>
						<a id="btnDelPolice" href="javascript:void(0);"
							class="easyui-linkbutton" iconcls="icon-tianyi-delete" 
							onclick="btnDelPolice()">删除</a>
					 
						<a id="btnSearchPolice" href="javascript:void(0);"
							class="easyui-linkbutton"  style="float:right; margin-right:20px"
							onclick="btnSearchPolice()">展开查询</a>
						 
					</div>
			 	</div>
			 	
			 	<div class="MySearch" id="my-search-box" style=" padding:3px;display:none; ">
		 			<div class="MySearchMain">
				 		<div>
							<form>
								<table>
									<tr>
										<td class="MySearchTDTitle">查询范围选择:</td>
										<td><input id="isSubOrg" class="easyui-combobox" name="dept" style="width:104px"
										data-options="valueField:'id',textField:'name',data:[{id: 0,name: '本級机构'},{id: 1,name: '本级及下级机构'}]" /></td>
										<td class="MySearchTDTitle">姓名:</td>
										<td><input id="txtsearchName" type="text"
										class="easyui-validatebox"></td>
										<td class="MySearchTDTitle">人员类别 :</td>
										<td><input id="sltType" class="easyui-combobox"  style="width:104px" /></td>
										<td><a id="btnSearchAction" href="javascript:void(0);"
							class="easyui-linkbutton" iconcls="icon-tianyi-search"  
							onclick="btnSearchAction()">查询</a></td>
									 
									</tr> 
								</table>
							</form>
						</div>	
					</div>		
				
				</div> 
				<div id="dtPolice"></div>
			 
			 
		</div>
		<div id="policeinfowindow" class="easyui-window" title="新增/编辑 警员信息"
			data-options="iconCls:'icon-edit',modal:true" closed="true" 
	 	collapsible="false" minimizable="false" maximizable="false" resizable="false" shadow="false" 
			style="width: 400px; height: 500px; padding: 10px;color:white">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'south',border:false"
					style="padding: 2px; height: 30px;">
					<a href="javascript:void(0);" class="easyui-linkbutton"
					  style="float:right"
						onclick="savePoliceAction()">保存并继续</a>  
				</div>
				<div data-options="region:'center',border:false"
					style="text-align: right;
            padding: 5px 0 0;">
					<table style="width:100%;height:99%; font: 12px 微软雅黑;">
						<tr>
							<td style="text-align: right;"><input type="hidden" id="policeId"><label>人员类别:　</label></td>
							<td><input id="txttype" class="easyui-combobox" /></td>
						</tr>
						<tr>
							<td style="text-align: right;"><label>姓名:　</label></td>
							<td><input id="txtname" type="text"
								class="easyui-validatebox" data-options="required:true"></td>
						</tr>
						<tr>
							<td style="text-align: right;"><label>身份证号码:　</label></td>
							<td><input id="txtidcardno" type="text" 
								class="easyui-validatebox"></td>
						</tr>
						<tr>
							<td style="text-align: right;"><label>警号:　</label></td>
							<td><input id="txtnumber" type="text"
								class="easyui-validatebox" ></td>
						</tr>
						<tr>
							<td style="text-align: right;"><label>职务:　</label></td>
							<td><input id="txttitle" type="text"
								class="easyui-validatebox"></td>
						</tr>
						<tr>
							<td style="text-align: right;"><label>手机号码:　</label></td>
							<td><input id="txtmobile" type="text"
								class="easyui-validatebox"></td>
						</tr>
						<tr>
							<td style="text-align: right;"><label>公安短号:　</label></td>
							<td><input id="txtmobileshort" type="text"
								class="easyui-validatebox"></td>
						</tr>
						<tr>
							<td style="text-align: right;"><label>组呼号:　</label></td>
							<td><input id="txtgroupno" class="easyui-combobox" /></td>
						</tr>
						<tr>
							<td style="text-align: right;"><label>个呼号:　</label></td>
							<td><input id="txtpersonalno" type="text"
								class="easyui-validatebox"></td>
						</tr>
						<tr>
							<td style="text-align: right;"><label>GPS设备:　</label></td>
							<td><input id="txtgpsid" class="easyui-combobox" /></td>
						</tr>
						<!-- <tr>
							<td style="text-align: right;"><label>GPS备注:　</label></td>
							<td><input id="txtgpsdes" type="text"
								class="easyui-validatebox"></td>
						</tr> -->
					</table>
				</div>
			</div>
		</div>
	 
</body>
</html>