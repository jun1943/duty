<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib2.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<!--  
<link href='//cdn.datatables.net/plug-ins/a5734b29083/integration/jqueryui/dataTables.jqueryui.css' type='text/css' />
-->
<script src='<%=basePath%>js/basedata/policegroup.js'
	type='text/javascript'></script>
<title>人员分组</title>


</head>

<body class="fuelux">


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

	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-5">
				<div class="my-menu-bg">
					<button id="addnew" type="button"
						class="btn btn-default my-menu-btn">
						<img src="images/icon/add.png" width="16" height="16"> 新增
					</button>
					<button type="button" class="btn btn-default my-menu-btn">
						<img src="images/icon/edit.png" width="16" height="16"> 编辑
					</button>
					<button type="button" class="btn btn-default my-menu-btn">
						<img src="images/icon/del.png" width="16" height="16"> 删除
					</button>
				</div>

				<div class="repeater" id="dtGroup">
					<div class="repeater-viewport">
						<div class="repeater-canvas"></div>
						<div class="loader repeater-loader"></div>

					</div>
					<div class="repeater-footer">
						<div class="repeater-footer-left">
							<div class="repeater-itemization">
								<span><span class="repeater-start"></span> - <span
									class="repeater-end"></span> of <span class="repeater-count"></span>
									items</span>
								<div class="btn-group selectlist" data-resize="auto">
									<button type="button" class="btn btn-default dropdown-toggle"
										data-toggle="dropdown">
										<span class="selected-label">&nbsp;</span> <span class="caret"></span>
										<span class="sr-only">Toggle Dropdown</span>
									</button>
									<ul class="dropdown-menu" role="menu">
										<li data-value="5"><a href="#">5</a></li>
										<li data-value="10" data-selected="true"><a href="#">10</a></li>
										<li data-value="20"><a href="#">20</a></li>
										<li data-value="50" data-foo="bar" data-fizz="buzz"><a
											href="#">50</a></li>
										<li data-value="100"><a href="#">100</a></li>
									</ul>
									<input class="hidden hidden-field" name="itemsPerPage"
										readonly="readonly" aria-hidden="true" type="text" />
								</div>
								<span>Per Page</span>
							</div>
						</div>
						      <div class="repeater-footer-right">
        <div class="repeater-pagination">
          <button type="button" class="btn btn-default btn-sm repeater-prev">
            <span class="glyphicon glyphicon-chevron-left"></span>
            <span class="sr-only">Previous Page</span>
          </button>
          <label class="page-label" id="myPageLabel">Page</label>
          <div class="repeater-primaryPaging active">
            <div class="input-group input-append dropdown combobox">
              <input type="text" class="form-control" aria-labelledby="myPageLabel">
              <div class="input-group-btn">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                  <span class="caret"></span>
                  <span class="sr-only">Toggle Dropdown</span>
                </button>
                <ul class="dropdown-menu dropdown-menu-right"></ul>
              </div>
            </div>
          </div>
          <input type="text" class="form-control repeater-secondaryPaging" aria-labelledby="myPageLabel">
          <span>of <span class="repeater-pages"></span></span>
          <button type="button" class="btn btn-default btn-sm repeater-next">
            <span class="glyphicon glyphicon-chevron-right"></span>
            <span class="sr-only">Next Page</span>
          </button>
        </div>
      </div>
					</div>
				</div>

			</div>
		</div>
	</div>

</body>
</html>
