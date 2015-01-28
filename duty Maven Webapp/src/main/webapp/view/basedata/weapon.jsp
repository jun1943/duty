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
<script src='<%=basePath%>script/basedata/weapon.js'
	type='text/javascript'></script>
	<script src='<%=basePath%>script/ajaxupload.3.9.js'
	type='text/javascript'></script>
	<script src='<%=basePath%>script/basedata/uploadsource.js'
	type='text/javascript'></script>
<title>基础数据——武器管理</title>


</head>

<body class="easyui-layout weaponlayout"  oncontextmenu=self.event.returnValue=false> 
 
	<div data-options="region:'center'" class="weaponcenter" >  
			<!-- 工具条 -->
			<div id="tbWeapon" class="btn-toolbar weapontoolbar" >
  				<div class="btn-group">
  					<a id="btnAddWeapon" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-tianyi-add"  onclick="btnAddWeapon('add')">新增</a>
                	<!-- <a id="btnEditWeapon" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-tianyi-edit" onclick="btnEditWeapon('edit')">修改</a> -->
                	<a id="btnDelWeapon" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-tianyi-delete"   onclick="btnDelWeapon()">删除</a>
                	 
  					<a id="btnSearchWeapon" href="javascript:void(0);" class="easyui-linkbutton weaponsearchbtn"  >展开查询</a>
                	<a href="javascript:void(0);" class="easyui-linkbutton policeexportorinport"  iconcls="icon-tianyi-export"  onclick="btnExportAction()">导出数据</a>
                	<a href="javascript:void(0);" class="easyui-linkbutton policeexportorinport"  iconcls="icon-tianyi-export"  onclick="btnInportAction()">导入数据</a>
                	<!-- a id="btnPrintList" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-print" plain="true" onclick="btnPrintList()">打印</a>
                	<a id="btnExportList" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-export" plain="true" onclick="btnExportList()">导出</a> -->
               	</div>
  			</div>
  			<!-- 查询条件 -->
  			<div class="MySearch" id="my-search-box">
		 		<div class="MySearchMain">
				 	<div>
							<form>
								<table>
									<tr>
										<td class="MySearchTDTitle">查询范围选择:</td>
										<td><input id="isSubOrg" class="easyui-combobox weaponsearchinput" name="dept"  
										data-options="editable:false,valueField:'id',textField:'name',data:[{id: 0,name: '本級机构'},{id: 1,name: '本级及下级机构'}]" /></td>
										<td class="MySearchTDTitle">武器编号:</td>
										<td><input id="txtsearchnumber" class="easyui-validatebox weaponsearchinput" type="text" ></td>
										<td><a id="btnSearchAction" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-tianyi-search"  onclick="btnSearchAction()">查询</a></td>
									 
									</tr> 
								</table>
							</form>
						</div>	
					</div>		
				
				</div>
  			 <!-- 武器列表 -->
  			<div id="dtWeapon" >
  			</div> 
 
	</div>   
	
  			<div id="weaponInfoinportwindow" class="easyui-window" title="导入数据选项" data-options="modal:true,headerCls:'bbbb'" closed="true" 
			style="width: 450px; height: 200px; padding: 10px;color:white;"
	 	collapsible="false" minimizable="false" maximizable="false" resizable="false" shadow="false" >
	 		<div class="windowdiv">  
	 			<input type="text" class="easyui-validatebox" readonly="readonly" id="txtentityfilename">
	 			<a id="btnfindfile"  href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-tianyi-add"   >选择文件</a> <a href="javascript:void(0);" onclick="btnDownLoadModel()">[点击下载excel模板]</a>  
	 			<br /><br />
				<label style="font-size:10px;color:blue">上传文件时，请先下载模板文件填写 ；</label><br />
				<label style="font-size:10px;color:blue">文件对象为：97—03版本的excel文件，后缀格式为xls；</label>
				<br /><br /> 
						&npbs;
				 <a  href="javascript:void(0);" class="easyui-linkbutton"   iconcls="icon-tianyi-save"  
						onclick="btnsaveWeaponData()">确定</a>  
						&npbs; 
					<a  href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-tianyi-delete"  
						onclick="btnCancelWeaponDataAction()">　　取消　　</a> 
	 		 </div> 
	 	</div>	
	<!-- 编辑窗体内容 -->
	 <div id="weaponinfowindow" class="easyui-window" title="新增/编辑武器信息"
			data-options="iconCls:'icon-tianyi-edit',modal:true,headerCls:'bbbb'" closed="true"
			style="width: 400px; height: 240px; padding: 10px;" 
	 	collapsible="false" minimizable="false" maximizable="false" resizable="false" shadow="false" 
			 >
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'south',border:false"
					class="weaponwindowsouth">
					<a id="btnsaveWeaponCon" href="javascript:void(0);" class="easyui-linkbutton weaponwindowbtnExit"
						 
						onclick="saveWeaponAction()">保存并继续</a> 
					<a href="javascript:void(0);" class="easyui-linkbutton weaponwindowbtn"
						 
						onclick="saveWeaponActionExit()">　　保　存　　</a> 
				</div>
				<div data-options="region:'center',border:false"
					 class="weaponwindowcenter">
					<table class="weaponwindowtable"> 
						<tr>
							<td style="text-align: right;"><input type="hidden" id="weaponId"><label>武器类型:　</label></td>
							<td><input id="txttype" class="easyui-combobox"  data-options="editable:false,required:true"/><span style="color:red">*</span></td>
						</tr>
						<tr>
							<td style="text-align: right;"><label>武器编号:　</label></td>
							<td><input type="text" class="easyui-validatebox" id="txtnumber" data-options="required:true,validType:['length[1,20]']"><span style="color:red">*</span></td>
						</tr>
						<tr>
							<td style="text-align: right;"><label>子弹数目:　</label></td>
							<td><input type="text" class="easyui-validatebox" id="txtstandard" data-options="validType:['length[0,20]']"></td>
						</tr> 
					</table>
				</div>
			</div>
		</div>
 
</body>
</html>
