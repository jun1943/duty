<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<!--  
<link href='//cdn.datatables.net/plug-ins/a5734b29083/integration/jqueryui/dataTables.jqueryui.css' type='text/css' />
-->
<script src='<%=basePath%>script/basedata/icons.js'
	type='text/javascript'></script>
<title>图标管理</title>


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
				<li class="active"><a href="/duty/view/basedata/icons.jsp" class="my-nav-btn">图标管理
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
			<div id="tbIcons" class="btn-toolbar">
  				<div class="btn-group">
  					<a id="btnAddIcons" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-add"  plain="true" onclick="btnAddIcons()">新增</a>
                	<a id="btnEditIcons" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-edit" plain="true" onclick="btnEditIcons()">修改</a>
                	<a id="btnDelIcons" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-cancel" plain="true" onclick="btnDelIcons()">删除</a> 
  				</div>  				
  				<div class="btn-group" style="float:right; margin-right:20px">
  					<a id="btnSearchIcons" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-search"  plain="true" onclick="btnSearchIcons()">查询</a>
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
                          <div class="input-group-addon">名称</div>
                           <input id="txtsearchName" class="form-control" type="text" placeholder="请输入关键字">
                        </div>
                      </div>
                  </div> 
                  <div class="form-group">
                      <div class="form-group">
                        <div class="input-group">
                          <div class="input-group-addon">图片类别</div>
                          <select id="sltType" class="form-control">
                            <option value="0">全部</option>
                            <option value="1">警员</option>
                            <option value="2">车辆</option>
                            <option value="3">武器</option>
                            <option value="4">定位设备</option>
                          </select>
                        </div>
                      </div>
                  </div> 
                 <a id="btnSearchAction" href="javascript:void(0);" class="easyui-linkbutton"  iconcls="icon-search"  plain="true" onclick="btnSearchAction()">查询</a>
				<!-- button type="submit" class="btn btn-info">查询</button -->
				</form>
			</div>	
  			<div id="dtIcons" >
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
					<h4 class="modal-title" id="myModalLabel">新增图标信息</h4>
				</div>
				<div class="modal-body">

					<!--表单开始-->
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label for="txttype" class="col-sm-3 control-label">图标类型:</label>
							<div class="col-sm-4">
								<select id="txttype" class="form-control"> 
                            <option value="0">请选择图标类型</option>
                            <option value="1">警员</option>
                            <option value="2">车辆</option>
                            <option value="3">武器</option>
                            <option value="4">定位设备</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="txtname" class="col-sm-3 control-label">名称:</label>
							<div class="col-sm-4">
								<input type="hidden" id="iconsId" >
								<input id="txtname" type="text" class="form-control"  placeholder="">
							</div>
						</div>  
						           
		                <div class="form-group">
		                   <label for="txticons" class="col-sm-3 control-label">选择图标:</label>
		                    <div class="col-sm-4">
		                      <input type =file id ="iconfile" style="display:none" />
		                      <input id="txticons" type="text" class="form-control" placeholder="请选择本地图标.">
		                    </div> 
		                    <div class="col-sm-4">
		                      <button id="btnfindIcon" onclick="selectIconsAction()" type="button" class="btn btn-primary">选择</button>
		                    </div>
	                  	</div>     
					</form>
					<!--表单结束-->

				</div>
				<div class="modal-footer">
					<button id="btnsaveicons" onclick="saveIconsAction()"
						type="button" class="btn btn-primary">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>	
  </body>
</html>
