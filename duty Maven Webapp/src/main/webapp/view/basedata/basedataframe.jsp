<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>基础数据管理</title>
    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script src='<%=basePath%>script/basedata/basedataframe.js' 	type='text/javascript'></script>
  </head>
  
  <body class="easyui-layout">
   		<div data-options="region:'west',split:true,title:'组织机构导航树'" style="width:16%;padding:10px;">
			<!-- div>
				<input type="text">
			</div> -->
		
			<ul id="orgtree" class="easyui-tree"> 
			</ul>
		</div>  
		<div data-options="region:'center'"style="padding:10px;">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'north',border:false" style="padding: 2px; height: 55px;overflow:hidden">
					<div class="container-fluid my-nav-bg">
						<ul class="nav navbar-nav">
							<li style="width:1px;"><a href="#">&nbsp;</a></li>
							<li id="li_police"><a href="javascript:void(0);" onclick="onPoliceManage()"
								class="my-nav-btn">人员管理 <img src="asset/css/images/nav-btn-icon.png"
									width="22" height="22"></a></li>
							<li id="li_vehicle"><a href="javascript:void(0);" onclick="onVehicleManage()" class="my-nav-btn">车辆管理
									<img src="asset/css/images/nav-btn-icon.png" width="22" height="22">
							</a></li>
							<li id="li_weapon"><a href="javascript:void(0);" onclick="onWeaponManage()"  class="my-nav-btn">武器管理
									<img src="asset/css/images/nav-btn-icon.png" width="22" height="22">
							</a></li>
							<li id="li_gpsdevice"><a href="javascript:void(0);"  onclick="onGpsdeviceManage()"  class="my-nav-btn">定位设备
									<img src="asset/css/images/nav-btn-icon.png" width="22" height="22">
							</a></li>
							<li id="li_icons"><a href="javascript:void(0);" onclick="onIconsManage()"  class="my-nav-btn">图标管理
									<img src="asset/css/images/nav-btn-icon.png" width="22" height="22">
							</a></li>
						</ul>
					</div>
				</div>
				<div data-options="region:'center'"style="padding:10px;">
					<iframe id="ifmWorkSpace" name="ifmWorkSpace"  scrolling='yes'  frameborder='0'  style="width:100%;height:100%"></iframe>
				</div>
			</div>
		</div>
  </body>
</html>
