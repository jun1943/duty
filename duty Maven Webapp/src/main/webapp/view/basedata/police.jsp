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
<title>人员分组</title>


</head>

<body class="easyui-layout">
	<div data-options="region:'north',border:false">
		<div class="container-fluid my-nav-bg">
			<ul class="nav navbar-nav">
				<li style="width:1px;"><a href="#">&nbsp;</a></li>
				<li class="active"><a href="/duty/view/basedata/police.jsp"
					class="my-nav-btn">人员管理 <img src="asset/css/images/nav-btn-icon.png"
						width="22" height="22"></a></li>
				<li><a href="/duty/view/basedata/vehicle.jsp" class="my-nav-btn">车辆管理
						<img src="asset/css/images/nav-btn-icon.png" width="22" height="22">
				</a></li>
				<li><a href="/duty/view/basedata/weapon.jsp" class="my-nav-btn">武器管理
						<img src="asset/css/images/nav-btn-icon.png" width="22" height="22">
				</a></li>
				<li><a href="/duty/view/basedata/gpsdevice.jsp" class="my-nav-btn">定位设备
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
	<div data-options="region:'center',title:'人员列表'"style="padding:10px;"> 
			<div id="tbPolice" class="btn-toolbar">
  				<div class="btn-group">
  					<a id="btnAddPolice" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-add"  plain="true" onclick="btnAddPolice()">新增</a>
                	<a id="btnEditPolice" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-edit" plain="true" onclick="btnEditPolice()">修改</a>
                	<a id="btnDelPolice" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-cancel" plain="true" onclick="btnDelPolice()">删除</a> 
  				</div>  				
  				<div class="btn-group" style="float:right; margin-right:20px">
  					<a id="btnSearchPolice" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-search"  plain="true" onclick="btnSearchPolice()">查询</a>
                	<!--a id="btnPrintList" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-print" plain="true" onclick="btnPrintList()">打印</a>
                	<a id="btnExportList" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-export" plain="true" onclick="btnExportList()">导出</a-->
               	</div>
  			</div>
  			<div id="my-search-box" class="panel-body" style="display:none">
  			 <form class="form-inline"> 
                  <div class="form-group">
                      <div class="form-group">
                        <div class="input-group">
                          <div class="input-group-addon">查询范围选择</div>
                          <select id="isSubOrg" class="form-control"> 
                            <option value="0">本级机构</option>
                            <option value="1">下级机构</option>
                          </select>
                        </div>
                      </div>
                  </div>
                  
                  <div class="form-group">
                      <div class="form-group">
                        <div class="input-group">
                          <div class="input-group-addon">姓名</div>
                           <input id="txtsearchName" class="form-control" type="text" placeholder="请输入关键字">
                        </div>
                      </div>
                  </div> 
                  <div class="form-group">
                      <div class="form-group">
                        <div class="input-group">
                          <div class="input-group-addon">人员类别</div>
                          <select id="sltType" class="form-control">
                            <option value="0">全部</option>
                            <option value="1">巡警</option>
                            <option value="2">民警</option>
                          </select>
                        </div>
                      </div>
                  </div>
                  <div class="form-group">
                      <div class="form-group">
                        <div class="input-group">
                          <div class="input-group-addon">人员状态</div>
                          <select class="form-control">
                            <option value="0">全部</option>
                            <option value="1">在岗</option>
                            <option value="2">离岗</option>
                          </select>
                        </div>
                      </div>
                  </div>
                 <a id="btnSearchAction" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-search"  plain="true" onclick="btnSearchAction()">查询</a>
				<!-- button type="submit" class="btn btn-info">查询</button -->
				</form>
			</div>	
  			<div id="dtPolice" >
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
					<h4 class="modal-title" id="myModalLabel">新增警员信息</h4>
				</div>
				<div class="modal-body">

					<!--表单开始-->
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
		                            <option value="0">请选择对讲机组呼号</option>
		                            <option value="1">350M</option>
		                            <option value="2">800M</option>
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
		                            <option value="0">请选择对讲机组呼号</option>
		                            <option value="1">350M</option>
		                            <option value="2">800M</option>
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
					<!--表单结束-->

				</div>
				<div class="modal-footer">
					<button id="btnsaveweapon" onclick="savePoliceAction()"
						type="button" class="btn btn-primary">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>			 
</body>
</html>