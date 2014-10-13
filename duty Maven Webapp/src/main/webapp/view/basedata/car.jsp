<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/view/lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'weapon.jsp' starting page</title>

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	 --> 
	<script src="js/basedata/car.js"></script>
</head>

<body>
	<!--导航开始-->
          <div class="container-fluid my-nav-bg">
              <ul class="nav navbar-nav">
                <li style="width:1px;"><a href="#">&nbsp;</a></li>
                <li><a href="/duty/view/basedata/police.jsp" class="my-nav-btn">人员管理 <img src="images/nav-btn-icon.png" width="22" height="22"></a></li>
                <li class="active"><a href="/duty/view/basedata/car.jsp" class="my-nav-btn">车辆管理 <img src="images/nav-btn-icon.png" width="22" height="22"></a></li>
                <li><a href="/duty/view/basedata/weapon.jsp" class="my-nav-btn">武器管理 <img src="images/nav-btn-icon.png" width="22" height="22"></a></li>
                <li><a href="/duty/view/basedata/weapon4.jsp" class="my-nav-btn">卡口管理 <img src="images/nav-btn-icon.png" width="22" height="22"></a></li>
              <ul>
          </div>
        <!--导航结束-->
        <!--操作菜单开始-->
          <div class="my-menu-bg">
            <button id="addnew" type="button" class="btn btn-default my-menu-btn"><img src="images/icon/add.png" width="16" height="16"> 新增</button>
            <button type="button" class="btn btn-default my-menu-btn"><img src="images/icon/edit.png" width="16" height="16"> 编辑</button>
            <button type="button" class="btn btn-default my-menu-btn"><img src="images/icon/del.png" width="16" height="16"> 删除</button>
          </div>
        <!--操作菜单结束-->
        <div class="my-line-5"></div>
        <!--列表开始-->
          <div class="container-fluid">
          	<div class="panel panel-default">
              <div class="panel-heading">
              	<div class="row">
                  <div class="col-md-8"><img src="images/icon/user.png" width="16" height="16"> <strong>车辆列表</strong></div>
                  <div class="col-md-4 my-title-btn" style="text-align:right">
                  	<ul>
                      <li class="my-hand"><img src="images/icon/printer.png" width="16" height="16"> 打印</li>
                      <li class="my-hand"><img src="images/icon/dark.png" width="16" height="16"> 打印设置</li>
                      <li class="my-hand"><img src="images/icon/out.png" width="16" height="16"> 导出</li>
                      <li class="my-hand" id="my-search"><img src="images/icon/zoom.png" width="16" height="16"> 查询</li>
                    <ul>
                  </div>
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
                  <button type="submit" class="btn btn-info"><img src="images/icon/zoom.png" width="16" height="16"> 查询</button>
                </form>
              </div>
              <table style="text-aligh:center" class="table">
              	<thead class="my-table-title">
              	  <tr>
                    <td width="20"><input type="checkbox" id="my-check-all" value="1"></td>
                    <td>机构</td>
                    <td>车辆类型</td>
                    <td>车牌号码</td> 
                    <td>车上装备</td> 
                    <td>车辆用途</td> 
                    <td>车辆品牌</td> 
                    <td>GPS设备ID</td> 
                    <td>对讲机组呼号</td> 
                    <td>对讲机个呼号</td> 
                  </tr>
                </thead>
                <tbody id="tbcarList"> 
                 <tr>
                  	<td><input type="checkbox" class="my-check" value="2"></td>
                  	<td>成都市公安局青羊区分局</td>
                    <td>制式警车</td>
                    <td>川A·5849警</td> 
                    <td> </td> 
                    <td>巡逻用车</td> 
                    <td>大众捷达</td> 
                    <td>30888020</td> 
                    <td>96.4</td> 
                    <td>95.5</td> 
                  </tr>
                  <tr>
                  	<td><input type="checkbox" class="my-check" value="3"></td>
                  	<td>成都市公安局青羊区分局</td>
                    <td>制式警车</td>
                    <td>川A·5849警</td> 
                    <td> </td> 
                    <td>巡逻用车</td> 
                    <td>大众捷达</td> 
                    <td>30888020</td> 
                    <td>96.4</td> 
                    <td>95.5</td> 
                  </tr>
                  <tr>
                  	<td><input type="checkbox" class="my-check" value="4"></td>
                  	<td>成都市公安局青羊区分局</td>
                    <td>制式警车</td>
                    <td>川A·5849警</td> 
                    <td> </td> 
                    <td>巡逻用车</td> 
                    <td>大众捷达</td> 
                    <td>30888020</td> 
                    <td>96.4</td> 
                    <td>95.5</td> 
                  </tr>
                  <tr>
                  	<td><input type="checkbox" class="my-check" value="5"></td>
                  	<td>成都市公安局青羊区分局</td>
                    <td>制式警车</td>
                    <td>川A·5849警</td> 
                    <td> </td> 
                    <td>巡逻用车</td> 
                    <td>大众捷达</td> 
                    <td>30888020</td> 
                    <td>96.4</td> 
                    <td>95.5</td> 
                  </tr>
                  <tr>
                  	<td><input type="checkbox" class="my-check" value="6"></td>
                  	<td>成都市公安局青羊区分局</td>
                    <td>制式警车</td>
                    <td>川A·5849警</td> 
                    <td> </td> 
                    <td>巡逻用车</td> 
                    <td>大众捷达</td> 
                    <td>30888020</td> 
                    <td>96.4</td> 
                    <td>95.5</td> 
                  </tr>
                  <tr>
                  	<td><input type="checkbox" class="my-check" value="6"></td>
                  	<td>成都市公安局青羊区分局</td>
                    <td>制式警车</td>
                    <td>川A·5849警</td> 
                    <td> </td> 
                    <td>巡逻用车</td> 
                    <td>大众捷达</td> 
                    <td>30888020</td> 
                    <td>96.4</td> 
                    <td>95.5</td> 
                  </tr>
                  <tr>
                  	<td><input type="checkbox" class="my-check" value="6"></td>
                  	<td>成都市公安局青羊区分局</td>
                    <td>制式警车</td>
                    <td>川A·5849警</td> 
                    <td> </td> 
                    <td>巡逻用车</td> 
                    <td>大众捷达</td> 
                    <td>30888020</td> 
                    <td>96.4</td> 
                    <td>95.5</td> 
                  </tr>
                  <tr>
                  	<td><input type="checkbox" class="my-check" value="6"></td>
                  	<td>成都市公安局青羊区分局</td>
                    <td>制式警车</td>
                    <td>川A·5849警</td> 
                    <td> </td> 
                    <td>巡逻用车</td> 
                    <td>大众捷达</td> 
                    <td>30888020</td> 
                    <td>96.4</td> 
                    <td>95.5</td> 
                  </tr>
                  <tr>
                  	<td><input type="checkbox" class="my-check" value="6"></td>
                  	<td>成都市公安局青羊区分局</td>
                    <td>制式警车</td>
                    <td>川A·5849警</td> 
                    <td> </td> 
                    <td>巡逻用车</td> 
                    <td>大众捷达</td> 
                    <td>30888020</td> 
                    <td>96.4</td> 
                    <td>95.5</td> 
                  </tr>
                
                </tbody>
                
              </table>
            </div>
          </div>
          <div class="my-page-fixed"></div>
		<!--列表结束-->
        <!--翻页开始-->
          <div class="my-page-btn">
            <ul class="pagination">
              <li><a href="#">&laquo;</a></li>
              <li><a href="#">1</a></li>
              <li><a href="#">2</a></li>
              <li><a href="#">3</a></li>
              <li><a href="#">4</a></li>
              <li><a href="#">5</a></li>
              <li><a href="#">&raquo;</a></li>
            </ul>
            
          </div>
        <!--翻页结束-->
        
        <!--新增开始-->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">新增武器信息</h4>
              </div>
              <div class="modal-body">
                
                <!--表单开始-->
                <form class="form-horizontal" role="form">
                  <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">武器类别：</label>
                    <div class="col-sm-10">
                        <select id="txttype"  class="form-control">
                          <option value="1">64制自动步枪</option>
                          <option value="2">AK47</option>
                        </select>
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="input2" class="col-sm-2 control-label">武器编号</label>
                    <div class="col-sm-10">
                      <input id="txtnumber" type="text" class="form-control" id="input2" placeholder="">
                    </div>
                  </div>
                   
                  <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">所属机构：</label>
                    <div class="col-sm-10">
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
                <button id="btnsaveweapon" onclick="savecarAction()" type="button" class="btn btn-primary">确定并新增</button>
              </div>
            </div>
          </div>
        </div>
</body>
</html>
