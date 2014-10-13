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
	<script>
        	$(function(){
				$('#my-search').click(function(){
					$('#my-search-box').toggle();	
				});	
				$('#my-check-all').click(function(){
					$(".my-check").each(function(){
						$(this).attr('checked','checked');
					});
				});
				$('#addnew').click(function(){
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
                <li class="active"><a href="#" class="my-nav-btn">人员管理 <img src="images/nav-btn-icon.png" width="22" height="22"></a></li>
                <li><a href="#" class="my-nav-btn">车辆管理 <img src="images/nav-btn-icon.png" width="22" height="22"></a></li>
                <li><a href="#" class="my-nav-btn">武器管理 <img src="images/nav-btn-icon.png" width="22" height="22"></a></li>
                <li><a href="#" class="my-nav-btn">卡口管理 <img src="images/nav-btn-icon.png" width="22" height="22"></a></li>
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
                  <div class="col-md-8"><img src="images/icon/user.png" width="16" height="16"> <strong>人员列表</strong></div>
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
              <table class="table">
              	<thead class="my-table-title">
              	  <tr>
                    <td width="20"><input type="checkbox" id="my-check-all" value="1"></td>
                    <td>机构</td>
                    <td>姓名</td>
                    <td>职务</td>
                    <td>身份证号</td>
                    <td>警号</td>
                    <td>人员类别</td>
                    <td>勤务类别</td>
                    <td>GPS设备ID</td>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                  	<td><input type="checkbox" class="my-check" value="option1"></td>
                  	<td>青羊区分局太升南路派出所</td>
                    <td>丁川</td>
                    <td>刑侦副所长</td>
                    <td>510101198112130278</td>
                    <td>007817</td>
                    <td>警员</td>
                    <td>接处警</td>
                    <td>510158786264646</td>
                  </tr>
                  <tr>
                  	<td><input type="checkbox" class="my-check" value="option1"></td>
                  	<td>青羊区分局太升南路派出所</td>
                    <td>马瑞</td>
                    <td>民警</td>
                    <td>510101198109090278</td>
                    <td>007816</td>
                    <td>警员</td>
                    <td>接处警</td>
                    <td>510158786264646</td>
                  </tr>
                  <tr>
                  	<td><input type="checkbox" class="my-check" value="option1"></td>
                  	<td>青羊区分局太升南路派出所</td>
                    <td>马瑞</td>
                    <td>民警</td>
                    <td>510101198109090278</td>
                    <td>007816</td>
                    <td>警员</td>
                    <td>接处警</td>
                    <td>510158786264646</td>
                  </tr>
                  <tr>
                  	<td><input type="checkbox" class="my-check" value="option1"></td>
                  	<td>青羊区分局太升南路派出所</td>
                    <td>马瑞</td>
                    <td>民警</td>
                    <td>510101198109090278</td>
                    <td>007816</td>
                    <td>警员</td>
                    <td>接处警</td>
                    <td>510158786264646</td>
                  </tr>
                  <tr>
                  	<td><input type="checkbox" class="my-check" value="option1"></td>
                  	<td>青羊区分局太升南路派出所</td>
                    <td>马瑞</td>
                    <td>民警</td>
                    <td>510101198109090278</td>
                    <td>007816</td>
                    <td>警员</td>
                    <td>接处警</td>
                    <td>510158786264646</td>
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
                      <input type="text" class="form-control" id="input2" placeholder="">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="input2" class="col-sm-2 control-label">姓名：</label>
                    <div class="col-sm-4">
                      <input type="text" class="form-control" id="input2" placeholder="">
                    </div>
                    <label for="input2" class="col-sm-2 control-label">职务：</label>
                    <div class="col-sm-4">
                      <input type="text" class="form-control" id="input2" placeholder="">
                    </div>
                  </div>
                                    
                  <div class="form-group">
                    <label for="input2" class="col-sm-2 control-label">手机号码：</label>
                    <div class="col-sm-4">
                      <input type="text" class="form-control" id="input2" placeholder="">
                    </div>
                    <label for="input3" class="col-sm-2 control-label">公安短号：</label>
                    <div class="col-sm-4">
                      <input type="text" class="form-control" id="input3" placeholder="">
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