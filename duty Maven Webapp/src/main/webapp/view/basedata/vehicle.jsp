<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<!--  
<link href='//cdn.datatables.net/plug-ins/a5734b29083/integration/jqueryui/dataTables.jqueryui.css' type='text/css' />
-->
<script src='<%=basePath%>script/basedata/vehicle.js'
	type='text/javascript'></script>
<title>车辆管理</title>


</head>

<body class="easyui-layout">
	<div data-options="region:'north',border:false">
		<div class="container-fluid my-nav-bg">
				<ul class="nav navbar-nav">
				<li style="width:1px;"><a href="#">&nbsp;</a></li>
				<li><a href="/duty/view/basedata/police.jsp"
					class="my-nav-btn">人员管理 <img src="asset/css/images/nav-btn-icon.png"
						width="22" height="22"></a></li>
				<li class="active"><a href="/duty/view/basedata/vehicle.jsp" class="my-nav-btn">车辆管理
						<img src="asset/css/images/nav-btn-icon.png" width="22" height="22">
				</a></li>
				<li><a href="/duty/view/basedata/weapon.jsp" class="my-nav-btn">武器管理
						<img src="asset/css/images/nav-btn-icon.png" width="22" height="22">
				</a></li>
				<li><a href="/duty/view/basedata/police.jsp" class="my-nav-btn">定位设备
						<img src="asset/css/images/nav-btn-icon.png" width="22" height="22">
				</a></li>
			</ul>
		</div>
	</div>
	
	<div data-options="region:'west',split:true,title:'组织机构导航树'" style="width:150px;padding:10px;">
		<div>
			<input type="text">
		</div>
		<ul id="tt" class="easyui-tree">    
		    <li>    
		        <span>Folder</span>    
		        <ul>    
		            <li>    
		                <span>Sub Folder 1</span>    
		                <ul>    
		                    <li>    
		                        <span><a href="#">File 11</a></span>    
		                    </li>    
		                    <li>    
		                        <span>File 12</span>    
		                    </li>    
		                    <li>    
		                        <span>File 13</span>    
		                    </li>    
		                </ul>    
		            </li>    
		            <li>    
		                <span>File 2</span>    
		            </li>    
		            <li>    
		                <span>File 3</span>    
		            </li>    
		        </ul>    
		    </li>    
		    <li>    
		        <span>File21</span>    
		    </li>    
		</ul>
	</div>  
	
	<div data-options="region:'center',title:'车辆列表'"style="padding:10px;"> 
			<div id="tbVehicle" class="btn-toolbar">
  				<div class="btn-group">
  					<a id="btnAddVehicle" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-add"  plain="true" onclick="btnAddVehicle()">创建</a>
                	<a id="btnEditVehicle" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-edit" plain="true" onclick="btnEditVehicle()">修改</a>
                	<a id="btnDelVehicle" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-cancel" plain="true" onclick="btnDelVehicle()">删除</a>
                	<a id="btnDelVehicle" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-cancel" plain="true" onclick="btnDelVehicle()">删除</a>
  				</div>  				
  				<div class="btn-group" style="float:right">
  					<a id="btnSearchVehicle" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-search"  plain="true" onclick="btnSearchVehicle()">查询</a>
                	<a id="btnPrintList" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-print" plain="true" onclick="btnPrintList()">打印</a>
                	<a id="btnExportList" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-export" plain="true" onclick="btnExportList()">导出</a>
               	</div>
  			</div>
  			<div id="my-search-box" class="panel-body" style="display:none">
					<div class="input-group">
						<div class="input-group-addon">查询范围选择:</div>
						<select id="inSubOrg" name="inSubOrg" class="form-control"
								value="${inSubOrg}">
							<option value=0>本级机构</option>
							<option value=1>本级及下级机构</option>
						</select>
					</div>
					<div class="input-group">
						<div class="input-group-addon">车牌号:</div>
						<input id="number" name="number" class="form-control" type="text"
								placeholder="请输入车牌号" value="${number}">
					</div>

					<button type="submit" class="btn btn-info">
						<img src="images/icon/zoom.png" width="16" height="16"> 查询
					</button>
			</div>
  			<div id="dtVehicle" >
  			</div> 
	</div>   
  
	<!--新增开始-->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">新增人员</h4>
				</div>
				<div class="modal-body">

					<!--表单开始-->
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">人员类别：</label>
							<div class="col-sm-10">
								<select class="form-control">
									<option>警员</option>
									<option>职工</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="input2" class="col-sm-2 control-label">身份证号码</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="input2"
									placeholder="">
							</div>
						</div>

						<div class="form-group">
							<label for="input2" class="col-sm-2 control-label">姓名：</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="input2"
									placeholder="">
							</div>
							<label for="input2" class="col-sm-2 control-label">职务：</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="input2"
									placeholder="">
							</div>
						</div>

						<div class="form-group">
							<label for="input2" class="col-sm-2 control-label">手机号码：</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="input2"
									placeholder="">
							</div>
							<label for="input3" class="col-sm-2 control-label">公安短号：</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="input3"
									placeholder="">
							</div>
						</div>
					</form>
					<!--表单结束-->

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary">确定并新增</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
