<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<!--  
<link href='//cdn.datatables.net/plug-ins/a5734b29083/integration/jqueryui/dataTables.jqueryui.css' type='text/css' />
-->
<script src='<%=basePath%>script/basedata/weapon.js'
	type='text/javascript'></script>
<title>人员分组</title>


</head>

<body class="easyui-layout">
	<div data-options="region:'north',border:false">
		<div class="container-fluid my-nav-bg">
				<ul class="nav navbar-nav">
				<li style="width:1px;"><a href="#">&nbsp;</a></li>
				<li><a href="/duty/view/basedata/police.jsp"
					class="my-nav-btn">人员管理 <img src="asset/css/images/nav-btn-icon.png"
						width="22" height="22"></a></li>
				<li><a href="/duty/view/basedata/vehicle.jsp" class="my-nav-btn">车辆管理
						<img src="asset/css/images/nav-btn-icon.png" width="22" height="22">
				</a></li>
				<li class="active"><a href="/duty/view/basedata/weapon.jsp" class="my-nav-btn">武器管理
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
	
	<div data-options="region:'center',title:'武器列表'"style="padding:10px;"> 
			<div id="tbWeapon" class="btn-toolbar">
  				<div class="btn-group">
  					<a id="btnAddWeapon" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-add"  plain="true" onclick="btnAddWeapon()">创建</a>
                	<a id="btnEditWeapon" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-edit" plain="true" onclick="btnEditWeapon()">修改</a>
                	<a id="btnDelWeapon" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-cancel" plain="true" onclick="btnDelWeapon()">删除</a>
                	<a id="btnDelWeapon" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-cancel" plain="true" onclick="btnDelWeapon()">删除</a>
  				</div>  				
  				<div class="btn-group" style="float:right">
  					<a id="btnSearchWeapon" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-search"  plain="true" onclick="btnSearchWeapon()">查询</a>
                	<a id="btnPrintList" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-print" plain="true" onclick="btnPrintList()">打印</a>
                	<a id="btnExportList" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-export" plain="true" onclick="btnExportList()">导出</a>
               	</div>
  			</div>
  			<div id="my-search-box" class="panel-body" style="display:none">
  			 <form class="form-inline" role="form">
				<div class="form-group">
                    <select class="form-control">
                      <option>姓名</option>
                      <option>警号</option>
                    </select>
                  </div>
                  <div class="form-group">
                    <div class="input-group">
                      <input class="form-control" type="text" placeholder="请输入关键字">
                    </div>
                  </div>
                  <div class="form-group">
                      <div class="form-group">
                        <div class="input-group">
                          <div class="input-group-addon">人员类别</div>
                          <select class="form-control">
                            <option>姓名</option>
                            <option>警号</option>
                          </select>
                        </div>
                      </div>
                  </div>
                  <div class="form-group">
                      <div class="form-group">
                        <div class="input-group">
                          <div class="input-group-addon">人员状态</div>
                          <select class="form-control">
                            <option>在岗</option>
                            <option>离岗</option>
                          </select>
                        </div>
                      </div>
                  </div>
				<button type="submit" class="btn btn-info">查询</button>
				</form>
			</div>	
  			<div id="dtWeapon" >
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
					<h4 class="modal-title" id="myModalLabel">新增武器信息</h4>
				</div>
				<div class="modal-body">

					<!--表单开始-->
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">武器类别：</label>
							<div class="col-sm-4">
								<select id="txttype" class="form-control">
									<option value="1">64制自动步枪</option>
									<option value="2">AK47</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="input2" class="col-sm-2 control-label">武器编号</label>
							<div class="col-sm-4">
								<input id="txtnumber" type="text" class="form-control"
									id="input2" placeholder="">
							</div>
						</div>

						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">所属机构：</label>
							<div class="col-sm-4">
								<select id="txtorg" class="form-control">
									<option value="1">成都市公安局</option>
									<option value="2">成都市公安局青羊分局</option>
								</select>
							</div>
						</div>
					</form>
					<!--表单结束-->

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button id="btnsaveweapon" onclick="saveweaponAction()"
						type="button" class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
