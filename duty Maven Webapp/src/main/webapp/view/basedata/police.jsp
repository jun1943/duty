<%@ page language="java" pageEncoding="utf-8"%>

<%@ include file="/view/lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'police.jsp' starting page</title>

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script src="js/basedata/police.js"></script> 
</head>

<body>
	<!--导航开始-->
	<div class="container-fluid my-nav-bg">
		<ul class="nav navbar-nav">
			<li style="width:1px;"><a href="#">&nbsp;</a></li>
			<li class="active"><a href="/duty/police/list.do"
				class="my-nav-btn">人员管理 <img src="images/nav-btn-icon.png"
					width="22" height="22"></a></li>
			<li><a href="/duty/view/basedata/vehicle.jsp" class="my-nav-btn">车辆管理
					<img src="images/nav-btn-icon.png" width="22" height="22">
			</a></li>
			<li><a href="/duty/view/basedata/weapon.jsp" class="my-nav-btn">武器管理
					<img src="images/nav-btn-icon.png" width="22" height="22">
			</a></li>
			<li><a href="/duty/view/basedata/police.jsp" class="my-nav-btn">卡口管理
					<img src="images/nav-btn-icon.png" width="22" height="22">
			</a></li>
		</ul>
	</div>
	<!--导航结束-->
	<!--操作菜单开始-->
	<div class="my-menu-bg">
		<button id="addnew" type="button" class="btn btn-default my-menu-btn">
			<img src="images/icon/add.png" width="16" height="16"> 新增
		</button>
		<button id="edit" type="button" class="btn btn-default my-menu-btn">
			<img src="images/icon/edit.png" width="16" height="16"> 编辑
		</button>
		<button id="delete" type="button" class="btn btn-default my-menu-btn">
			<img src="images/icon/del.png" width="16" height="16"> 删除
		</button>
	</div>
	<!--操作菜单结束-->
	<div class="my-line-5"></div>
	<!--列表开始-->
	<div class="container-fluid">
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="row">
					<div class="col-md-8">
						<img src="images/icon/user.png" width="16" height="16"> <strong>人员列表</strong>
					</div>
					<div class="col-md-4 my-title-btn" style="text-align:right">
						<ul>
							<li class="my-hand"><img src="images/icon/printer.png"
								width="16" height="16"> 打印</li>
							<li class="my-hand"><img src="images/icon/dark.png"
								width="16" height="16"> 打印设置</li>
							<li class="my-hand"><img src="images/icon/out.png"
								width="16" height="16"> 导出</li>
							<li class="my-hand" id="my-search"><img
								src="images/icon/zoom.png" width="16" height="16"> 查询</li>
							<ul>
					</div>
				</div>
			</div>
			<div id="my-search-box" class="panel-body" style="display:none">
				<form class="form-inline" role="form"  id="searchForm" name="searchForm" action=police/list.do	method="post">
					<div class="input-group">
						<div class="input-group-addon">查询范围选择:</div>
						<select id="inSubOrg" name="inSubOrg" class="form-control"
								value="${inSubOrg}">
							<option value=0>本级机构</option>
							<option value=1>本级及下级机构</option>
						</select>
					</div>
					
					<div class="form-group">
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon">姓名</div>
								<div class="input-group">
							<input class="form-control" id="number" name="number"  type="text" placeholder="请输入关键字" value="${name}">
						</div>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon">警号</div>
								<div class="input-group">
							<input class="form-control" id="number" name="number"  type="text" placeholder="请输入关键字" value="${number}">
						</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon">人员类别</div>
								<select id="typeid" name="typeid" class="form-control" value="${typeid}">
									<option>全部</option>
									<option value="0">民警</option>
									<option value="1">巡警</option>
									<option value="2">领导</option>
								</select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon">勤务类别</div>
								<select class="form-control">
									<option>全部</option>
									<option value="0">接警处</option>
									<option value="1">领导值班</option>
								</select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon">组呼号</div>
								<select class="form-control">
									<option>全部</option>
									<option>350M</option>
									<option>800M</option>
								</select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon">人员状态</div>
								<select class="form-control">
									<option>全部</option>
									<option>在岗</option>
									<option>离岗</option>
								</select>
							</div>
						</div>
					</div>
					<button type="submit" class="btn btn-info">
						<img src="images/icon/zoom.png" width="16" height="16"> 查询
					</button>
				</form>
			</div>

		</div>
	</div>
	<!-- <div class="my-page-fixed"></div-->
	<!--列表结束-->

	<input type="hidden" id="pageCount" name="pageCount"
		value="${page.pageCount}" />
	<input type="hidden" id="pageNumber" name="pageNumber"
		value="${page.pageNo}" />
	<input type="hidden" id="totalCount" name="totalCount"
		value="${page.total}" />
		<input
			type="hidden" id="orgId" name="orgId" value="${orgId}" /> <input
			type="hidden" id="orgCode" name="orgCode" value="${orgCode}" /> <input
			type="hidden" id="orgPath" name="orgPath" value="${orgPath}" />

	<!-- Data Table -->
	<div class='box-content box-no-padding'>
		<div class='responsive-table'>
			<div class='scrollable-area'>
				<table class="table table-bordered table-hover table-striped "
					style="margin-bottom:0;">
					<!-- table class="table"-->
					<thead class="my-table-title">
						<th width="20"><input type="checkbox" id="my-check-all"
							value="1"></th>
						<th>机构</th>
						<th>姓名</th>
						<th>身份证号</th>
						<th>警号</th>
						<th>人员类别</th>
						<th>职位</th>
						<th>勤务类别</th>
						<th>手机号</th>
						<th>公安短号</th>
						<th>GPS设备ID</th>
						<th>GPS名称</th>
					</thead>

					<tbody id="tbpoliceList">
						<c:forEach items="${page.rows}" var="policevm">
							<tr id="tr_${policevm.id}" ondblclick="dbclickList(${policevm})" onclick="clickList(${policevm.id})">
								<td><input type="checkbox" name="checkedIds"
									id="ck_${policevm.id}" class="my-check" value="${policevm.id}">
								</td>
								<td id="td1_${policevm.id}" class="table-font">${policevm.orgName}</td>
								<td id="td2_${policevm.id}" class="table-font">${policevm.name}</td>
								<td id="td3_${policevm.id}" class="table-font">${policevm.idcardno}</td>
								<td id="td4_${policevm.id}" class="table-font">${policevm.number}</td>
								<td id="td5_${policevm.id}" class="table-font">${policevm.typeName}</td>
								<td id="td6_${policevm.id}" class="table-font">${policevm.intercomGroup}</td>
								<td id="td7_${policevm.id}" class="table-font">${policevm.title}</td>
								<td id="td8_${policevm.id}" class="table-font">${policevm.mobile}</td>
								<td id="td9_${policevm.id}" class="table-font">${policevm.mobileShort}</td>
								<td id="td10_${policevm.id}" class="table-font">${policevm.gpsId}</td>
								<td id="td11_${policevm.id}" class="table-font">${policevm.gpsName}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<!--  Page show -->
		<div class="ui-pagelist">
			<div style="float: right;" id="pager"></div>
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
							<label for="name" class="col-sm-2 control-label">姓名：</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="name" placeholder="">
								<input type="hidden" id="policeid">
							</div>
						</div>

						<div class="form-group">
							<label for="policetype" class="col-sm-2 control-label">人员类别：</label>
							<div class="col-sm-4">
								<select id="policetype" class="form-control">
									<option>警员</option>
									<option>职工</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="idcardno" class="col-sm-2 control-label">身份证号码</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="idcardno"
									placeholder="">
							</div>
						</div>

						<div class="form-group">
							<label for="code" class="col-sm-2 control-label">警号：</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="code" placeholder="">
							</div>
						</div>

						<div class="form-group">
							<label for="duty" class="col-sm-2 control-label">职务：</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="duty" placeholder="">
							</div>
						</div>

						<div class="form-group">
							<label for="mobilephone" class="col-sm-2 control-label">手机号码：</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="mobilephone"
									placeholder="">
							</div>
						</div>

						<div class="form-group">
							<label for="shortno" class="col-sm-2 control-label">公安短号：</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="shortno"
									placeholder="">
							</div>
						</div>
						<div class="form-group">
							<label for="groupno" class="col-sm-2 control-label">组呼号：</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="groupno"
									placeholder="">
							</div>
						</div>

						<div class="form-group">
							<label for="personno" class="col-sm-2 control-label">个呼号：</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="personno"
									placeholder="">
							</div>
						</div>
						<div class="form-group">
							<label for="dutytype" class="col-sm-2 control-label">勤务类型：</label>
							<div class="col-sm-4">
								<select id="dutytype" class="form-control">
									<option>接处警</option>
									<option>办公室值班</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="gpsid" class="col-sm-2 control-label">GPS_ID：</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="gpsid"
									placeholder="">
							</div>
						</div>
						<div class="form-group">
							<label for="gpsdes" class="col-sm-2 control-label">GPS备注：</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="gpsdes"
									placeholder="">
							</div>
						</div>
						<div class="form-group">
							<label for="icons" class="col-sm-2 control-label">图标：</label>
							<div class="col-sm-10"></div>
						</div>
					</form>
					<!--表单结束-->

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button id="btnsaveweapon" onclick="savepoliceAction()"
						type="button" class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>
	<!--新增结束-->

	<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog"
		aria-lableledby="confirmModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="confirmModalLabel">消息确认？</h4>
				</div>
				<div class="modal-body">
					<!--表单开始-->
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label class="col-sm-6 control-label">是否确认删除？</label>
						</div>
					</form>
					<!--表单结束-->

				</div>
				<div class="modal-footer">
					<button onclick="confirmAction()" type="button"
						class="btn btn-primary">确认</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>