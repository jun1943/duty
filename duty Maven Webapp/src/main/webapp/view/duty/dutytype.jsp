<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<!--  
<link href='//cdn.datatables.net/plug-ins/a5734b29083/integration/jqueryui/dataTables.jqueryui.css' type='text/css' />
-->
<script src='<%=basePath%>script/duty/dutytype.js' 	type='text/javascript'></script>
 
<title>备勤类型</title>

</head>
	
<body oncontextmenu=self.event.returnValue=false >
	<div id="tbDutyType" class="btn-toolbar">
				<div class="btn-group">
					<a href="javascript:void(0);" iconcls="icon-tianyi-add"  
						class="easyui-linkbutton icon-camera-retro" 
						onclick="addRootDutyType()">新建根节点</a> 
					<a href="javascript:void(0);" class="easyui-linkbutton"
						 iconcls="icon-tianyi-add"   onclick="addChildDutyType()">新建子节点</a>
					<a href="javascript:void(0);" class="easyui-linkbutton"
						iconcls="icon-tianyi-edit"  onclick="editDutyType()">编辑</a>
					<a  href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-tianyi-unlock" 
						onclick="useDutyType()">启用</a>
					<a  href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-tianyi-lock" 
						onclick="unUseDutyType()">停用</a>
					<a  href="javascript:void(0);"	class="easyui-linkbutton" iconcls="icon-tianyi-delete" 
						onclick="delDutyType()">删除</a>
				</div>
			</div>
			<div id="dtDutyType"></div>

	<div id="winDutyType" class="easyui-window" title="勤务类型" 
	style="height:330px;width:360px;padding:4px;"
        data-options="iconCls:'icon-tianyi-save',modal:true" closed="true"  onClose="onWinDutyTypeClose"
        collapsible="false" minimizable="false" maximizable="false" resizable="false" shadow="false">

			<input type="hidden" id="txtDutyTypeId"></input>
			<input type="hidden" id="txtDutyTypeParentId"></input>
			<input type="hidden" id="txtDutyTypeIsUsed"></input>
			<input type="hidden" id="txtDutyTypeIsLeaf"></input>
			<table class="dtypewindowtable">
				<tr >
					<td style="text-align:right"><lable>上级名称:</lable></td>
					<td ><input id="txtDutyTypeParentName" type="text" disabled="disabled"
						class="easyui-validatebox prop_val"></input></td>
				</tr>
				<tr >
					<td style="text-align:right"><lable>上级全路径:</lable></td>
					<td><input id="txtDutyTypeParentFullPath" type="text" disabled="disabled"
						class="easyui-validatebox prop_val"></input></td>
				</tr>
				<tr>
					<td style="text-align:right"><lable>名称:</lable></td>
					<td><input id="txtDutyTypeName" type="text"
						class="easyui-validatebox prop_val" ></input></td>
				</tr>
				<tr>
					<td style="text-align:right"><lable>人数上限:</lable></td>
					<td>
						<input id="txtMaxPolice" class="easyui-validatebox"  data-options="required:false"   style="width:30%" disabled="disabled"></input>
						<label>人</label>
						<label><input id=chkUnMax 	type="checkbox"  onclick="changeUnMax()" ></input>不限</label> 
					</td>
				</tr>
				<tr>
					<td style="text-align:right"><lable>统计显示:</lable></td>
					<td>
						<label><input id="radioDisplayType1" name="displayType"	type="radio" value="0" ></input>人数</label> 
						<label><input 	id="radioDisplayType2" name="displayType"  type="radio" value="1"	></input>名称</label>
					</td>
				</tr>
				<tr>
					<td style="text-align:right"><lable>属性:</lable></td>
					<td>
						<input id="cmbProperty"  class="easyui-combobox prop_val"  />
					</td>
				</tr>
				<tr>
					<td style="text-align:right"><lable>关联任务:</lable></td>
					<td>
						<input id="cmbTaskType"  class="easyui-combobox prop_val"  />
					</td>
				</tr>
				
				<tr>
					<td style="text-align:right"><lable>着装方式:</lable></td>
					<td>
						<label><input id="radioAttireType1" name="attireType"	 type="radio"  value="0" ></input>制服</label> 
						<label><input 	id="radioAttireType2" name="attireType"  type="radio" value="1"	></input>便衣</label>
					</td>
				</tr>
				<tr>
					<td style="text-align:right"><lable>武装:</lable></td>
					<td>
						<label><input id="radioArmamentType1" name="armamentType"	 type="radio"  value="0" ></input>非武装</label> 
						<label><input 	id="radioArmamentType2" name="armamentType"  type="radio" value="1"	></input>武装</label>
					</td>
				</tr>
			</table>
			<div id="tbGroup" class="btn-toolbar">
				<div class="dtypewindowbtngroup">
					<a  href="javascript:void(0);" style="float:right;margin-right:15px; margin-bottom:5px"
						class="easyui-linkbutton" 
						onclick="saveDutyType()">　　保 　存　　</a>  
				</div>
			</div>
		</div>

</body>

</html>
