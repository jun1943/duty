<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<!--  
<link href='//cdn.datatables.net/plug-ins/a5734b29083/integration/jqueryui/dataTables.jqueryui.css' type='text/css' />
-->  
<title>人员分组</title>

<script type="text/javascript">
	$(function() {
		$('#win').dialog('close');
	});
	function btnAddPolice(){
		$('#win').dialog('open');	
		$('#dtPolice').datagrid({
		url : "police/getPoliceList.do", 
		pagination : true,
		fitColumns : true,
		pageNumber : 1,
		pageSize : 10,
		// title:"s",
		// singleSelect: true,
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			title : 'Id',
			field : 'id',
			align : 'center',
			width : 10,
			hidden : true
		}, {
			title : '机构',
			field : 'orgName',
			align : 'center',
			width : 100
		}, {
			title : '姓名',
			field : 'name',
			align : 'center',
			width : 100
		}, {
			title : '警员类别',
			field : 'typeName',
			align : 'center',
			width : 100
		}, {
			title : '职务',
			field : 'title',
			align : 'center',
			width : 100
		}, {
			title : '手机号',
			field : 'mobile',
			align : 'center',
			width : 100
		}, {
			title : '公安短号',
			field : 'mobileShort',
			align : 'center',
			width : 150
		}, {
			title : '身份证号码',
			field : 'idcardno',
			align : 'center',
			width : 80
		}, {
			title : '警号',
			field : 'number',
			align : 'center',
			width : 80
		}, {
			title : 'GPS名称',
			field : 'gpsName',
			align : 'center',
			width : 200
		} ] ]
	});
	}
</script>
</head>

<body class="easyui-layout">

	<div data-options="region:'center',title:'人员列表'" style="padding:10px;">

		<a id="btnAddPolice" href="javascript:void(0);"
			class="easyui-linkbutton" iconcls="icon-add" plain="true"
			onclick="btnAddPolice()">新增</a>
	</div>
	<div id="win" class="easyui-dialog" title="新增/编辑排班信息"
		data-options="iconCls:'icon-save'"
		style="width: 600px; height: 400px; padding: 10px;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'north'"
				style="padding: 2px; height: 30px;">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-save',plain:true" onclick="saveForm()">保存</a>
			</div>
			<div data-options="region:'west'" style="padding: 5px; width: 430px;">
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
			<div data-options="region:'center',border:false"
				style="text-align: right; margin-left: 5px;
            padding: 5px 0 0;">
				<div id="tt" style="height: auto">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
					<a href="javascript:void(0)" class="easyui-linkbutton"
						data-options="iconCls:'icon-remove',plain:true" onclick="remove()">删除</a>
					 
				</div>

			</div>
			<div data-options="region:'east'" style="padding: 5px; width: 430px;">
				 
	  			<div id="dtPolice" >
	  			</div> 
			</div>
		</div>
	</div>

</body>
</html>