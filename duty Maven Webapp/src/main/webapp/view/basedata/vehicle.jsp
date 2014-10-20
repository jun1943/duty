<%@ page language="java" pageEncoding="utf-8"%>

<%@ include file="/view/lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'vehicle.jsp' starting page</title>

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script>
	$(function() {
		$('#my-search').click(function() {
			$('#my-search-box').toggle();
		});
		$('#my-check-all').click(function() {
			$(".my-check").each(function() {
				$(this).attr('checked', 'checked');
			});
		});
		$('#addnew').click(function() {
			$('#myModal').modal('show');
		});
		
		
	});
</script>
</head>

<body>
	<!--导航开始-->
 
	<div class="container-fluid my-nav-bg">
		<ul class="nav navbar-nav">
			<li style="width:1px;"><a href="#">&nbsp;</a></li>
			<li class="active"><a href="#" class="my-nav-btn">人员管理 <img
					src="images/nav-btn-icon.png" width="22" height="22"></a></li>
			<li><a href="#" class="my-nav-btn">车辆管理 <img
					src="images/nav-btn-icon.png" width="22" height="22"></a></li>
			<li><a href="#" class="my-nav-btn">武器管理 <img
					src="images/nav-btn-icon.png" width="22" height="22"></a></li>
			<li><a href="#" class="my-nav-btn">卡口管理 <img
					src="images/nav-btn-icon.png" width="22" height="22"></a></li>
		</ul>
	</div>
	<!--导航结束-->
	<!--操作菜单开始-->
	<div class="my-menu-bg">
		<button id="addnew" type="button" class="btn btn-default my-menu-btn">
			<img src="images/icon/add.png" width="16" height="16"> 新增
		</button>
		<button type="button" class="btn btn-default my-menu-btn">
			<img src="images/icon/edit.png" width="16" height="16"> 编辑
		</button>
		<button type="button" class="btn btn-default my-menu-btn">
			<img src="images/icon/del.png" width="16" height="16"> 删除
		</button>
	</div>
	<!--操作菜单结束-->
	<div class="my-line-5"></div>
	<form class='form' style='margin-bottom: 0;' accept-charset="UTF-8" id="searchForm" name="searchForm"
		action=vehicle/list.do	method="post"> 
		<!--列表开始-->
		<div class="container-fluid">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="row">
						<div class="col-md-8">
							<img src="images/icon/user.png" width="16" height="16"> <strong>车辆列表</strong>
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
							</ul>
						</div>
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
			</div>
		</div>
		<!-- <div class="my-page-fixed"></div-->
		<!--列表结束-->

		<input type="hidden" id="pageCount" name="pageCount"
			value="${page.pageCount}" /> <input type="hidden" id="pageNumber"
			name="pageNumber" value="${page.pageNo}" /> <input type="hidden"
			id="totalCount" name="totalCount" value="${page.total}" /> <input
			type="hidden" id="orgId" name="orgId" value="${orgId}" /> <input
			type="hidden" id="orgCode" name="orgCode" value="${orgCode}" /> <input
			type="hidden" id="orgPath" name="orgPath" value="${orgPath}" />
		<!-- Data Table -->
		<div class='box-content box-no-padding'>
			<div class='responsive-table' style="height:650px">
				<div class='scrollable-area'>
					<table class="table table-bordered table-hover table-striped "
						style="margin-bottom:0;">
						<!-- table class="table"-->
						<thead class="my-table-title">
							<th>选择</th>
							<th>机构</th>
							<th>类型</th>
							<th>车牌号</th>
							<th>用途</th>
						</thead>

						<tbody>
							<c:forEach items="${page.rows}" var="vehicleVM">
								<tr>
									<td><input type="checkbox" name="checkedIds"
										id="checkedIds" class="my-check" value="${vehicle.id}">
									</td>
									<td class="table-font">${vehicleVM.orgName}</td>
									<td class="table-font">${vehicleVM.typeName}</td>
									<td class="table-font"><a
										href="javascript:void(0) onclick="vehicleDlg()">${vehicleVM.number}</a>
									</td>
									<td class="table-font">${vehicleVM.purpose}</td>
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

	</form>


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
